public class Planinar extends Osoba implements PenjanjeInterface, GasenjePozaraInterface{
    
    public Planinar (String ime, String visina, String oprema, int redNaMapi){
        super(ime, visina, oprema, redNaMapi);
    }

    @Override
    public Object penjanje(){
        return null;
    }

    @Override
    public Object gasenjePozara(){
        return "Pozar ugasen.";
    }
}
