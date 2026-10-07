package src.messages;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Messages{
    private ArrayList<Message> messages = new ArrayList<>();
    private boolean free = true;

    public synchronized void setMessage(){
        if(!free){
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            Random rand = new Random();
            int poeni = rand.nextInt(1,4);
            Message poruka = new Message("Igrac " + rand.nextInt(100), 
                                        "Tim " + rand.nextInt(1)+1, 
                                        new Date().getTime(), 
                                        poeni);
            Message.rezultat += poeni;
            System.out.println("Trenutni rezultat: " + Message.rezultat + ". Broj poruka: " + (messages.size()+1));
            messages.add(poruka);
            free = false;
        }
    }

    public synchronized Message getMessage(){
        Message message = messages.get(messages.size()-1);
        free = true;
        notify();
        return message;
    }
}
