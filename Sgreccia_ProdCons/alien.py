import socket as s

HOST = "127.0.0.1"  # localhost
PORT = 8010  # Port to connect to (non-privileged ports are > 1023)

sock = s.socket(s.AF_INET, s.SOCK_STREAM)
sock.connect((HOST, PORT))
sock.send(b"msg(idPy,request,AlienProducer,El_Consumador,69,0)\n") # messaggio request
response = sock.recv(4096) # response

while not response.endswith(b"\n"):
    response += sock.recv(4096) 

print(f"Received {str(response)}")
sock.send(b"msg(idPy,dispatch,AlienProducer,El_Consumador,420,0)\n") # messaggio dispatch
#sock.shutdown(s.SHUT_WR)
sock.close()
