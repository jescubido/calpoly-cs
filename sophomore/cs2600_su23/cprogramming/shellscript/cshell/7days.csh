#!/bin/csh

set days = "$*"

set index = 1
set index2 = 1
while ($index <= $#days)
	foreach day ($days)
		foreach element ($day)
			echo "$element $days"
		end
		@ index++
	end
	@ index2++
end
