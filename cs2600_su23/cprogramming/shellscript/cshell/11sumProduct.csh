#!/bin/csh

echo -n "Enter an integer between 1-10: "
set num = $<
set sum = 0
set factorial = 1

if ($num < 1 || $num > 10) then
	echo "Error:  please enter valid integer between 1-10"
	exit 1
endif

@ index = $num
while ($index > 0)
	@ sum += $index
	@ factorial *= $index
	@ index--
end

echo "The sum of 1 to $num is: $sum"
echo "The factorial of $num is: $factorial"
