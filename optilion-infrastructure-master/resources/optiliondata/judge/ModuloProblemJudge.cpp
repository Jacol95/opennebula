#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int main (int argc, char* argv[]) {
  int a,b,c;
  ifstream myfile (argv[1]);
  if (myfile.is_open())
  {
    myfile >> a;
    myfile >> b;    
    myfile.close();
  }
  
  ifstream myfile2 (argv[2]);
  if (myfile2.is_open())
  {
    myfile2 >> c;
    myfile.close();
  }
  if(a/b == c) cout << "1|SUCCESS";
  else cout << "0|WRONG ANSWER";

  return 0;
}
