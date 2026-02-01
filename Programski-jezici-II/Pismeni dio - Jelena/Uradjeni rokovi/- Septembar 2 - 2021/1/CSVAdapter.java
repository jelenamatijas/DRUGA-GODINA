import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

class CSVAdapter extends DataAdapter {
    
    CSVAdapter(String filePath) {
        super(filePath);
    }
    
    @Override
    public List<Element> importData() {
        List<Element> rezultat = new ArrayList<>();
        int brojUcitanihProizvoda = 0;
        
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            
            String firstLine = br.readLine();
            if (firstLine == null) {
                System.out.println("Fajl je prazan: " + filePath);
                return rezultat;
            }
            
            if (firstLine.startsWith("\uFEFF")) {
                firstLine = firstLine.substring(1);
            }
            firstLine = firstLine.trim();
            
            int expectedCount;
            try {
                expectedCount = Integer.parseInt(firstLine);
            } catch (NumberFormatException e) {
                System.out.println("GREsKA: Prva linija mora sadrzati broj proizvoda: " + firstLine);
                return rezultat;
            }
            
            String s;
            while ((s = br.readLine()) != null) {
                s = s.trim();
                if (s.isEmpty()) continue;
                
                String[] podaci = s.split(",");
                
                if (podaci.length == 6) {
                    try {
                        String sifra = podaci[0].trim();
                        String naziv = podaci[1].trim();
                        double kolicina = Double.parseDouble(podaci[2].trim());
                        double cijena = Double.parseDouble(podaci[3].trim());
                        String proizvodjac = podaci[4].trim();
                        String vrsta = podaci[5].trim();
                        
                       
                        if (sifra.isEmpty() || naziv.isEmpty() || 
                            proizvodjac.isEmpty() || vrsta.isEmpty()) {
                            System.out.println("Preskacem red sa praznim podacima: " + s);
                            continue;
                        }
                        
                        Proizvod p = new Proizvod(sifra, naziv, kolicina, cijena);
                        Proizvodjac pr = new Proizvodjac(proizvodjac);
                        Vrsta v = new Vrsta(vrsta);
                        
                        rezultat.add(p);
                        rezultat.add(pr);
                        rezultat.add(v);
                        
                        brojUcitanihProizvoda++;
                        
                    } catch (NumberFormatException e) {
                        System.out.println("Preskacem red sa nevalidnim brojevima: " + s);
                    }
                } else {
                    System.out.println("Preskacem red sa neispravnim brojem kolona (" + 
                                     podaci.length + "): " + s);
                }
            }
            
            if (brojUcitanihProizvoda != expectedCount) {
                System.out.println("GRESKA u " + new File(filePath).getName() + 
                                 ": Ocekivano " + expectedCount + " proizvoda (iz prve linije), ucitano " + 
                                 brojUcitanihProizvoda);
            }
            
            System.out.println("Uspjesno ucitano " + brojUcitanihProizvoda + 
                             " proizvoda iz " + new File(filePath).getName());
            
        } catch (IOException e) {
            System.out.println("Greska pri obradi fajla: " + filePath);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Neocekivana greska: " + e.getMessage());
            e.printStackTrace();
        }
        
        return rezultat;
    }
    
    @Override
    public void run() {
        List<Element> rezultat = this.importData();
        if (!rezultat.isEmpty()) {
            synchronized (Main.lock) {
                Main.podaci.addAll(rezultat);
            }
        }
    }
}