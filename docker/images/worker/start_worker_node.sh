#!/usr/bin/env bash

# Start ssh deamon
/usr/sbin/sshd -D &

# daemon and management tool for managing platform virtualization
/etc/init.d/libvirt-bin start