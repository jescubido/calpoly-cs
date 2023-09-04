#!/bin/env python3

import os
import datetime

#create a to do list that marks completed tasks
    # prioritizes tasks with deadlines coming up

#function for clearing the screen
def clearScreen():
    return os.system('clear')
clearScreen()

tasks = [] #initialize list of tasks

print("----- Welcome to Task Reminders -----")

#function for printing exit message and exiting program
def exitCommand():
    print("\nBrought to you by Jarisse Escubido. Thank you!")
    exit()

#function for viewing tasks
def viewTasks():
    clearScreen()
    if len(tasks) == 0:
        print("You have no tasks to complete! Yay!")
        reprompt()
    else:
        #lines below work to mark if task is completed by appending an x, or space if incomplete.
        sortedTasks = sorted(tasks, key=lambda x: x["deadline"])
        for i, taskInfo in enumerate(sortedTasks, start=1):
            task = taskInfo["task"]
            deadline = taskInfo["deadline"].strftime("%m/%d")
            completed = "x" if taskInfo["completed"] else " "
            print(f"[{completed}] {i}. {task}, DUE: {deadline}")

#function for adding new tasks to To Do List
def addTask():
    viewTasks()
    task = input("Enter new task: ")
    dateInquiry = input("Does this task have a deadline? (y/n): ")[0]
    if (dateInquiry == 'y' or dateInquiry == 'Y'):
        deadlineInput = input("Enter deadline (m/d): ")
        try:
            deadline = datetime.datetime.strptime(deadlineInput, "%m/%d")
            newTask = {"task": task, "deadline": deadline, "completed": False}
            #this part of function is a for loop that finds where to add the new task
                #sorts by deadline
            indexToInsert = 0
            for i, appendedTasks in enumerate(tasks):
                if newTask["deadline"] > appendedTasks["deadline"]:
                    indexToInsert = i + 1
                else:
                    break
            tasks.insert(indexToInsert, newTask)
            print("\nTask added!")
            viewTasks()
            reprompt()
        except ValueError:
            print("Invalid input! Please use the format 'm/d'.")
    elif (dateInquiry == 'n' or dateInquiry == 'N'):
        tasks.append({"task": task, "deadline": "12/31", "completed": False})
        print("\nTask added!")
        viewTasks()
        reprompt()

    else:
        print("Invalid input! Task cannot be added. Please try again later.")
        reprompt()

#function for marking completed tasks
def markCompleted():
    viewTasks()
    taskNum = int(input("Enter task number to mark as completed: ")) - 1
    if 0 <= taskNum < len(tasks):
        tasks[taskNum]["completed"] = True
        print("\nTask has been marked completed!")
        reprompt()
    else:
        print("Invalid input!")
        reprompt()

#function for removing tasks from To Do List
def removeTask():
    viewTasks()
    taskNum = int(input("Enter task number to be removed: ")) - 1
    if 0 <= taskNum < len(tasks):
        taskToRemove = tasks.pop(taskNum)
        print("\nTask has been removed!")
        reprompt()
    else:
        print("Invalid input! Unable to remove task. Please try again later.")
        reprompt()

#function for printing the menu options
def printMenu():
    print("[1]View tasks \n[2]Exit")

#function for printing task menu options
def viewTaskMenu():
    print("\n[1]Add new task \n[2]Mark task completed \n[3]Remove task \n[4]Exit")

#function for getting the input for task menu options
def viewInput():
    while True:
        try:
            clearScreen()
            viewSelection = int(input())
            break
        except:
            print("Invalid input! Try again!")
            viewTaskMenu()
    if viewSelection == 1:
        addTask()
    elif viewSelection == 2:
        markCompleted()
    elif viewSelection == 3:
        removeTask()
    elif viewSelection == 4:
        exitCommand()

#function that gets the input for menu options
def menuInput():
    while True:
        try:
            clearScreen()
            menuSelection = int(input())
            break
        except:
            print("Invalid Input! Try again!")
    if menuSelection == 1:
        viewTasks()
        viewTaskMenu()
        viewInput()
    elif menuSelection == 2:
        exitCommand()

#function that reprompts the user
def reprompt():
    question = input("Would you like to do something else? (y/n): ")[0]
    if (question == 'y' or question == 'Y'):
        viewTaskMenu()
        viewInput()
    elif (question == 'n' or question == 'N'):
        exitCommand()
    else:
        print("Invalid input! Please try again later.")
        exitCommand()

#program initially prints the menu options
printMenu()
menuInput()
