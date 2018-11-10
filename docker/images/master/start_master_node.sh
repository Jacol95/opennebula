#!/usr/bin/env bash

# Start ssh deamon
/usr/sbin/sshd -D &

# opennebula daemon
one start

# opennebula web application
sunstone-server start