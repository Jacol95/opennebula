grepNumber=`ps -aux | grep -c 'optilion-engine-0.0.4-jar-with-dependencies.jar'`
if [ "${grepNumber}" -le 2 ]
then
	java -jar /home/optilion/git/optilion-engine/target/optilion-engine-0.0.4-jar-with-dependencies.jar &
fi
