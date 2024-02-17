import socket

PORT = 8888
ip = 'localhost'
#'192.168.4.40'

c = socket.socket()

c.connect((ip, PORT))

print(c.recv(1024).decode()) #prints welcome message

name = input("Enter your name: ")
c.send(bytes(name, 'utf-8'))

clientInt = input("Enter a number between 1 to 100: ")

if int(clientInt) < 1 or int(clientInt) > 100 or clientInt == None:
    print("The number entered is out of bounds")
    print("\nClient exiting..\n")
    c.close()
    
else:
    c.send(bytes(clientInt, 'utf-8'))
    serverInt = c.recv(1024).decode()
    sum = c.recv(1024).decode()

    print("Client's Number: ", clientInt)
    print("Server's Number: ", serverInt)
    print("The sum = ", sum)

    c.close()   

