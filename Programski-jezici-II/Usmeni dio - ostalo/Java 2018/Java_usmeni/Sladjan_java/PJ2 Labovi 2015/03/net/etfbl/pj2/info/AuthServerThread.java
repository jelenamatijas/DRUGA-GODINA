package net.etfbl.pj2.info;


import java.io.*;
import java.net.*;

public class AuthServerThread extends Thread {

    public AuthServerThread(Socket sock, int value) {
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
    
    //implementiraj run metodu
    public void run() {
        try {
            //zahtjev u formatu AUTH#user#pass
            String request = in.readLine();
            if(request.startsWith("AUTH#") && request.split("#").length == 3){
                String user = request.split("#")[1];
                String pass = request.split("#")[2];
                if(AuthServer.users.containsKey(user) && AuthServer.users.get(user).equals(pass)){
                    out.println("AUTH OK");
                } else {
                    out.println("AUTH NOK");
                }
            } else {
                out.println("INVALID REQUEST");
            }
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
