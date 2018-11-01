#include <iostream>
#include <string>
#include <cstdlib>
#include <cstdio>
#include <string>

using namespace std;

void cec14_test_func(double *, double *,int,int,int);

void ierror(int where) {
	fprintf(stderr, "Internal error - test data are incorrect at %d\n", where);
	exit(2);
}

void readdata(FILE *fpt, double x[], int n) {
	for(int i = 0; i < n; ++i) {
		if(fscanf(fpt, "%lf", &x[i]) != 1) {
			printf("%d|Wrong output format\n", -1);
			exit(0);
		}
		if(x[i] > 100.0 || x[i] < -100.0) {
			printf("%d|Answer out of bounds\n", -1);
 			exit(0);
		}
	}
}

int main(int argc, char * argv[]) {
	
	//cerr << "Dir: " << directory_name << endl;
	
	int func_num;
	int n = 100, m = 1;
	double * x, *f;
	FILE *fpt;
	
	x=(double *)malloc(m*n*sizeof(double));
	f=(double *)malloc(sizeof(double)  *  m);
	
	func_num = atoi(argv[2]); 
	
	while(argc==3) {
		for(int k=0; k < n; k++) {
			cin >> x[k];
		}
		cout << x[0] << endl;
	}

	if(argc == 4) {
		fpt = fopen(argv[3], "r");
		if (fpt == NULL) ierror(9);
		readdata(fpt, x, n);
		printf("%f|SUCCESS\n", x[0]);
	}

	return 0;
}
