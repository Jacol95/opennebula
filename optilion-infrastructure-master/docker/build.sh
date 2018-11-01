cp -r ../../optilion-hyper/HHexecutor/ .
cp -r /opt/cplex .

sudo docker build -t opt-doc .

rm -r cplex
rm -r HHexecutor
