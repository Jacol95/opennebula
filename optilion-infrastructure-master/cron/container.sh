for name in $(sudo lxc-ls)
do
	sudo lxc-attach -n $name -- sudo apt-get --fix-missing update -y
	sudo lxc-attach -n $name -- sudo apt-get --fix-missing upgrade -y
done
