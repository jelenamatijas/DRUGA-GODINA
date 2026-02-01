package pismeni.R_2021_09_08.Z03;

import java.util.Random;

public class RemoveThread extends Thread {

    Random rand = new Random();
    UlancaniStek<Integer> stek;
    int number;

    public RemoveThread(UlancaniStek<Integer> stek, int number) {
        this.stek = stek;
        this.number = number;
    }

    @Override
    public void run() {
        int vrijednost = 0;
        for (int i = 0; i < number; i++) {
            if (StackMain.END)
                break;

            synchronized (stek) {
                try {
                    vrijednost = stek.pop();
                } catch (StackEmptyException e) {
                    System.out.println(e.getMessage());
                    // TODO Treba li wait i notify ako se desi izuzetak, da sen e računa kao iteracija?
                }
            }
            try {
                Thread.sleep(rand.nextInt(100) + 200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" Skinuto sa steka " + vrijednost);
        }
    }
}