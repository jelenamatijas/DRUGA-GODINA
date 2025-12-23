/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package konkurentno2;

/**
 *
 * @author Milan
 */
public class Igrac extends Thread
{
    public Semafor sem;
    public int novac;
    public String ime;
    public Mapa mapa;
    public Boolean desni;
    public int pozicija;
    public Boolean kraj=false;
    
    public Igrac(String ime, Mapa mapa, Boolean desni)
    {
        this.ime=ime;
        this.mapa = mapa;
        this.desni=desni;
        if(desni) pozicija=mapa.getMapa().length;
        else pozicija=0;
        
        this.sem=mapa.getMapa()[pozicija].sem;
        this.novac+= mapa.getMapa()[pozicija].novac;
        //mapa.getMapa()[pozicija].ig=this;
        
        
    }
    
    public void pomjeraj()
    {
        try
        {
            if (sem != null)
                while(!sem.toString().equals("Zeleno"))
                    sleep(1);
            sleep(500);
            
            pozicija++;
            if (pozicija == mapa.getMapa().length-1)kraj();
            else
            {
                mapa.getMapa()[pozicija].ig=this;
                synchronized(mapa.getMapa()[pozicija])
                {
                    this.novac+= mapa.getMapa()[pozicija].novac;
                    mapa.getMapa()[pozicija].novac=0;
                }
                this.sem=mapa.getMapa()[pozicija].sem;
            }
         }catch(Exception e)
         {
             System.out.println("Greska u pomjeranju igraca "+ime);
         }
    }
    
    @Override
    public void run()
    {
        pomjeraj();
    }
    
    @Override
    public String toString()
    {
        return "Ima igraca: "+ime;
    }

    private void kraj() 
    {
        kraj=true;
        try
        {
        this.join();
        this.sem.join();
        }catch (Exception e)
        {
            System.out.println("Greska u stopiranju treda IGRAC");
        }
    }
        
    
}
