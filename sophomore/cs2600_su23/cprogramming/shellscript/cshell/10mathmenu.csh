#!/bin/csh

if ($#argv != 2) then
	echo "Error: please have two valid int arguments"
	exit 1
endif

set num1 = $1
set num2 = $2

while (1)
	echo "\n"
	echo "Add the numbers (enter a or A)"
	echo "Subtract the numbers (enter s or S)"
	echo "Multiply the numbers (enter m or M)"
	echo "Divide the numbers (enter d or D)"
	echo "Exit (select e or E)"

	echo -n "\nEnter selection: "
	set selection = $<

	switch("$selection")
		case [aA]:
			set result = `expr $num1 + $num2`
			echo "Result: $result"
			breaksw
		case [sS]:
			set result = `expr $num1 - $num2`
			echo "Result: $result"
			breaksw
		case [mM]:
			set result = `expr $num1 * $num2`
			echo "Result: $result"
			breaksw
		case [dD]:
			if ($num2 == 0) then
				echo "Error: division by 0 is undefined"
				exit 1
			else
				set result = `expr $num1 / $num2`
				printf  "Result: %.2f" $result
			endif
			breaksw
		case [eE]:
			exit 0
			breaksw
		default:
			echo "Error: invalid choice. try again!"
			continue
			breaksw
	endsw

	echo "\n"
	echo "1) Perform another operation on same two integers"
	echo "2) Do another operation with two different integers"
	echo "3) Exit"

	echo -n "\nEnter selection: "
	set newSelection = $<
	switch ($newSelection)
		case 1:
			continue
			breaksw
		case 2:
			echo -n "Enter new integer: "
			set num1 = $<
			echo -n "Enter another integer: "
			set num2 = $<
			breaksw
		case 3:
			exit 0
			breaksw
		default:
			echo "invalid input"
			exit 1
			breaksw
	endsw
end
