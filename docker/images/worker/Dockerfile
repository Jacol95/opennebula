FROM ubuntu:16.04

RUN apt-get update && apt-get install \
    wget \
    apt-transport-https \
    -y
RUN wget -q -O- https://downloads.opennebula.org/repo/repo.key | apt-key add -
RUN echo "deb https://downloads.opennebula.org/repo/5.6/Ubuntu/16.04 stable opennebula" > /etc/apt/sources.list.d/opennebula.list



ENV TERM xterm
ENV DEBIAN_FRONTEND noninteractive
COPY ./keyboard /etc/default/keyboard


RUN apt-get update && apt-get install \
    build-essential \
    libmysqlclient-dev \
    ruby-dev \
    opennebula-node \
    openssh-server \
    iputils-ping \
    nfs-common \
    bridge-utils \
    less \
    vim \
    net-tools \
    -y





RUN mkdir /var/run/sshd
COPY .ssh/ /var/lib/one/.ssh/
RUN chmod 600 /var/lib/one/.ssh/*
RUN chown -R oneadmin:oneadmin /var/lib/one/.ssh/



COPY start_worker_node.sh /usr/local/bin/
CMD start_worker_node.sh && /bin/bash
