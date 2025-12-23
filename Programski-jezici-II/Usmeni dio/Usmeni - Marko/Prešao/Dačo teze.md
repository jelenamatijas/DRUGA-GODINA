
- **OSNOVE**

- Identifikator ne smije pocinjati brojem i ne smije se poklapati sa rezervisanim rijecima.

- Char je neoznacen, sve je ostalo oznaceno.

- Moguce je iz nestacickog inicijalizacionog bloka inicijalizovati staticke atribute.

- Nije moguca implicitna konverzija sa sireg tipa na uzi. ( byte->short->int->long->float->double), samo sa kastovanjem

- Prilikom suzavanja reference moguce je da se baci ClassCastException.

- Boxing primitivne u neki neodgovarajuci wrapper ne prolazi kompajliranje.

byte = 1 bajt
short = 2 bajta
int = 4 bajta
long = 8 bajta
float = 4 bajta
double = 8 bajta
char = 2 bajta
boolean = 1 bajt

- Kompajler ne zna da li referenca u toku izvršavanja pokazuje na objekat koji ima valjanu metodu.

- Metode interfejsa se uvijek implementiraju kao metode instance, nikako kao staticke metode.

- Konstante interfejsa moraju biti inicijalizovane.

- Nestaticki blok vidi sve reference koje pripadaju objektu, ali redoslijed izvrsavanja ide odozgo ka dole.
1) Staticki blok nadklase ( ako je potrebno )
2) Staticki blok podklase ( ako je potrebno )
3) Nestaticki blok nadklase
4) Konstruktor nadklase
5) Nestaticki blok podklase
6) Konstruktor podklase ---> paziti na redoslijed nestatickog inicijalizacionog bloka i polja klase

- Lokalne promjenljive se ne inicijalizuju same prilikom njihovog kreiranja kod poziva metode, tj. Kada započne izvršavanje metode ( isto važi i za konstruktore i blokove)

- Switch radi sa byte, short, char i int primitivnim tipovima, odgovarajućim okružujućim klasama, enum tipovima i String klasom

- Kod switch-case, kod case stavke mora biti neka konstanta ili final promjenljiva, ne moze promjenljiva ako nije final.(Error)

- U for petlji može višestruka inicijalizacija ali promjenljive moraju biti istog tipa (for(int i=0, j=0; i<n; i++))

- Reference this i super nisu dostupne iz statičkog konteksta jer se statički kod ne izvršava u kontekstu objekta

- super ili this su prvi izrazi u tijelu konstruktora

- super i this ne mogu nikako biti zajedno u istom konstruktoru

- instanceof:
	* vraća false ako je operator s lijeve strane null
	* zahtjeva provjeru za vrijeme kompajliranja i izvršavanja
    * Provjera za vrijeme kompajliranja veza tip-podtip
    * Za vrijeme izvršavanja je bitan stvarni tip objekta, a ne deklarisani tip.

- Ako klasa nasljednica želi da koristi metodu clone(), mora implementirati Cloneable interfejs, inače će se pri pokušaju kloniranja objekta te klase baciti CloneNotSupportedException

- Metoda hashCode() - vraća memorijsku adresu objekta podrazumijevano

- okružujće klase su final klase(Integer, Double)

- Sve klase u fajlu mogu imati main(String[] args) metodu. Pokrenuce onu metodu prve definisane klase u fajlu.

- Moze se pozvati main() metoda druge klase u fajlu u main() metodi prve klase preko punog naziva druge  klase (obicni poziv staticke metode), ali main metoda te druge klase mora imati argument. Ako nista mora biti null kao argument.

- Ako main() ima throws i ako se u mainu ne uhvati neki izuzetak nego proslijedi iznad taj izuzetak ce biti ispisan.

- ClassCastException ako B nasljedjuje A  B b = new A() ne kompajlira se, a ako se B b = (B)new A() kompajlira se ali imamo ClassCastException(runtime exception)

- ((A2) a3).metoda2(); i A2 i A3 imaju metodu metoda2()(redefinisana) iako smo kastovali obj a3 u A2 pozvace se metoda iz A3, jer je a3 objekat tipa A3 a koja metoda ce se pozvati se gleda po tome koji je stvarni tip objekta a ne koji je tip reference(polimorfizam), ako a3 nema metoda2() a a2 ima, iako je a3 kastovano u a2 program ce puci(runtime) jer stvarni objekat nema tu metodu nevezano za kastovanje(klk sam skontao)

- Za statičke i privatne metode, poziva se metoda prema tipu reference, cak i u polimofrizmu.

- Za ne-privatne i ne-statičke metode, poziva se metoda prema stvarnom tipu objekta zbog dinamičkog vezivanja (polimorfizma).

- kod Integer vazi slicno kao kod String klasa - interning, vai od -128 do 127
```java
Integer i = 5;
Integer j = 5;
System.out.println(i == j); //true
System.out.println( i == (new Integer(5)); //false
```
- Wrapper tipovi za brojeve se mogu kastovati samo preko svojih metoda, a ne npr:
  Integer i = d 
ili  
  Integer i = (Integer) d 
i slično.

