from tkinter import *
from tkinter import ttk

#colors
bone = "#f4f4f9"
gray = "#8d99ae"
blush = "f9e9ec"
lavender = "#e7e6f7"
purple = "#aaa1c8"
dark_gray = "#8b8c89"


#create interface
window = Tk()
window.title('Calculator')
window.minsize(235, 318)
window.maxsize(235, 318)
window.configure(bg=bone)

style = ttk.Style(window)

ttk.Separator(window, orient=HORIZONTAL).grid(row=0, columnspan=1, ipadx=280)

#create frame for numbers to be displayed
frame = Frame(window, width=300, height=56, bg=purple, pady=0, padx=0)
frame.grid(row=1, column=0, sticky=NW)

# panel for all the buttons
buttons = Frame(window, width=300, height=340, bg=purple, pady=0, padx=0)
buttons.grid(row=2, column=0, sticky=NW)


"""
Creating the functions for the operations and operands
"""

def entering_numbers(number):
    global all_values

    all_values = all_values + str(number)
    text_value.set(all_values)

all_values = ""
text_value = StringVar()

operators = ['+', '-', '/', '%', '.', '*']

def calculate():
    global all_values
    
    if all_values.__contains__('/0'):
        text_value.set("Error: division by zero")

    elif all_values.endswith('+'):
        text_value.set("Error: invalid syntax")

    elif all_values.endswith('-'):
        text_value.set("Error: invalid syntax")

    elif all_values.endswith('/'):
        text_value.set("Error: invalid syntax")

    elif all_values.endswith('*'):
        text_value.set("Error: invalid syntax")

    elif all_values.endswith('%'):
        text_value.set("Error: invalid syntax")

    elif all_values.endswith('.'):
        text_value.set("Error: invalid syntax")

    else:
        result = str(eval(all_values))
        text_value.set(result)
        all_values = ""

def clear_screen():
    global all_values
    all_values = ""
    text_value.set("")

# numbers show up when buttons are pressed
calculation_screen = Label(frame, width=16, height=2, textvariable=text_value, padx=7, anchor="e", bd=0, justify=RIGHT, font=('Ivy 18 '), bg=purple, fg=bone)
calculation_screen.place(x=0, y=0)

"""
Making all the operands and number buttons
"""
# clear button
clear_button = Button(buttons, text="C", command=lambda:clear_screen(), width=11, height=2, bg=lavender, fg=purple, font=("Ivy 13 bold"), relief=RAISED, overrelief=RIDGE)
clear_button.place(x=0, y=0)
# percent button
percent_button = Button(buttons, text="%", command=lambda:entering_numbers("%"), width=5, height=2, bg=lavender, fg=purple, font=("Ivy 13 bold"), relief=RAISED, overrelief=RIDGE)
percent_button.place(x=118, y=0)
#division button
division_button = Button(buttons, text="/", command=lambda:entering_numbers("/"), width=5, height=2, bg=lavender, fg=purple, font=("Ivy 13 bold"), relief=RAISED, overrelief=RIDGE)
division_button.place(x=177, y=0)

# number 7 button
seven = Button(buttons, text="7", command=lambda:entering_numbers("7"), width=5, height=2, bg=lavender, fg=purple, font=("Ivy 13 bold"), relief=RAISED, overrelief=RIDGE)
seven.place(x=0, y=52)
# number 8 button
eight = Button(buttons, text="8", command=lambda:entering_numbers("8"), width=5, height=2, bg=lavender, fg=purple, font=("Ivy 13 bold"), relief=RAISED, overrelief=RIDGE)
eight.place(x=59, y=52)
# number 9 button
nine = Button(buttons, text="9", command=lambda:entering_numbers("9"), width=5, height=2, bg=lavender, fg=purple, font=("Ivy 13 bold"), relief=RAISED, overrelief=RIDGE)
nine.place(x=118, y=52)
# multiply button
multiply_button = Button(buttons, text="x", command=lambda:entering_numbers("*"), width=5, height=2, bg=lavender, fg=purple, font=("Ivy 13 bold"), relief=RAISED, overrelief=RIDGE)
multiply_button.place(x=177, y=52)

# number 4 button
four = Button(buttons, text="4", command=lambda:entering_numbers("4"), width=5, height=2, bg=lavender, fg=purple, font=("Ivy 13 bold"), relief=RAISED, overrelief=RIDGE)
four.place(x=0, y=104)
# number 5 button
five = Button(buttons, text="5", command=lambda:entering_numbers("5"), width=5, height=2, bg=lavender, fg=purple, font=("Ivy 13 bold"), relief=RAISED, overrelief=RIDGE)
five.place(x=59, y=104)
# number 6 button
six = Button(buttons, text="6", command=lambda:entering_numbers("6"), width=5, height=2, bg=lavender, fg=purple, font=("Ivy 13 bold"), relief=RAISED, overrelief=RIDGE)
six.place(x=118, y=104)
# subtraction button
sub_button = Button(buttons, text="-", command=lambda:entering_numbers("-"), width=5, height=2, bg=lavender, fg=purple, font=("Ivy 13 bold"), relief=RAISED, overrelief=RIDGE)
sub_button.place(x=177, y=104)

# number 1 button
one = Button(buttons, text="1", command=lambda:entering_numbers("1"), width=5, height=2, bg=lavender, fg=purple, font=("Ivy 13 bold"), relief=RAISED, overrelief=RIDGE)
one.place(x=0, y=156)
# number 2 button
two = Button(buttons, text="2", command=lambda:entering_numbers("2"), width=5, height=2, bg=lavender, fg=purple, font=("Ivy 13 bold"), relief=RAISED, overrelief=RIDGE)
two.place(x=59, y=156)
# number 3 button
three = Button(buttons, text="3", command=lambda:entering_numbers("3"), width=5, height=2, bg=lavender, fg=purple, font=("Ivy 13 bold"), relief=RAISED, overrelief=RIDGE)
three.place(x=118, y=156)
# addition button
add_button = Button(buttons, text="+", command=lambda:entering_numbers("+"), width=5, height=2, bg=lavender, fg=purple, font=("Ivy 13 bold"), relief=RAISED, overrelief=RIDGE)
add_button.place(x=177, y=156)

# number 0 button
zero = Button(buttons, text="0", command=lambda:entering_numbers("0"), width=11, height=2, bg=lavender, fg=purple, font=("Ivy 13 bold"), relief=RAISED, overrelief=RIDGE)
zero.place(x=0, y=208)
# decimal button
decimal = Button(buttons, text=".", command=lambda:entering_numbers("."), width=5, height=2, bg=lavender, fg=purple, font=("Ivy 13 bold"), relief=RAISED, overrelief=RIDGE)
decimal.place(x=118, y=208)
# equal sign button
equals_button = Button(buttons, text="=", command=lambda:calculate(), width=5, height=2, bg=lavender, fg=purple, font=("Ivy 13 bold"), relief=RAISED, overrelief=RIDGE)
equals_button.place(x=177, y=208)

window.mainloop()