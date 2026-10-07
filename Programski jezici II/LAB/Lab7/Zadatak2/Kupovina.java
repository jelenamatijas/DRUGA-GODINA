import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.io.ObjectInputStream;


public class Kupovina {
    
    public static void main(String args[]){
        Proizvodjac proizvodjaci[] = {
            new Proizvodjac("HP", "Njemacka", "hp@email.com", Status.AKTIVAN),
            new Proizvodjac("Nokia", "Japan", "lenovo@japan.yahoo", Status.AKTIVAN)
        };
        Proizvod proizvodi[] = { 
            new Racunar("1111", 1230.15, "HP Victus", proizvodjaci[0], "R5, 8GB RAM, 512 SSD"),
            new Monitor("2222", 546.28, "Lenovo 2800", proizvodjaci[1], "Full HD", "v1.03"),
            new Telefon("3333",120.28, "Lenovo mobile", proizvodjaci[1], "Toucsh screen", "v1.03" ),
            new Softver("4444",200.15, "HP office", proizvodjaci[0], "Word, Excel, PP")
        };

        Cjenovnik cjenovnik = new Cjenovnik(proizvodi);

        Racun racun = new Racun();
        boolean krajKupovine = false;
        System.out.println("Kupovina je zapocela. Unesite odgovarajuci broj opcije ispod:");
        Scanner scanner = new Scanner(System.in);
        while(!krajKupovine){
            System.out.println("1. Prikaz cjenovnika\n2. Pretraga po nazivu proizvoda");
            System.out.println("3. Pretraga po sifri proizvoda\n4. Kupovina proizvoda");
            System.out.println("5. Pregled svih kupljenih proizvoda\n6. Ukljanjanje proizvoda sa racuna");
            System.out.println("7. Zavrsetak kupovine\n8. Prikaz svih racuna i ukupan iznos");
            
            int opcija = -1;
            try {
                System.out.print("Unesite opciju: ");
                opcija = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Molimo unesite ispravan broj opcije.");
                continue; 
            }

            switch(opcija){
            case 1:
                System.out.println("\n-------------------------------------\n");
                cjenovnik.ispisProizvoda();
                System.out.println("\n-------------------------------------\n");
                break;
            case 2:
                System.out.println("\n-------------------------------------\n");
                try{
                    System.out.print("Unesite naziv proizvoda: ");
                    String naziv = scanner.nextLine().trim();
                    cjenovnik.pretragaProizvodaPoNazivu(naziv);
                    System.out.println("\n-------------------------------------\n");
                }catch(NepostojeciProizvodException e){
                    System.out.println(e.getMessage());
                }catch(Exception e){
                    e.getStackTrace();
                }
                break;
            case 3:
                System.out.println("\n-------------------------------------\n");
                try{
                    System.out.print("Unesite sifru proizvoda: ");
                    String sifra = scanner.nextLine().trim();
                    cjenovnik.pretragaProizvodaPoSifri(sifra);
                    System.out.println("\n-------------------------------------\n");
                }catch(NepostojeciProizvodException e){
                    System.out.println(e.getMessage());
                }catch(Exception e){
                    e.getStackTrace();
                }
                break;
            case 4:
                System.out.println("\n-------------------------------------\n");
                System.out.println("Unesi sifru proizvoda: ");
                String sifra = scanner.nextLine().trim();
                Proizvod p = new Proizvod();
                boolean proizvodPostoji = false;
                for (Proizvod proizvod : proizvodi) {
                    if(sifra.equals(proizvod.getSifra())){
                        p = proizvod;
                        proizvodPostoji = true;
                    }
                }
                if(proizvodPostoji){
                    racun.dodajProizvod(p);
                }else{
                    System.out.println("Proizvod sa sifrom: " + sifra + " ne postoji!");
                }
                System.out.println("\n-------------------------------------\n");
                break;
            case 5:
                System.out.println("\n-------------------------------------\n");
                racun.ispisProizvoda();
                System.out.println("\n-------------------------------------\n");
                break;
            case 6:
                System.out.println("\n-------------------------------------\n");
                try{
                    System.out.println("Unesi sifru proizvoda: ");
                    sifra = scanner.nextLine().trim();
                    racun.ukloniProizvod(sifra);
                }catch(NepostojeciProizvodException e){
                    System.out.println(e.getMessage());
                }catch(Exception e){
                    e.getStackTrace();
                }
                System.out.println("\n-------------------------------------\n");
                break;
            case 7:
                System.out.println("\n-------------------------------------\n");
                krajKupovine = true;
                if(racun.proizvodi.length>0){
                    racun.zakljuciKupovinu();
                
                    try{
                        File folder = new File("racuni");
                        if (!folder.exists()) {
                            folder.mkdirs(); 
                        }

                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy_hh.mm.ss");
                        String filename = sdf.format(new Date());
                        FileOutputStream pisac = new FileOutputStream("racuni/" + filename + ".etf");
                        ObjectOutputStream object = new ObjectOutputStream(pisac);
                        object.writeObject(racun);
                        object.close();
                        pisac.close();

                    }catch (FileNotFoundException e) {
                        System.out.println("Fajl nije pronadjen!");
                    }catch(IOException e){
                        e.printStackTrace();
                        System.out.println("IOException!");
                    }
                }
                System.out.print(racun);
                System.out.println("\n-------------------------------------\n");
                break;
            case 8:
                System.out.println("\n-------------------------------------\n");
                FileInputStream citac;
                File[] files = new File("racuni").listFiles();
                double iznos = 0.0;
                for(File f:files){
                    try{
                        citac = new FileInputStream(f);
                        ObjectInputStream objectInput = new ObjectInputStream(citac);
                        Racun r = (Racun) objectInput.readObject();
                        System.out.println("Racun: " + r.datumKupovine + "\nCijena: " + r.ukupnaCijena);
                        iznos += r.ukupnaCijena;
                        objectInput.close();
                        citac.close();
                    }catch(FileNotFoundException e){
                        System.out.println("Fajl nije pronadjen!");
                    }catch(Exception e){
                        e.printStackTrace();
                        System.out.println("Exception!");
                    }
                }
                System.out.println("Ukupan iznos: " + iznos);
                System.out.println("\n-------------------------------------\n");
                break;

            }
        }

        scanner.close();
    }
}
