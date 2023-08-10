#!/bin/csh

if ($#argv != 1) then
	echo "Error: make sure to have valid int argument"
	exit 1
endif

set num = $1
set cube = `expr $num \* $num \* $num`
echo "Number $num"
echo "Cube $cube"

