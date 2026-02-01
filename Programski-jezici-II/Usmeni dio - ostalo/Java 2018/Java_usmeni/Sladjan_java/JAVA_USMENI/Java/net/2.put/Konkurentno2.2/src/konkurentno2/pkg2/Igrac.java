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
class Igrac extends Thread {
    public static volatile int kraj=0;
    public int novac=0;
    public boolean desni=false;
    public Polje[] mapa;
    public int pozicija;
    
    Igrac(Polje[] mapa, boolean desni){
        this.mapa=mapa;
        this.desni=desni;
        if(desni) pozicija=mapa.length-1;
        else pozicija=0;
        mapa[pozicija].setIg(this);
    }
    
    @Override
    public void run(){
        
        for (int i = 0;i<mapa.length;i++){
            System.out.println(""+this+" - Pozicija:"+pozicija);
            if (i!=0){ //u prvom prolazu treba samo da pokupi novac sa pocetnih pozicija
                if(desni){
                    pozicija--;
                    synchronized (mapa){
                        mapa[pozicija].setIg(this);
                        mapa[pozicija+1].setIg(null);
                    }
                   // if(pozicija==0)break; //ne treba zbog for petlje

                }else{
                    pozicija++;
                    synchronized(mapa){
                       mapa[pozicija].setIg(this);
                       mapa[pozicija-1].setIg(null);
                    }
                   // if(pozicija==mapa.length-1)break; 
                }
            }
                synchronized(mapa){
                    novac+=mapa[pozicija].getNovac();
                    mapa[pozicija].setNovac(0);
                }
           
            try{
            if (mapa[pozicija].getSe()!=null)
                while(!mapa[pozicija].getSe().zeleno()){
                    sleep(500);
                    System.out.print(""+mapa[pozicija].getSe()+"      ");
                }
            System.out.println();
                //System.out.print("|");
            
                sleep(500);
            }catch(Exception e){
                e.printStackTrace();
                kraj++;
            }
        }
        System.out.println(this+" ZAVRSIO");
        kraj++;
         for (Polje polje:mapa){
            System.out.print(polje);
        }
        System.out.println();
        //if(kraj==2)interrupt();
    }
    
    @Override
    public String toString(){
        if(desni)return "Desni: "+novac;
        else return "Lijevi: "+novac;
    }
}
