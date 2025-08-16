public class Pjesak extends Takmicar implements SavladajVoduIntereface, SavladajKamenInterface{

    public Pjesak(String ime){
        super(ime);
    }

    @Override
    public void obradiBonus(){
        super.obradiBonus();
        setPozicija(getPozicija() + 3);
    }
}