```java
class Dog {}
Dog [][] theDogs = new Dog[3][]; 
System.out.println(theDogs[2][0]); //throws NullPointerException - only first dimension of array is initialized;
```

- ako roditeljska klasa ne posjeduje podrazumijevani konstruktor, onda ubacivanje poziva konstrukcije super() od strane kompajlera u konstruktore klasa nasljednica neće imati efekta – u ovakvim situacijama desiće se greška pri kompajliranju (paziti na ovo)

- obratiti paznju na maskiranje atributa klase sa argumentom funkcije

- Ako metoda zavrsava bacanjem izuzetka, return ne mora da postoji.

- new Random().nextDouble() → (0, 1.0) → uslov > 1.0
     možemo imati i if else, pa pišemo moguće scenarije

- Pri deklaraciji metode može da postoji samo jedan varargs parameter koji mora biti posljednji u listi parametara metode.

- **MODIFIKATORI**

- metoda ne smije biti private i abstract

- metoda ne smije biti static i abstract

- metoda ne smije biti final i abstract

- interfejs moze biti deklarisan kao apstraktan, ali je to redundantno

- enum ne moze biti deklarisan kao apstraktan

- klasa ne moze u isto vrijeme biti deklarisana kao abstract i final

- u interfejsu su metode podrazumijevano public (greska ce se javiti samo u slucaju da ih deklarisemo kao private ili protected)

- ukoliko redefinisemo metodu iz interfejsa i ne navedemo joj public kao modifikator pristupa, to znaci da je smanjena vidljivost, te ce se javiti greska

- staticke metode ne mogu biti definisane kao apstraktne (ne mogu biti redefinisane jer su staticke)

- klasa moze biti samo public, abstract i final (i bez modifikatora); ne moze biti private ili protected, jer joj se tada ne bi moglo pristupiti, pa je zabranjeno

- final reference uvijek pokazuju na isti objekat(taj objekat moze mjenjati vrijednosti atributa, obratiti paznju na to)

- konstante interfejsa su implicitno public static final

- sve metode u interfejsima koje nisu default su implicitno public i abstract

- Static-ka metoda se moze pozvati i preko reference objekta koji ima datu staticku metodu, ali se obicno koristi naziv klase.

- protected metode se mogu pozivati unutar te klase, unutar podklase i u drugim klasama unutar istog paketa, čak i ako te klase nisu podklase.

- **Redefinisanje (Override):**
1. identican naziv
2. isti modifikator pristupa ili manje restriktivan
3. identicna lista parametara
4. kovarijantni povratni tip (podtip originalnog tipa)
5. deklaracija podskupa originalnog skupa izuzetaka(ne mora se niti jedan navesti)
6. private, final i static (i default ako nisu u istom paketu) metode ne mogu biti redefinisane
7. kljucna rijec synchronized se moze koristiti ili izostaviti i ne utice na pravila redefinisanja
8. nije moguce redefinisati staticku metodu metodom instance, niti je moguce redefinisati metodu instance statickom metodom

- **Preklapanje (Overloading):**
1. u istoj klasi ili u naslijedjenoj klasi
2. naziv MORA biti identican
3. lista parametara NE SMIJE biti identicna(u suprotnom nije u pitanju overloading)
4. za sve ostalo nema ogranicenja
5. moguce je definisati metodu koja ima isti naziv kao konstruktor(klasa)

**Napomena:** ukoliko imamo dvije metode ciji se naziv i lista parametara poklapaju, a neki od ostalih uslova za redefinisanje nisu ispunjeni, docice do greske pri kompajliranju! (treba se odluciti ili za redefinisanje ili za preklapanje)
**Napomena:** moguce je imati iste uslove kao za redefinisanje za metodu koja je staticka u roditeljskoj, i u naslijedjenoj klasi; o tome koja metoda ce se izvrsavati odlucuje tip reference koji je zadat.

- **Maskiranje (Hiding):**
1. moguce je maskirati polja iz roditeljske klase u naslijedjenoj klasi; bitan je samo naziv - nije bitan tip, niti da li je polje static ili ne
2. moguce je maskirati staticku metodu roditeljske klase u naslijedjenoj klasi takodje statickom metodom; uslovi treba da budu isti kao za redefinisanje; 
ukoliko su potpisi metoda isti ( naziv i lista argumenata ), a neki drugi uslovi za redefinisanje nisu, docice do greske pri kompajliranju; 
ukoliko su potpisi razliciti, tada vec pricamo o preklapanju, i tu vise nema pravila;
Zahtjev za maskiranje staticke metode je isti kao i za redefinisanje obicne metode. // netačno

- **TIPOVI**

- nije moguce uraditi konverziju:
	long a = 5L;
	int b = a;
	
- medjutim, moguce je kastovati:
	long a = 5L;
	int b = (int)a;
	
- nad boolean tipom se mogu primijeniti &,|,^ osim operatora ~


- **UNUTRASNJE KLASE**

- Inner klase ne mogu imati staticka polja ni metode, osim ako su u pitanju static final polja, jer se one ne mijenjaju.

