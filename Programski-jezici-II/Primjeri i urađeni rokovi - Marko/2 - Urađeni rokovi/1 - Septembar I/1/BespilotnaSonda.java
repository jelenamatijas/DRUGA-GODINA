public class BespilotnaSonda extends SvemirskaLetjelica implements RaketaStit {

    private String senzor;
    private Laser laser;

    public BespilotnaSonda(int id, int kolona, String senzor, Laser laser) {
        super(id, kolona);
        this.senzor = senzor;
        this.laser = laser;
    }

    public synchronized void detektujLetjelice() {
        for (int i = this.red; i < this.red + 3; i++) {
            if (i >= 0 && i < 20) {
                PilotnaLetjelica pilotnaLetjelica = null;

            // Nulta kolona
            if (Main.MAPA[i][0] != null && !(Main.MAPA[i][0] instanceof LaserStit)) {
                if (Main.MAPA[i][0] instanceof SvemirskiBrod) {
                    System.out.println("BespilotnaSonda " + this.id + " je unistila SvemirskiBrod na lokaciji [" + i + "]" + "[0]");
                } else if (Main.MAPA[i][0] instanceof MatricnaStanica) {
                    System.out.println("BespilotnaSonda " + this.id + " je unistila MatricnuStanicu na lokaciji [" + i + "]" + "[0]");
                }
                pilotnaLetjelica = (PilotnaLetjelica) Main.MAPA[i][0];
            }

            // Prva kolona
            if (Main.MAPA[i][1] != null && !(Main.MAPA[i][1] instanceof LaserStit)) {
                if (Main.MAPA[i][1] instanceof SvemirskiBrod) {
                    System.out.println("BespilotnaSonda " + this.id + " je unistila SvemirskiBrod na lokaciji [" + i + "]" + "[1]");
                } else if (Main.MAPA[i][1] instanceof MatricnaStanica) {
                    System.out.println("BespilotnaSonda " + this.id + " je unistila MatricnuStanicu na lokaciji [" + i + "]" + "[1]");
                }
                pilotnaLetjelica = (PilotnaLetjelica) Main.MAPA[i][0];
            }

            // Druga kolona
            if (Main.MAPA[i][2] != null && !(Main.MAPA[i][2] instanceof LaserStit)) {
                if (Main.MAPA[i][2] instanceof SvemirskiBrod) {
                    System.out.println("BespilotnaSonda " + this.id + " je unistila SvemirskiBrod na lokaciji [" + i + "]" + "[2]");
                } else if (Main.MAPA[i][1] instanceof MatricnaStanica) {
                    System.out.println("BespilotnaSonda " + this.id + " je unistila MatricnuStanicu na lokaciji [" + i + "]" + "[2]");
                }
                pilotnaLetjelica = (PilotnaLetjelica) Main.MAPA[i][0];
            }

            // Gasenje niti
            if (pilotnaLetjelica != null) {
                pilotnaLetjelica.ugasiLetjelicu();
            }
            }
        }
    }

    @Override
    public void run() {
        int i;

        synchronized(Main.lock) {
            i = Main.DUZINA_MAPE - 1;
        }

        for (; i >= 0; i--) {
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
                Main.MAPA[this.red][this.kolona] = null;
            }

            System.out.println(this);

            try {
                sleep(1000);
                this.vrijeme += 1000;
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            //this.detektujLetjelice();
        }

        this.ziva = false;

        System.out.println(this + " JE ZAVRSILA KRETANJE!");
    }

    @Override
    public String toString() {
        return "BespilotnaSonda{id=" + this.id + ", red=" + this.red + ", kolona=" + this.kolona + ", senzor=" + this.senzor + ", laser=" + this.laser + "}";
    }
}