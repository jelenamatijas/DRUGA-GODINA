import java.io.*;
import java.net.*;
public class Server {
public static final int TCP_PORT = 9000;
public static void main(String[] args) {
try {
int clientCounter = 0;
// slušaj zahteve na datom portu
ServerSocket ss = new ServerSocket(TCP_PORT);
System.out.println("Server running...");
while (true) {
Socket sock = ss.accept();
System.out.println("Client accepted: " +
(++clientCounter));
ServerThread st = new ServerThread(sock, clientCounter);
}
} catch (Exception ex) {
ex.printStackTrace();
}}}