- Unutrasnje lokalne klase(unutar metode):
	- ne moze biti static
	- imaju pristup svim poljima koji pripadaju instanci klase (nestaticka polja)
	- imaju pristup samo finalnim ili efektivno finalnim lokalnim promenljivima iz metode u kojoj su definisane.
	- moze ih sve ispisati, ali pristup ima samo navedenim

- Nestaticke ugnjezdene klase:
	- mogu biti final, abstract
	- mogu imati samo nestaticke clanove
	- ima pristup svim clanovima klase, uključujući i privatne.

- Staticke ugnjezdjene klase:
	- ne mogu direktno pristupati clanovima obuhvatajuce klase (jedino preko instance spoljne klase)
	- klasa ugnjezdjena u interfejs je uvijek static
	- mogu imati i staticke i nestaticke clanove

- kada imamo anonimnu klasu new A(){...} to znaci da je to nova klasa koja je izvedena iz klase A (nasljedjuje A)

- kompajlerska greska ukoliko se anonimna klasa izvede iz finalne klase

- Pristup objektu anonimne klase se moze raditi samo preko API-a klase/interfejsa koja je nasledjena.

- Anonimna klasa može pristupati svim članovima svoje okružujuce klase osim ako je u statičkoj metodi. Ako se anonimna klasa nalazi u statičkoj metodi onda može pristupati samo statičkim elementima svoje okružujuce klase.

- objekat ugnjezdjene staticke klase kreiramo sa:
```java
Outer.Inner oi = new Outer.Inner();
```

- objekat ugnjezdjene unutrasnje klase kreiramo sa:
```java
Outer.Inner oi = new Outer().new Inner();
```


- **Enum**

- static values() - kolekcija svih vrijednosti

- ordinal()

- Konstruktor privatan, moguce napraviti, primjer sa intom i value metodom

- Enum ne moze biti abstract.

- Enum ne moze da se stavi final, iako je implicitno final.

```java
public enum Status {
    AKTIVAN(1), NEAKTIVAN(2), NEPOZNATO(0);

    private final int x;

    private Status(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }
}
```

- enum ne može naslijediti drugu klasu, enum ne može biti roditeljska klasa.


- **String**

- Immutable koncept

- Samo literali se izracunavaju prilikom kompajliranja i sredjuju se tako da pokazuju na isti objekat.

- Svi stringovi kreirani pomoću `new` su različiti objekti, iako recimo imaju istu sekvencu karaktera.

- String i StringBuffer su thread-safe, StringBuilder nije.

- Objekti `StringBuilder` i `StringBuffer` moraju biti konvertovani u `String` kako bi se mogli porediti (jer ne redefinišu equals, hashCode metode i ne implementiraju Compareable interfejs)

```java
String s = new String("hello"); StringBuffer sb = (StringBuffer)s;  // Compile error : Invertible types because there is no relationship between.

Object o = new String("hello"); StringBuffer sb = (String)o;       // Compile error : Incompatible types because String is not child class of StringBuffer.

Object o = new String("hello"); StringBuffer sb = (StringBuffer)o; // Runtime Exception : ClassCastException because 'o' is string type and trying to cast into StingBuffer and there is no relationship between String and StringBuffer.
```

- System.out.println(1 + 2 + “3”) -> "33"

- Konstantni izrazi koji se izračunavaju za vrijeme kompajliranja, a koji rezultuju identičnom sekvencom, dijeliće identičan objekat klase String.

- **Labele**

* nazivi labela se nalaze u posebnom prostoru imena, pa ne može doći do konflikta
* nalaze se ispred naredbe ili bloka koda
* greška -> dvije iste labele
* greška -> deklaracija promjenljive ne može biti ozhnačena labelom `label3: int i = 3;`
* `break labela`
* `continue labela`

- **IZUZECI**

- Kompajler zahtjeva da programsko bacanje izuzetka bude tako da se baca objekat koji je tipa Throwable.

- Hijerarhija: Throwable->Error
					    ->Exception->RuntimeException

- Checked exception moraju biti uhvaceni, unchecked ne moraju

- Redefinisanje metode zahtjeva da nova metoda baca sve, neke ili nijedan izuzetak pri cemu ako baca izuzetak on mora biti podtip izuzetka.( novi izuzetak moze biti necekirani)

- Try-with-resources, objekti koji implementiraju AutoCloseable interfejs uključujući i one koji implementiraju Closeable interfejs mogu se koristiti kao resursi. Resursi se zatvaraju obrnutim redoslijedom od redoslijeda kreiranja

- Metoda mora baciti izuzetke koji su podklase izuzetaka navedenih u throws klauzuli(vazi samo za checked)

- **Unchecked izuzeci**
-ArrayIndexOutOfBoundsException
-NullPointerException
-ClassCastException
-ArithmeticException
-NumberFormatException

-ukoliko u nekoj metodi bacamo izuzetak sa throw new Throwable() (bilo sta osim unchecked exception), tada moramo uraditi jednu od dvije stvari:
1. deklarisati metodu tako da se zna da baca taj izuzetak
2. formirati try-catch blok za taj izuzetak u bas toj metodi

- moguce je da redefinisana metoda baca Error ili RuntimeException iako metoda u roditeljskoj klasi ne baca nijedan izuzetak, to ne vazi za Throwable

