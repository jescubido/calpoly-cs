#!/bin/sh

read -p "Enter your score (0-100): " score

if ! [[ "$score" =~ [0-9] ]]
then
	echo "Invalid input: The score must be between 0 and 100."
	exit 1
fi

if [ "$score" -lt 0 ] || [ "$score" -gt 100 ]
then
	echo "Error: The score must be between 0 and 100."
	exit 1
elif [ "$score" -ge 90 ] && [ "$score" -le 100 ]
then
	echo "you got A"
elif [ "$score" -ge 80 ]
then
	echo "you got B"
elif [ "$score" -ge 70 ]
then
	echo "you got C"
elif [ "$score" -ge 60 ]
then
	echo "you got D"
else
	echo "you failed"
fi

