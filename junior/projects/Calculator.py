#!/usr/bin/env python
"""
A simple python calculator using GUI Tkinter
"""
from tkinter import *
__author__ = "Jarisse Escubido"
__date__ = "12/04/2023"
__email__ =  "jarisse.escubido@gmail.com"
__version__ = "0.0.1"

calculation = ""

def press(num):
    global calculation
    calculation = calculation + str(num)
    equation.set(calculation)

def equalSign():
    try:
        global calculation
        total = str(eval(calculation))
        equation.set(total)
        calculation = ""
    except:
        equation.set(" error ")
        calculation = ""
def clear():
    global calculation
    calculation = ""
    equation.set("")

# Initialize frame
if __name__ == "__main__":
    frame = Tk()
    frame.title("Simple Calculator")
    frame.geometry('270x150')
    equation = StringVar()
    calculationField = Entry(frame, textvariable=equation)
    calculationField.grid(columnspan=4, ipadx=70)

    # Create number buttons
    button1 = Button(frame, text='1', fg='black', command=lambda: press(1), height=1, width=7)
    button1.grid(row=2, column=0)

    button2 = Button(frame, text='2', fg='black', command=lambda: press(2), height=1, width=7)
    button2.grid(row=2, column=1)

    button3 = Button(frame, text='3', fg='black', command=lambda: press(3), height=1, width=7)
    button3.grid(row=2, column=2)

    button4 = Button(frame, text='4', fg='black', command=lambda: press(4), height=1, width=7)
    button4.grid(row=3, column=0)

    button5 = Button(frame, text='5', fg='black', command=lambda: press(5), height=1, width=7)
    button5.grid(row=3, column=1)

    button6 = Button(frame, text='6', fg='black', command=lambda: press(6), height=1, width=7)
    button6.grid(row=3, column=2)

    button7 = Button(frame, text='7', fg='black', command=lambda: press(7), height=1, width=7)
    button7.grid(row=4, column=0)

    button8 = Button(frame, text='8', fg='black', command=lambda: press(8), height=1, width=7)
    button8.grid(row=4, column=1)

    button9 = Button(frame, text='9', fg='black', command=lambda: press(9), height=1, width=7)
    button9.grid(row=4, column=2)

    button0 = Button(frame, text='0', fg='black', command=lambda: press(0), height=1, width=7)
    button0.grid(row=5, column=0)

    # Create operation buttons
    plus = Button(frame, text=' + ', fg='black', command=lambda: press("+"), height=1, width=7)
    plus.grid(row=2, column=3)

    minus = Button(frame, text=' - ', fg='black', command=lambda: press("-"), height=1, width=7)
    minus.grid(row=3, column=3)

    multiply = Button(frame, text=' * ', fg='black', command=lambda: press("*"), height=1, width=7)
    multiply.grid(row=4, column=3)

    divide = Button(frame, text=' / ', fg='black', command=lambda: press("/"), height=1, width=7)
    divide.grid(row=5, column=3)

    equal = Button(frame, text=' = ', fg='black', command=equalSign, height=1, width=7)
    equal.grid(row=5, column=2)

    # Create additional buttons
    clear = Button(frame, text=' C ', fg='black', command=clear, height=1, width=7)
    clear.grid(row=5, column=1)

    decimal = Button(frame, text=' . ', fg='black', command=lambda: press("."), height=1, width=7)
    decimal.grid(row=6, column=0)

    frame.mainloop()