- ukoliko postoji vise catch blokova, u bloku iznad ne smije biti supertip izuzetka ispod

- sleep(), close(), join() moraju biti u try/catch bloku ili deklarisati izuzetak u throws klauzuli metode

- yield(), start() ne bacaju exception

- finally blok se uvijek izvrsava; cak i ako je novi izuzetak bacen u catch bloku (nije bitno da li je obradjen ili nije)

- ukoliko je metoda u kojoj bacamo izuzetak override-ovana, a metoda superklase koju override-ujemo ima deklarisano da baca izuzetak, nije nuzno potrebno navesti da override-ovana metoda baca izuzetak. Potrebno je, medjutim tu gdje se ona poziva formirati try-catch blok // netačna 2. rečenica

- ako se u try bloku ne može nikako baciti izuzetak koji catch blok hvata - kompajlerska greska

- NE smije se nalaziti izuzeti sa različitih nivoa u hijerarhiji unutar multicatch bloka:
     ne može npr. catch (IOException | Exception e) -> kompajlerska greška


- **SERIJALIZACIJA/DESERIJALIZACIJA**

- transient promjenjive se ne serijalizuju, osim ako to ne uradimo eksplicitno

- staticke promjenljive se ne serijalizuju

**Napomena:** ako klasa implementira interfejs Serializable, a ima referencu koja referencira klasu koja ne implementira Serializable, doći će do greške

**Napomena:** pri implementiranju Externalizable interfejsa, konstruktor mora biti ili default ili javan

- ukoliko implementiramo Externizable interfejs, moramo redefinisati metode writeExternal i readExternal

- njihove deklaracije:
```java
public void writeExternal(ObjectOutput out) throws IOException
public void readExternal(ObjectInput in) throws  IOException, ClassNotFoundException
```

- ako implementiramo Externalizable interfejs, tada se pri deserijalizovanju prvo poziva konstruktor, a zatim readExternal() metoda

- ako koristimo metode writeObject i readObject, one moraju biti *private*, inace ce biti ignorisane; takodje, ove metode nisu dio niti jednog interfejsa niti klase, njih poziva JavaVM ukoliko ih pronadje u klasi koja implementira Serializable interfejs

- njihove deklaracije:
```java
 private void writeObject(ObjectOutputStream out) throws IOException
 private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
 private void readObjectNoData() throws ObjectStreamException
```

-ako imamo klasu koja nasljedjuje Externizable interfejs, mi sami definisemo sta ce se sacuvati a sta ne, transient modifikator nema nikakav uticaj vec ako se polje ne serijalizuje ono ce dobiti vrijednost kojom je inicijalizovana u konstruktoru ili bloku, dok kod Serializable ce transient polje dobiti vrijednost nula

- Redoslijed deserializacije je bitan
   - Ser: Int + Long = 4 + 8 = 12
   - Deser: Long + Int = 8 + 4 = 12
   - prolazi jer je veličina dovoljna, u suprotnom -> exception

- ako klasa implementira Serializable, a ima `writeExternal` i `readExternal` metode onda se one tretiraju kao obične metode

- ako postoji hijerarhija, ide super.readObject pa tek onda readObject

- **NIO**

(nema konkretnih detalja korisnih za usmeni)
- Java NIO file paket:
	- Path - Analogan File objektu. Moguće ga kreirati pomoću File objekta, Paths.get(String) i postoječih Pathova
	- Files klasa sadrži statičke metode za manipulaciju fajlovima: copy, createDirectory, createFile,delete, exists, move, walkFileTree (za kretanje po direktorijumu), readAllLines, readAllBytes, write (bajtovi ili karakteri)

- FileVisitor - implementacija čije se metode pozivaju kada se pozove walkFileTree(visitor)
- Metode koje treba implementirati:
1. postVisitDirectory(T dir, IOException exc)
2. preVisitDirectory(T dir, BasicFileAttributes attrs)
3. visitFile(T file, BasicFileAttributes attrs)
4. visitFileFailed(T file, IOException exc)
- Metode vraćaju FileVisitResult koji govori šta treba sljedeće da se desi, moguće vrijednosti:
1. CONTINUE
2. SKIP_SIBLINGS
3. SKIP_SUBTREE
4. TERMINATE

- WatchService - Za praćenje promjena unutar direktorijuma
- WatchKey key = dir.register(watchService,ENTRY_CREATE,ENTRY_DELETE,ENTRY_MODIFY);
- registrujemo watchService na odredjene dogadaje.
- Pomoću metoda poll() (Vraća ukoliko postoji, u suprotnom null) i take() - čeka dok se ne desi,
čekamo događaj.
https://docs.oracle.com/javase/tutorial/essential/io/notification.html

