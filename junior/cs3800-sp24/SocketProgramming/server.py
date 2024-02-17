import socket

PORT = 8888
localhost = "192.168.4.40"

s = socket.socket()
print("Socket Created")

s.bind(('localhost', PORT))

s.listen(3)
print("waiting for connections")

while True:
    c, addr = s.accept()
    name = c.recv(1024).decode()
    
    print("Connected with ", addr, name)

    c.send(bytes("Welcome to Jarisse's server.", 'utf-8'))

    clientInt = c.recv(1024).decode()
    serverInt = input("Enter a number between 1 and 100: ")

    sum = int(clientInt) + int(serverInt)
    c.send(bytes(str(sum), 'utf-8'))

    print("Server's Number: ", serverInt)
    print("Client's Number: ", clientInt)
    print("Sum = ", sum)

    c.close()
