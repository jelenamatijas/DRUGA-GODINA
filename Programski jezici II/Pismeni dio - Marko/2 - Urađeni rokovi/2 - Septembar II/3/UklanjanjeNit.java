public class UklanjanjeNit extends Thread {

    public UklanjanjeNit() {
        super();
    }

    private boolean krajVremena = false;

    public void setKrajVremena() {
        this.krajVremena = true;
    }

    @Override
    public void run() {
        while (!krajVremena) {
            Knjiga knjiga;
            synchronized (Main.lockObject) {
                if (!Main.stek.isEmpty()) {
                    knjiga = Main.stek.pop();
                    System.out.println(knjiga + " je skinuta sa steka.");
                    Main.obrisanihKnjiga++;
                }
            }
            try {
                sleep(400);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}