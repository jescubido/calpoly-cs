#!/bin/sh

read -p "Enter a color: " color
case $color in
	bl*|Bl*)
		echo "the sky color is $color"
		;;
	red|yellow)
		echo "the sun is $(tput sitm)sometimes$(tput ritm) this color"
		;;
	*)
		echo "the color is not in any of the colors defined"
		;;
esac
