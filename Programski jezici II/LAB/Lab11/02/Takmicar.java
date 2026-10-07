import java.util.Random;

public class Takmicar extends Thread{
    private String ime;
    private int brojBodova = 0;
    private int pozicija = 0;
    protected int vrijemeCekanja = 2000;
    protected int pomjeraj = 1;
    private int brzinaKretanja = (new Random().nextInt(1)+1)*1000;

    public Takmicar(String ime){
        this.ime = ime;
    }

    public String getIme(){
        return ime;
    }

    public void setIme(String ime){
        this.ime = ime;
    }
    
    public Integer getBrojBodova(){
        return brojBodova;
    }

    public void setBrojBodova(int brojBodova){
        this.brojBodova = brojBodova;
    }

    public int getPozicija(){
        return pozicija;
    }

    public void setPozicija(int pozicija){
        this.pozicija = pozicija;
    }
    public int getPomjeraj(){
        return pomjeraj;
    }

    public void setPomjeraj(int pomjeraj){
        this.pomjeraj = pomjeraj;
    }


    public void obradiBonus(){
        synchronized(Mapa.mapa[pozicija]){
            Bonus bonus = (Bonus)Mapa.mapa[pozicija].getElement();
            brojBodova += bonus.getJacina();
            Mapa.mapa[pozicija].setElement(null);
            System.out.println("Takmicar: " + this + " je pokupio bonus " + bonus.getJacina());
        }
    }

    public void obradiPrepreku(Prepreka p){
        brojBodova -= p.getJacina();
        try{
            sleep(vrijemeCekanja);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return "Ime: " + ime + " Pozicija: " + pozicija + " Broj bodova: " + brojBodova;
    }

    @Override
    public void run(){
        for(int i=0; i<50; i+=pomjeraj){
            pozicija = i;
            if(Mapa.mapa[i].getElement() instanceof Bonus){
                obradiBonus();
            }else if(Mapa.mapa[i].getElement() instanceof Prepreka){
                Prepreka p = (Prepreka)Mapa.mapa[i].getElement();
                if(p instanceof Voda && !(this instanceof SavladajVoduIntereface)){
                    ((Voda) p).poplavi();
                    obradiPrepreku(p);
                }

                if(p instanceof Vatra && !(this instanceof SavladajVatruInterface)){
                    ((Vatra) p).gori();
                    obradiPrepreku(p);
                }

                if(p instanceof Kamen && !(this instanceof SavladajKamenInterface)){
                    ((Kamen) p).obrusiSe();
                    obradiPrepreku(p);
                }
            }else{
                try{
                    sleep(brzinaKretanja);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

            System.out.println("Takmicar: " + this);
        }

        System.out.println("Takmicar " + this + " je zavrsio kretanje.");
    }
}
