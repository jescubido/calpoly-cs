from tkinter import *
from tkinter import ttk

#colors
bone = "#f2e9e4"
gray = "#8d99ae"


#create interface
window = Tk()
window.title('Calculator')
window.geometry('235x318')
window.configure(bg=bone)

style = ttk.Style(window)

ttk.Separator(window, orient=HORIZONTAL).grid(row=0, columnspan=1, ipadx=280)

#create frame for numbers to be displayed
frame_result = Frame(window, width=300, height=56, bg=gray, pady=0, padx=0)
frame_result.grid(row=1, column=0, sticky=NW)

app_screen = Label(frame_result, width=16, height=2, padx=7, anchor="e", bd=0, justify=RIGHT, font=('Ivy 18'), bg=gray, fg=bone)


window.mainloop()