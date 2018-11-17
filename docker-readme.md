# About

Dockerfiles that contains opennebula master and worker nodes

### Building docker images

1. Change directory to docker/images (where docker compose is located)
2. Run bash command: `docker-compose build`

### Runing docker images

1. Run bash command: `docker-compose up -d`
2. Verify that images are running by `docker ps`

Openebula services should be started
When they are started you can go to `http://localhost:9869/` to login to opennebula web
User and password are stored in file `/var/lib/one/.one/one_auth`


<<<<<<< HEAD
1. Login to master node by `docker exec -it images_master-node_1 /bin/bash` (if docker image name = images_master-node_1)
2. Run: `/etc/init.d/opennebula start` to start opennebula daemon
3. Run: `/etc/init.d/opennebula-sunstone start` to start opennebula web service
=======
### Adding worker nodes to the cluster
>>>>>>> d28a4e47ed0d065a2d259ec71164c460ed426233

1. Login to master node
2. Login to user oneadmin `su - oneadmin`
3. Add worker nodes to the cluster

`onehost create images_worker-node-1_1 -i kvm -v kvm`

`onehost create images_worker-node-2_1 -i kvm -v kvm`

1. You should have up and running cluster. You can verify it by

`onehost list`

or 

`onehost show 0`  to see worker_node_1 parameters or

`onehost show 1`  to see worker_node_2 parameters
