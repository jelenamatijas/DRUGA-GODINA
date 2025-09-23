public class Raketa {
    
    private int xKoordinata;
    private int yKoordinata;

    public Raketa(int xKoordinata, int yKoordinata) {
        this.xKoordinata = xKoordinata;
        this.yKoordinata = yKoordinata;
    }

    @Override
    public String toString() {
        return "Raketa{xKoordinata=" + this.xKoordinata + ", yKoordinata=" + this.yKoordinata + "}";
    }
}