#include <cstdio>
#include <cstring>
#include <cstdlib>

#define MAXD 1024
#define MAXN 200

using namespace std;

int w, h, n;
int quality[MAXD][MAXD];
int inside[MAXD][MAXD];
int rad[MAXN], solx[MAXN], soly[MAXN];
FILE *valfile, *resfile;

void ierror(int where)
{
  fprintf(stderr, "Internal error - test data are incorrect at %d\n", where);
  exit(2);
}

void readdata()
{
  if (fscanf(valfile, "%d", &n) != 1) ierror(4);
  if (n > 50 || n < 3) ierror(5);
  for (int i = 0; i < n; i++)
  {
    if (fscanf(valfile, "%d", rad+i) != 1) ierror(6);
    if (fscanf(resfile, "%d %d", solx+i, soly+i) != 2)
    {
      printf("%d|Wrong output format\n", -1); // [TODO] Error
      exit(0);
    }
  }
  if (fscanf(valfile, "%d %d", &w, &h) != 2) ierror(1);
  for (int i = 0; i < n; i++)  
    if (solx[i] < 0 || soly[i] < 0 || solx[i] >= h || soly[i] >= w)
    {
      printf("%d|Center of the factory #%i (0-based) outside the map\n", -1, i);
      exit(0);
    }
  for (int i = 0; i < h; i++)
    for (int j = 0; j < w; j++)
    {
      if (fscanf(valfile, "%d", quality[i]+j) != 1) ierror(2);
      if (quality[i][j] < -255 || quality[i][j] > 255) ierror(3);
    }
}

int getres()
{
  int res = 0;

  memset(inside, false, sizeof(inside));
  for (int i = 0; i < n; i++)
    for (int xx = solx[i] - rad[i]; xx <= solx[i] + rad[i]; xx++) if (xx >= 0 && xx < h)
      for (int yy = soly[i] - rad[i]; yy <= soly[i] + rad[i]; yy++) if (yy >= 0 && yy < w)
        if ((solx[i] - xx) * (solx[i] - xx) + (soly[i] - yy) * (soly[i] - yy) <= rad[i] * rad[i])
          inside[yy][xx]++;
  for (int i = 0; i < h; i++)
    for (int j = 0; j < w; j++)
      res += quality[i][j] * inside[i][j] * (inside[i][j] + 1) / 2;
  return res;
}

int main(int argc, char **argv)
{
  if (argc != 4)
  {
    printf("Usage: %s mwater mwaterres\n", argv[0]);
    return 1;
  }
  valfile = fopen(argv[1], "r");
  resfile = fopen(argv[2], "r");
  if (valfile == NULL) ierror(7);
  if (resfile == NULL) ierror(9);
  readdata();
  printf("%d|SUCCESS\n", getres());
  return 0;
}
