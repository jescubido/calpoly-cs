#!/bin/sh

file="datebook.txt"

menu()
{
	echo -e "\n*** PHONEBOOK MENU ***"
	echo "(1) Listing of records in alphabetical order"
	echo "(2) Listing of records in reverse alphabetical order"
	echo "(3) Search for a record by Last Name"
	echo "(4) Search for a record by birthday"
	echo "(5) Insert Record"
	echo "(6) Delete Record"
	echo -e "(7) Exit\n"
}

listFirstName()
{
	sort $file
}

listLastName()
{
	sortLast="$1"
	sort -t':' -k2,2 -k1,1 $file
}

searchByLastName()
{
	read -p "Enter Last Name: " searchLast
	grep -i "$searchLast" $file
}

searchByBirthday()
{
	read -p "Search by (1) Birthday Year or (2) Birthday Month: " birthdaySelection
	case $birthdaySelection in
		1)
			read -p "Enter birthday year (YY): " birthdayYear
			if ! [ grep -i "$birthdayYear" $file ]
			then
				echo "No records were found with: $birthdayYear"
			else
				grep $birthdayYear $file
			fi
			;;
		2)
			read -p "Enter birthday month (MM): " birthdayMonth
			if ! [ grep "$birthdayYear" $file ]
			then
				echo "No records were found with: $birthdayMonth"
			else
				grep $birthdayMonth $file
			fi
			;;
		*)
			echo "Invalid input. Try again later."
			;;
	esac
}

insertRecord()
{
	#reprompt user for first name and last name after every incorrect input
	while true
	do
		read -p "Enter First Name: " firstName
		read -p "Enter Last Name: " lastName
	
		#checks if input contains numbers
		if [[ "$firstName" =~ [0-9] ]] || [[ "$lastName" =~ [0-9] ]]
		then
			echo -e "Invalid input. Try again.\n"
		#checks if input is empty
		elif [[ -n "$firstName" ]] || [[ -n "$lastName" ]]
		then
			echo -e "Nothing was entered. Try again.\n"
		else
			break
		fi
	done
	
	#reprompt user if home or mobile phone in incorrect format
	while true
	do
		read -p "Enter Home Phone Number (###-###-####): " homePhone
		read -p "Enter Mobile Phone Number (###-###-####): " mobilePhone
		phonePattern="^[0-9]{3}-[0-9]{3}-[0-9]{4}$"

		#reprompt if home or mobile phone contains letters
		if [[ $homePhone =~ [a-zA-Z] ]] || [[ $mobilePhone =~ [a-zA-Z] ]]
		then
			echo -e "Invalid input. Try again.\n"
		elif ! [[ $homePhone =~ $phonePattern ]]
		then
			echo "$homePhone is not in the correcr format: ###-###-####"
			echo "Try again"
		#reprompt user if mobile phonenot in correct format and not unique
		elif ! [[ $mobilePhone =~ $phonePattern ]] && [ grep "$mobilePhone" $file ]
		then
			echo "$mobilePhone is not in the correct format or not unique"
			echo "Try again"
		else
			break
		fi
	done

	read -p "Enter Address (Street address, City, State, Zip): " address
	
	#reprompt if birthdate not in correct format
	while true
	do
		read -p "Enter Birthdate (MM/DD/YY): " birthdate
		birthdatePattern="[0-1][0-9]/[0-2][0-9]/[0-9]{4}"
		if ! [[ $birthdate =~ $birthdatePattern ]]
		then
			echo "Birthdate is not in the correct format. Try again"
		else
			break
		fi
	done
	
	#reprompt if salary contains letters
	while true
	do
		read -p "Enter salary: " salary
		if [[ $salary =~ [a-zA-Z] ]]
		then
			echo "Input contains letters. Try again."
		else
			break
		fi
	done

	echo "$firstName:$lastName:$homePhone:$mobilePhone:$address:$birthdate:$salary" >> $file
	echo "Record successfully added"
}

deleteRecord()
{
	read -p "Enter Mobile Phone Number or Last Name to delete record: " delete
	sed "/$delete/d" $file
	echo "Record successfully deleted"
}

while true
do
	menu
	echo -e "\n"
	read -p "Enter selection: " menuSelection
	case $menuSelection in
		1)
			read -p "List in alphabetical order by (1) First Name or (2) Last Name: " alphabetSort
			case $alphabetSort in
				1)
					listFirstName
					;;
				2)
					listLastName
					;;
				*)
					echo "Invalid input. Try again"
					continue
					;;
			esac
			;;
		2)
			read -p "List in reverse alphabetical order by (1) First Name or (2) Last Name: " reverseAlphabetSort
			case $reverseAlphabetSort in
				1)
					listFirstName -r
					;;
				2)
					listLastName -r
					;;
				*)
					echo "Incorrect input. Try again"
					continue
					;;
			esac
			;;
		3)
			searchByLastName
			;;
		4)
			searchByBirthday
			;;
		5)
			insertRecord
			;;
		6)
			deleteRecord
			;;
		7)
			exit 0
			;;
		*)
			echo "Invalid input. Try again"
			;;
	esac
done
