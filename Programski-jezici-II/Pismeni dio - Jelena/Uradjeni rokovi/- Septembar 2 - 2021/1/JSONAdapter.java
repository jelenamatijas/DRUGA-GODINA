import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

class JSONAdapter extends DataAdapter {
    
    JSONAdapter(String filePath) {
        super(filePath);
    }
    
    @Override
    public List<Element> importData() {
        List<Element> rezultat = new ArrayList<>();
        int brojUcitanihProizvoda = 0;
        int expectedCount = -1;
        
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            
            String s;
            String sifra = null, naziv = null, proizvodjac = null, vrsta = null;
            Double kolicina = null, cijena = null;
            
            while ((s = br.readLine()) != null) {
                s = s.trim();
                
                if (s.contains("\"brojProizvoda\"")) {
                    try {
                        expectedCount = Integer.parseInt(extractJsonValue(s));
                        System.out.println("Ocekivano proizvoda: " + expectedCount);
                    } catch (NumberFormatException e) {
                        System.out.println("Nevalidan brojProizvoda: " + s);
                    }
                    continue;
                }
                
                if (s.contains("\"sifra\"")) {
                    sifra = extractJsonValue(s);
                } else if (s.contains("\"naziv\"")) {
                    naziv = extractJsonValue(s);
                } else if (s.contains("\"kolicina\"")) {
                    try {
                        kolicina = Double.parseDouble(extractJsonValue(s));
                    } catch (NumberFormatException e) {
                        System.out.println("Nevalidna kolicina: " + s);
                    }
                } else if (s.contains("\"cijena\"")) {
                    try {
                        cijena = Double.parseDouble(extractJsonValue(s));
                    } catch (NumberFormatException e) {
                        System.out.println("Nevalidna cijena: " + s);
                    }
                } else if (s.contains("\"proizvodjac\"")) {
                    proizvodjac = extractJsonValue(s);
                } else if (s.contains("\"vrsta\"")) {
                    vrsta = extractJsonValue(s);
                }
                
                if (sifra != null && naziv != null && kolicina != null && 
                    cijena != null && proizvodjac != null && vrsta != null) {
                    
                    try {
                        Proizvod p = new Proizvod(sifra, naziv, kolicina, cijena);
                        Proizvodjac pr = new Proizvodjac(proizvodjac);
                        Vrsta v = new Vrsta(vrsta);
                        
                        rezultat.add(p);
                        rezultat.add(pr);
                        rezultat.add(v);
                        
                        brojUcitanihProizvoda++;
                        
                        sifra = naziv = proizvodjac = vrsta = null;
                        kolicina = cijena = null;
                        
                    } catch (Exception e) {
                        System.out.println("Greska pri kreiranju proizvoda: " + e.getMessage());
                    }
                }
            }
            
            // Validacija broja proizvoda
            if (expectedCount == -1) {
                System.out.println("GRESKA: Nije pronađen 'brojProizvoda' u JSON fajlu: " + filePath);
                return new ArrayList<>();
            }
            
            if (brojUcitanihProizvoda != expectedCount) {
                System.out.println("GRESKA u " + new File(filePath).getName() + 
                                 ": Ocekivano " + expectedCount + " proizvoda (iz brojProizvoda), ucitano " + 
                                 brojUcitanihProizvoda);
            }
            
            System.out.println("UspjeSno ucitano " + brojUcitanihProizvoda + 
                             " proizvoda iz " + new File(filePath).getName());
            
        } catch (IOException e) {
            System.out.println("GreSka pri obradi fajla: " + filePath);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Neocekivana greSka: " + e.getMessage());
            e.printStackTrace();
        }
        
        return rezultat;
    }
    
    private String extractJsonValue(String line) {       
        int colonIndex = line.indexOf(':');
        if (colonIndex == -1) return "";
        
        String value = line.substring(colonIndex + 1).trim();
        
        // Ukloni navodne znake, zareze i zagrade
        value = value.replaceAll("\"", "")
                     .replaceAll(",", "")
                     .replaceAll("\\}", "")
                     .trim();
        
        return value;
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