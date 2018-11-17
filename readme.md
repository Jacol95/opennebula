# About

Dockerfiles that contains opennebula master and worker nodes

### Building docker images

1. Change directory to docker/images (where docker compose is located)
2. Run bash command: `docker-compose build`

### Runing docker images

1. Run bash command: `docker-compose up -d`
2. Verify that images are running by `docker ps`


### Starting opennebula services

1. Login to master node by `docker exec -it images_master-node_1 /bin/bash` (if docker image name = images_master-node_1)
2. Run: `/etc/init.d/opennebula start` to start opennebula daemon
3. Run: `/etc/init.d/opennebula-sunstone start` to start opennebula web service

When they are started you can go to `http://localhost:9869/` to login to opennebula web
User and password are stored in file `/var/lib/one/.one/one_auth`
