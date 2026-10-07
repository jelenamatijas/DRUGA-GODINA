package pismeni.R_2022_06_29.Z03;

public class RemoveThread extends Thread {
    Long postotak;

    public RemoveThread(long postotak) {
        this.postotak = postotak;
        start();
    }

    @Override
    public void run() {
        try {
            while (!Simulacija.END) {
                Thread.sleep(500 * postotak);
                Student student = Simulacija.red.studenti.take();
                System.out.println("Uklonjen je: " + student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}