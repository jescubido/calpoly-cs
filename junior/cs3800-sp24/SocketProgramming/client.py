import socket

PORT = 8888
localhost = "192.168.4.40"

c = socket.socket()

c.connect(('localhost', PORT))

name = input("Enter your name: ")
c.send(bytes(name, 'utf-8'))

clientInt = input("Enter a number between 1 and 100: ")
c.send(bytes(clientInt, 'utf-8'))

print(c.recv(1024).decode())

serverInt = int(c.recv(1024).decode())

print("Server's Number: ", serverInt)
print("Client's Number: ", clientInt)

# TODO: print sum of client and server's numbers

c.close()