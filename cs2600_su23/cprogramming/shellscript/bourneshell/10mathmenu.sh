#!/bin/sh

if [ "$#" -ne 2 ]
then
	echo "Error: please make sure to enter two valid integer arguments"
	exit1
fi

num1=$1
num2=$2

reprompt()
{
	echo -e "\n1) Perform another operation on same two integers"
	echo "2) Do another operation with two different integers"
	echo "3) Exit"

	read -p "Enter selection: " newSelection

	case $newSelection in
		2)
			read -p "Enter a new integer: " num1
			read -p "Enter another integer: " num2
			;;
		3)
			exit
			;;
		*)
			continue
			;;
	esac
}
while true
do
	echo -e "\nMenu:"
	echo "Add the numbers (enter a or A)"
	echo "Subtract the numbers (enter s or S)"
	echo "Multiply the numbers (enter m or M)"
	echo "Divide the numbers (enter d or D)"
	echo -e "Exit (select e or E)\n"
	
	read -p "Enter selection: " selection

	case "$selection" in
		[aA])
			result=$((num1 + num2))
			echo "Result: $result"
			reprompt
			;;
		[sS])
			result=$((num1 - num2))
			echo "Result: $result"
			reprompt
			;;
		[mM])
			result=$((num1 * num2))
			echo "Result: $result"
			reprompt
			;;
		[dD])
			if [ "$num2" -eq 0 ]
			then
				echo "Error: cannot divide by 0"
				exit 1
			fi
			result=$(bc <<< "scale=2; $num1 / $num2")
			echo "Result: $result"
			reprompt
			;;
		[eE])
			exit
			;;
		*)
			echo "Invalid input. Try again!"
			;;
	esac
done
