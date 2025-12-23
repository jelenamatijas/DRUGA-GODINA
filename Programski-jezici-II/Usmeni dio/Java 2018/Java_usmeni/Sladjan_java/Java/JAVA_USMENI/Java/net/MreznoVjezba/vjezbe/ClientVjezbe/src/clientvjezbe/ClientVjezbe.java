/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clientvjezbe;
import java.util.ArrayList;
import java.util.Scanner;
import java.net.*;
import java.io.*;
import proizvodi.Proizvod;
//import servervjezbe.*;

/**
 *
 * @author Milan
 */
public class ClientVjezbe {

    /**
     * @param args the command line arguments
     */
    public static final int TCP_PORT = 9000;
    
    public static void main(String[] args) {
        ArrayList<Proizvod> racun = new ArrayList<>();
        
        try{
            InetAddress addr = InetAddress.getByName("127.0.0.1");
            
            Socket sock = new Socket (addr, TCP_PORT);
            
            ObjectOutputStream oos = new ObjectOutputStream (sock.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream (sock.getInputStream());
            
            Scanner scan = new Scanner(System.in);
            Scanner scan2 = new Scanner (System.in);
            
            int opcija = 0;
            do{
            if(opcija ==1){
                oos.writeObject("LIST");
                Proizvod[] p = (Proizvod[]) ois.readObject();
                for (Proizvod pr :p){
                    System.out.println(pr);
                }
            } else if(opcija == 2){
                System.out.println("Unesite sifru proizvoda: ");
                String sifra = scan2.nextLine();
                oos.writeObject("BUY#"+sifra);
                oos.flush();
                Proizvod p = (Proizvod) ois.readObject();
                if(p != null){
                    System.out.println("Kupljen prizvod: "+p);
                    racun.add(p);
                } else {
                    System.out.println("Proizvod sa navedenom sifrom ne postoji");
                }
            } else if (opcija == 3) {
                oos.writeObject("VIEW");
                oos.flush();
                racun = (ArrayList<Proizvod>) ois.readObject();
                for (Proizvod pr : racun){
                    System.out.println(pr);
                }
            } else if (opcija == 4) {
                System.out.println("Unesite sifru proizvoda: ");
                String sifra = scan2.nextLine();
                oos.writeObject("DEL#" + sifra);
                oos.flush();
                Proizvod p = (Proizvod) ois.readObject();
                if(p != null){
                    if (racun.remove(p)){
                        System.out.println("Vracen prizvod: "+ p);
                    } else {
                        System.out.println("Proizvod nije prethodno kupljen!");
                    }
                } else {
                    System.out.println("Proizvod sa navedenom sifrom ne postoji!");
                }
            }
            
            System.out.println("Opcije:\n"
                    + "1 - prikaz cjenovnika\n"
                    + "2 - kupocina prizvoda\n"
                    + "3 - pregled svih kupljenih proizvoda\n"
                    + "4 - uklanjanje proizvoda sa racuna\n"
                    + "5 - zavrsetak kupovine");
        } while ((opcija=scan.nextInt()) != 5);
        oos.writeObject("END");
        oos.flush();
        System.out.println("Kupljeni prizvodi:");
        double ukupno = 0;
        for (Proizvod p : racun){
            System.out.println(p.getSifra() + " " +p.getNaziv() + " " +
                    p.getCijena());
            ukupno+=p.getCijena();
        }
        System.out.println("Ukupno za placanje: "+ukupno);
        ois.close();
        oos.close();
        sock.close();
            
        }catch (Exception e){}
    }
    
}
