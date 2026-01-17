public class DodavanjeNit extends Thread {

    public DodavanjeNit() {
        super();
    }

    private boolean krajVremena = false;

    public void setKrajVremena() {
        this.krajVremena = true;
    }

    @Override
    public void run() {
        while (!krajVremena) {
            Knjiga knjiga = new Knjiga();
            synchronized (Main.lockObject) {
                Main.stek.add(knjiga);
            }
            Main.dodanihKnjiga++;
            System.out.println(knjiga + " je stavljena na stek.");
            try {
                sleep(250);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}