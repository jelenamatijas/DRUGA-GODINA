/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package labkonkuretno;

import java.util.Random;

/**
 *
 * @author Milan
 */
public class Automobil extends Thread {
    
    public String tipAutomobila;
    public int id;
    public Object[][] staza;
    public int ostaloDoKraja=0;
    public int red;
    public static boolean kraj=false;
    public static int number=0;
    
    public Automobil (Object[][] staza, int red, String tipAutomobila) 
    {
       Random slucajna=new Random();
        this.staza=staza;
        this.id=++number;
        this.tipAutomobila=tipAutomobila;        
    }
    
    @Override
    public void run()
    {
        for(int i=0;i<15;i++)
        {
             
            if (kraj)
            {
                System.out.print("Automobil nije zavrsio trku prvi...\n"+this+"\n");
                interrupt();
                break;
            }
            if(i==14)
            {
                System.out.println("Automobil je POBJEDIO!!!"+this);
                kraj=true;
                interrupt();
                break;
                     
            }else
            {
                //staza[red][i]=this;
                ostaloDoKraja=14-i;
                System.out.print("Automobil se krece..."+this);
                
                if(staza[red][i]!=null && staza[red][i].equals("STOP"))
                {
                    try
                    {
                        sleep(5000);
                        System.out.println("Automobil je zaustavljen "+this);
                    }catch(InterruptedException e)
                    {
                        System.out.println("Automobil nije zavrsio prvi"+this);
                    }
                }else
                {
                    try
                    {
                        sleep(2000);
                    }catch(InterruptedException e)
                    {
                        System.out.println("Automobil nije zavrsio prvi" +this);
                    }
                }
            }
        }
        
    }
    
    @Override
    public String toString()
    {
        return "\nTip automobila:"+tipAutomobila+
                "\nID:"+id+
                "\nOstalo mu je do kraja: "+ostaloDoKraja+"\n";
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Object[][] staza={new Object[15], new Object[15],new Object[15]};
        for (int i=1;i<4;i++)
        {
            staza[new Random().nextInt(3)][new Random().nextInt(15)]="STOP";
        }
        
        Automobil[] a={new Automobil(staza,0,"Mercedes"),
                        new Automobil(staza,1,"BMW"),
                        new Automobil(staza,2,"Audi")};
        for(int i=0;i<3;i++)
        {
            a[i].start();
        }
        };
        // TODO code application logic here
    }
    

