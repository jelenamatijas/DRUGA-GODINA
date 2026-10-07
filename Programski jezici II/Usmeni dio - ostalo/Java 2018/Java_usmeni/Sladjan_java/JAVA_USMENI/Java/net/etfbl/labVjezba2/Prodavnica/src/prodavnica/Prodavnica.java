

package prodavnica;

import java.util.Scanner;


public class Prodavnica {

    
    public static void main(String[] args) throws Exception {
       
        Proizvodjac pro1 = new Proizvodjac("Philips","Japan","philips@japan.com");
        Proizvodjac pro2 = new Proizvodjac("Appel","USA","office@apple.com");
        Racunari rac=new Racunari();
        Racunari rac1=new Racunari("rac1",500.99,"Air1",pro2,"Veoma dobra masina");
        Telefoni tel=new Telefoni("tel1",100,"Galaxy",pro1,"1GB RAM, 1GHz CPU","Smoll");
        Monitori mon=new Monitori("mon1",100,"Glass",pro1,"22 inch","22");
        Softveri sof=new Softveri("sof1",400,"DocCreator",pro2,"Sluzi da kreidanje dokumenata");
        Scanner sc=new Scanner(System.in);
        int ulaz=0;
        Racun racun= new Racun();
        
        do
        {
            System.out.println("0-kupovina\n 1-ponuda\n2-pretragaPoNazivu\n3-kupovinaPrizvoda\n4-Pregled kupljenih prizvoda\n5-brisanjeKupljenog");
            ulaz=sc.nextInt();
            switch(ulaz)
            {
                case 0: ulaz=0;break;
                case 1: Cjenovnik.prikazProizvoda();
                    break;
                case 2: pretraga();
                    break;
                case 3: kupovina(racun);
                    break;
                case 4: racun.pregledProizvoda();
                    break;
                case 5: racun.izbrisatiProizvod();
                    break;
                default: System.out.println("Niste unijeli valjanu komandu!\n ");
                    ulaz=1;
            }
            
        }while (ulaz!=0);
        racun.zakljucaj();
        new AnalizaProdaje();
    }

    private static void pretraga() {
        Scanner sc=new Scanner (System.in);
        System.out.println("Unesite naziv prizvoda");
        System.out.println(Cjenovnik.pretragaPoNazivu(sc.next()));
    }

    private static void kupovina(Racun racun) {
       Scanner sc=new Scanner (System.in);
       System.out.println("Unesite naziv ili sifri proizvoda kojeg zelite kupiti");
       String s=sc.next();
       racun.dodatiProizvod(s);

        }
    
}
