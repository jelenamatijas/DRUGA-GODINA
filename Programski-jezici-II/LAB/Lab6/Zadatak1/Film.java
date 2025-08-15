import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.util.Arrays;

public class Film{
    private static Integer MAKSIMALNA_DUZINA = 6;
    private String naziv;
    private Integer godinaObjavljivanja;
    private String glumci[];
    private double prosjecnaOcjena;

    Film(){
        super();
    }
    Film(String naziv, Integer godinaObjavljivanja, String []glumci, double prosjecnaOcjena){
        super();
        this.naziv = naziv;
        this.godinaObjavljivanja = godinaObjavljivanja;
        this.prosjecnaOcjena = prosjecnaOcjena;
        this.glumci = new String[Integer.min(MAKSIMALNA_DUZINA, glumci.length)];
        for(int i=0; i < Integer.min(MAKSIMALNA_DUZINA, glumci.length); i++){
            this.glumci[i] = glumci[i];
        }
    }

    @Override
    public String toString(){
        String s = "";
        for(String glumac : glumci){
            s+=glumac + ", ";
        }
        return "Film: " + naziv + "\nGodina objavljivanja: " + godinaObjavljivanja
                + "\nProsjecna ocjena: " + prosjecnaOcjena + "\nGlumci: " + s;
    }

    public boolean daLiUFilmuGlumiGlumac(String glumac){
        for(String g: glumci){
            if(g != null && g.compareTo(glumac) == 1){
                return true;
            }
        }
        return false;
    }

    public boolean daLiUFilmuGlumiGlumacCaseInsensitive(String glumac){
        for(String g: glumci){
            if(g.compareToIgnoreCase(glumac) == 1){
                return true;
            }
        }
        return false;
    }

    public Integer kolikoJeStarFilm(){
        return LocalDate.now().getYear() - godinaObjavljivanja;
    }

    public boolean daLiNazivFilmaSadrziRijec(String rijec){
        return naziv.contains(rijec);
    }

    public String getNaziv(){
        return naziv;
    }

    public double getProsjecnaOcjena(){
        return prosjecnaOcjena;
    }
}