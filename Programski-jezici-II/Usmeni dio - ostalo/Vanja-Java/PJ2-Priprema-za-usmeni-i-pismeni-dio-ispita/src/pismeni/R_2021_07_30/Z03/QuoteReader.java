package pismeni.R_2021_07_30.Z03;

public class QuoteReader extends Thread {
    public int cekanje = 2000;

    public QuoteReader() {
        setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            for (String s : QuoteStorage.quotes)
                System.out.println(s);
            try {
                sleep(cekanje);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}