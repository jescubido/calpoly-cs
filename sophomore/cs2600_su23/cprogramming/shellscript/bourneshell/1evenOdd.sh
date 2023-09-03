#!/bin/sh

echo "Enter a number: "
read number
if [ $((number % 2)) -eq 0 ]
then
	echo "The number entered is even"
else
	echo "The number entered is odd"
fi