- Baferi i kanali.
- Bafer ima kapacitet, trenutnu poziciju i predstavlja podatke. Potrebno je alocirati kapacitet prilikom kreiranja.
- Korisnik radi sa bufferom (čita i piše sa njega) i metodom flip() mijenja način rada.
- Postoje baferi samo za primitivne tipove, ne i za string i objekte.
- Kanali predstavljaju kanal komunikacije, postoje FileChannel, DatagramChannel, SocketChannel.
- Kanali rade sa bufferima (čitaju i pišu iz njih).
- FileChannel - FileInputStream.getChannel() ili FileOutputStream.getChannel()
- Moguć je transfer iz jednog u drugi kanala (jedan od kanala mora biti FileChannel);
- Neblokirajuće operacije, ne čeka se na čitanje/pisanje, npr. ukoliko postoji nešto za čitanje, pročitaće se u buffer, neće se čekati, slično i za upis.
- NIO Selector omogućava upravljanje i monitoring više kanala na jednom mjestu, slično WatchService-u.


- **THREADOVI**

- Java aplikacija je u radu sve dok ne završe sve korisničke niti.

- Posebna vrsta niti (demonska) traje sve dok traju korisničke niti i gasi se kad se završe sve korisničke.

- Korisne metode:
- Thread.sleep(TIME) - Trenutna nit spava specifikovani vremenski period.
- Thread.yield() - Nit "predaje" izvršavanje scheduleru (Scheduler može, ali i ne mora da dodijeli procesorsko vrijeme drugoj niti)
- threadObject.join() - Trenutna nit čeka na kraj izvršavanje niti nad kojom poziva metodu.

- Najveći problemi u sinhronizaciji niti:
	* Race condition- dvije niti istovremeno mijenjaju neku vrijednost
	* Dead lock- prva nit čeka na drugu, a druga nit čeka na prvu, i tako beskonačno čekaju
	* Live lock - prva nit prepušta izvršavanje drugoj, a druga nit prepušta prvoj, i tako ukrug
	* Liveness - Kada jedna nit zauzme na dug period procesorsko vrijeme i druge niti ne stižu da se izvršavaju

- Kombinacijom zaštićenih dijelova koda i wait/notify mehanizma rješavamo najčešće probleme u sinhronizaciji, i to upotrebom guarded blockova.
- Guarded blockovi su dijelovi koda koji se nalazi u zaštićenim dijelovima, a izvršavaju se tek kad se stekne neki uslov. 
```java
synchronized(objekat){ // Čitav kod je u zaštićenom dijelu
	while (!objekat.uslovIspunjen()){ // Kod neće izaći iz petlje dok se određeni uslov ne ispuni
		try{
			objekat.wait() // Thread čeka da mu se javi da je nešto od interesa promijenjeno u objektu,
			// tj. neko je pozvao notify/notifyAll nad objektom, i izlazi iz zaštićenog dijela koda
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	// KOD koji radi nešto
	objekat.notifyAll() // Ukoliko je potrebno da se obavjeste drugi threadovi koji čekaju na notifikaciju
}
```

- Sinhronizacija konstruktora nije moguća – sintaksna greška

- thread.run ne pokrene se novi tred nego se izvrsava u okviru treda iz kojeg se poziva moze se pozvati vise puta

- this.join super.join beskonacna petlja ili ako pozove sama sebe stackOwerfllow

- ako pozovemo setDeamon poslije poziva start() metode baca se IllegalThreadStateException

- ako pokušaš da pozoveš wait(), notify(), ili notifyAll() van sinhronizovanog bloka, biće bačen IllegalMonitorStateException.

- Metoda `start` može baviti izuzetak `IllegalThreadStateException` u slučaju kada se pozove nad objektom nad kojim je već pozvana metoda start.

- Redefinisana start() metoda, u kojoj nema super.start() -> ne kreira se nova nit

- `t2 = new Thread(t1)` → t2 dobija samo run metodu, ostale stvari se ne preuzimaju (ako je t1 demonska metoda, pisaće i da je t2 demonska, iako ona to stvarno nije) // netačno, t2 neče biti demonska, provjeriti u kodu


- **GENERICI**

- tipske promjenljlive moraju biti poznate u vrijeme kompajliranja kako bi se genericki tip mogao koristiti

- tipskoj promjenjivoj T se ne moze pristupiti iz statickog konteksta

- Moguće je ograničiti koji se tipovi mogu koristiti u generičkoj klasi metodi npr.
```java
class Array<T extends Person & Serializable>{}
```
- Moguće je navesti 0 ili više interfejsa koje klasa mora da implementira i 0 ili 1 klasu koju može da nasljeđuje.

- Nije moguće koristiti primitivne tipove u generičkim klasama/metodama već se moraju koristiti Wrapper tipovi.

- Ograničavanje tipova `T extends Number` onda možemo koristiti i metode iz Number

- Svaku generičku klasu je moguće dodijeliti referenci koja nija parametrizovana.
```java
Holder<Integer> intHolder=new Holder<>();
Holder holder=intHolder;
Holder<Integer> secondIntHolder=(Holder<Integer>)holder;
```

- Osnovni interfejsi:
    * List - uređena lista elemenata,
    * Set - skup elemenata koji se ne mogu ponavljati
        * SortedSet - nasljeđuje Set i omogućava da se elementi sortiraju po nekom kriterijumu
            * NavigableSet - nasljeđuje SortedSet i omogućava navigaciju kroz njega
    * Queue - red, dodavanje moguće samo na kraj.
        * Deque - nasljeđuje Queue, omogućava dodavanje elementa sa oba kraja 
    * Map - rad sa parovima ključ/vrijednost, ključ mora biti jedinstven
        * SortedMap - nasljeđuhe Map i omogućava da se parovi sortiraju
            * NavigableMap - nasljeđuje SortedMap i omogućava navigaciju

