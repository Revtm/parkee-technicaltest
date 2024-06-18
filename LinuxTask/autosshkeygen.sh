#!/bin/bash

dir=$1
if [[ ! -d $dir ]]
then
 mkdir $dir
fi

ssh-keygen -t rsa -N "" -f "${dir}/id_rsa"
