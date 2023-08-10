#!/bin/sh

attempts=0
savings=1000.00
checking=1000.00


while [ $attempts -lt 4 ]
do
	echo -e "\n*** Welcome to Cal Poly's ATM ***"
	read -p "Please enter your PIN: " pin
	clear

	if ! [ "$pin" -eq 111 ]
	then
		clear
		attempts=$((attempts+1))
		if [ "$attempts" -eq 3 ] #number of illegal pins reached
		then
			clear
			echo "Too many illegal PINs. Try again later."
			exit 1
		fi
	else
		
		break
	fi
done

while true;
do
	#print menu
	echo -e "\n*** Welcome to Cal Poly's ATM ***"
	echo -e "\n(1) Transfer from checking account to savings account\n"
	echo -e "(2) Transfer from savings account to checking account\n"
	echo -e "(3) Savings account balance\n"
	echo -e "(4) Checking account balance\n"
	echo -e "(5) Withdraw Cash from either account\n"
	echo -e "(6) Exit\n"
	
	read -p "==> Please select option (1-6): " menuSelection

	case $menuSelection in
		1) #transferring from checking to savings
			echo -e "\n"
			read -p "Amount to transfer from checking to savings: " transferAmount
			if [ $transferAmount -gt $checking ]
			then
				clear
				echo "Transaction not completed"
				echo "Current checking balance: $checking"
			else
				clear
				checking=$((checking-transferAmount))
				savings=$((savings+transferAmount))
				echo "Transaction completed"
				echo "New checking balance: $checking"
			fi
			;;
		2) #transfer from savings to checking
			echo -e "\n"
			read -p "Amount to transfer from savings to checking: " transferAmount
			if [ $transferAmount -gt $savings ]
			then
				clear
				echo "Transaction not completed"
				echo "Current savings balance: $savings"
			else
				clear
				savings=$((savings-transferAmount))
				checking=$((checking+transferAmount))
				echo "Transaction completed"
				echo "New savings balance: $savings"
			fi
			;;
		3) #print savings balance
			clear
			echo -e  "\nSavings account balance: $savings"
			;;
		4) #print checking balance
			clear
			echo -e "\nChecking account balance: $checking"
			;;
		5) #withdraw from checking or savings account
			echo -e "\n"
			read -p "Withdraw from (1)Checking or (2)Savings account: " choice
			case $choice in
				1) #withdraw from checking account
					read -p "Amount to withdraw from checking: " withdrawAmount
					if [ $((checking-withdrawAmount)) -lt 0 ]
					then
						clear
						echo "Not enough funds in checking account"
						echo "Current balance: $checking"
					elif [ $withdrawAmount -lt 0 ]
					then
						clear
						echo "Transaction not completed"
						echo "Cannot withdraw negative amount"
					else
						clear
						checking=$((checking-withdrawAmount))
						echo "Transaction completed"
						echo "New checking balance: $checking"
					fi
					;;
				2) #withdraw from savings account
					read -p "Amount to withdraw from savings: " withdrawAmount
					if [ $((savings-withdrawAmount)) -lt 0 ]
					then
						clear
						echo "Not enough funds in savings account"
						echo "Current balance: $savings"
					elif [ $withdrawAmount -lt 0 ]
					then
						clear
						echo "Transaction not completed"
						echo "Cannot withdraw a negative amount"
						break
					else
						clear
						savings=$((savings-withdrawAmount))
						echo "Transaction completed"
						echo "New savings balance: $savings"
					fi
					;;
				*) #invalid input
					clear
					echo "Invalid input"
					break
					;;
			esac
			;;
		6) #exiting script
			clear
			echo -e "\nThank you for using the ATM system."
			exit 0
			;;
		*) #invalid input and printing main menu
			clear
			continue
			;;
	esac
done

