#!/bin/bash

os_name=$1

if [[ $os_name == "Ubuntu" ]]
then
   sudo apt-get update && sudo apt-get -y upgrade > log.txt
elif [[ $os_name == "CentOS" ]]
then
   yum -y update > log.txt
fi

echo "DONE update ALL package"
