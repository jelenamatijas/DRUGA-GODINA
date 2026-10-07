package src.game;

import java.util.Date;

import src.messages.Messages;
import src.messages.Message;

public class Watcher extends Thread{
    Messages informacije;   

    public Watcher(Messages informacije){
        this.informacije = informacije;
    }

    @Override
    public void run(){
        long start = new Date().getTime();
        while(new Date().getTime() - start <= 120000){
            try{
                Message poruka = informacije.getMessage();
                System.out.println(poruka);
                sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
