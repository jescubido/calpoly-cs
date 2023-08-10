#!/bin/sh

date=$(date)
IFS=' ' read -ra parts <<< "$date"

for elements in "${parts[@]}"
do
	echo "$elements"
done
