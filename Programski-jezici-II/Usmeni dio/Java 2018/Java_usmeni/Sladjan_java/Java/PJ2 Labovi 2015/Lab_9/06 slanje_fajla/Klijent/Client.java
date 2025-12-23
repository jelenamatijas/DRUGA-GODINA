import java.io.*;
import java.net.*;
public class Client {
public static final int TCP_PORT = 9000;
public static void main(String[] args) {
try {
// odredi adresu racunara sa kojim se povezujemo
// (povezujemo se sa nasim racunarom)
InetAddress addr = InetAddress.getByName("127.0.0.1");
// otvori socket prema drugom racunaru
Socket sock = new Socket(addr, TCP_PORT);
// inicijalizuj ulazni stream
BufferedReader in = new BufferedReader(new InputStreamReader(
sock.getInputStream()));
// inicijalizuj izlazni stream
PrintWriter out = new PrintWriter(
new BufferedWriter( new OutputStreamWriter(
sock.getOutputStream())), true);
System.out.println("[Client]: mreze.ppt");
out.println("mreze.ppt");
// procitaj duzinu fajla
int duzina=Integer.parseInt(in.readLine());
System.out.println("[Server]: " + duzina);
int kontrolnaDuzina=0, flag=0;
byte[] buffer=new byte[2*1024*1024];
OutputStream fajl = new FileOutputStream("noveMreze.ppt");
InputStream is=sock.getInputStream();
while((kontrolnaDuzina=is.read(buffer))>0){
  fajl.write(buffer, 0, kontrolnaDuzina);
  flag+=kontrolnaDuzina;
  if(duzina==flag) break;
}
System.out.println("Preuzimanje zavrseno...");
fajl.close();
// zatvori konekciju
in.close();
out.close();
sock.close();
} catch (UnknownHostException e1) {
e1.printStackTrace();
} catch (IOException e2) {
e2.printStackTrace();
}
}
}