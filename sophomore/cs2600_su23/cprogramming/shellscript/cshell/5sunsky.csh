#!/bin/csh

echo -n "Enter a color: "
set color = $<

switch ("$color")
case bl*:
	echo "the sky is $color"
	breaksw
case Bl*:
	echo "the sky is $color"
	breaksw
case red:
	echo "the sun is sometimes $color"
	breaksw
case yellow:
	echo "the sun is sometimes $color"
	breaksw
default:
	echo "$color is not in any of the categories defined"
	breaksw
endsw
