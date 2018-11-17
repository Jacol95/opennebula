FROM ubuntu:16.04

RUN apt-get update && apt-get install \
    wget \
    apt-transport-https \
    -y

RUN wget -q -O- https://downloads.opennebula.org/repo/repo.key | apt-key add -
RUN echo "deb https://downloads.opennebula.org/repo/5.6/Ubuntu/16.04 stable opennebula" > /etc/apt/sources.list.d/opennebula.list




RUN apt-get update && apt-get install \
    build-essential \
    libmysqlclient-dev \
    ruby-dev \
    opennebula \
    opennebula-sunstone \
    opennebula-gate \
    opennebula-flow \
    iputils-ping \
    less \
    ruby-dev \
    make \
    vim \
    openssh-server \
    net-tools \
    -y


RUN mkdir /var/run/sshd
COPY .ssh/ /var/lib/one/.ssh/
RUN chmod 600 /var/lib/one/.ssh/*
RUN chown -R oneadmin:oneadmin /var/lib/one/.ssh/


RUN gem install sunstone

#COPY sunstone-server.conf /etc/one/
ENV ONE_AUTH /var/lib/one/.one/one_auth


# Copy start scirpt and start
COPY start_master_node.sh /usr/local/bin/
CMD start_master_node.sh && /bin/bash