package film;

/**
 *
 * @author Igor
 */
public class AnimiraniFilm extends Film {

    private String crtac;

    public AnimiraniFilm(String crtac, String naziv, int godinaObjavljivanja, double prosjecnaOcjena, String[] glumci) throws Exception{
        super(naziv, godinaObjavljivanja, prosjecnaOcjena, glumci);
        this.crtac = crtac;
    }

    public String getCrtac() {
        return crtac;
    }

    public void setCrtac(String crtac) {
        this.crtac = crtac;
    }

    @Override
    public String toString() {
        return super.toString() + "\nCrtac: " + crtac;
    }

    public String crtacUnazad() {
        StringBuilder sb = new StringBuilder(crtac);
        return sb.reverse().toString();
    }

}
