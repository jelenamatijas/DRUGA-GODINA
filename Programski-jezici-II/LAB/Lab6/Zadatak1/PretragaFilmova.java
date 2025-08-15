import java.util.Random;
import java.util.Scanner;

public class PretragaFilmova {

    public static void main(String args[]){

        String glumci[] = {"Leonardo Di Caprio", "Kejt Vinslet", "Bili Zejn", "Keti Bejs", "Bil Pakston",
                            "Majk Majers", "Edi Marfi", "Tom Henks", "Robin Rajt", "Geri Siniz", "Sali Fild",
                            "Džozef Gordon-Levit", "Elen Pejdž", "Tom Hardi", "Paton Oswalt", "Ian Holm", "Lu Romano",
                            "Tim Robins", "Morgan Frimen", "Albert Bruks", "Elen Dedženeris", "Alexander Gould",
                            "Marlon Brando", "Al Paćino", "Džejms Kan"};
        String rijeci[] = {"Titanik", "Shrek", "Forrest", "Gump", "Inception", "Ratatouille", "The", "Shawshank", "Redemption",
                           "Finding", "Nemo", "The", "Godfather"};
        
        Film[] filmovi = {
            new Film("Titanik", 2000, new String[]{"Leonardo Di Caprio", "Kejt Vinslet", "Bili Zejn", "Keti Bejs", "Bil Pakston"}, 8.9),
            new AnimiraniFilm("Shrek", 2001, new String[]{"Majk Majers", "Edi Marfi"}, 9.1, "Andrew Adamson"),
            new Film("Forrest Gump", 1994, new String[]{"Tom Henks", "Robin Rajt", "Geri Siniz", "Sali Fild"}, 9.0),
            new Film("Inception", 2010, new String[]{"Leonardo Di Caprio", "Džozef Gordon-Levit", "Elen Pejdž", "Tom Hardi"}, 8.8),
            new AnimiraniFilm("Ratatouille", 2007, new String[]{"Paton Oswalt", "Ian Holm", "Lu Romano"}, 8.1, "Brad Bird"),
            new Film("The Shawshank Redemption", 1994, new String[]{"Tim Robins", "Morgan Frimen"}, 9.3),
            new AnimiraniFilm("Finding Nemo", 2003, new String[]{"Albert Bruks", "Elen Dedženeris", "Alexander Gould"}, 8.2, "Andrew Stanton"),
            new Film("The Godfather", 1972, new String[]{"Marlon Brando", "Al Paćino", "Džejms Kan"}, 9.2)
        };

        System.out.println("------------- Podaci o filmovima: -------------\n\n");
        for (Film film : filmovi) {
            System.out.println(film.getNaziv());
            System.out.println(film);
            if (film instanceof AnimiraniFilm) {
                AnimiraniFilm af = (AnimiraniFilm) film;
                System.out.println(af.crtacUnazad());
            }

            int brGlumaca = glumci.length;
            Random rand = new Random();
            String glumac = glumci[rand.nextInt(brGlumaca)];

            if(film.daLiUFilmuGlumiGlumac(glumac)){
                System.out.println("Glumac " + glumac + " glumi u filmu " + film.getNaziv() + ".");
            }else{
                System.out.println("Glumac " + glumac + " ne glumi u filmu " + film.getNaziv() + ".");
            }

            if(film.daLiUFilmuGlumiGlumacCaseInsensitive(glumac.toLowerCase())){
                System.out.println("Glumac " + glumac + " glumi u filmu " + film.getNaziv() + ".");
            }else{
                System.out.println("Glumac " + glumac + " ne glumi u filmu " + film.getNaziv() + ".");
            }

            System.out.println("Film je sniman prije " + film.kolikoJeStarFilm() + " godina.");

            int brRijeci = rijeci.length;
            String rijec = rijeci[rand.nextInt(brRijeci)];
            if(film.daLiNazivFilmaSadrziRijec(rijec)){
                System.out.println("Rijec " + rijec + " se nalazi u nazivu filma " + film.getNaziv() + ".");
            }else{
                System.out.println("Rijec " + rijec + " se ne nalazi u nazivu filma " + film.getNaziv() + ".");
            }

            System.out.println("-----------------------------------------------");
        }

        System.out.println("\n\n------------- Pretraga filmova: -------------\n\n");
        System.out.println("Unesi kriterijum za pretragu filma: ");
        Scanner scanner = new Scanner(System.in);
        String kriterijum = scanner.nextLine();
        String znakPoredjenja = (kriterijum.contains("veci") ? "veci" : "manji");
        double ocjena = Double.parseDouble(kriterijum.replaceAll("\\D+",""));

        boolean imaFilm = false;
        for(Film film : filmovi){
            double prosjecnaOcjena = film.getProsjecnaOcjena();
            if(kriterijum.contains("veci") && (prosjecnaOcjena>ocjena)){
                if(film instanceof AnimiraniFilm){
                    AnimiraniFilm af = (AnimiraniFilm) film;
                    System.out.println(af);
                }else{
                    System.out.println(film);
                }
                imaFilm = true;
                System.out.println("-----------------------------------------------");
            }else if(kriterijum.contains("manji") && (prosjecnaOcjena<ocjena)){
                if(film instanceof AnimiraniFilm){
                    AnimiraniFilm af = (AnimiraniFilm) film;
                    System.out.println(af);
                }else{
                    System.out.println(film);
                }
                imaFilm = true;
                System.out.println("-----------------------------------------------");
            }
            
        }

        if(!imaFilm){
            System.out.println("Ne postoji odgovarajuci film.");
        }
        scanner.close();

    }
    
}
