package src.game;

import src.messages.Messages;

public class TextualGameWatcher {
    public static void main(String []args){
        Messages messages = new Messages();
        Informer informer = new Informer(messages);
        informer.start();
        try{
            Thread.sleep(12000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        Watcher watcher = new Watcher(messages);
        watcher.start();
    }
}
