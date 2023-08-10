#!/bin/csh

set checking = 1000
set savings = 1000
set attempts = 0

while ($attempts < 4)
	echo "\n*** Welcome to Cal Poly's ATM ***"
	echo -n "Please enter your PIN: "
	set pin = $<

	switch ($pin)
		case 111:
			break
		default:
			@ attempts++
			if ($attempts == 3) then
				echo "Too many illegal PINs. Try again later."
				exit 1
			endif
		endsw
end

while (1)
	echo "\n\n*** Welcome to Cal Poly's ATM ***"
    	echo "\n(1) Transfer from checking account to savings account\n"
    	echo "(2) Transfer from savings account to checking account\n"
    	echo "(3) Savings account balance\n"
    	echo "(4) Checking account balance\n"
    	echo "(5) Withdraw Cash from either account\n"
    	echo "(6) Exit\n"	
	
	echo -n "==> Please select option (1-6): "
	set menuSelection = $<

	switch ($menuSelection)
		case 1:
			echo -n "\nAmount to transfer from checking to savings: "
			set transferAmount = $<
			if ($transferAmount > $checking) then
				clear
				echo "Transaction not completed"
				echo "Current checking balance: $checking"
			else
				clear
				@ checking -= $transferAmount
				@ savings += $transferAmount
				echo "Transaction completed"
				echo "New checking balance: $checking"
			endif
			breaksw

		case 2:
			echo -n "\nAmount to transfer from savings to checking: "
			set transferAmount = $<
			if ($transferAmount > $savings) then
				clear
				echo "Transaction not completed"
				echo "Current savings balance: $savings"
			else
				clear
				@ checking += $transferAmount
				@ savings -= $transferAmount
				echo "Transaction completed"
				echo "New savings balance: $savings"
			endif
			breaksw

		case 3:
			clear
			echo "\nSavings account balance: $savings"
			breaksw

		case 4:
			clear
			echo "\nChecking account balance: $checking"
			breaksw

		case 5:
			echo -n "\nWithdraw from (1)Checking or (2) Savings account: "
			set choice = $<
			switch ($choice)
				case 1:
					echo -n "Amount to withdraw from checking: "
					set withdrawAmount = $<
					if (($checking - $withdrawAmount) < 0) then
						clear
						echo "Not enough funds in checking account"
						echo "Current balance: $checking"
					else if ($withdrawAmount < 0) then
						clear
						echo "Transaction not completed"
					else
						clear
						@ checking -= $withdrawAmount
						echo "Transaction completed"
						echo "New checking balance: $checking"
					endif
					breaksw
			
				case 2:
					echo -n "Amount to withdraw from savings: "
					set withdrawAmount = $<
					if (($savings - $withdrawAmount) < 0) then
						clear
						echo "Not enough funds in savings account"
						echo "Current balance: $savings"
					else if ($withdrawAmount < 0) then
						clear
						echo "Transaction not completed"
						echo "Cannot withdraw a negative amount"
					else
						clear
						@ savings -= $withdrawAmount
						echo "Transaction completed"
						echo "New savings balance: $savings"
					endif
					breaksw
	
				default:
					clear
					echo "Invalid input"
					breaksw
			endsw
			breaksw

		
		case 6:
			clear
			echo "\nThank you for using the ATM system."
			exit 0
			breaksw

		default:
			clear
			continue
			breaksw
	endsw
end
