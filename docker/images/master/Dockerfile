FROM ubuntu:16.04

RUN apt-get update && apt-get install wget -y
RUN wget -q -O- https://downloads.opennebula.org/repo/repo.key | apt-key add -
RUN apt-get update && apt-get install build-essential libmysqlclient-dev ruby-dev -y

RUN apt-get install opennebula opennebula-sunstone opennebula-gate opennebula-flow -y



RUN apt-get install iputils-ping -y

COPY .ssh/ /var/lib/one/.ssh/
RUN chmod 600 /var/lib/one/.ssh/*
RUN chown -R oneadmin:oneadmin /var/lib/one/.ssh/

RUN service opennebula restart
