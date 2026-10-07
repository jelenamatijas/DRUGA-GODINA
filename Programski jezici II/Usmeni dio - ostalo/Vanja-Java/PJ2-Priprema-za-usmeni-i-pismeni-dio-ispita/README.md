# Programski jezici 2 - Pismeni dio ispita

## :pushpin: Oblasti

1. Uvod

2. Paketi, moduli i kontrola pristupa

3. Izuzeci

4. OOP

5. Osnovne Java klase

6. Ulazno-izlazni podsistem

7. Generički tipovi

8. Kolekcije

9. Uvod u konkurentno programiranje

10. Lambda izrazi i Stream API

11. GUI

## :pushpin: Tipovi zadataka na pismenom dijelu ispita

<details>
  <summary>Prvi zadatak</summary>
  
  
<p>
Akcenat je na konkurentnom programiranju i pravilnoj sinhronizaciji. Bitno je napraviti pravilnu hijerarhiju klasa i interfejsa.
</p>

==_NOTE_== Ne smijemo koristiti **instanceof** da provjerimo da li je roditelj dijete, to postižemo upotrebom interfejsa ili provjeravanjem nekog flega/atributa.

</details>

<details>
  <summary>Drugi zadatak</summary>

- I/O

</details>

<details>
  <summary>Treći zadatak</summary>

- Stream API
- </details>

## :rotating_light: Usmeni ispit

[:arrow_forward: Usmeni ispit](resources/Usmeni.md)

## :sun_with_face: Tips and tricks

- Importovanje paketa
  ```java
      import java.util.stream.*;
      import java.util.*;
      import java.io,*;
  ```
- Svi atributi klase **default** paketski modifikator pristupa, bez navođenja public (manje kucanja), bez getter i setter metoda
- **Default constructor** kod Stream API

  ```java
  public Proizvod() {
      id = "ID" + new Date().getTime();
      naziv = "Proizvod" + count++;
      opis = "Opis" + count;
      cijena = new Random().nextInt(100) + 1000;
      tip = (count % 2 == 0) ? Tip.KNJIGA : Tip.SVESKA;
  }
  ```

- **toString** Override
  ```java
  @Override
    public String toString() {
        return id + " " + " " + naziv + " " + opis + " " + cijena + " " + tip.toString();
    }
  ```
- **`catch(Exception e)`**
- `java.util.Stream.toList()`
- `Collectors.metoda()`
- `stream().collect(Collectors.groupingBy(p -> p.tip))`
- Konverzija String u TIP -> `TIP.parseTIP(string)`
- Trenutno vrijeme `new Date().getTime()`
- Kopiranje liste u drugu preko parametrizovanog konstruktora
  ```java
  ArrayList<Proizvod> copy = new ArrayList<>(original);
  ```
- Serijalizacija
  ```java
      String fajl = Main.class.getName() + "_" + new Date().getTime() + ".ser";
      try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fajl))) {
          oos.writeObject(copyProizvodi);
      } catch (IOException e) {
          e.printStackTrace();
      }
  ```
- Deserijalizacija
  ```java
       String fajl = "fajl.ser";
       try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fajl))) {
              proizvodi = (ArrayList<Proizvod>) ois.readObject();
          } catch (ClassNotFoundException | IOException e) {
              e.printStackTrace();
          }
  ```
- ```java
  File[] file.listFiles()
  ```
- ```java
    import java.util.concurrent.*;
    BlockingQueue<Student> red = new LinkedBlockingQueue<>();
  ```
- soriranje
  ```java
    kolekcija.stream().sorted(Comparator.comparingInt(Object::hashCode))
  ```
- **File Watcher**
  ```java
    public class FileWatcher extends Thread {
    public int cnt = 0;

    public FileWatcher() {
        setDaemon(true);
    }

    @Override
    public void run() {
        try {
            WatchService service = FileSystems.getDefault().newWatchService();
            Path dir = Paths.get(Main.PATH);
            dir.register(service, ENTRY_CREATE);
            while (true) {
                WatchKey key = null;
                try {
                    key = service.take();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    if (kind.equals(ENTRY_CREATE))
                        cnt++;
                }

                boolean valid = key.reset();
                if (!valid)
                    break;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
  }
  ```

