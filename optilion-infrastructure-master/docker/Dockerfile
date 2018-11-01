#Dockerfile for Optilion Container
FROM ubuntu

RUN apt-get update && apt-get -y install software-properties-common

#Additional repositories
RUN add-apt-repository ppa:hvr/ghc

#Required packages ## $(uname -r) ##
RUN apt-get update && apt-get install -y \
	default-jdk \
	openjfx \
	mono-mcs\
	g++ \
	cmake \
	linux-tools-generic \
	linux-cloud-tools-generic \
	pipexec \
	python \
	python-pip \
	python3-pip \
	libboost-system-dev \
	libboost-thread-dev \
	vim \
	libz-dev \
	libxml2-dev \
	nodejs \
	ghc-8.0.2 \
	libboost-all-dev \
	mono-vbnc \
&& rm -fr /var/lib/apt/lists/*

RUN pip install --upgrade pip
RUN pip install networkx
RUN pip install numpy
RUN pip install python-igraph

RUN pip3 install --upgrade pip
RUN pip3 install networkx
RUN pip3 install numpy
RUN pip3 install python-igraph
RUN pip3 install intervaltree

#Create optuser
RUN groupadd -r ubuntu && useradd -r -m -g ubuntu ubuntu

#Create solutions directory
RUN mkdir /home/optilion/
RUN mkdir /home/optilion/solutions
RUN mkdir /opt/cplex

#Copy optilion scripts
COPY optilion /usr/local/bin/
COPY HHexecutor/HHexecutor.py /usr/local/bin/
COPY HHexecutor/LLHexecutor.py /usr/local/bin/
COPY HHexecutor/hh_helpers /usr/local/bin/hh_helpers
COPY HHexecutor/monitor.sh /usr/local/bin/
COPY cplex /opt/cplex

RUN apt-get update && apt-get install -y \
	linux-tools-4.4.0-109-generic \
	linux-cloud-tools-4.4.0-109-generic \
&& rm -fr /var/lib/apt/lists/*

#Set default user
USER ubuntu
WORKDIR /home/optilion/solutions

#Update path variable
ENV PATH="/opt/ghc/bin:${PATH}"
