#Guess-A-Number AI
#
#Jordan Houle
#september 1,2016
import random
import math

def start_screen():
    print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
    print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
    print("@@   _______      __  __    ______    ______    ______                        @@")            
    print("@@  /______/\    /_/\/_/\  /_____/\  /_____/\  /_____/\                       @@")
    print("@@  \::::__\/__  \:\ \:\ \ \::::_\/_ \::::_\/_ \::::_\/_                      @@")
    print("@@   \:\  /____/\ \:\ \:\ \ \:\/___/\ \:\/___/\ \:\/___/\                     @@")
    print("@@    \:\ \_  _\/  \:\ \:\ \ \::___\/_ \_::._\:\ \_::._\:\                    @@")
    print("@@     \:\_ \ \ \   \:\_\:\ \ \:\____/\  /____\:\  /____\:\                   @@")
    print("@@      \______\/    \_____\/__\_____\/  \_____\/  \_____\/                   @@")
    print("@@                      /_______/\                                            @@")
    print("@@                      \::: _  \ \                                           @@")
    print("@@                       \::(_)  \ \                                          @@")
    print("@@                        \:: __  \ \                                         @@")
    print("@@                         \:.\ \  \ \                                        @@")
    print("@@   ___    __     __  __   \__\/\__\/     _______    ______    ______        @@")
    print("@@  /__/\  /__/\  /_/\/_/\  /__//_//_/\  /_______/\  /_____/\  /_____/\       @@")
    print("@@  \::\_\ \  \ \ \:\ \:\ \ \::\| \| \ \ \::: _  \ \ \::::_\/_ \:::_ \ \      @@")
    print("@@   \:. `--\  \ \ \:\ \:\ \ \:.      \ \ \::(_)  \/_ \:\/___/\ \:(_) ) )_    @@")
    print("@@    \:. _     \ \ \:\ \:\ \ \:.\-/\  \ \ \::  _  \ \ \::___\/_ \: __ `\ \   @@")
    print("@@     \. \`--\  \ \ \:\_\:\ \ \. \  \  \ \ \::(_)  \ \ \:\____/\ \ \ `\ \ \  @@")
    print("@@      \__\/  \__\/  \_____\/  \__\/ \__\/  \_______\/  \_____\/  \_\/ \_\/  @@")
    print("@@                                                                            @@")
    print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
    print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
    print("                                                                                ")
    print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
    print("@@@@@@@@@@@@@@@@@@@@@@@@ THIS IS GUESS-A-NUMBER AI! @@@@@@@@@@@@@@@@@@@@@@@@@@@@")
    print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
    print("                                                                                ")
    print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
    print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
    print("@@@@@@@@@@@@@@@@@@@@@@@@@ PRESS ENTER TO PLAY!  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
    print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
    print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
                         
                                                                                                                                     

    input()

def play():

    low = 1
    high = 100
    limit = int(math.log(high-low, 2)) + 1
    tries = 0

    print()
    print("You think of a number between " + str(low) + " to " +str(high) + ".")
    print("I'll try to guess, and you tell me if I'm right.")
    print("I have " + str(limit) + " attempts to get it.")
    print("If my guess is to low then put 'higher' or 'h' if my guess is to high then put 'lower'or 'l' and if i get it right type 'yes' or 'y'")
    print()
    print("Press enter when you have your number.")

    input()

    got_it = False
    

    while got_it == False and tries < limit:
        
        guess = (high + low) //2

        print("Is Your number " + str(guess) + "?")
        answer = input()
        
        if answer == "yes" or answer == "y":
            print("yes I got it right!")
            return True
            
        elif answer == "lower" or answer == "l":
            high = guess - 1
            
        elif answer == "higher" or answer == "h":
            low = guess + 1
            
        print("What? Input is invalid, you cheater. please input a proper answer.") 
             

        tries += 1
        print()

    if got_it == True:
        print("Yes I' amazing")
    else:
        print("Wow your good at this game.")

    print()

def play_again():

    while True:
        answer = input("Would you like to play again?")

        if answer == 'no' or answer == 'n':
            return False
        elif answer == 'yes' or answer == 'y':
            return True

        print("what?!!! Just say yes or no, n or y.")


def credit_screen():
    print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
    print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
    print("@@                                                          @@")
    print("@@  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  @@")
    print("@@  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  @@")
    print("@@  @@                                                  @@  @@")
    print("@@  @@        _______  _______  __   __  _______        @@  @@")
    print("@@  @@       |       ||   _   ||  |_|  ||       |       @@  @@")
    print("@@  @@       |    ___||  |_|  ||       ||    ___|       @@  @@")
    print("@@  @@       |   | __ |       ||       ||   |___        @@  @@")
    print("@@  @@       |   ||  ||       ||       ||    ___|       @@  @@")
    print("@@  @@       |   |_| ||   _   || ||_|| ||   |___        @@  @@")
    print("@@  @@       |_______||__| |__||_|   |_||_______|       @@  @@")
    print("@@  @@        _______  __   __  _______  ______         @@  @@")
    print("@@  @@       |       ||  | |  ||       ||    _ |        @@  @@")
    print("@@  @@       |   _   ||  |_|  ||    ___||   | ||        @@  @@")
    print("@@  @@       |  | |  ||       ||   |___ |   |_||_       @@  @@")
    print("@@  @@       |  |_|  ||       ||    ___||    __  |      @@  @@")  
    print("@@  @@       |       | |     | |   |___ |   |  | |      @@  @@")
    print("@@  @@       |_______|  |___|  |_______||___|  |_|      @@  @@")
    print("@@  @@                                                  @@  @@")
    print("@@  @@                                                  @@  @@")
    print("@@  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  @@")
    print("@@  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  @@")
    print("@@                                                          @@")
    print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
    print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
    print("                                                              ")
    print("##############################################################")
    print("############## GAME CREATED BY JORDAN HOULE ##################")
    print("##############################################################")
        
#game begins
start_screen()

again = True

while again == True:
    play()
    again = play_again()



credit_screen()







