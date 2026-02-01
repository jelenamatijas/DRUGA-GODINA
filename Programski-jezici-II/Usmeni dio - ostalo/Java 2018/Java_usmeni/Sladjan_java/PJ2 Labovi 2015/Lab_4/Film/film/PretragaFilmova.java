package film;

import java.util.Scanner;

/**
 *
 * @author Igor
 */
public class PretragaFilmova {

    public static void main(String[] args) {
        //kreiranje filmova
      Film[] filmovi = new Film[0];
      try{
        filmovi = new Film[]{
            new Film("Gone in Sixty Seconds", 2000, 6.4, new String[]{"Nicolas Cage", "Angelina Jolie", "Giovanni Ribisi"}),
            new Film("Star Wars: Episode IV - A New Hope", 1977, 8.7, new String[]{
                "Mark Hamill", "Harrison Ford", "Carrie Fisher"}),
            new AnimiraniFilm("William Steig", "Shrek the Third", 2007, 6.0, new String[]{"Mike Myers", "Cameron Diaz", "Eddie Murphy"})
        };
      }catch(Exception e){
        System.out.println(e);
      }

        //prikaz svih filmova
        for (Film f : filmovi) {
            System.out.println(f);
            System.out.println("Prva rijec naziva filma: " + f.getNaziv().split(" ")[0]);
            if (f instanceof AnimiraniFilm) {
                System.out.println(((AnimiraniFilm) f).crtacUnazad());
            }
            System.out.println("Da li naziv filma sadrzi rijec the:" + f.daLiNazivFilmaSadrziRijec("the"));
            System.out.println("Da li u filmu glumi Harrison Ford: " + f.daLiUFilmuGlumiGlumac("Harrison Ford"));
            System.out.println("Da li u filmu glumi HarrIson FOrD: " + f.daLiUFilmuGlumiGlumac("HarrIson FOrD"));
            System.out.println("Da li u filmu glumi haRRison FoRD: " + f.daLiUFilmuGlumiGlumacCaseInsensitive("haRRison FoRD"));
            System.out.println("Koliko je star film: " + f.kolikoJeStarFilm());
            System.out.println("----------------------------");
        }

        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Opcije");
            System.out.println("veci od broj");
            System.out.println("manji od broj");

            boolean loop = true;
            while (loop) {
                String opcija = scan.nextLine();
                try {

                    if (opcija.contains("veci od") || opcija.contains("manji od")) {
                        double vrijednost = Double.parseDouble(opcija.split(" ")[2]);
                        String operacija = opcija.split(" ")[0];
                        switch (operacija) {
                            case "manji":
                                for (Film f : filmovi) {
                                    if (f.getProsjecnaOcjena() < vrijednost) {
                                        System.out.println(f + "\n");
                                    }
                                }
                                break;
                            case "veci":
                                for (Film f : filmovi) {
                                    if (f.getProsjecnaOcjena() > vrijednost) {
                                        System.out.println(f + "\n");
                                    }
                                }
                                break;
                        }

                    }
                    loop = false;
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                    System.out.println(ex);
                }

            }

        }

    }
}
