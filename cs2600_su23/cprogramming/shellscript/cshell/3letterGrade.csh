#!/bin/csh

echo -n "Enter your score: "
set score = $<

if ($score >= 90 && $score <= 100) then
	echo "you got A"
else if ($score >= 80) then
	echo "you got B"
else if ($score >= 70) then
	echo "you got C"
else if ($score < 60 && $score >= 0) then
	echo "you failed"
else
	echo "Error: score entered is out of bounds"
endif
