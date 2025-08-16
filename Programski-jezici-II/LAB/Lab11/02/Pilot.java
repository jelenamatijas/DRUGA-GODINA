public class Pilot extends Takmicar implements SavladajVatruInterface, SavladajKamenInterface {
    
    public Pilot(String ime){
        super(ime);
        pomjeraj = 2;
        vrijemeCekanja = 3000;
    }
}
