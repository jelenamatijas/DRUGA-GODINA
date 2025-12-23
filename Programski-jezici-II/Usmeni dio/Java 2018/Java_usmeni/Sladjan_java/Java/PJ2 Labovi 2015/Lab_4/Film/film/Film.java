package film;

import java.util.Arrays;
import java.util.Calendar;

/**
 *
 * @author Igor
 */
public class Film {

    private String naziv;
    private int godinaObjavljivanja;
    private String[] glumci = new String[6];
    private double prosjecnaOcjena;

    private final String EXCEPTION_PORUKA = "Broj glumaca ne moze biti veci od 6";
    public Film() {
    }

    public Film(String naziv, int godinaObjavljivanja, double prosjecnaOcjena, String[] glumci) throws Exception{
      if(glumci!=null && glumci.length>6) throw new Exception(EXCEPTION_PORUKA);
        this.naziv = naziv;
        this.godinaObjavljivanja = godinaObjavljivanja;
        this.prosjecnaOcjena = prosjecnaOcjena;
        this.glumci = glumci;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getGodinaObjavljivanja() {
        return godinaObjavljivanja;
    }

    public void setGodinaObjavljivanja(int godinaObjavljivanja) {
        this.godinaObjavljivanja = godinaObjavljivanja;
    }

    public String[] getGlumci() {
        return glumci;
    }

    public void setGlumci(String[] glumci) throws Exception{
      if(glumci!=null &&  glumci.length>6)throw new Exception(EXCEPTION_PORUKA);
        this.glumci = glumci;
    }

    public double getProsjecnaOcjena() {
        return prosjecnaOcjena;
    }

    public void setProsjecnaOcjena(double prosjecnaOcjena) {
        this.prosjecnaOcjena = prosjecnaOcjena;
    }

    @Override
    public String toString() {
        String g= new String();
        for(String s : glumci){
            g+=s+", ";
        }
        return "Film: " + naziv + "\nGodina objavljivanja: " + godinaObjavljivanja
                + "\nProsjecna ocjena: " + String.format( "%.2f", prosjecnaOcjena) + "\nGlumci: " + g;
    }

    public boolean daLiUFilmuGlumiGlumac(String glumac) {
        for (String s : glumci) {
            if (s.equals(glumac)) {
                return true;
            }
        }
        return false;
    }

    public boolean daLiUFilmuGlumiGlumacCaseInsensitive(String glumac) {
        for (String s : glumci) {
          //if(s.equalsIgnoreCase(glumac)) ili
            if (s.toLowerCase().equals(glumac.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public boolean daLiNazivFilmaSadrziRijec(String s) {
        return naziv.contains(s);
    }

    public int kolikoJeStarFilm() {
        return Calendar.getInstance().get(Calendar.YEAR) - godinaObjavljivanja;
    }

}