- Implementacije:
   * List: ArrayList - niz promjenljive duzine, LinkedList - ulančana lista , Vector - slična ArrayList-i, sinhronizovano i efikasnije upravlja prostorom.
   * Set: HashSet - čuva el. u hash tabeli , LinkedHashSet - hash tabela sa ulancanom listom
   * NavigableSet: TreeSet - balansirano stablo na osnovu nekog kriterijuma
   * Queue: PriorityQueue - elementi su sortirani po nekom kriterijumu, LinkedList
   * Deque: ArrayDeque, LinkedList
   * Map: HashMap, LinkedHashMap - nasljedjuje HashMap,Hashtable
   * NavigableMap: TreeMap - crveno-crno stablo

- HashMap ima dva parametra koji utiču na permormanse: inicijalni kapacitet(podrazumijevano je 16) i faktor operećenja(0.75)

- Hashtable, neuređena, sinhornizova, bez null vrijednosti i null ključeva. Kapacitet podrazumijevano je 11 a load factor 0.75

- Prilikom upotrebe kolekcija koje omogućavaju da se sortiraju po nekom poretku i porede, najčešće je potrebno da klase čiji se elementi nalaze u kolekciji implementiraju Comparable interfejs, ili se kolekciji prosljeđuje Comparator interfejs.

```java
public interface Comparable<T> { // Nasljeđuje ju klasa čiji su elementi u kolekciji
    public int compareTo(T o); // < 0 ako je primljeni objekat manji, 0 ako je isti, a > 0 ako je veći od tekućeg objekta 
}

public interface Comparator<T> { // Proslijedimo ju kolekciji
    int compare(T o1, T o2); // <0 ako je o1 manji od o2, 0 ako su isti, >0 ako je o1 veci od o2
}
```

- Klasa Collections obezbjeđuje različite metode za rad sa kolekcijama, poput sortiranja, pretraživanja i zamjene elemenata u kolekciji. Sve metode ove klase su deklarisane kao static i public.

- Wildcard tipovi označavaju nepoznat tip. <?>

- Object je natklasa svih klasa, ali kolekcija objekata nije natklasa nijedne kolekcije.

- Na primjer List<Object> nije natklasa List<String> i ukoliko probamo dodijeliti List<Object> varijabli List<String>, dobićemo gršku prilikom kompajliranja, slično je i sa svim ostalim tipovima.

```java
public static void paintAllBuildings(List<Building> buildings) {
	for (Building building:buildings)
		building.paint();
}
```
- Ukoliko recimo postoji House koja je potklasa od Building, mi ne možemo iskoristiti ovu metodu, iako je House potklasa od Building. U tom slučaju možemo iskoristi wildcard:
```java
public static void paintAllBuildings(List<? extends Building> buildings) {
	for (Building building:buildings)
		building.paint();
}
```

- Ovo se zove upper bound wildcard, jer je Building gornja granica.
- Kod ovakvog wildcard-a možemo pristupiti elementu Kolekcije,ali ne možemo mijenjati kolekciju

- Takođe, moguće je definisati lower bound wildcard <? super Building> gdje je Building donja granica, odnosno nepoznati tip je Building ili neka od natklasa od Building klase.
- U ovom slučaju možemo mijenjati kolekciju, ali ne možemo pristupiti elementu, osim ako ga kastujemo u Object.

- Postoji i unbounded wildcard <?> koji je sličan upper bound wildcard-u, odnosno možemo ga posmatrati kao da smo napisali <? extends Object>

- Joker znakovi se NE smiju koristiti u sljedecim situacijama:
 1. u zaglavlju deklaracije referentnog tipa
 2. u extends/implements klauzulama super tipova
 3. u izrazima za kreiranje instanci (tada definisemo stvarni tip)

```java
GenericHolder holder = new GenericHolder<Integer>();// holder je raw tip sto rezultira tome da kompajler ne provjerava poklapanje tipova generika, sto nije preporucljivo i bice upozorenje
holder.set("10");// proci ce kompajliranje jer se tipovi ne provjeravaju
GenericHolder<Byte> holder2 = holder;// isto prolazi kompajliranje jer se raw tip moze pridruziti nekom specificnom tipu
System.out.println(holder2.get()); //ispisace 10 (nisam siguran)
Byte s = holder2.get(); //program ce puci (provjeriti dodatno)
```


- **FUNKCIONALNI INTERFEJSI**

- Da bi se on koristio moramo definisati klasu koja implementira interfejs. Često se za ovakve interfejse definišu unutrašnje klase, kada ih želimo koristiti samo u jednoj klasi, ili čak i anonimne klase.
- Čest primjer do Jave 8:
```java
class KlasaKomparator implements Comparator<Klasa>{
    @Override
    int compare(Klasa o1,Klasa o2){
        return o1.test(o2);
    };
}
```

