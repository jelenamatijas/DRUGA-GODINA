package src.game;

import java.util.Date;

import src.messages.Messages;

public class Informer extends Thread{
    Messages informacija;

    public Informer(Messages informacija){
        this.informacija = informacija;
    }

    @Override
    public void run(){
        long start = new Date().getTime();
        while(new Date().getTime() - start <= 120000){
            try {
                informacija.setMessage();
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}