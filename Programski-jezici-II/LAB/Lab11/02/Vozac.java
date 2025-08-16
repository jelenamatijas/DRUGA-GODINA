import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Vozac extends Takmicar implements SavladajVatruInterface{
    
    public Vozac(String ime){
        super(ime);
    }

    @Override
    public void obradiPrepreku(Prepreka p){
        super.obradiPrepreku(p);

        try{
            PrintWriter pw= new PrintWriter(new FileOutputStream("event.txt",  true));
            pw.println("Vozac " + this.getIme() + " nije mogao da savlada prepreku " + p.getClass().getName());
            pw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
