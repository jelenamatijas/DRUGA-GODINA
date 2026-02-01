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
public class Semafor extends Thread 
{
   // public String stanje="Crveno";
    public int stanje = 0;
    public Semafor()
    {
        stanje=0;
    }
    
    @Override
    public void run()
    {
        while(true)
        {
           if (stanje == 2)
               stanje = 0;
            else
               stanje++;     
           try
           {
               if (stanje == 0 || stanje == 2)
                   sleep(1800);
               else
                   sleep(700);
               
           }catch (Exception e)
           {
               e.printStackTrace();
           }
        }
    }
    
    @Override
    public String toString()
    {
        if(stanje == 0)
            return "Crveno";
        else
            if (stanje == 2)
                return "Zeleno";
            else     
                return "Zuto"; //ukoliko je semafor pokvaren ispisivace se zuto.
        
    }
    
}