- čitanje fajla
  ```java
  try {
       List<String> linije = Files.readAllLines(Path.of(nazivFajla));
  } catch (IOException e) {
          e.printStackTrace();
  }
  ```
- pisanje u fajl
  ```java
  try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(FILE)))) {
        pw.println("Tekst");
    } catch (IOException e) {
        System.out.println(e.getMessage());
    }
  ```
- Konverzija Array u List
  ```java
  new ArrayList<>(Arrays.asList(array))
  ```
- Rekurzivni obilazak foldera
  ```java
  public static void obidji(File file) {
        if (file.isFile() && file.getPath().endsWith(ekstenzija)) {
            prebrojRijeci(file);
        } else if (file.isDirectory()) {
            File[] fajlovi = file.listFiles();
            for (File f : fajlovi) {
                if (f.isDirectory())
                    obidji(f);
                else if (f.isFile() && f.getPath().endsWith(ekstenzija))
                    prebrojRijeci(f);
            }
        }
    }
  ```
- Čitanje iz fajla
  ```java
  try (BufferedReader br = new BufferedReader(new FileReader(file))) {
          String linija = null;
          while ((linija = br.readLine()) != null) {
              // (linija.toUpperCase().contains(rijec))
                 // broj++;
          }
          // putanje.put(file.getPath(), broj);
      } catch (IOException e) {
          e.printStackTrace();
      }
  ```
- mapiranje u kolekciju Intova, Double i vršenje nekih operacija nad njima average, sum itd.
  ```java
  mapToInt(e -> e.value)
  mapToDouble(e -> e.value)
  ```
- reduce
  ```java
    System.out.println("Prikaz svih razlicitih godina rodenja u formatu godina1#godina2#...koristenjem reduce metode");
    String rez = studenti.stream().mapToInt(s -> s.godinaRodj).distinct().sorted().mapToObj(String::valueOf).reduce("", (s1, s2) -> s1 + "#" + s2);
    System.out.println(rez);
  ```
- Comparable

  ```java
  implements Comparable<Podatak>

  public int compareTo(Podatak p){
    return Integer.compare(hashCode(), p.hashCode());
  }
  ```

- Pravljenje novog direktorijuma
  ```java
  Path destDir = Paths.get(args[1]);
  destDir.toFile().mkdir(); // napravi direktorijum ako ne postoji
  ```
- Kopiranje jednog fajla u drugi
  ```java
  try{
    Path source = Paths.get(f);
    // kopiranje jednog fajla u drugi
    Files.copy(source, Path.of(destDir.toString(), source.getFileName().toString()), StandardCopyOption.REPLACE_EXISTING);
  } catch(Exception e){
    e.printStackTrace();
  }
  ```
- **Kod HASH KOLEKCIJA je bitno uraditi redefinisanje toString i hashCode metoda!**
- equals metoda
  ```java
  @Override
  public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
     Knjiga knjiga = (Knjiga) o;
      return this.naslov.equals(knjiga.naslov) && (this.godinaIzdavanja == knjiga.godinaIzdavanja);
  }
  ```
  - hashCode metoda
  ```java
  @Override
    public int hashCode() {
        int hash = 3;
        hash = 7 * hash + naslov.hashCode();
        hash = 7 * hash + Integer.hashCode(godinaIzdavanja);
        return hash;
    }
  ```
- Sortiranje kolekcije rastuće/opadajuće
  ```java
  stream().sorted(Comparator.reverseOrder()) // opadajuće
  stream.sorted() // rastuće
  ```
- Svaki drugi ključ veći od 5 filtriranje
  ```java
  IntStream.range(0, mapa.size()).filter(i -> i % 2 == 0 && mapa.get(i).key > 5).mapToObj(mapa::get).collect(Collectors.toList());
  ```
- Slučajno izaberi jedno slovo iz engleskog alfabeta
  ```java
  char slovo = (char) (new Random().nextInt(26) + 'a');
  ```
- Specijalni karakter se mora drugačije navoditi npr. tačka
  ```java
  .split("\\.") 
  ```
- Trenutni datum (yyyy-MM-dd)
  ```java
  import java.time.LocalDate;
  LocalDate currentDate = LocalDate.now();
  ```
- Stream MIN, MAX
  ```java
  grupa1.stream().min(Comparator.comparing(z -> z.tezina)).ifPresent(System.out::println);
  ```

