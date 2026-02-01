public abstract class PilotnaLetjelica extends SvemirskaLetjelica implements LaserStit {

    protected String navigacijskiSistem;
    protected Laser laser;
    protected Raketa raketa;

    public PilotnaLetjelica(int id, int kolona, String navigacijskiSistem, Laser laser, Raketa raketa) {
        super(id, kolona);
        this.navigacijskiSistem = navigacijskiSistem;
        this.laser = laser;
        this.raketa = raketa;
    }

    public void ugasiLetjelicu() {
        this.ziva = false;
    }

    @Override
    public void run() {
        int duzinaMape;

        synchronized(Main.lock) {
            duzinaMape = Main.DUZINA_MAPE;
        }


        for (int i = 0; i < duzinaMape; i++) {
            if (!this.ziva) {
                break;
            }

            synchronized(Main.lock) {
                if (Main.cekanje) {
                    try {
                        Main.lock.wait();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            this.red = i;

            Main.MAPA[this.red][this.kolona] = this;
            if (this.red != 0) {
                Main.MAPA[this.red - 1][this.kolona] = null;
            }
            
            System.out.println(this);

            try {
                sleep(1000);
                this.vrijeme += 1000;
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        System.out.println(this + " JE ZAVRSILA KRETANJE!");

        this.ziva = false;
    }
}