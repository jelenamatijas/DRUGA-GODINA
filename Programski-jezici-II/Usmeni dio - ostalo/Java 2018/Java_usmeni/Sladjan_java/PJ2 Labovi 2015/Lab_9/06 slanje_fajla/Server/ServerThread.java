import java.io.*;
import java.net.*;
public class ServerThread extends Thread {
public ServerThread(Socket sock, int value) {
this.sock = sock;
this.value = value;
try {
// inicijalizuj ulazni stream
in = new BufferedReader(
new InputStreamReader(
sock.getInputStream()));
// inicijalizuj izlazni stream
out = new PrintWriter(
new BufferedWriter(
new OutputStreamWriter(
sock.getOutputStream())), true);
} catch (Exception ex) {
ex.printStackTrace();
}
start();
}
public void run() {
try {
// ocitamo ime fajla
String fajlName = in.readLine();
// posaljemo duzinu
long duzina=new File(fajlName).length();
out.println(duzina);
byte[] buffer=new byte[2*1024*1024];
InputStream fajl=new FileInputStream(new File(fajlName));
int length=0;
OutputStream os=sock.getOutputStream();
while((length=fajl.read(buffer))>0){
        os.write(buffer, 0, length);
        System.out.println("Preostalo jos "+(duzina-length));
}
System.out.println("Ocitavanje zavrseno...");
// zatvori konekciju
fajl.close();
os.close();
in.close();
out.close();
sock.close();
} catch (Exception ex) {
ex.printStackTrace();
}
}
private Socket sock;
private int value;
private BufferedReader in;
private PrintWriter out;
}