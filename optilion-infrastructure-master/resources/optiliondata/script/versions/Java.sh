java -version 2>&1 | awk -F '"' '/version/ {print $2}'