import socket

PORT = 8888
ip = 'localhost'

s = socket.socket()
print("socket successfully created..")

s.bind((ip, PORT))
print("socket successfully binded..")

s.listen(3)
print("server listening..")

while True:
    c, addr = s.accept()
    
    c.send(bytes("Server of Jarisse Escubido\n", 'utf-8'))

    name = c.recv(1024).decode()
    print("\nConnected with ", addr, name, "\n")

    clientInt = c.recv(1024).decode()
    serverInt = 50
    serverStr = str(serverInt)
    c.send(bytes(serverStr, 'utf-8'))
    
    if not clientInt:
        print("The client entered a number that is out of bounds")
        print("\nServer closing..\n")
        c.close()
        exit()

    sum = serverInt + int(clientInt)
    result = str(sum)

    c.send(bytes(result, 'utf-8'))

    print("Client's Number: ", clientInt)
    print("Server's Number: ", serverInt)
    print("The sum = ", sum)    
    c.close()
