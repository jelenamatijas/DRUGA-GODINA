public class Vatrogasac extends Osoba implements GasenjePozaraInterface, PenjanjeInterface{
 
    public Vatrogasac (String ime, String visina, String oprema, int redNaMapi){
        super(ime, visina, oprema, redNaMapi);
    }

    @Override
    public Object gasenjePozara(){
        return "Pozar ugasen.";
    }

    @Override
    public Object penjanje(){
        return null;
    }
}
