/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servervjezbe;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import proizvodi.Proizvod;
/**
 *
 * @author Milan
 */
class ServerThread extends Thread {
    private Socket sock;
    private int value;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ArrayList<Proizvod> racun = new ArrayList<>();
    
    public ServerThread(Socket sock, int value){
        this.sock = sock;
        this.value = value;
        try{
            oos = new ObjectOutputStream(sock.getOutputStream());
            ois = new ObjectInputStream (sock.getInputStream());
        }catch (Exception ex){}
        
        start();
        
    }
    
    @Override
    public void run(){
        try{
            String option ="";
            while (!"END".equals(option)){
                option = (String) ois.readObject();
                System.out.println(option);
                if("LIST".equals(option)){
                   oos.writeObject(ServerVjezbe.cjenovnik); 
                   oos.flush();
                }else if ("VIEW".equals(option)){
                    oos.writeObject(racun);
                    oos.flush();
                }else if (option.startsWith("BUY")){
                    String sifra = option.split("#")[1];
                    boolean found = false;
                    for(Proizvod p: ServerVjezbe.cjenovnik){
                        if(p.getSifra().equals(sifra)){
                            racun.add(p);
                            found=true;
                            oos.writeObject(p);
                            break;
                        }
                    }
                    if(!found){
                       oos.writeObject(null);
                    }
                }else if (option.startsWith("DEL")){
                    String sifra = option.split("#")[1];
                    boolean found = false;
                    for (Proizvod p: racun){
                        if(p.getSifra().equals(sifra)){
                            racun.remove(p);
                            found = true;
                            oos.writeObject(p);
                            break;
                        }
                    }
                    if (!found){
                        oos.writeObject(null);
                    }
                }
            }
            oos.close();
        ois.close();
        sock.close();
        System.out.println("[Client " + value + "] se odjavio");
        }catch (Exception e){
    e.printStackTrace();
        }        
        
    }

}
