## :rotating_light: Usmeni ispit

1. **I DIO** - 35 bodova (7 x 5b) Veći zadaci (compile error ili ispis)
2. **II DIO** - 9/6b Memorija (crtanje, ispis, Exceptions)
3. **III DIO** - 6/9b Mali zadaci (compile error ili ispis)
   **Ukupno 50 bodova**

### :one: Prvi dio

#### Ostalo

- rekurzija u main metodi

[Java rules for casting](https://stackoverflow.com/questions/2233902/java-rules-for-casting)

```java
String s = new String("hello"); StringBuffer sb = (StringBuffer)s;  // Compile error : Invertible types because there is no relationship between.

Object o = new String("hello"); StringBuffer sb = (String)o;       // Compile error : Incompatible types because String is not child class of StringBuffer.

Object o = new String("hello"); StringBuffer sb = (StringBuffer)o; // Runtime Exception : ClassCastException because 'o' is string type and trying to cast into StingBuffer and there is no relationship between String and StringBuffer.
```

#### ClassCastException

```java
class A {...}
class B extends A {...}
class C extends A {...}
```

1. You can cast any of these things to Object, because all Java classes inherit from Object.
2. You can cast either B or C to A, because they're both "kinds of" A
3. You can cast a reference to an A object to B *only if* the real object is a B.
4. You can't cast a B to a C even though they're both A's.

#### TODO: Inner classes

[Java Nested and Inner Class](https://www.programiz.com/java-programming/nested-inner-class)

#### TODO: == vs equals

[What is the difference between == and equals() in Java?](https://stackoverflow.com/questions/7520432/what-is-the-difference-between-and-equals-in-java)

#### Overriding _static_ methods

[Can I override and overload static methods in Java?](https://stackoverflow.com/questions/2475259/can-i-override-and-overload-static-methods-in-java)

---

1. **Hijerarhija klasa, redoslijed pozivanja konstruktora, statičkih, nestatičkih blokova i atributa članova**

   - U izvedenim klasama **override metode** ne smiju imati **smanjen modifikator pristupa**
     protected >> friendly
   - **Privatni konstruktor** ne može biti pozvan pozivom **super()**
   - **Nema podrazumijevanog konstruktora**, a poziva se implicitno ili eksplicitno **super()**
   - **Override metoda** **ne** može baciti **više izuzetaka** nego što je definisano u osnovnoj klasi,
     a može baciti unchecked excpetions (Error, RuntimeException)
   - **StackOverflowError → ꚙ→ rekurzija (**pozivi metoda npr. errorCheck, ili u klasi A, da je podatak član A opet… **)**
   - poziv nepostojećeg konsturktora
   - this(a1), a a1 nije kreiran objekat → **NullPointerException**

   ***

   1. statički blok
   2. super()/ this()
   3. **članovi**
   4. **nestatički blok**
   5. ostatak konstruktora

   Tačke 3. i 4. se izvršavaju u redoslijedu navođenja.

---

2. **Hijerarhije klasa, interfejsi, preklapanje metoda, statičke/unutrašnje klase, anonimne klase**
   - hijerarhiju klasa, interfejsa provjeriti (abstract ako nešto nije implemenirano, ali može biti negdje drugo implicitno implementirano)
   - weakening type, metode u interfesu su po defaultu public, ukoliko implementiramo metodu bez `public` metoda je `friendly` -> __Compile Error__
   - hashCode, equals redefinisanje
   - ispis objekata NazivKlase@hashCode
   - **TODO**: Načini kreiranja statičkih i nestatičkih ugnježdenih klasa!
   - implements AutoCloseable, close metoda, try-with-resources

---

3. **Izuzeci**

   ![Exceptions hierarchy](img/exceptions.png)

   - metoda može u **throws** bacati izuzetak koji se ne može baciti u tijelu metode
   - **Compile Error**: ako se u **try bloku** ne može nikako baciti izuzetak koji catch blok hvata
   - **Compile Error**: **nije obrađen izuzetak** na neki od 2 načina
   - **Compile Error:** **redoslijed hvatanja izuzetaka**, od većeg ka manjem**!**
   - Može se bacati unchecked exception, i navoditi u catch bloku
   - **Finally** se **uvijek izvršava**
   - **multi-catch blok** → **NE** smije se nalaziti izuzeti sa različitih nivoa u hijerarhiji
     ne može npr. IOException | Exception e
   - finally ima veću prednost od catch bloka
   - **Finally** se svakako izvršava, bez obzira da li je bačen izuzetak u try bloku

   ![Zna da ima return; u finally bloku, ako ga izbacimo → kompajlerska greška](img/return.png)

   Zna da ima return; u finally bloku, ako ga izbacimo → kompajlerska greška

   - zatvaranje resursa (koji implementiraju Closeable ili AutoCloseable interfejs) u redoslijedu obrnutom od redoslijeda navođenja u try-with-resources
   - new Random().nextDouble() → (0, 1.0) → uslov > 1.0
     možemo imati i if else, pa pišemo moguće scenarije

---

4. **Random zadatak, lambda, streamovi, ctor reference, metode reference (da li se poklapaju tipovi)**

   - **TODO**: P10 → instance ref methods
   - **Class::instanceMethod** ⇒ ima dodatni implicitni argument **this**
   - **obj::instanceMethod**
   - Lambde
   - Stream API
   - Ctor reference
   - Metoda reference
   - Da li se poklapaju tipovi
   - P10 -> instancerefmethods
   - _Primjeri sa predavanja_

---

5. **Threads, hijerarhija klasa → uglavnom nema compile error**

   - Runnable, Thread
   - join(), sleep()
   - Niz niti
   - Thread ID preko counter-a
   - **daemon** niti → okončavaju izvršavanje po završetku glavnih/worker niti
   - start() → pokreće se nit
   - run() → ne pokreće nit, već samo izvršava run metodu (ne crtamo novu nit, već na tekućoj ispisujemo rezultat run metode)
   - **\*IllegalThreadStateException →** dva puta poziv metode **start()\***
   - Redefinisana start() metoda, u kojoj nema super.start() → ne kreira se nova nit
   - `t2 = new Thread(t1)` → t2 dobija samo run metodu, ostale stvari se ne preuzimaju
     ako je t1 demonska metoda, pisaće i da je t2 demonska, iako ona to stvarno nije

---

6. **Generici →** vjerovatno nema compile error

   - raw tipovi → briše parametrizovane tipove i stavlja Object
   - **ClassCastException** → kastovanje
   - generici rade samo sa metodama iz Object klase
   - `<? extends String>` → onda se mogu koristiti i metode iz klase String

---

7. **Serijalizacija →** uglavnom se izvršava, dosta caka
   - BITNO https://www.geeksforgeeks.org/object-serialization-inheritance-java/
   - **Serializable** je markerski interfejs, ne mora se ništa implementirati
  može
      ```java
      private void writeObject(java.io.ObjectOutputStream out) throws IOException
      private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException;
      ```
   - **`Externalizable` mora** implementirati sljedeće metode
        ```java
        public void writeExternal(ObjectOutput out) throws IOException
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
        ```
   - **`Externalizable` nadjačava `Serializable`**
   - SVAKA klasa koja implementira **`Externalizable` MORA IMATI DEFAULT CTOR, jer se on prvo poziva u procesu deserijalizacije, inače `InvalidClassException`**
   - **Roditelj**, **Dijete** implementira **`Serializable`, Roditelj MORA imati default ctor → inače Runtime Exception, atributi roditelja** dobijaju default vrijednosti, a ne stvarne, u suprotnom Roditelj ne mora imati default ctor (jer se on ni ne poziva u tom slučaju)
  ![Serialization image](img/ser.png)
   - Redoslijed deserializacije je bitan
   - S: Int + Long = 4 + 8 = 12
  D: Long + Int = 8 + 4 = 12
  **OK ⇒ jer je veličina dovoljna
  NOT OK → veličine ne odgovaraju → Exception**
   - **implements** **`Serializable`,** a ima **writeExternal i readExternal** metode **→** tretiraju se kao **obične metode**
   - writeObject i readObject mogu pozivati writeExternal, readExternal
   - ako postoji hijerarhija, ide **super.readObject** pa tek onda readObject
   - **static** i **transient** promjenljive se neće serijalizovati implicitno
   - `NotSerializableException` → svi složeni atributi neke klase takođe treba da implementiraju **`Serializable`**

### :two: Drugi dio

- izračunati veličinu svih objekata na početku
- **CHAR JE 2B**
- **OutOfMemoryError**
- **ArrayIndexOutOfBounds**
- **finalize** metoda se poziva prilikom svakog poziva **System.gc()**
- **finalize** se poziva bez eksplicitnog poziva gc zbog uslova zadatka za oslobađanje memorije

### :three: Treći dio

- try-with-resources u kom je Exception → pa se poziva close() metoda
- `Enum`, poziv metode `ordinal()` počinje od 0
  **ctor** mora biti **private,** prvi put kada se koristi Enum se kreiraju svi objekti,
  sljedeći put neće
- `Stream` → može biti svašta, npr. parallel ispis → ispisati u bilo kom redoslijedu
- niti
- kolekcije
- binarni, bitski operatori `i++, i += ++i` itd. `1&2|3^4|5`

  ```java
  int i = 4;
  for(;i<=12;i+=2){
      i++;
      i-=1;
      ++i;
      i++;
      i+=1;
  }
  ```

- unutrašnja klasa

### General notes
 ![literali](https://user-images.githubusercontent.com/130909026/235144517-6a923b3d-ef45-43a6-974e-c5f84e78da55.png)
 
 ![tipovi](https://user-images.githubusercontent.com/130909026/235145822-839f975a-d9f0-4053-8d3a-dc768d0225f9.png)
 
![inic-blok](https://user-images.githubusercontent.com/130909026/235147837-db7536bc-3300-4cc0-9cd0-d8e5e27d18a3.png)

- __Compile Error__ Kompajler će kao grešku prijaviti svaki pokušaj korištenja neinicijalizovane promjenljive. (metoda, konstruktor, blok)
- __Compile Error__ 
  ```java
  int x;
  x = 3.14;
  ```
- Implicitna konverzija tipa `byte -> short -> int -> long -> float -> double `
- __Compile Error__ Nelegalan pokušaj kastovanja. *
- __ClassCastException__ Sužavanje reference. *
![brojevi](https://user-images.githubusercontent.com/130909026/235176432-32aba165-d573-4720-a8c3-b19f4912f11b.png)

![bitski-operatori](https://user-images.githubusercontent.com/130909026/235176597-e6154d0e-426f-4cf7-b8b1-218818fb5903.png)

- Kod izračunavanja izraza u kojima se nalaze vrijednosti **byte** i **short** vrši njihova promocija u **int**.
- Pomijeranje ulijevo << MNOŽENJE
- Pomijeranje udesno >> DIJELJENJE Check? Da li se na gornjim pozicijama ubacuju nule ili znak koji je prethodno bio zbog održavanja znaka?
- Short circuit && i || -> Drugi operand se neće izračunati ako se vrijednost rezultata može dobiti na osnovu prvog operanda.

![operator-precedence](https://user-images.githubusercontent.com/130909026/235178981-1cdaee69-08e1-424b-9ca8-f36d30ad6fc5.png)

- Pri deklaraciji metode može da postoji samo jedan varargs parameter koji mora biti posljednji u listi parametara metode.
- Moguće je deklarisati metodu koja ima identičan naziv nazivu konstruktora.
- Ako klasa ne specificira nijedan konstruktor, kompajler će generisati implicitni podrazumijevani konstruktor.
- Jedini zadatak implicitnog podrazumijevanog ctora je da pozove ctor roditeljske klase `super()`.
- __Compile Error__ Ukoliko postoje nepodrazumijevani konstruktori, svaki pokušaj korištenja podrazumijevanog konstruktora dovodi do greške pri kompajliranju.
- __Compile Error__ Poziv metode nad objektom, a metoda nije definisana u klasi tog objekta.
- __Compile Error__ Pristup privatnom atributu neke klase.
- Moguće je pristupiti statičkim poljima klase preko objekta te klase, ALI nije preporučljivo. `calculator.numberOfInstances()` umjesto `Calculator.numberOfInstances()`
- **Ugnježdene klase** 4.2.9 (45-46 str.)
    * Opseg vidljivosti ugnježdenih klasa je ograničen opsegom vidljivosti okružujuće klase.
    * Ugnježdena klasa može pristupiti članovima okružujuće klase, čak i privatnim.
    * Okružujuća klasa nema pristup članovima ugnježdene klase.
    * Okružujuća klasa može biti samo `public` ili `friendly`, dok ugnježdene mogu biti `public`, `friendly`, `protected` i `private`.
    * Postoje i _lokalne ugnježdene klase_, koje su deklarisane u nekom _bloku_ koda.
    * Postoje **statičke** i **nestatičke** ugnježdene klase.
    * Pristup nestatičkim članovima okružujuće klase iz statičke ugnježdene klase je moguć samo preko objekta okružujuće klase.
    * Kreiranje objekta **statičke ugnježdene klase**.
      ```java 
      Outer.StaticNestedClass obj = new Outer.StaticNestedClass();
      ```
    * Kreiranje objekta **unutrašnje (nestatičke) klase**.
      ```java 
      Outer.InnerClass obj = outerObject.new InnerClass();
      ```
  - Konstante enumeracije su implicitno `public`, `static`, `final`.
    * `enum-type [] values()`
    * `enum-type valueOf(String str)`
  - Enumeracije mogu imati konstruktore, metode i promjenljive, a mogu i implementirati interfejse (sve isto kao i bilo koja klasa).
  - Ograničenje enum : Enum ne može naslijediti drugu klasu, enum ne može biti roditeljska klasa.
  - `ordinal()` metoda Enum klase. (često na ispitu)
- Iz statičkog konteksta se može pristupiti samo statičkim članovima, tako i reference `this` i `super` nisu dostipne iz statičkog konteksta.
- `public -> protected -> friendly -> private`
- Interfejsi su implicitno `abstract`, **može** se navesti ali je nepotrebno i redundatno.
- Enum tipovi **ne** mogu biti `abstract`.
- Enum tipovi su implicitno `final`, i **ne** mogu biti eksplicitno deklarisani kao `final`.
- `final` klase se **ne** mogu nasljeđivati.
- Klasa **ne** može biti u isto vrijeme i `final` i `abstract`.
- `final` promjenljive i reference ne mogu promijeniti vrijednost/referencu nakon inicijalizacije.
- Pomjenljive unutar interfejsa su implicitno `final, nije potreno navoditi, ali nije greška.
- Metode unutar interfejsa su implicitno `abstract`, nije potreno navoditi, ali nije greška.
- `final` promjenljiva ne mora biti inicijalizovana pri deklaraciji, ali mora prije korištenja.
- `abstract private void metoda()` **ne** može, jer nije moguće onda napisati implementaciju te metode.
- `abstract static void metoda()` **ne** može -> statička metoda ne može biti redefinisana
- `astract final void metoda()` **ne** može
---
- Moguće je i bez bloka
  ```java 
  if(value > 1) ; 
  ```
- U switchu može biti `Enum`, `char`, `String`, `byte`, `short` i `int` kao i Wrapper klase za ove primitivne tipove.
- Labele moraju biti konstanti izrazi istog tipa kao i vrijednost u switch izrazu, i moraju biti jedinstvene, ne mogu postojati dvije case naredbe sa istom vrijednošću.
- `break` naredba nakon svakog case-a inače se izvršavaju i naredne naredbe.
- **Labele**
  * nazivi labela se nalaze u posebnom prostoru imena, pa ne može doći do konflikta
  * nalaze se ispred naredbe ili bloka koda
  * greška -> dvije iste labele
  * greška -> deklaracija promjenljive ne može biti ozhnačena labelom `label3: int i = 3;`
  * `break labela`
  * `continue labela`
- Ako metoda završava bacanjem izuzetka, postojanje return naredbe nije neophodno.
  ``` java
  static int metoda() {
    throw new RuntimeException();
  }
  ```
- **Izuzeci**
  * ![izuzeci](https://user-images.githubusercontent.com/130909026/235310673-31c26337-1362-4a08-870e-adda18231b0e.png)
  * Kompajler ne zahtjeva da se obrade neprovjereni izuzeci (RuntimeException, Error i njihove izvedene klase).
  * `InstantiationException` pokušaj instanciranja objekta apstraktne klase ili interfejsa
  * `RuntimeException`
      - `ArithmeticException`
      - `ArrayIndexOutOfBoundsException`   
      - `ClassCastException`
      - `IllegalArgumentException`
      - `NumberFormatException`
      - `NullPointerException`
      - `UnsupportedOperationException`
      - `IllegalStateException`
  * **Error**
      - `OutOfMemoryError`
      - `StackOverflowError` -> rekurzija npr.
  * Nakon try bloka mora doći barem jedan catch ili finally blok.
  * Tip u catch bloku mora biti Throwable ili neke klase nasljednice.
  * __Compile Error__ Redoslijed klasa u catch bloku veče -> manjem nije dozvoljen, pravilno je uži tip -> širi tip.
  * multi-catch blok Koje klase smiju biti u istom bloku? Da li samo one koje su na istom nivou u hijerarhiji?
  * Metoda koja redefiniše metodu roditeljske klase može: (PODSKUP)
    - baciti manje izuzetaka ili izostaviti throws klauzulu
    - baciti iste izuzetke
    - baciti izuzetke koji su podklase onih koji su bačeni u metodi koja je redefinisana
  * try-with-resources
    - mogu se naći resursi koji imaplementiraju `Closeable` ili `Autocloseable` interfejs pri čemu se po izlasku iz try automatski poziva `close()` metoda
---
- __Compile Error__ Moguće je pozvati samo metode koje psotoje u Calculator, a ne i one u ExtendedCalculator.
  ```java
  Calculator calc1 = new ExtendedCalculator(1, 2);
  double sum = calc1.add();
  // double product = calc1.multiply();
  ```
- Redefinisana metoda u izvedenoj klasi može imati povratni tip koji je podtip redefinisane metode, ne može smanjiti dostupnost, ali može povećati, može baciti sve, nijedan ili podskup provjerenih izuzetaka.
- Metoda instance u klasi nasljednici ne može redefinisati statičku metodu osnovne klase- Ali može doći do skrivanja.
- __Compile Error__ pokušaj redefinisanja final metode.
- U izvedenoj klasi može se navesti metoda sa istim potpisom kao iz osnovne klase sa modifikatorom private koja nije dostupna u izvedenoj klasi.
- Povratni tip ne pripada potpisu metode, prema tome za preklapanje metoda nije dovoljno da metode imaju različite povratne tipove, već listu parametara.
- __Compile Error__ Metode se razlikuju samo po povratnom tipu.
- Kompajler bira metodu sa najspecifičnijim parametrom.
- __Compile Error__ Ukoliko kompajler nije u stanju da odredi koju metodu da izabere, jer parametri nisu u relaciji tip -> podtip.
- ![redefinisanje-preklapanje](https://user-images.githubusercontent.com/130909026/235313329-b430a340-4add-4863-937a-26756048527d.png)
- __Compile Error__ Statička metoda u klasi nasljednici ne može maskirati metodu instance osnovne klase.
- Referenca `super` ne može biti kastovana, niti njenan vrijednost može biti dodijeljena drugim referencama.
- **super** ili **this** su prvi izrazi u tijelu konstruktora
- **super** i **this** ne mogu nikako biti zajedno u istom konstruktoru
- Kompajler implicitno ubacuje **super()** ukoliko nije naveden 
- __Compile Error__ Kompajler implicitno ubaci super(), a ne postoji default ctor u osnovnoj klasi.
- Klasa ne može smanjiti dostupnost metode interfejsa, niti može specificirati novi izuzetak u throws klauzuli.
- Metode interfejsa uvijek moraju biti implementirane kao metode instance, a ne kao statičke metode.
- Ugnježdeni interfejsi **
- Klasa može da redefiniđe default metodu interfejsa, ali i ne mora.
- Statičke metode interfejsa se ne nasljeđuju od strane klase/interfejsa koja implementira interfejs. (TODO)
- Privatne metode interfejsa se mogu pozvati samo od strane default metode ili druge privatne metode unutar interfejsa.
- **instanceof** A instanceof B
  * uvijek vraća `false` ako je A `null`
  * zahtjeva provjeru za vrijeme kompajliranja i izvršavanja
  * Provjera za vrijeme kompajliranja veza tip-podtip
  * Za vrijeme izvršavanja je bitan stvarni tip objekta, a ne deklarisani tip.
---
- Metoda clone -> kreira se novi identični objekat (sa identičnim stanjem) - plitko kopiranje
  ```java 
  protected Object clone() throws CloneNotSupportedException
  ```
- Klasa koju kloniramo a koja se oslanja na `clone()` iz Object klase mora implementirati interfejs `Cloneable`, u suprotnom se baca izuzetak `CloneNotSupportedException`.
- Metoda `equals` vraća `true` ako i samo ako dvije reference koje se porede pokazuju na isti objekat. (Možemo je redefinisati da vrati true ako je sadržaj objekta isti)
  ```java 
  public boolean equals(Object obj)
  ```
- Metoda `hashCode` - vraća memorijsku adresu objekta podrazumijevano
  ```java 
  public int hashCode()
  ```
  * Ako su dva objekta jednaka, onda bi i njihovi hash-evi trebali biti jednaki -> redefinisanje hashCode metode.
- `toString()` podrazumijevano vraća `nazivKlase@hashVrijednostObjekta` ukoliko nije redefinisana
- Sve Wrapper klase za primitivne tipove su `immutable`, vrijednost ovih objekata se ne može promijeniti
- `String` objekti su `immutable`
  * Svi string literali sa identičnom sekvencom karaktera dijele samo jedan objekat klase String. 
  * Konstantni izrazi koji se izračunavaju za vrijeme kompajliranja, a koji rezultuju identičnom sekvencom, dijeliće identičan objekat klase String.
  * Svi stringovi kreirani pomoću `new` su različiti objekti, iako recimo imaju istu sekvencu karaktera.
  * `StringBuilder` i `StringBuffer` implementiraju promjenljive sekvence karaktera.
  * Pošto prethodne klase nemaju zajedničkog roditelja, njihove reference se ne mogu dovesti u relaciju sa `String` referencama, čak ni eksplicitnim kastovanjem.
  * `StrignBuilder` nije sinhronizovana.
  * Inicijalni kapacitet je 16, osim ako nije definisano u konstruktoru drugačije, za ctor sa argumentom String je str.length() + 16
  * Objekti `StringBuilder` i `StringBuffer` moraju biti konvertovani u `String` kako bi se mogli porediti (jer ne redefinišu equals, hashCode metode i ne implementiraju Compareable interfejs)
---
- Četiri apstraktne klase za rad sa ulazno-izlaznim podistemom : `InputStream`, `OutputStream`, `Reader`, `Writer`.
- `InputStream` <-> `OutputStream` nasljednice:
  * `AudioInputStream` 
  * `ByteArrayInputStream`  `ByteArrayOutputStream`
  * `FileInputStream` `FileOutputStream` -> imaju dosta klasa nasljednica npr. `BufferedInputStream`
  * `FilterInputStream` `FilterOutputStream`
  * `ObjectInputStream` `ObjectOutputStream`
  * `PipedInputStream`  `PipedOutputStream`
  * `SequenceInputStream`
- `Reader` <-> `Writer` nasljednice:
  * `BufferedReader`   `BufferedWriter` 
  * `CharArrayReader` `CharArrayWriter`
  * `FilterReader`   `FilterWriter`
  * `InputStreamReader`   `OutputStreamWriter`
  * `PrintWriter`
  * `PipedReader` `PipedWriter`
  * `StringReader`  `StringWriter`
  * `URLReader`
- **Serijalizacija** - 214-222 str.
  * Da bi objekat neke klase bio serijalizovan, neophodno je da klasa implementira `java.io.Serializable` interfejs.
  * `transient` i `static` promjenljive neće se serijalizovati.
  * Svi referencirani objekti sadržavajućeg objekta moraju biti serijalizabilni da bi se mogli serijalizovati, inače se baca `NotSerializableException`
  * Objekti klasa koje nasljeđuju serijalizabilnu klasu uvijek serijalizabilni.
  * Kontrolisanje serijalizacije pomoću privatnih metoda koje ne pripadaju nijednom interfejsu:
    - ```java
      private void writeObject(ObjectOutputStream) throws IOException
      private void readObject(ObjectInputStream) throws IOException, ClassNotFoundException
      ```
   * Kontrolisanje se može izvršiti i pomoću `Externalizable` interfejsa
     ```java
     void writeExternal(ObjectOutput out) throws IOException
     void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
     ```
     - Ove metode nadjačavaju writeObject i readObject.
     - Kod deserijalizacije se poziva podrazumijevani konstruktor, a onda readExternal metoda.
     - Ukoliko nema podrazumijevanog konstruktora desiće se `InvalidClassException`.
     - Iako je **serialVersionUID** statička promjenljiva, ona se serijalizuje zajedno sa objektom.
 ---
 
- Niti dijele isti adresni prostor, a međunitna komunikacija nije skupa.
- Metoda `start` može baviti izuzetak `ThreadStateExcepetion` u slučaju kada se pozove nad objektom nad kojim je već pozvana metoda start.
- Java razlikuje korisničke i demonske niti.
- Demonske niti rade u pozadini i obično wervisiraju korisničke niti.
- Sve novokreirane niti su podrazumijevano korisničke niti.
- `setDaemon` mora biti pozvana prije nego što je nit pokrenuta metodom start
- Pokušaj promjene statusa niti nakon što je pokrenuta baca `IllegalThreadStateException`
- Stanja niti SLIKA
- statička metoda yield tekuća nit privremeno pauzira izvršavanje čime omogućava drugim jitima da sobiju procesorsko vrijeme. *
- join pozvama nad drugom niti peouzrokovaće da tekuća nit čeka dok se nit nad kojom je join metoda pozvama ne okonča.
- prioritet niti podrazumijevano 5, viši prioritet je bolji
- `NullPointerException` ako je referenca null u `synchronized` bloku
- Objekat klase `Timer` je pozadinska nit koja izvršava sve zadatke sekvencijalno. -> metode `cancel` i `purge`
- `TimerTask`
- ```java
  (new Thread(runnable)).start();
  executor.execute(runnable);
  ```
- Za razliku od Wrapper klasa, atomske promjenljive su promjenljive, tj. nisu immutable. -> nisu pogodne za ključeve u hash kolekcijama
---

- Generički mehanizam -> referencni tipovi (klase, interfejsi i nizovi) i metode parametrizuju informacijama o tipu.
- Nad nepoznatim tipom `T` je moguće pozivati samo metode iz `Object` klase.
- Tipskoj promjenljivoj T ne može se pristupiti iz statičkog konteksta.
- Ne postoji veza između različitih parametrizovanih tipova.
- Parametrizaciju generičkog tipa nije moguće izvršiti primitivnim tipovima, već samo referencnim.
- __Compile Error__ 
  ```java
  GenericBase<Integer> sub = new GenericSub<String>();
  ```
- Ograničavanje tipova `T extends Number` onda možemo koristiti i metode iz Number
- `<?>` predstavlja bilo koji tip
- `GenericHolder<? super Integer>` - bilo koji tip koji je supertip Integer-u, uključujući i Integer
- `GenericHolder<? extends Number>` - bilo koji tip koji je podtip Number-u, uključujući i Integer
- Generička metoda
  ```java
  boolean result = Util.<Long>.compare(i1, i2);
  
  public class Util{
    public static <T> boolean compare(GenericHolder<T> o1, GenericHolder<T> o2){
        return o1.get().equals(o2.get());
    }
  ```
- Raw tipovi - zamjena svih tipova sa Object
- Konkretna klasa ne može naslijediti generički tip, već samo parametrizovani!
![generici](https://user-images.githubusercontent.com/130909026/235428598-bf212887-1622-42d2-bf1e-747fbc114cc9.png)
---

![kolekcije](https://user-images.githubusercontent.com/130909026/235449114-062e5cd8-8cad-421d-8761-7a97294061d4.png)
![koelkcije-2](https://user-images.githubusercontent.com/130909026/235450471-dbbda7fe-295c-48f7-a59a-ba057102e3cd.png)

- `ConcurrentModificationException` - ako je objekat klase modifikovan nakon što je iterator kreiran na bilo koji način osim kroz metode iteratora
  * `HashSet`
  * `LinkedHashSet`
  * `TreeSet`
  * `ArrayList`
  * `LinkedList`
  * `Vector`
  * `HashMap`
  * `Hashtable`
- `HashSet` - inicijalni kapacitet = 16  faktor opterećenja = 0.75 ne garantuje se poredak
- `Vector` klasa slična `ArrayList` samo je sinhronizovana, inicijalni kapacitet = 10 i udvostručava se
- `PriorityQueue` inicijalni kapacitet = 11
- Kod `LinkedHashMap` se kod iteriranja mijenja poredak tako da će elementi kojima je najskorije pristupano biti posljednji obiđeni.
---

- Na mjestu definisanja lambda izraza kreira se objekat anonimne klase koja implementira interfejs sa jednom apstraktnom metodom.
- U tijelu lambda izraza paziti na bacanje izuzetaka. __Compile Error__ ako metoda funkcionalnog interfejsa ne baca izuzetak, a tijelo lambde baca.
- __Compile Error__ Lambda izraz može da koristi samo finalne ili efektivno finalne promjenljive, što znači da se njena vrijednost ne može mijenjati u lambda izrazu.
![lambde](https://user-images.githubusercontent.com/130909026/235467842-b38b25f3-657a-4d81-98d3-f3476517588b.png)
- Reference metoda
  ```java
  Class::staticMethod()
  object::instanceMethod()
  Class::instanceMethod()
  ```
- Funkcionalni interfejsi u java.util.function paketu
  * `Function`  `R apply(T t)`
  * `Predicate`  `boolean test(T t)`
  * `Consumer`    `void accept(T t)`
  * `Supplier`    `T get()`
- Postoje i BiFunction, BiPredicate i BiConsumer koji primaju dva argumenta.
---

- `IllegalStateException` dva poziva terminalne operacije nad istim stream-om.
- `peek()` bude na ispitu
- *lazy* operacije nad streamovima -> pipeline se izvršava vertikalno
  ![lazy](https://user-images.githubusercontent.com/130909026/235481301-6f3855de-cd14-48cf-ae3b-95cb03b3b9fe.png)
- combiner funkcija se ne izvršava ukoliko stream nije paralelan
- ako imamo parallelStream za sabiranje brojeva npr. 1,2,3, a početna vrijednost je 1, svaka nit sabere svoje i combiner metoda vraća rezultat svih niti 2 + 3 + 4 = 9

---
- Wrapper tipovi za brojeve se mogu kastovati samo preko svojih metoda, a ne npr `Integer i = d` ili  `Integer i = (Integer) d` i slično.
- B1 b1 = b2; b1.metoda(); Pošto je objekat referenciram preko B1 onda se gleda da li B1 ima metodu metoda(), ako ima ona se koristi, ako nema, pretražuje se dublje u hijerarhiji, tako da se bira ona metoda koja je najbliža vrhu.
  ```java
   public abstract class B1 { 
     B1() {
        super();
        System.out.println("B1()");
     }

   public static void main(String[] args) {
      B3 b3 = new B3();
      b3.metoda();
      B2 b2 = b3;
      b2.metoda();
      B1 b1 = b2;
      b1.metoda();
   }

   private void metoda() {
      System.out.println("B1 metoda...");
   }
  }
  abstract class B2 extends B1 { 
   B2() {
      System.out.println("B2()");
   } 
   abstract protected void metoda(); 
   void metoda2() {
      System.out.println("B2 metoda...");
   }
  }
  final class B3 extends B2 { 
   B3() {
      super();
      System.out.println("B3()");
   } 
  public void metoda() {
    System.out.println("B3 metoda...");
   }
  }
```
- UVIJEK PAZITI na privatne atribute članove i statički/nestatički kontekst
    



