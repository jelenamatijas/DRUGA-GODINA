public class Cjenovnik {
    Proizvod proizvodi[];

    Cjenovnik(){
        this.proizvodi = new Proizvod[0];
    }

    Cjenovnik(Proizvod [] proizvodi){
        this.proizvodi = proizvodi;
    }

    public void dodajProizvod(Proizvod p){
        Proizvod newProizvodi[] = new Proizvod[proizvodi.length+1];
        for (int i=0 ; i < proizvodi.length; i++) {
            newProizvodi[i] = proizvodi[i];
        }
        newProizvodi[proizvodi.length] = p;
        this.proizvodi = newProizvodi;
    }

    public void ispisProizvoda() {
        for (Proizvod proizvod : proizvodi) {
            System.out.println(proizvod);
        }
    }


    public void ukloniProizvod(String sifra) throws NepostojeciProizvodException{
        Proizvod newProizvodi[] = new Proizvod[proizvodi.length-1];
        int j=0;
        if(getProizvodSaSifrom(sifra)!=null){
            for(int i=0; i<proizvodi.length; i++){
                if(!proizvodi[i].sifra.equals(sifra)){
                    newProizvodi[j] = proizvodi[i];
                    j++;
                }
            }
            this.proizvodi = newProizvodi;
        }
        else{
            throw new NepostojeciProizvodException("Ne postoji proizvod sa sifrom: " + sifra + ", te nije bilo omoguceno brisanje istog s racuna!");
        }
        
        
    }

    public void pretragaProizvodaPoNazivu(String naziv)throws NepostojeciProizvodException{
        Proizvod p = new Proizvod();
        boolean proizvodPronadjen = false;
        for (Proizvod proizvod : proizvodi) {
            if(naziv.equals(proizvod.getNaziv())){
                p = proizvod;
                proizvodPronadjen = true;
            }
        }
        if(proizvodPronadjen){
            System.out.println(p);
        }else{
            throw new NepostojeciProizvodException("Ne postoji proizvod pod nazivom: " + naziv);
        }
    }

    public void pretragaProizvodaPoSifri(String sifra)throws NepostojeciProizvodException{
        Proizvod p = null;
        for (Proizvod proizvod : proizvodi) {
            if(sifra.equals(proizvod.getSifra())){
                p = proizvod;
            }
        }
        if(p != null){
            System.out.println(p);
        }else{
            throw new NepostojeciProizvodException("Ne postoji proizvod pod sifrom: " + sifra);
        }
    }

    public Proizvod getProizvodSaSifrom(String sifra)throws NepostojeciProizvodException{
         Proizvod p = null;
        for (Proizvod proizvod : proizvodi) {
            if(sifra.equals(proizvod.getSifra())){
                p = proizvod;
            }
        }
        if(p != null){
            return p;
        }else{
            throw new NepostojeciProizvodException("Ne postoji proizvod pod sifrom: " + sifra);
        }
    }

    @Override
    public String toString(){
        String string = "";
        for (Proizvod proizvod : proizvodi) {
            string += proizvod.toString() + "\n";
        }
        return string;
    }

}
