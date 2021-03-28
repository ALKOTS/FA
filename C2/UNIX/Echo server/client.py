import socket
import logging
from threading import Thread
import sys
import pickle
from validation import ip_validation, port_validation
from getpass import getpass
from time import sleep

IP_DEFAULT = "127.0.0.1"
PORT_DEFAULT = 9090
logging.basicConfig(filename='Logs/client.log',
                    format="%(asctime)s [%(levelname)s] %(funcName)s: %(message)s", level=logging.INFO)

class Client:
    def __init__(self, server_ip, port, status = None):
        self.server_ip = server_ip
        self.port = port
        self.status = status
        self.server_connection()
        self.polling()

    def server_connection(self):
        sock = socket.socket()
        sock.setblocking(1)
        try:
            sock.connect((self.server_ip, self.port))
        except ConnectionRefusedError:
            print(f"Unable to connect {self.server_ip, self.port}")
            sys.exit(0)
        self.sock = sock
        logging.info(
            f"Established connection {self.sock.getsockname()} with ('{self.server_ip}', {self.port})")

    def polling(self):
        Thread(target=self.recv).start()
        print("Use 'exit', to disconnect")
        while self.status != 'finish':
            if self.status:
                if self.status == "auth":
                    self.auth()
                    logging.info(f"{self.sock.getsockname()} registered")
                elif self.status == "passwd":
                    self.sendPasswd()
                elif self.status == "success":
                    self.success()
                else:
                    msg = input(f"{self.username}> ")
                    if msg != "":
                        if msg == "exit":
                            self.status = "finish"
                            logging.info(f"Disconnecting {self.sock.getsockname()} from server")
                            break
                        sendM = pickle.dumps(["message", msg, self.username])
                        self.sock.send(sendM)
                        logging.info(f"Sending data from {self.sock.getsockname()}: {msg}")
        self.sock.close()

    def sendPasswd(self):
        passwd = getpass(self.data)
        self.sock.send(pickle.dumps(["passwd", passwd]))
        sleep(1.5)

    def auth(self):
        print("Введите имя:")
        self.username = input()
        self.sock.send(pickle.dumps(["auth", self.username]))
        sleep(1.5)

    def success(self):
        print(self.data)
        self.status = "ready"
        self.username = self.data.split(" ")[1]
        logging.info(f"Client {self.sock.getsockname()} authorized")

    def recv(self):
        while True:
            try:
                self.data = self.sock.recv(1024)
                if not self.data:
                    sys.exit(0)
                status = pickle.loads(self.data)[0]
                self.status = status
                if self.status == "message":
                    print(f"\n{pickle.loads(self.data)[2]} -->", pickle.loads(self.data)[1])
                    logging.info(f"Client {self.sock.getsockname()} recieved data: {pickle.loads(self.data)[1]}")
                else:
                    self.data = pickle.loads(self.data)[1]
            except OSError:
                break


def main():
    user_port = input("Enter port (enter for default):")
    if not port_validation(user_port):
        user_port = PORT_DEFAULT
        print(f"Set {user_port} as default port")

    user_ip = input("Enter server ip (enter for default):")
    if not ip_validation(user_ip):
        user_ip = IP_DEFAULT
        print(f"Set {user_ip} as default")

    Client(user_ip, int(user_port))


if __name__ == "__main__":
    main()
