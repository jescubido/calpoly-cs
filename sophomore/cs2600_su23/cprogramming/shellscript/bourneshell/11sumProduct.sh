#!/bin/sh

while true;
do
	read -p "Enter number between 1-10: " num

	if ! [[ "$num" =~ [0-9] ]]
	then
		echo "Invalid input: integer was not between 1 and 10"
	else
		break
	fi
done

sum=0
factorial=1

for ((i=1; i<=num; i++))
do
	sum=$((sum + i))
done
echo "The sum of $num is: $sum"

for ((j=1; j<=num; j++))
do
	factorial=$((factorial * j))
done
echo "The factorial of $num is: $factorial"

