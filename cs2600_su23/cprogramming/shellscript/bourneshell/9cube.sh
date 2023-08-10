#!/bin/sh

cal_cube()
{
	echo "Number $num"
	cube=$((num * num * num))
	echo "Cube $cube"
}

num=$1
if [ "$#" -ne 1 ] || ! [[ "$1" =~ [0-9] ]]
then
	echo "Error: Please provide a valid integer argument"
	exit 1
fi

cal_cube "$1"
