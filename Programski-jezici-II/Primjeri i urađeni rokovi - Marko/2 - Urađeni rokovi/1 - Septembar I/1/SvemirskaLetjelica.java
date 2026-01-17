public abstract class SvemirskaLetjelica extends Thread {

    protected int id;
    protected int red;
    protected int kolona;

    protected boolean ziva = true;

    protected long vrijeme = 0;
    
    public SvemirskaLetjelica(int id, int kolona) {
        this.id = id;
        this.kolona = kolona;
    }

    public boolean jeLiZiva() {
        return this.ziva;
    }

    public int getRed() {
        return this.red;
    }

    public int getKolona() {
        return this.kolona;
    }

    public long getVrijeme() {
        return this.vrijeme;
    }
}