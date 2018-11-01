/* A simple server in the internet domain using TCP
   The port number is passed as an argument */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h> 
#include <sys/socket.h>
#include <netinet/in.h>
#include <signal.h>
#include <fstream>
#include <string>
using namespace std;

int sockfd, newsockfd, portno;
socklen_t clilen;
char buffer[256];
struct sockaddr_in serv_addr, cli_addr;
int n;

// Define the function to be called when ctrl-c (SIGINT) signal is sent to process
void signal_callback_handler(int signum) {
	// Cleanup and close up stuff here
	close(newsockfd);
	close(sockfd);
	// Terminate program
	exit(signum);
}

void error(const char *msg)
{
	printf("Error");
	perror(msg);
	exit(1);
}

int main(int argc, char *argv[])
{
	signal(SIGINT, signal_callback_handler);
	
	if(argv[1][0] == 'j'){
		ifstream myfile (argv[3]);
		if (myfile.is_open())
		{
			int x;
			myfile >> x;  
			myfile.close();
			printf("%d|SUCCESS", x*x + 2*x + 3);
		}
		return 0;
	}

	if (argv[1][0] != 'r') {
		 fprintf(stderr,"ERROR, no port provided\n");
		 exit(1);
	}
	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if (sockfd < 0) {
		error("ERROR opening socket");
	}     
	bzero((char *) &serv_addr, sizeof(serv_addr));
	portno = atoi(argv[3]);
	serv_addr.sin_family = AF_INET;
	serv_addr.sin_addr.s_addr = INADDR_ANY;
	serv_addr.sin_port = htons(portno);
	if (bind(sockfd, (struct sockaddr *) &serv_addr,
	      sizeof(serv_addr)) < 0) 
	      error("ERROR on binding");
	listen(sockfd,5);
	clilen = sizeof(cli_addr);
	newsockfd = accept(sockfd,(struct sockaddr *) &cli_addr, &clilen);
	if (newsockfd < 0) { 
		error("ERROR on accept");
	}

	while(1){
		bzero(buffer,256);
		n = read(newsockfd,buffer,255);
		if (n < 0) {
			error("ERROR reading from socket");
		}
		int x = atoi(buffer);
		int y = x*x + 2*x + 3;
		bzero(buffer,256);
		sprintf(buffer,"%d",y);
		n = write(newsockfd,buffer,255);
		if (n < 0) {
			error("ERROR writing to socket");
		}
	}	

	return 0; 
}