- Folder Watcher
  ```java 
  public class FolderWatcher extends Thread {

    String folder;
    ArrayList<String> txt = new ArrayList<>();

    public FolderWatcher(String folder) {
        setDaemon(true);
        this.folder = folder;
    }

    public void run() {
        while (!Sakupljac_Watchera.END) {
            try {
                File[] files = new File(folder).listFiles();
                for (File f : files) {
                    String filename = f.getName();
                    if (filename.endsWith(".txt") && !txt.contains(filename)) {
                        System.out.println(">>> NEW FILE" + filename + " FOLDER: " + folder);
                        Files.readAllLines(Path.of(folder, filename)).forEach(System.out::println);
                        txt.add(filename);
                    }
                }
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
  }
  ```
- Kod ProrityQueue ili HashMap/Set mora biti implementiran Comparable interfejs
  ```java
  implements Comparable<Podatak>
  public int compareTo(Podatak p) {
        return Integer.compare(hashCode(), p.hashCode());
    }
    ```
- processData, varargs, Predicate, Consumer, generici
  ```java
   public static <T, V> void processData(List<Predicate<Data<T, V>>> predikati, Consumer<Data<T, V>> consumer, List<Data<T, V>>... listaPodataka) {
        ArrayList<Data<T, V>> podaci = new ArrayList<>();
        for (List<Data<T, V>> lista : listaPodataka) {
            for (Data<T, V> data : lista) {
                if (predikati.stream().allMatch(dataPredicate -> dataPredicate.test(data))) {
                    podaci.add(data);
                }
            }
        }
        podaci.stream().sorted(Comparator.comparingInt(Object::hashCode)).forEach(consumer); // (d1, d2) -> d1.hashCode() - d2.hashCode()
    }
  ```


## :page_facing_up: Praktični ispiti

<details>
<summary>26.04.2023 ✅</summary>

1. Letjelica sa pogonskim, komunikacionim i navigacionim modulom
2. Rad sa fajlovima
3. Simulacija biranja 2 studenta
</details>

--- 

<details>
<summary>29.06.2022 ✅</summary>

1. Simulacija leta
2. Stream API Registar proizvoda
3. Simulacija RED
</details>

<details>
  <summary>15.06.2022 ✅</summary>

1. Orwell 1984 Big brother
2. Rad sa fajlovima
3. Generici + Predicate + Consumer ExampleData1
</details>

<details>
  <summary>09.02.2022 ✅</summary>

1. Trka vozila
2. Stream API + Rad sa fajlovima Filmovi
3. Pretraga direktorijuma Rad sa fajlovima + Stream API
</details>

<details>
  <summary>26.01.2022 ** ➡️</summary>

1. Slanje poruka + FileWatcher
2. Simulacija rad sa fajlovima
3. Analiza rješenja ispitnih zadataka (paketi, klase, komentari)
</details>

---

<details>
  <summary>06.10.2021 ➡️</summary>

1. Studentski izbori (.SAVE, .ELECTION, .REGISTER, .STATUS)
2. Rad sa fajlovima
3. Stream API Agencija za zapošljavanje
</details>

<details>
  <summary>08.09.2021 ✅</summary>

1. Obrada podataka kompanije (IMPORT, AUTO, SAVE, STATUS)
2. Stream API Studenti
3. UlancaniStek AddThread, RemoveThread
</details>

<details>
  <summary>25.08.2021 **</summary>

1. Virtuelna pošta
2. Rad sa fajlovima
3. Generici , Predicate, Podatak
</details>

<details>
  <summary>30.07.2021 ✅</summary>

1. Simulacija kupovine na kasi
2. Generici (Skladište, Podaci, Obavještenje , Alarmi)
3. FileWatcher + Rad sa fajlovima + Simulacija DailyQuotes
</details>

<details>
  <summary>16.06.2021 ✅</summary>

1. Vojna plovila
2. Stream API Knjige
3. Pretraga direktorijuma Rad sa fajlovima + Stream API
</details>

---

<details>
  <summary>13.02.2020 **</summary>

1. Cirkus + Biljojedi + Mesojedi
2. Rad sa fajlovima + Rekurzija
3. ArrayOps
</details>

<details>
  <summary>30.01.2020 ➡️</summary>

1. Trka vozila
2. Sakupljac_Watcher + rad sa fajlovima
3. Stream API | Zec
</details>

