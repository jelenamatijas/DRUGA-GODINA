package pismeni.R_2022_06_29.Z01;

public abstract class Letjelica extends Thread {

    String model;
    String oznaka;
    static volatile int count = 0;
    boolean transponder = true;
    boolean srusena = false;
    int red = 0;
    int brzina;
    int endPos = Simulacija.DIM;

    public Letjelica() {
        synchronized (this) {
            model = "Model" + count;
            oznaka = "Oznaka" + count++;
        }
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < endPos; i++) {
                if (srusena) {
                    System.out.println("Letjelica je srusena");
                    break;
                }
                if (this instanceof Civilni && i == endPos) {
                    i = endPos;
                    endPos = 0;
                }
                Simulacija.poruke.put(oznaka + " leti na poziciji" + red + "," + i);
                System.out.println(oznaka + " leti na poziciji " + red + "," + i);
                Thread.sleep(brzina * 1000L);
                Simulacija.mapa[red][i] = this;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Letjelica " + oznaka + " je zavrsila kretanje");
    }
}