- **LAMBDA IZRAZI**

- Gore navedeni primjer sada postaje:
```java
Collections.sort(listaObjekata,(o1,o2)->{
        return o1.test(o2);
});
```

- Ukoliko tijelo lambda izraza sadrži samo return izraz vitičaste zagrade i klučna riječ return nisu potrebne.

- Takođe ukoliko tijelo lambda izraza sadrži samo jedan izraz (npr. System.out.println ili poziv neke druge funkcije) takođe je moguće izostaviti vitičaste zagrade

```java
Collections.sort(listaObjekata,(o1,o2)->o1.test(o2));
```

Ukoliko metoda ima samo jedan parametar moguće je izostaviti i male zagrade
```java
button.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
		doSomethingWith(e);
	}
});

button.addActionListener(e -> doSomethingWith(e));
```

- Važno, this u lambda izrazima se odnosi na objekat klase u kojoj se nalazi metoda koja poziva lambda izrazima, pa je moguće i pristupati podacima članovima te klase.

- Takođe, moguće je refencirati ali ne i mijenjati promjenljive koje se nalaze u metodi koja okružuje lambda izraz.

```java
class A{
    int a;
    void drugaMetoda(){...}

    void metoda(){
		// Do something...
        int c;
        List<Integer> list=new ArrayList<Integer>;

        Collections.sort(list2,(o1,o2)->{
            // Do something...
            this.drugaMetoda() // Odnosi se na objekat klase A
            this.a; // Odnosi se na objekat klase A
            list=
            c=8;// odnosi ne na promjenljivu int c definisanu u metodi
        })
    }
}
```

- Reference metoda:

- Osnovna ideja jeste koristiti referencu metode kako bi se neka postojeća metoda mogla koristiti i tretirati kao lambda izraz
```java
variable -> variable.getMethod();
variable::getMethod;
```
- Kod refernciranja statičkih metoda koristi se notacija Klasa::metoda

- Reference konstruktora su identične referencama metoda, osim što je naziv metode uvijek „new“:
```java
Klasa::new
new Klasa();
```

- U java.util.function paketu postoje generički funkcionalni interfejsi koji "predstavljaju" osnovne funkcije:
1. Function<T,R> - predstavlja funkciju koja prihvata jedan argument tipa T i vraća rezultat tipa R
	* R apply(T t)
2. Predicate<T> - predstavlja funkciju koja prihvata jedan argument tipa T i vraća rezultat tipa boolean
	* boolean test(T t)
3. Consumer<T> - predstavlja funkciju koja prihvata jedan argument tipa T i ne vraća rezultat, tj. povratni tip je void
	* void accept(T t)
4. Supplier<T> - predstavlja funkciju koja nema argumente i vraća rezultat tipa T
	* T get()
5. BiFunction<T,U,R> - predstavlja funkciju koja prihvata dva argumenta tipa T i U i vraća rezultat tipa R
	* R apply(T t, U u)
6. BiPredicate<T,U> - predstavlja funkciju koja prihvata dva argumenta tipa T i U i vraća rezultat tipa boolean
	* boolean test(T t, U u)
7. BiConsumer<T,U> - predstavlja funkciju koja prihvata dva argumenta tipa T i U i ne vraća rezultat
	* void accept(T t, U u)

8. UnaryOperator<T> - predstavlja operaciju nad jednim operandom koja vraća rezultat istog tipa kao što je tip operanda, specijalizacija Function funkcije koja radi sa argumentom tipa T i vraća rezultat tipa T

9. BinaryOperator<T> - predstavlja operaciju nad dva operanda istog tipa koja vraća rezultat istog tipa kao što je tip operanada, specijalizacija BiFunction funkcije koja prihvata dva argumenta tipa T i vraća rezultat tipa T


- **STREAM API**
 
- Ne podržavaju indeksiran pristup, moguće je zahtijevati prvi element, ali ne i drugi, treći, itd.

- Većina Stream operacija se odlaže dok se ne utvrdi koliko podataka je potrebno.

- forEach - Prolazi kroz sve elemente niza pomoću Consumer<T> - terminalna metoda
```java
empList().stream().forEach(e -> e.setSalary(e.getSalary() * 11/10));
```

- Map - Na osnovu Function<T,R> transformiše element stream-a iz T->R
```java
Stream<String> stream = list.stream();
stream.map(e -> e + e).forEach(e -> System.out.println(e));
```

- Collect - Pomoću collect terminalne metode moguće je kreirati kolekciju na osnovu streama.
```java
List<Product> products = Arrays.asList(new Product("Shoes A", "Running Shoes", 20), new Product("Shoes B", "Running Shoes", 55), new Product("Shoes C", "Soccer", 30));
List<String> productsList= products.stream().map(Product::getName).collect(Collectors.toList());
```

- Filter - Na osnovu Predicate<T> filtrira elemente streama

- Find First - Terminalna, pronalazi prvi element streama
- orElse - vraća prosljeđeni objekat ukoliko je stream prazan.
```java
Integer optiInt = intStream.filter(e -> e > 8).findFirst().orElse(0);
System.out.println(optiInt);
```