---

<details>
  <summary>03.10.2019 ✅</summary>

</details>

<details>
  <summary>19.09.2019.</summary>

1. JavaAds + JavaCity
2. Rad sa fajlovima + komandna linija (-src, -dest, -unique, -limit)
3. StekRed **08.09.2021.**
</details>

<details>
  <summary>05.09.2019.</summary>

1. JavaFabrika
2. Generici Baza artikala prodavnice + CSV
3. JavaBank Stream API + Simulacija
</details>

<details>
  <summary>04.07.2019. **</summary>

1. Java vodeni svijet
2. Java kompanija pozivnice Rad sa fajlovima
3. Generici Predicate hashCode **25.08.2021.**
</details>

<details>
  <summary>18.04.2019.</summary>

1. Java takmičenje prepreke, bonusi, voda, vatra, stijena
2. FileWatcher binarne datoteke Rad sa fajlovima
3. Takmičenje niti Xnit, Ynit, Znit
</details>

<details>
  <summary>14.02.2019.</summary>

1. Narudžbenica i Faktura
2. Generici, Stream API, Predicate
3. Rad sa fajlovima ispis brojeva (10)
</details>

---

<details>
  <summary>05.10.2018</summary>

1. Simulacija Kino dvorane
2. ConsoleTable \*\*\*
3. Rad sa fajlovima
</details>

<details>
  <summary>20.09.2018</summary>

1. Java firma varioci, vozači, bravari
2. Queue AddThread, RemoveThread
3. Minsko polje simulacija
</details>

<details>
  <summary>06.09.2018 ✅</summary>

1. Sipanje goriva na benzinskoj pumpi
2. Rad sa fajl sistemom
3. Java NIO + Stream API
</details>

<details>
  <summary>19.04.2018</summary>

1. Simulacija voda, vatra, stijena
2. Obrada sadržaja tekstualnih fajlova na različite načine (loader, processor, exporter) \*\*
3. Stream API
</details>

<details>
  <summary>15.02.2018</summary>

1. Simulacija sakupljanje zlatnika robot, planinar, atletičar, skijaš
2. Modifikacija konfiguracioni fajl
3. Generička metoda \*\*
</details>

<details>
  <summary>01.02.2018</summary>

1. Java hidrocentrala
2. [Ponovljeno] broj klasa, paketa **26.01.2022**
3. FileWatcher
</details>

---

<details>
  <summary>27.09.2017</summary>

1. Fabrika, trake, mašine
2. Proširenje prethodnog zadatka, Narudžbe, algoritam za raspoređivanje
3. Stream API
</details>

<details>
  <summary>07.09.2017</summary>

1. Upravljanje fajlova u folderima
2. Proširenje
3. Proširenje
</details>

<details>
  <summary>24.08.2017 **</summary>

1. **08.09.2021.**
2. Processor
3. Exporter
</details>

<details>
  <summary>29.06.2017</summary>

1. Takmičenje na mapi prepeke + bonusi
2. CSV analiza poslovanja izmišljenog preduzeća
3. Generator Java klasa
</details>

<details>
  <summary>27.04.2017</summary>

1. Turističke atrakcije
2. Stack PusherThread PopperThread
3. Stream API Quotes CSV
</details>

<details>
  <summary>09.02.2017**</summary>

1. Grad robovil Roboti
2. Procesiranje \*\*
3. Class-creator
</details>

---

<details>
  <summary>22.10.2016</summary>

1. Mitološka bića
2. Ppogađanje riječi
3. Fajlovi sa riječima istog početnog slova
</details>

<details>
  <summary>22.09.2016 **</summary>

1. Takmičenje prepreke i bonusi
2. Kopiranje fajlova, foldera
3. XML, JSON -> Java klase
</details>

<details>
  <summary>08.09.2016 **</summary>

1. Muzički zapisi
2. HTML Parser \*\*
3. Rad sa fajlovima
</details>

<details>
  <summary>30.06.2016 **</summary>

1. Ambulanta
2. LIMUNADA Pogađanje riječi
3. Rad sa fajlovima razvrstavanje
</details>

<details>
  <summary>16.06.2016 ***</summary>

1. Evropsko prvenstvo u fudbalu
2. Proširenje
3. Fajlovi
</details>
