#include<iostream>
#include<fstream>
using namespace std;

int main(int argc, char** argv){

  double miin,a;
  int k;
  for(int i = 0; i < 10; i++){
    for(int j = 0; j<100; j++){
      cout << i << endl;
    }
    cin >> a;
    if(i==0) {
      miin = a;
      k = 0;
    } else {
        miin = a<miin?a:miin;
	k = i;
    }
  }
  ofstream myfile;
  myfile.open(argv[1]);
  for(int i = 0; i < 100; i++){
    myfile << k << endl;
  }
  myfile.close();

  return 0;
}
