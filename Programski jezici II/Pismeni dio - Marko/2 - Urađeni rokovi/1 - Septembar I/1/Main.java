import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static Object[][] MAPA;
    public static Map<Integer, SvemirskaLetjelica> mapaLetjelica = new HashMap<>();

    private static Random random = new Random();
    private static int id = 1;

    public static int DUZINA_MAPE;
    public static Object lock = new Object();
    public static boolean cekanje = false;

    private static void postavljanjeLetjelica() {
        int polovinaBrojaRedova = DUZINA_MAPE / 2;

        // Postavljanje svemirskih brodova
        for (int i = 0; i < 2; ) {
            int randomRed = random.nextInt(polovinaBrojaRedova);
            int randomKolona = random.nextInt(3);
            if (MAPA[randomRed][randomKolona] == null) {
                MAPA[randomRed][randomKolona] = new SvemirskiBrod(id, randomKolona,"NS " + id, new Laser(id), new Raketa(random.nextInt(10), random.nextInt(10)));
                SvemirskaLetjelica svemirskaLetjelica = (SvemirskaLetjelica) MAPA[randomRed][randomKolona];
                mapaLetjelica.put(id++, svemirskaLetjelica);
                i++;
            }
        }

        // Postavljanje matricnih stanica
        for (int i = 0; i < 2; ) {
            int randomRed = random.nextInt(polovinaBrojaRedova);
            int randomKolona = random.nextInt(3);
            if (MAPA[randomRed][randomKolona] == null) {
                MAPA[randomRed][randomKolona] = new MatricnaStanica(id, randomKolona, "NS " + id, new Laser(id), new Raketa(random.nextInt(10), random.nextInt(10)));
                SvemirskaLetjelica svemirskaLetjelica = (SvemirskaLetjelica) MAPA[randomRed][randomKolona];
                mapaLetjelica.put(id++, svemirskaLetjelica);
                i++;
            }
        }

        // Postavljanje bespilotnih sondi
        for (int i = 0; i < 2; ) {
            int randomRed = random.nextInt(polovinaBrojaRedova + 1) + polovinaBrojaRedova;
            int randomKolona = random.nextInt(3);
            if (MAPA[randomRed][randomKolona] == null) {
                MAPA[randomRed][randomKolona] = new BespilotnaSonda(id, randomKolona, "Senzor " + id, new Laser(random.nextInt(id)));
                SvemirskaLetjelica svemirskaLetjelica = (SvemirskaLetjelica) MAPA[randomRed][randomKolona];
                mapaLetjelica.put(id++, svemirskaLetjelica);
                i++;
            }
        }
    }

    public static void pokretanjeLetjelica() {
        for (Map.Entry<Integer, SvemirskaLetjelica> entry : mapaLetjelica.entrySet()) {
            SvemirskaLetjelica letjelica = entry.getValue();

            letjelica.start();
        }
    }

    private static boolean nisuSveZavrsile() {
        for (Map.Entry<Integer, SvemirskaLetjelica> entry : mapaLetjelica.entrySet()) {
            SvemirskaLetjelica letjelica = entry.getValue();

            if (letjelica.jeLiZiva()) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws CommandNotValidException {
        if (args.length != 1 || Integer.parseInt(args[0]) < 20 || Integer.parseInt(args[0]) > 40) {
            System.out.println("GRESKA: args.length != 1 ILI args[0] < 20 ILI args[0] > 40");
            return;
        }

        DUZINA_MAPE = Integer.parseInt(args[0]);

        MAPA = new Object[DUZINA_MAPE][3];

        postavljanjeLetjelica();

        Scanner scanner = new Scanner(System.in);
        String unos = "";

        pokretanjeLetjelica();

        while (nisuSveZavrsile()) {
            unos = scanner.nextLine();

            if (unos.startsWith("INFO")) {
                int id = Integer.parseInt(unos.split(" ")[1]);
                System.out.println("ID: " + id + " RED: " + mapaLetjelica.get(id).getRed() + " KOLONA: " + mapaLetjelica.get(id).getKolona());
            } else if (unos.startsWith("TIME")) {
                int id = Integer.parseInt(unos.split(" ")[1]);
                System.out.println("ID: " + id + " VRIJEME: " + mapaLetjelica.get(id).getVrijeme() + "ms");
            } else if (unos.startsWith("WAIT")) {
                cekanje = true;
            } else if (unos.startsWith("NOTIFY")) {
                if (cekanje) {
                    synchronized(lock) {
                        cekanje = false;
                        lock.notifyAll();
                    }
                } else {
                    throw new CommandNotValidException();
                }
            }
        }

        for (Map.Entry<Integer, SvemirskaLetjelica> entry : mapaLetjelica.entrySet()) {
            SvemirskaLetjelica letjelica = entry.getValue();
            try {
                letjelica.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        scanner.close();

        System.out.println("\nSIMULACIJA ZAVRSENA!");
    }
}