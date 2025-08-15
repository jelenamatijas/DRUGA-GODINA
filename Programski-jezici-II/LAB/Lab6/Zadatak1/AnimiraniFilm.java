public class AnimiraniFilm extends Film{
    private String crtac;
    AnimiraniFilm(){
        super();
    }
    AnimiraniFilm(String naziv, Integer godinaObjavljivanja, String []glumci, double prosjecnaOcjena, String crtac){
        super(naziv, godinaObjavljivanja, glumci, prosjecnaOcjena);
        this.crtac = crtac;
    }

    @Override
    public String toString(){
        return super.toString() + "\nCrtac: " + crtac;
    }

    public String crtacUnazad(){
        StringBuffer sf = new StringBuffer(crtac);
        return sf.reverse().toString();
    }
}
