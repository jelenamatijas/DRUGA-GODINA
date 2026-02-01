package pismeni.R_2022_06_29.Z03;

public class AddThread extends Thread {

    Long postotak;

    public AddThread(Long postotak) {
        this.postotak = postotak;
        start();
    }

    @Override
    public void run() {
        try {
            while (!Simulacija.END) {
                // Pauza od pola sekunde
                Thread.sleep(500 * postotak);
                Student student = new Student();
                Simulacija.red.studenti.put(student);
                System.out.println("Dodan je: " + student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}