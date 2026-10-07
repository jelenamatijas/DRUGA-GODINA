import java.util.Scanner;

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
            System.out.println("7. Zavrsetak kupovine");
            
            int opcija = -1;
            try {
                System.out.print("Unesite opciju: ");
                opcija = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Molimo unesite ispravan broj opcije.");
                continue; 
            }

            if(opcija == 1){
                System.out.println("\n-------------------------------------\n");
                cjenovnik.ispisProizvoda();
                System.out.println("\n-------------------------------------\n");
            }else if( opcija == 2){
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
                
            }else if( opcija == 3){
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
            }else if( opcija == 4){
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
            }else if( opcija == 5){
                System.out.println("\n-------------------------------------\n");
                racun.ispisProizvoda();
                System.out.println("\n-------------------------------------\n");
            }else if( opcija == 6){
                System.out.println("\n-------------------------------------\n");
                try{
                    System.out.println("Unesi sifru proizvoda: ");
                    String sifra = scanner.nextLine().trim();
                    racun.ukloniProizvod(sifra);
                }catch(NepostojeciProizvodException e){
                    System.out.println(e.getMessage());
                }catch(Exception e){
                    e.getStackTrace();
                }
                System.out.println("\n-------------------------------------\n");
            }else if( opcija ==7){
                System.out.println("\n-------------------------------------\n");
                krajKupovine = true;
                racun.zakljuciKupovinu();
                System.out.println("\n-------------------------------------\n");
            }
        }

        scanner.close();
    }
}
