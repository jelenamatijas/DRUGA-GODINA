/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servervjezbe;

import java.io.IOException;
import java.net.*;
import proizvodi.Monitor;
import proizvodi.Proizvod;
import proizvodi.Racunar;
import proizvodi.Softver;
import proizvodi.Telefon;
import proizvodi.Proizvodjac;

/**
 *
 * @author Milan
 */
public class ServerVjezbe {
    
    public static final int TCP_PORT = 9000;
    
    public static Proizvod[] cjenovnik;
    
    public static void init(){
        Proizvodjac samsung = new Proizvodjac ("Samsung", "Juzna Koreja", "info@samsung.com");
        Proizvodjac lenovo = new Proizvodjac ("Lenovo", "Kina", "info@lenovo.com");
        Proizvodjac lg = new Proizvodjac ("LG", "Juzna Korea", "info@lg.com");
        Proizvodjac microsoft = new Proizvodjac ("Microsoft", "USA", "info@microsoft.com");
        
        Monitor m1 = new Monitor("123", "    Screen size (inches)"
                + "    Panel Type 34"
                + "    Aspect Ratio IPS"
                + "    Resolution 21:9", "m100", 300.0, "Monitor LG 123", lg);

        Racunar r1 = new Racunar("Intel i3, RAM 4 GB, HDD 300 GB, Windows 7", "r100", 1025.5, "Laptop ThinkPad", lenovo);
        Racunar r2 = new Racunar("Intel i5, RAM 8 GB, HDD 500 GB, Windows 7", "r101", 1325.5, "Laptop ThinkPad", lenovo);
        Racunar r3 = new Racunar("Intel i7, RAM 16 GB, HDD 1 TB, Windows 7", "r102", 1625.5, "Laptop ThinkPad", lenovo);

        Telefon t1 = new Telefon("Galaxy S4", "RAM 2 GB, Super AMOLED capacitive touchscreen, 16M colors ", "t100",
                800, "Samsung Galaxy S4", samsung);

        Softver s1 = new Softver("Operativni sistem Windows 7", "s100", 100, "Windows 7", microsoft);
        cjenovnik = new Proizvod[]{m1, r1, r2, r3, t1, s1};
                
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        init();
        try{
            int clijentCounter = 0;
            
            ServerSocket ss = new ServerSocket(TCP_PORT);
            System.out.println("Server running...");
            while(true){
                Socket socket = ss.accept();
                System.out.println("Clijent accepted: "
                + (++clijentCounter));
                
                ServerThread st = new ServerThread (socket, clijentCounter);
            }
        }catch(IOException ex){
                    ex.printStackTrace();
                    }
    }
    
}
