#!/bin/bash
# This script sets up the LXC container when it is used for the first time.

#Name of the container
if [ "$1" = "" ] ; then
	name="hue"
else	
	name="$1"
fi

installLXC(){
    sudo apt-get update
    sudo apt-get -y install lxc
}

# Create container based on Ubuntu template
createAndStartLXC(){
    sudo lxc-create -n $name -t ubuntu
    sudo lxc-start -n $name -d
}

# Create default user that will execute problems' solutions
createOptUser(){
    sudo lxc-attach -n $name -- useradd -p optuser optuser 
    userID=`sudo lxc-attach -n $name -- id -u optuser`
}


modifySudoers(){
    # Allow any user in the host system execute lxc-attach with root privileges in the container.
    # As a result any user logged into host system can execute any commmand with root privileges in the container.
    if [ ! "$(sudo cat /etc/sudoers | grep '^ALL ALL = (root) NOPASSWD: /usr/bin/lxc-ls, /usr/bin/lxc-attach$')" ]
    then
        echo 'ALL ALL = (root) NOPASSWD: /usr/bin/lxc-ls, /usr/bin/lxc-attach' | (EDITOR="tee -a" visudo)
    fi

    # Allow root and ubuntu users in the container execute any command with optuser privileges.
    # i.e. they can execute sudo -u optuser command.
    if [ ! "$(sudo cat /var/lib/lxc/$name/rootfs/etc/sudoers | grep '^root ALL = (optuser) NOPASSWD: ALL$')" ]
    then
        sudo echo 'root ALL = (optuser) NOPASSWD: ALL' | (EDITOR="tee -a" visudo -f /var/lib/lxc/$name/rootfs/etc/sudoers)
        sudo echo 'ubuntu ALL = (optuser) NOPASSWD: ALL' | (EDITOR="tee -a" visudo -f /var/lib/lxc/$name/rootfs/etc/sudoers)
    fi
}

# Install required packages
# TODO: Has to be executed twice (packages sometimes are not installed).
installPackages(){
    sudo lxc-attach -n $name -- apt-get update
    sudo lxc-attach -n $name -- apt-get -y install default-jdk
    sudo lxc-attach -n $name -- apt-get -y install linux-tools-common linux-tools-generic
    sudo lxc-attach -n $name -- apt-get -y install linux-tools-3.16.0-53-generic
    sudo lxc-attach -n $name -- apt-get -y install mono-gmcs
    sudo lxc-attach -n $name -- apt-get -y install g++
    sudo lxc-attach -n $name -- apt-get -y install cmake
}

# Create directory for temporary storing solutions and data files
# Host downloads these files from db and use this directory to pass them to container.
prepareWorkingDirecotry(){
    sudo chmod o+x /var
    sudo chmod o+x /var/lib
    sudo chmod o+x /var/lib/lxc
    sudo chmod o+x /var/lib/lxc/$name
    sudo chmod o+x /var/lib/lxc/$name/rootfs
    sudo chmod o+x /var/lib/lxc/$name/rootfs/home/
    sudo chmod o+x /var/lib/lxc/$name/rootfs/home/ubuntu

    mkdir /var/lib/lxc/$name/rootfs/home/ubuntu/solutions
    sudo chmod 777 /var/lib/lxc/$name/rootfs/home/ubuntu/solutions
}

# $1 - directory, $2 - userID
# Recursively remove write permission from given folder and all children
setPermissions(){
    # Current permission minus write permission
    perm=`sudo getfacl -p "$1" | grep "other::" | cut -d ":" -f3 | tr "w" "-"`
    # Set current and default permission for current directory
    sudo setfacl -m u:$2:$perm "$1"
    sudo setfacl -dm u:$2:$perm "$1"

    if [ ! "$(sudo ls -A "$1")" ]
    then
      return 0
    fi

    # Recuresively enter to each directory and set permission for files in current directory
    # Using find to include hidden files
    sudo find $1 -mindepth 1 -maxdepth 1 -print0 | sort -z | while read -r -d "" f
    do
      perm=`sudo getfacl -p "$f" | grep "other::" | cut -d ":" -f3 | tr "w" "-"`
      sudo setfacl -m u:$2:$perm "$f"
      if [ -d "$f" ]
      then
        setPermissions "$f/" $2
      fi
    done
}

echo "Creating LXC container for Optilion. This will be executed only during the first access to the container and may take some time. Logs will be placed in lxclogs subdirectory."
mkdir lxclogs
echo "Installing lxc"
installLXC &> lxclogs/install.log
echo "Creating lxc with name $name"
createAndStartLXC &> lxclogs/createAndStartLXC.log
echo "Creating optuser"
createOptUser &> lxclogs/createOptUser.log
echo "User created, id: $userID"
echo "Modyfing sudoers"
modifySudoers &> lxclogs/modifySudoers.log
echo "Skipping package instalation, install them manually"
#installPackages &> lxclogs/installPackages.log
echo "Creating working directory"
prepareWorkingDirecotry &> lxclogs/prepareWorkingDirecotry.log
echo "Copying optilion script"
sudo cp ../../optilion /var/lib/lxc/$name/rootfs/usr/local/bin/optilion
echo "Setting permissions, this may take a while"
setPermissions "/var/lib/lxc/$name/rootfs/" $userID &> lxclogs/setPermissions.log

