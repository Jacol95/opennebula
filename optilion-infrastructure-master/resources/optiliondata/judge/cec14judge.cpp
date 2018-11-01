#include <iostream>
#include <string>
#include <cstdlib>
#include <cstdio>
#include <string>

using namespace std;

void cec14_test_func(double *, double *,int,int,int);

double *OShift,*M,*y,*z,*x_bound;
int ini_flag=0,n_flag,func_flag,*SS;

string directory_name;



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
	string arg0(argv[0]);
	directory_name = arg0.substr(0, arg0.find_last_of("/") + 1);
	
	//cerr << "Dir: " << directory_name << endl;
	
	int func_num;
	int n = 100, m = 1;
	double * x, *f;
	FILE *fpt;
	
	//cerr << "Zaczynam alokowac" << endl;
	
	x=(double *)malloc(m*n*sizeof(double));
	f=(double *)malloc(sizeof(double)  *  m);

	//cerr << "Zaalokowalem" << endl;
	
	func_num = atoi(argv[2]); 
	
	while(argc==3) {
		//cerr << "Zaczynam wczytywac liczby" << endl;
		for(int k=0; k < n; k++) {
			cin >> x[k];
		}
		//cerr << "Wcztalem liczby" << endl;
		cec14_test_func(x, f, n, m, func_num);
		//cerr << "Policzylem funkcje" << endl;
		cout << f[0] << endl;
		//cerr << "Koniec petli" << endl;
	}

	if(argc == 4) {
		fpt = fopen(argv[3], "r");
		if (fpt == NULL) ierror(9);
		readdata(fpt, x, n);
		cec14_test_func(x, f, n, m, func_num);
		printf("%f|SUCCESS\n", f[0]);
	}

	return 0;
}
