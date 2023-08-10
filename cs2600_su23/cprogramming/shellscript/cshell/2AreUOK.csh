#!/bin/csh


while (1)
	echo -n "Are you OK? (y/n) "
	set response = $<
	
	if ($response == 'y' || $response == 'Y') then
		echo "glad to hear it"
		break
	else if ($response == 'n' || $response == 'N') then
		echo "sorry that you are not feeling good"
		break
	else
		echo "incorrect input. try again!"
	endif
end
