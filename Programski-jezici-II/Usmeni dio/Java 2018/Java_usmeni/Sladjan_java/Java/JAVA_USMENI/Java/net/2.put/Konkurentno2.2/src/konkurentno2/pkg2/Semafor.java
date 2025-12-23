/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package konkurentno2.pkg2;

/**
 *
 * @author Milan
 */
class Semafor extends Thread{
    public int i=0;
    
    @Override
    public void run(){
        while(Igrac.kraj!=2)
            try{
                switch(i){
                    case 0: i++;
                        sleep(1800);
                        break;
                    case 1: i++;
                        sleep(700);
                        break;
                    case 2: i=0;
                        sleep(1800);
                        break;
                }
                
            }catch(Exception e){
                e.printStackTrace();
            }
    }
    
    public boolean zeleno(){
        if (i==0)return true;
        else return false;
    }
    
    @Override
    public String toString(){
        if(i==0)return "Zeleno";
                else if(i==1) return "Zuto";
                        else return "Crveno";
    }
    
}
