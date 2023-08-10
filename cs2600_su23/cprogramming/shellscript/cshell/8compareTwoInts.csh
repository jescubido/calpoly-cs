#!/bin/csh

if ($# != 2) then
	echo "Error: please make sure to have two valid int arguments"
	exit 1
endif

set num1 = $1
set num2 = $2

if ($num1 <= 0 || $num2 <= 0) then
	echo "Error: make sure both arguments are positive integers"
	exit 1
endif

if ($num1 < $num2) then
	set smaller = $num1
else
	set smaller = $num2
endif

set x = $num1
set y = $num2
while ($y != 0)
	set temp = $y
	set y = `expr $x % $y`
	set x = $temp
end

set gcd = $x

set lcm = `expr \( $num1 \* $num2 \) / $gcd`

echo "GCM of $num1 and $num2 is: $gcd"
echo "LCM of $num1 and $num2 is: $lcm"
