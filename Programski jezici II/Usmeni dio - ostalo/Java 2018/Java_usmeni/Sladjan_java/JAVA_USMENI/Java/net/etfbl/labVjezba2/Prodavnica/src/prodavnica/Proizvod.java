

package prodavnica;

import java.io.Serializable;


public abstract class Proizvod implements Serializable{
    public String sifra="";
    public double cijena=0;
    public String naziv="";
    //public String proizvodjac="";
    public static Proizvod[] proizvodi;
    public Proizvodjac proizvodjac;
    
    public Proizvod(String sifra,double cijena, String naziv, Proizvodjac pr)
    {
        this.sifra=sifra;
        this.cijena=cijena;
        this.naziv=naziv;
        //this.proizvodjac=proizvodjac;
        proizvodjac=pr;
        int brojProizvoda=0;
        if(Proizvod.proizvodi!=null) brojProizvoda=Proizvod.proizvodi.length;
        Proizvod[] saNovim=new Proizvod[brojProizvoda+1];
        for (int i=0;i<brojProizvoda;i++)
            saNovim[i]=proizvodi[i];
        saNovim[brojProizvoda]=this;
        proizvodi=saNovim;
    }
    public Proizvod()
    {
        this("NoSifra",0,"NoName",new Proizvodjac());
    }
    
    
    @Override
    public String toString()
    {
        return ("Naziv:"+naziv+"\nCijena:"+cijena+"\nProizvodjac:2"
                + " "+proizvodjac);
    }
    

    @Override
    public boolean equals(Object obj)
    {
        Proizvod pr=(Proizvod)obj;
        return this.sifra==pr.sifra;
    }
   
    
}
