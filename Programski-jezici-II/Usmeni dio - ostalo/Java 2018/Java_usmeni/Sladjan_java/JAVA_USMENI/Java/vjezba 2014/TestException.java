
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Milan
 */
public class TestException {
    
    public static void main(String args[]){
        try{
        Scanner sc= new Scanner(System.in);
        if(sc.hasNextInt())System.out.println("ovo je broj");
        else throw new Greska("niste unijeli broj!");
    }
        catch(Greska g){
            System.out.println("Greska se desila: "+g.poruka);
        }
    }
    
}

class Greska extends Exception {
    public String poruka="";
    public Greska(String s){
    poruka=s;
    }
}
