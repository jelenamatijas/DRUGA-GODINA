package elementi;

public abstract class Element {
    private int jacina;

    public Element(int jacina){
        this.jacina = jacina;
    }

    public int getJacina(){
        return jacina;
    }

    public void setJacina(int jacina){
        this.jacina = jacina;
    }
}