- Reduce - kombinuje sve elemenate stream-a u cilju kreiranja jedne vrijednosti (koja predstavlja rezultat operacije redukcije) - terminalna metoda
```java
Optional<T> reduce(BinaryOperator<T> accumulator);

Stream<Integer> stream = list.stream();
Optional<Integer> result = stream.distinct().filter(e -> e > 100).reduce((e, accumulator) -> e + accumulator);
// Rezultat je zbir svih elemenata vecih od 100;
```

Još neke međuoperacije:
```java
Stream<T> distinct() // uklanaj duplikate
Stream<T> limit(long maxSize) // ogranicava kolicinu elemenata
Stream<T> skip(long n) // preskace n elemenata
Stream<T> sorted() // sortira
Stream<T> sorted(Comparator<? super T> // sortira pomocu comparatora
```

Još terminalnih operacija:
```java
Object[] toArray() // Vraca niz 
Optional<T> min(Comparator<? super T> comparator) // pronalazi "najmanji" element na osnovu komparatora
Optional<T> max(Comparator<? super T> comparator) // pronalazi "najveci" element na osnovu komparatora
long count() // vraca broj elemenata streama
boolean anyMatch(Predicate<? super T> predicate)  //vraca true ako bar jedan element zadovoljava predicate
boolean allMatch(Predicate<? super T> predicate) //vraca true ako svi elementi zadovoljavaju predicate
boolean noneMatch(Predicate<? super T> predicate) // vraca true ako nijedan od elemenata ne zadovoljava predicate
Optional<T> findFirst() // pronalazi prvi element streama
Optional<T> findAny() // pronalazi bilo koji element stream-a
```

- stream intermediate operacije se ne izvrsavanju ako nema terminalne, lazy su!!!


- **CAKE**

- A2 ima polje tipa A3, A3 nasljedjuje A2. Pokusas kreirati jednog, upadnes u beskonacnu petlju.
		
- Bazna klasa neke klase nema defaultni konstruktor, a nemamo nigdje: super(param1, param2)

- Konstruktor ne poziva drugi konstruktor sa this, nego tako sto navede ime klase // greška, suprotno

- Object clone(Object) je protected metoda, imati na umu u slucaju da se koristi za redefinisanje

- Izuzeci. Napravi par klasa, koje nasljedjuju Exception, RuntimeException, Throwable, pa ih baci u nekim metodama, pa je pitanje koje od njih mora prijaviti, koje mora uhvatiti, da li je ispravan red catch naredbi. Sta ako i try i finally imaju return - koja se uzima, sta ako catch baci izuzetak, sta ako finally baci izuzetak. Smijes li imati try-catch blok u kom se ne baca nista i slicno.

- Niti. Napravi i pokrene nekoliko threadova, napravi neke objekte koje koristi za zakljucavanjem, i pokrene niti. Najlakse ti je da ih crtas, tipa jedna linija predstavlja main nit, pa onda kako se koja pravi ti granas tu liniju, oznacavas gdje je wait, gdje je join i napises za koje stvari ne znas red kojim se izvrsavaju, za koje znas.

- Interfejsi. Imas 4, 5 interfejsa, neki se nasljedjuju, neki imaju iste ili slicne metode, par klasa koje implementiraju te interfejse, pa treba da vidis da li to moze (klasa implementira interfejs, a metodu iz interfejsa oznaci kao protected). U mainu onda pravi instance klasa, dodjeljuje ih referencama interfejsa (MojInterfejs aa = new MojaKlasa()),pa onda cast-a, poziva metode i slicno.

- Unutrasnje i anonimne klase. Da ti par klasa, neke staticke, neke ne, neke ugnjezdene, neke ne pa kreira i poziva metode. Bitno ti je da znas one unutrasnje kako se kreiraju, sta ako je unutrasnja staticka, vanjska ne, sta ako je obrnuto i slicno. Da znas kojim dijelovima okruzujuce klase mogu pristupiti i slicno.

- Genericke klase, napravi nekoliko obicnih klasa, nekoliko generickih, pa gleda kako se snalazis sa onim: Klasa<? extends NekaDrugaKlasa> i slicno. Isto i raw tipovi (umjesto ArrayList<Integer> kazes ArrayList, sve ce raditi, ali moras cast-ovati) 

- Memorija. Imas kod, velicinu heapa (tipa 1450MB) i kaze ti da se garbage collector pokrece samo na pozivima GC.collect() (koji se nalazi u kodu na par mjesta) i kad se kreira novi objekat.
U kodu obicno imas dve, tri klase, obe imaju neke nizove za polja, tako da jedan objekat klase bude cca 50MB, i onda u mainu kreira neke, pa kreira nizove njih, pa neke reference brise i slicno.
Najlakse nacrtati stack s jedne strane, heap sa druge, kako se kreira, crtati, kad treba da se brise vidis koje dostupan sa stacka ko ne.
Cake:
	- Staticka polja se prave jednom za klasu (ne instancu)
	- Matrice su nizovi nizova, i takvi se cuvaju u memoriji
	- Kod padne zbog neceg desetog (kod mene je bilo ArrayIndexOutOfBoundsException)