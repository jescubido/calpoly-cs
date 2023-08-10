#!/bin/sh

gcd()
{
	while [ "$arg2" -ne 0 ]
	do
		temp=$arg2
		arg2=$((arg1 % arg2))
		arg1=$temp
	done
	echo "$arg1"
}

lcm()
{
	gcd_result=$(gcd "$arg1" "$arg2")
	lcm_result=$((arg1 * arg2 / gcd_result))
	echo "$lcm_result"
}

arg1=$1
arg2=$2

if [ "$#" -ne 2 ]
then
	echo "Error: please provide exactly two positive integer arguments"
	exit 1
elif ! [[ $arg1 =~ [0-9] ]] && ! [[$arg2 =~ [0-9] ]]
then
	echo "Error: one or both arguments are not positive integers"
	exit 1
fi

if [ $arg1 -lt $arg2 ]
then
	echo "$arg1 is the smallest number between the two integers"
	break
else
	echo "$arg2 is the smallest number between the two integers"
	break
fi

gcd_result=$(gcd "$arg1" "$arg2")
lcm_result=$(lcm "$arg1" "$arg2")

echo "The GCD of $arg1 and $arg2 is: $gcd_result"
echo "The LCD of $arg1 and $arg2 is: $lcm_result"
