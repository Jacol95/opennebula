#include <iostream>
#include <cstdio>

using namespace std;

int main()
{
  int n, r, w, h;
  scanf("%d", &n);
  printf("%d\n", n);
  for (int i = 0; i < n; i++) 
  {
    scanf("%d", &r);
    if (i) printf(" ");
    printf("%d", r);
  }
  scanf("%d %d", &w, &h);
  printf("\n%d %d\n", w, h);

  for (int i = 0; i < h; i++)
  {
    for (int j = 0; j < w; j++)
    {
      if (j) printf(" ");
      scanf("%d", &r);
      printf("%d", (r+255)/2);
    }
    printf("\n");
  }   
  return 0;
}
