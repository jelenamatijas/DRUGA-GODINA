public abstract class Predmet{
    protected double specificnaTezina;
    protected static int id;
    protected int identifikator;

    Predmet(double specificnaTezina){
        this.specificnaTezina = specificnaTezina;
        id++;
    }

    public abstract void print();
    public abstract void read()throws PredmetException;
    public abstract double zapremina();

    public double tezina(){
        return specificnaTezina * zapremina();
    }

    public static int poredjenje(Predmet p1, Predmet p2){
        return (p1.zapremina() > p2.zapremina()) ? 1 :((p1.zapremina() < p2.zapremina()) ? -1 : 0);
    }

    @Override
    public boolean equals(Object obj){
        if(obj != null && (obj instanceof Predmet)){
            return this.zapremina() == ((Predmet) obj).zapremina();
        }
        return false;
    }

    @Override
    public String toString(){
        return "Specificna tezina = " + specificnaTezina + ".\nZapremina = " + zapremina() 
                + ".\nIdentifikator = " + identifikator + ".\nTezina = " + tezina() + ".";
    }
}