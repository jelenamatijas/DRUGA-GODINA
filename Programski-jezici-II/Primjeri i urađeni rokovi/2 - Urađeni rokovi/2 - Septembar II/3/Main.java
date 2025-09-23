import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;

public class Main {

    public static Object lockObject = new Object();

    public static int dodanihKnjiga = 0;
    public static int obrisanihKnjiga = 0;

    public static Stack<Knjiga> stek = new Stack<>();

    public static long VRIJEME_IZVRSAVANJA = 20000; // milisekunde

    public static void main(String[] args) {

        DodavanjeNit dodavanjeNit = new DodavanjeNit();
        UklanjanjeNit uklanjanjeNit = new UklanjanjeNit();

        dodavanjeNit.start();
        uklanjanjeNit.start();

        long startTime = System.currentTimeMillis();

        while ((System.currentTimeMillis() - startTime) < VRIJEME_IZVRSAVANJA) {
            
        }

        dodavanjeNit.setKrajVremena();
        uklanjanjeNit.setKrajVremena();

        try {
            dodavanjeNit.join();
            dodavanjeNit.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        System.out.println("\nDODANIH KNJIGA = " + dodanihKnjiga);
        System.out.println("\nOBRISANIH KNJIGA = " + obrisanihKnjiga);

        File txtFajl = new File("knjigeSaSteka.txt");
        try (PrintWriter pw = (new PrintWriter(txtFajl))) {
            while (!stek.isEmpty()) {
                pw.println(stek.pop());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}