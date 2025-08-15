public class Plivac extends Osoba implements PlivanjeInterface, PenjanjeInterface{
    
    public Plivac(String ime, String visina, String oprema, int redNaMapi){
        super(ime, visina, oprema, redNaMapi);
    }

    @Override
    public Object penjanje(){
        return null;
    }

    @Override
    public Object plivanje(){
        return null;
    }
}
