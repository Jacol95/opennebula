FROM ubuntu:16.04

RUN apt-get update && apt-get install wget -y
RUN wget -q -O- https://downloads.opennebula.org/repo/repo.key | apt-key add -
RUN apt-get update && apt-get install build-essential libmysqlclient-dev ruby-dev -y

ENV TERM xterm
ENV DEBIAN_FRONTEND noninteractive


COPY ./keyboard /etc/default/keyboard

RUN apt-get install opennebula-node -y

RUN apt-get install -y openssh-server
RUN mkdir /var/run/sshd


RUN apt-get install iputils-ping -y

COPY .ssh/ /var/lib/one/.ssh/
RUN chmod 600 /var/lib/one/.ssh/*
RUN chown -R oneadmin:oneadmin /var/lib/one/.ssh/


RUN apt-get update &&  apt-get install nfs-common bridge-utils -y

RUN apt-get update && apt-get install less vim net-tools -y

CMD /usr/sbin/sshd -D && /bin/bash
