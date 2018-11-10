#!/usr/bin/env bash

# Start ssh deamon
/usr/sbin/sshd -D &

# opennebula daemon
/etc/init.d/opennebula start

# opennebula web application
/etc/init.d/opennebula-sunstone start