package pismeni.R_2021_06_16.Z01;

public abstract class Plovilo extends Thread {
    public String identifikator;
    public int x, y;
    public long pocetak;

    public Plovilo(String identifikator, int x, int y) {
        this.identifikator = identifikator;
        this.x = x;
        this.y = y;
    }

    @Override
    public void run() {
        pocetak = System.currentTimeMillis();
        try {
            while (true) {
                int newX = x + 1;
                if (newX == Mapa.X)
                    return;
                //synchronized(Mapa.mapa)
                //{
                Mapa.mapa[x][y] = null;
                Mapa.mapa[newX][y] = this;
                x = newX;
                System.out.println("Brod " + x + " " + y);
                sleep(1000);
                //}
            }
        } catch (InterruptedException e) {
        }
    }
}