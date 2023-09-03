#!/bin/sh

while true; do
	read -p "Are you OK? (y/n) " input

	if [ "$input" = "y" ] || [ "$input" = "Y" ]; then
		echo "glad to hear it"
		break
	elif [ "$input" = "n" ] || [ "$input" = "N" ]; then
		echo "sorry that you are not feeling good."
		break
	else
		echo "Incorrect choice. Try again!"
	fi
done
