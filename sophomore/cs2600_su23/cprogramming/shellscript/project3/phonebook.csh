#!/bin/csh

set file = "datebook.txt"

while (1)
	echo "\n*** PHONEBOOK MENU ***\n"
	echo "(1) List records in alphabetical order"
	echo "(2) List records in reverse alphabetical order"
	echo "(3) Search for a record by Last Name"
	echo "(4) Search for a record by birthday"
	echo "(5) Insert Record"
	echo "(6) Delete Record"
	echo "(7) Exit" 

	echo -n "\nEnter selection: "
	set menuSelection = $<
	
	switch($menuSelection)
		case 1:
			sort -n $file
			breaksw
		case 2:
			sort -nr $file
			breaksw
		case 3:
			echo -n "Enter Last Name: "
			set searchLast = $<
			grep -i "$searchLast" $file
			breaksw
		case 4:
			echo -n "Search by (1) Birthday Year or (2) Birthday Month: "
			set birthdayOption = $<
			if ($birthdayOption == 1) then
				echo -n "Enter Birthday Year (YY): "
				set birthdayYear = $<
				grep -i "$birthdayYear" $file
			else if ($birthdayOption == 2) then
				echo -n "Enter Birthday Month (MM): "
				set birthdayMonth = $<
				grep -i "$birthdayMonth" $file
			else
				echo "Invalid Input"
			endif
			breaksw
		case 5:
			echo -n "Enter First Name: "
			set firstName = $<

			if (echo $firstName | grep '[0-9]') then
				echo "Invalid input"
				exit 1

			echo -n "Enter Last Name: "
			set lastName = $<
			if (echo $lastName | grep '[0-9]') then
				echo "Invalid input"
				exit 1

			echo -n "Enter Home Phone Number (###-###-####): "
			set homePhone = $<
			echo -n "Enter Mobile Phone Number (###-###-####): "
			set mobilePhone = $<

			echo -n "Enter Address (Street address, City, State, Zip): "
			set address = $<
			
			echo -n "Enter Birthdate (MM/DD/YY): "
			set birthdate = $<

			echo -n "Enter salary: "
			set salary = $<

			echo "$firstName:$lastName:$homePhone:$mobilePhone:$address:$birthdate:$salary" >> $file
			echo "Record successully added"
			breaksw

		case 6:
			echo -n "Enter Mobile Phone Number or Last Name to delete record: "
			set delete = $<
			echo "Record successfully deleted"
			breaksw
		case 7:
			exit 0
			breaksw
		default:
			echo "Invalid input"
			breaksw
	endsw
end
