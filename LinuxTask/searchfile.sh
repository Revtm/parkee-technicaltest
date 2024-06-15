#!/bin/bash

dir=$1
extention=$2

for file in ${dir}/*
  do
    fileext="${file##*.}"

    if [[ $fileext == $extention ]]
    then
      echo "$file"
    fi
  done
