#pragma once
KONSTRUKTOR KOPIJE I MOVE KONSTRUKTOR
{
    !!!!!!KONSTRUKTOR KOPIJE IMA SAMO JEDAN ARGUMENT, NE POSTOJI DRUGI ARGUMENT!!!!!!!!!!!!!!!!!!

    KK - Slican normalnom konstruktoru, ali kreira kopiju postojeceg objekta.
    MOVE - Pravimo kopiju objekta(koji ce biti unisten) koji u sebi sadrzi dinamicki objekat tako da kopija preuzima dinamicki objekat originala
    i nakon toga original nema veze sa dinamickim objektom

Konstruktor se moze iskoristiti za implicitnu konverziju :
class Osnovna {
int i;
public:
Osnovna(int i) { this->i = i; }
void Dodaj(Osnovna);
};
void Osnovna::Dodaj(Osnovna drugi)
{
    i += drugi.i;
}
Osnovna o(30);
o.Dodaj(40);
// Funkcija Dodaj ocekuje argument tipa Osnovna; Konstruktor ove klase bice automatski pozvan da izvrsi implicitnu konverziju int u Osnovna 

Ukoliko zelimo da zabranimo implicitnu(automatsku) konverziju ispred konstruktora navodimo kljucnu rec explicit:
class Osnovna {
    int i;
public:
    explicit Osnovna(int i) { this->i = i; }
    void Dodaj(Osnovna);
};
void Osnovna::Dodaj(Osnovna drugi)
{
    i += drugi.i;
}
Osnovna o(30);
o.Dodaj(40); // Ovo kompajler nece dozvoliti
o.Dodaj(Osnovna(40));  // Ovo prolazi bez problema
}

PRIJATELJI KLASA
{
Povlasceni korisnici mogu biti:
    funkcije(globalne ili clanice drugih klasa)
    cele klase.​

Funkcija je prijateljska ako se u definiciji klase navede deklaracija funkcije ​sa ključnom reči friend ispred.
Prijateljska funkcija nema pokazivač this na objekat klase kojoj je prijatelj.​
Prijateljstvo je relacija koja reguliše pravo pristupa, a ne oblast važenja i ​vidljivost identifikatora.

Osobine prijateljstva : ​
 ne nasleđuje se, ​
 nije simetrična relacija ​
 nije tranzitivna relacija

Prijateljske funkcije mogu da budu : ​
 globalne funkcije ili ​
 članice drugih klasa
}

PREKLAPANJE OPERATORA
{
1.Ne mogu da se preklope operatori ., .*, ::, ?:, sizeof
2.Ne mogu da se redefinisu znacenja operatora za primitivne(standardne) tipove podataka
3.Ne mogu da se uvode novi simboli za operatore
4.Ne mogu da se mijenjaju osobine operatora koje su ugradjene u jezik: n-arnost, prioritet i asocijativnost
    
Operatorske funkcije mogu biti : ​
Funkcije članice(ne statičke) kod kojih je skriveni argument levi operand ili ​
globalne funkcije(uglavnom prijatelji klasa) kod kojih je bar  jedan argument tipa korisničke klase.​
Nije dozvoljeno da operator istovremeno bude preklopljen i funkcijom članicom i globalnom funkcijom


PREDEFINISANI operatori su operatorske funkcije koje su unaprijed definirane u jeziku C++.
Te funkcije su automatski pozvane kada se primijeni neki operator na određeni tip podataka.
Aritmetički operatori : +, -,*, / ,% .
Operatori uspoređivanja : == , != , <, >, <= , >= .
Operatori pridruživanja : =, +=, -=, *=, /=, %= i tako dalje.
Operatori inkrementiranja i dekrementiranja : ++, --.
Operatori logičkog i bitovskog pomicanja : &&, || , !, &, | ,^, << , >> .
Operatori indeksiranja : [] .
Operatori članstva : ., ->. 

3. Kako se kreiraju imena operatorskih funkcija ?
Imena operatorskih funkcija sastoje se od riječi operator sljedećeg znaka koji predstavlja željeni operator.

Kod operatorske f-je clanice klase levi operand je skriveni argument - objekat date klase. Ne dozvoljava konverziju levog operanda.
Ako lijevi operand operatorske funkcije treba da bude standardnog tipa mora se definisati prijateljska f - ja u klasi drugog argumenta
Npr: complex operator-(double d, complex c) mora biti prijateljska, a ne clanica

Unarni operator ima samo jedan operand, pa se moze realizovati:
    1.kao operatorska f-ja clanica bez argumenata:
       tip operator@ ()
    2.kao globalna f-ja sa jednim argumentom:
       tip operator@ (X x)

Binarni operator ima dva argumenta, pa se moze realizovati:
    1.kao operatorska f-ja clanica sa jednim argumentom:
       tip operator@ (X desni)
    2.kao globalna f-ja sa dva argumenta:
       tip operator@ (X levi, X desni)


Operator preinkrementiranja ++obj objekta klase MyClass kompajler prevodi u  obj.operator++( ) ili operator++(obj), 
tako da se mora deklarisati i definisati kao:
funkcija članica:​
MyClass& operator++ ();​

Ili kao globalna funkcija​
MyClass& operator++ (const MyClass&);

    
Operator postinkrementiranja obj++ objekta klase MyClass, kompajler prevodi u obj.operator++(0) ili operator(obj, 0), a deklariše se na sledeći način : ​
kao funkcija članica​
const MyClass operator++ (int); ​

kao globalna funkcija​
const MyClass operator++ (const MyClass&, int); ​

int “dummy” argument je samo pomoć kompajleru da napravi razliku između prefiksnog i postfiksnog operatora.​
Prefiksni operator vraća referencu MyClass& na inkrementiranu vrednost​
Postfiksni operator vraća const objekat MyClass, tj.kopiju* this koja još nije inkrementirana.

!!!!!!!!!!!!!!VAZNO!!!!!!!!!!!!!! 
Zašto postfix operator mora da vrati const objekat ? 
​
Razlog tome je što postfix operator vraća kopiju originalnog objekta prije nego što ga modifikuje.
Budući da se vraća kopija, ne bi bilo ispravno da korisnik može mijenjati taj objekat nakon što se primijeni operator 
inkrementiranja ili dekrementiranja.Zato se vraća konstantan objekat, koji se ne može mijenjati, iako je modifikovan originalni objekat.




Operatori inkrementiranja i dekrementiranja mogu da se preklope za enumeracije. U tom slučaju se preklapaju kao globalne funkcije.​
enum Grupa {A,B,C,END};​
Grupa &operator++(Grupa &g)​
{ return g=(g==END)?A:Grupa(g+1);}​
const Grupa operator++(Grupa &g,int)​
{ ​
Grupa tmp=g;​
++g;​
return tmp;​
}​​



RETURN VALUE
Vrlo često je moguće(ali to zavisi od kompajlera) da se prilikom vraćanja objekta eliminiše cena kreiranja i brisanja privremenog objekta.
Trik je u vraćanju argumenata konstruktora umesto objekta.Tom prilikom se primenjuje tzv.“Return Value” optimizacija.


Preklapanje operatora poređenja​
Ovi operatori treba da vrate rezultati tipa bool i da budu preklopljeni kao konstantne operatorske funkcije​
Obično se kompletan skup operatora definiše na osnovu operatora == i < .

    class MyClass {
        //…​
    public:​
        bool operator ==(const MyClass&) const; ​
        bool operator !=(const MyClass& that) const​
        {
        return !(*this == that);
        }
        bool operator <(const MyClass&) const; ​
        bool operator >(const MyClass& that) const​
        {
        return that < *this;
        }   ​
        bool operator >=(const MyClass& that) const​
        {
        return !(*this < that);
        }​
        bool operator <=(const MyClass& that) const​
        {
        return !(that < *this);
        }   ​
}; ​

}

NASLJEDJIVANJE I POLIMORFIZAM
{
Izvedena klasa ne nasleđuje : ​
  funkciju članicu operator=, ​
  konstruktore osnovne klase       ​
  destruktor osnovne klase.​


Izvedena klasa može biti osnovna klasa za sledeće izvođenje ​

Pri kreiranju objekta izvedene klase redosled poziva konstruktora je sledeći : ​
inicijalizuje se podobjekat osnovne klase, pozivom konstruktora osnovne klase; ​
inicijalizuju se podaci članovi, eventualno pozivom njihovih konstruktora, po redosledu deklarisanja; ​
izvršava se telo konstruktora izvedene klase.​

Pri uništavanju objekta, redosled poziva destruktora je uvek obratan : ​
 najpre se izvršava destruktor izvedene klase​
 zatim se izvršavaju destruktori objekata članova​
 i na kraju se izvršava destruktor osnovne klase ​

Objekat javno izvedene klase se može posmatrati kao objekat osnovne klase.​

---------------------------------------------------------------------------------------------------------------
VIRTUELNE F-JE

Funkcije članice osnovne klase koje se u izvedenim klasama mogu redefinisati, 
a ponašaju se polimorfno, nazivaju se virtuelne funkcije(engl. virtual functions).​

Virtuelni mehanizam se aktivira samo ako se objektu pristupa preko reference ili pokazivača.​

DINAMICKO POVEZIVANJE

Mehanizam koji obezbeđuje da se funkcija koja se poziva određuje po tipu objekta, 
a ne po tipu pokazivača ili reference na taj objekat, naziva se dinamičko povezivanje.​
Odlučivanje koja će se virtuelna funkcija pozvati obavlja se u toku izvršavanja programa - dinamički.​
Bitna je razlika u odnosu na mehanizam preklapanja imena funkcija koji je statički.​
Virtuelna funkcija osnovne klase ne mora da se redefiniše u svakoj izvedenoj klasi, tada važi funkcija iz osnovne klase.​
Deklaracija virtuelne funkcije u izvedenoj klasi mora da se potpuno slaže sa deklaracijom iste u osnovnoj klasi.​
Virtuelne funkcije moraju biti nestatičke članice svojih klasa, a mogu biti prijatelji drugih klasa.

Ako se u izvedenoj klasi deklariše neka funkcija koja ima isto ime kao i virtuelna funkcija iz osnovne klase,
ali različit broj i / ili tipove argumenata, onda ona sakriva sve ostale funkcije sa istim imenom iz osnovne klase.​
U izvedenoj klasi treba ponovo definisati sve ostale funkcije sa tim imenom.​
Nije dobro da izvedena klasa sadrži samo neke funkcije iz osnovne klase : ne radi se o pravom nasleđivanju.
Korisnik izvedene klase očekuje da će ona ispuniti sve zadatke koje može i osnovna klasa.

class A {
public:​
   virtual int f() { return 1; }​
}; ​
class B : public A {
public:​
    virtual int f() { return 2; }​
}; ​
int main()​
{
   A* pA = new B(); ​
   pA->f();  // dinamički se povezuje funkcija B::f()​
   pA->A::f();  // forsira se statičko povezivanje, od kompajlera se eksplicitno traži da pozove funkciju A::f()​
}​

-----------------------------------------------------------------------------------------------------------------------
DINAMICKO POVEZIVANJE   
​
Virtuelna funkcija koja nije definisana za osnovnu klasu naziva se čistom virtuelnom funkcijom.​
Deklaracija čiste virtuelne funkcije u osnovnoj klasi sadrži umesto tela = 0.​
Klasa koja sadrži barem jednu čistu virtuelnu funkciju naziva se apstraktnom klasom ​
Apstraktna klasa ne može imati instance(objekte), već se iz nje samo mogu izvediti druge klase.
            ​

!!!!!! VAZNO !!!!
KOnstruktor ne moze da bude virtuelna f-ja, destruktor moze!
U vrijeme izvrsavanjae se odlucuje koji destruktor se poziva. Kada neka klasa ima neku virt f-ju onda i njen destruktor(ako ga ima)
treba da bude virtuelan. Unutar destruktora izvedene klase ne treba pozivati destruktor osnovne klase(implicitno se poziva)

!!!!!!!!Nije moguće(samo) deklarisati čisto virtuelni destruktor.Sledeći kod pokazuje način da se to prevazidje navodjenjem definicije  virtuelnog destruktora.​!!!!!!!!

override(standard C11 i kasniji)​
specificira da je virtuelna metoda u izvedenoj klasi predefinisana metoda osnovne klase​
Nije ključna reč C++ jezika​

final
specificira da virtuelna metoda u narednim izvedenim klasama ne može da bude predefinisana tj.da je ona konačna​
specificira da klasa ne može da bude roditeljska tj.prekida se lanac nasleđivanja​
---------------------------------------------------------------------------------------------------------------------------------
VISESTRUKO IZVODJENJE

Podrazumevani : private za klasu

Osnovna klasa može biti virtuelna – treba je deklarisati kao virtuelnu sa virtual​
Ako je osnovna klasa virtuelna onda se podobjekti osnovne klase unutar objekta ​izvedene klase dele sa svim podobjektima koji imaju istu osnovnu klasu kao virtuelnu​
Obraćanje članu osnovne klase nije dvosmisleno ako postoji samo jedan primerak ​tog člana bez obzira na način pristupa​
!!!!!Ako je potrebno da izvedena klasa poseduje samo jedan podobjekat indirektne​ osnovne klase onda osnovnu klasu treba deklarisati kao virtuelnu !!!

}

PITANJA NEKA
{
    6. Šta se podrazumijevano kreira pri definisanju izvedene klase i koje je ponašanje ?
    Kada se u C++ definira izvedena klasa, podrazumijevano se kreira sljedeće:
    
Konstruktor bez argumenata - kreira se podrazumijevani konstruktor (default constructor) koji ne prima argumente
    i poziva se konstruktor bazne klase bez argumenata. Ovaj konstruktor može biti prazan (npr. ako svi članovi klase imaju podrazumijevane vrijednosti), ali se ipak mora kreirati.
    
        Destruktor - kreira se podrazumijevani destruktor (default destructor) koji nema argumenata i poziva se destruktor bazne klase.
    Ovaj destruktor može biti prazan ako klasa ne alocira nikakve resurse, ali se ipak mora kreirati.
    
        Konstruktor kopije - kreira se podrazumijevani konstruktor kopije (default copy constructor) koji prima konstantnu referencu na objekt
    istog tipa i poziva se konstruktor kopije bazne klase. Ovaj konstruktor kopije izvodi površinsko kopiranje (shallow copy) svih varijabli u klasi,
    što znači da se kopiraju samo pokazivači na objekte, ali se ne kopiraju sami objekti.
   
    Operator dodjele - kreira se podrazumijevani operator dodjele (default assignment operator) koji prima konstantnu referencu na objekt istog tipa
    i vrši površinsko kopiranje svih varijabli u klasi, što znači da se kopiraju samo pokazivači na objekte, ali se ne kopiraju sami objekti.
    
    Podrazumijevano ponašanje ovih konstruktora i operatora dodjele odgovara površinskom kopiranju(shallow copy) svih varijabli u klasi,
     što može uzrokovati probleme ako klasa sadrži dinamički alocirane objekte.Zbog toga se u mnogim slučajevima preporučuje eksplicitno
     definiranje ovih konstruktora i operatora kako bi se izbjegli takvi problemi.

10. Redoslijed poziva konstruktora i destruktora za automatske lokalne objekte.
----------------------------------------------------------------------------------------------------------------------------------------------------------------
U C++ - u se automatski lokalni objekti kreiraju kada program uđe u blok u kojem su definirani i uništavaju se kada program napusti taj blok.
Kada se objekt kreira, najprije se poziva konstruktor, a kada se objekt uništi, poziva se destruktor.
Redoslijed poziva konstruktora i destruktora ovisi o redoslijedu definicije varijabli u bloku.
int main() {
  MyObject obj1; // prva varijabla
  MyObject obj2; // druga varijabla
  {
    MyObject obj3; // treća varijabla
  }
  return 0;
}
prvo se kreira objekt obj1, zatim objekt obj2, a nakon toga u unutrašnjem bloku se kreira objekt obj3.
Kada se program napusti blok, najprije se uništava obj3, a zatim redom obj2 i obj1.
Važno je napomenuti da se uništavanje objekata vrši u suprotnom redoslijedu u kojem su kreirani, tj.
u posljednje kreirani objekt se uništava prvi.Ovo je bitno imati na umu kod kreiranja objekata koji međusobno ovise o stanju drugih objekata.


9.Navesti operatore za pristup članovima klase------------------------------------ - odradjen--------------------------------
//., ->

----------------------------------------------------------------------------------------------------------------------------------------------------------------
Kako se definiše virtuelna statička članica klase ?

Virtualna statička članica klase je virtuelna funkcija koja se poziva na ime klase, a ne na ime objekta.Međutim, za razliku od običnih virtuelnih funkcija,
virtuelna statička funkcija je statička, što znači da se njen poziv ne vezuje za instancu objekta.

To znači da se virtuelna statička funkcija može pozivati i ako nema objekta klase, ali se i dalje može naslediti i redefinisati u podklasama.
Ova funkcionalnost može biti korisna u situacijama kada je potrebno definisati funkciju koja je povezana sa klasom, ali ne i sa pojedinačnim objektima.

U C++ - u, sintaksa za definisanje virtuelne statičke funkcije je slična kao i kod obične virtuelne funkcije, samo se koristi ključna reč "static"
ispred funkcije i deklaracija je unutar klase, a definicija se može dati van klase.
Primer :

class Base {
public:
    static virtual void foo() { cout << "Base::foo()" << endl; }
};

class Derived : public Base {
public:
    static void foo() override { cout << "Derived::foo()" << endl; }
};

int main() {
    Base::foo();    // poziva se Base::foo()
    Derived::foo(); // poziva se Derived::foo()
    Base* p = new Derived();
    p->foo();       // poziva se Base::foo() jer nije virtuelna
    return 0;
}


U ovom primeru, virtuelna statička funkcija "foo" se nasleđuje u podklasi "Derived" i redefiniše se da ispiše drugačiju poruku.
Funkcija se poziva direktno na ime klase, bez kreiranja objekta.
----------------------------------------------------------------------------------------------------------------------------------------------------------------
4. Klasa A je izvedena iz klase B, a klasa B iz klase C.Kojim redoslijedom treba poređati catch klauzule
čiji su tipovi redom A, Bi C ?


U ovom slučaju, catch klauzule bi trebalo poredati od najspecifičnijeg do najopštijeg tipa izuzetka.
To bi značilo da bismo trebali prvo uhvatiti izuzetke tipa A, zatim izuzetke tipa B i na kraju izuzetke tipa C.
Razlog tome je što, ako postoji izuzetak tipa A, on će se prvo uhvatiti u prvom catch bloku.
Ako catch blok ne uspije da obradi izuzetak, onda će se izvršiti drugi catch blok, koji će pokušati uhvatiti izuzetak tipa B.
Ako ni drugi catch blok ne uspije da obradi izuzetak, tada će se izvršiti posljednji catch blok koji će pokušati uhvatiti izuzetak tipa C.
Dakle, pravilni redoslijed catch klauzula bi bio :
try {
    // Kod koji baca izuzetke
}
catch (const A& e) {
    // Handler za izuzetak tipa A
}
catch (const B& e) {
    // Handler za izuzetak tipa B
}
catch (const C& e) {
    // Handler za izuzetak tipa C
}


----------------------------------------------------------------------------------------------------------------------------------------------------------------
1.Kako treba realizovati funkciju Ispit operator-(int x, Ocena a) ?

Pretpostavljamo da klasa Ocena ima privatne članove brojPoena i ocena.
Jedan način za realizaciju ove funkcije bi bio

Ocena operator-(int x, Ocena a) {
    int noviBrojPoena = a.brojPoena - x;
    if (noviBrojPoena < 0) {
        noviBrojPoena = 0;
    }
    return Ocena(noviBrojPoena);
}

Primjer korišćenja ove funkcije bi mogao biti :
Ocena a(85);
Ocena razlika = 5 - a;
std::cout << "Razlika: " << razlika.ocena << std::endl;
----------------------------------------------------------------------------------------------------------------------------------------------------------------
2.Kako treba realizovati funkciju Zakon operator+(int kazna, Osoba v) ?

Funkcija operator+ koja prima int i Osoba kao argumente treba vratiti novi objekat Zakon koji predstavlja 
kaznu dodijeljenu osobi v u iznosu kazna.Pretpostavljamo da klasa Zakon ima privatne članove osoba i iznos.

Zakon operator+(int kazna, Osoba v) {
    return Zakon(v, kazna);
}

Osoba osoba("Marko", 30);
Zakon kazna = 100 + osoba;
std::cout << "Kazna za osobu " << kazna.getOsoba().getIme() << ": " << kazna.getIznos() << std::endl;

U ovom primjeru, stvaramo objekat Osoba sa imenom "Marko" i starošću 30 godina.
Zatim, kreiramo novi objekat Zakon koji predstavlja kaznu od 100 jedinica, dodijeljenu osobi osoba.Nakon toga, ispisujemo ime osobe i iznos kazne.

----------------------------------------------------------------------------------------------------------------------------------------------------------------
2.Koja je namena funkcije istream& get(char& znak)


Funkcija istream& get(char& znak) je funkcija ulaznog toka koja čita jedan karakter iz ulaznog toka i smješta ga u promjenjivu znak.
Argument funkcije znak predstavlja promjenjivu u koju se smješta pročitani karakter.Funkcija vraća referencu na sam ulazni tok(objekat klase istream),
što omogućava njeno pozivanje više puta u jednom nizu tokovnih operatora.
Ova funkcija može biti korisna kada je potrebno pročitati samo jedan karakter iz ulaznog toka, na primjer, kada se čita korisnički unos sa tastature.

#include <iostream>

int main() {
    char c;
    std::cin.get(c); // čitanje jednog karaktera iz ulaznog toka (tastature) i smještanje u promjenjivu c
    std::cout << "Unijeli ste karakter: " << c << std::endl;
    return 0;
}
    
Objasniti dejstvo funkcije istream& read(char* niz, int broj).

Funkcija istream& read(char* niz, int broj) je metoda klase istream u C++ - u koja omogućava čitanje niza karaktera iz ulaznog toka u određenu oblast memorije.
Argument niz predstavlja pokazivač na prvu lokaciju u memoriji u koju će se čitati karakteri, a argument broj označava koliko karaktera se treba pročitati.
Funkcija čita broj karaktera iz ulaznog toka i smješta ih u niz koji počinje na adresi niz.
Dejstvo funkcije read() je da se direktno i brzo čita niz karaktera iz ulaznog toka bez potrebe za ekstrakcijom operatora >> .
To je korisno ako se, na primjer, radi sa binarnim fajlovima ili sa tekstualnim fajlovima koji imaju specijalni format koji nije pogodan za korišćenje operatora ekstrakcije.

#include <iostream>
#include <fstream>
#include <cstring>

using namespace std;

int main() {
    char buffer[100];

    ifstream file("tekst.txt");
    if (file.is_open()) {
        file.read(buffer, 10); // čita prvih 10 karaktera iz fajla u buffer
        buffer[10] = '\0'; // postavi terminirajuću nulu na kraju niza karaktera
        cout << "Procitani karakteri: " << buffer << endl; // ispisuje procitane karaktere
        file.close(); // zatvara fajl
    }
    else {
        cout << "Greska prilikom otvaranja fajla!" << endl;
    }

    return 0;
}
U ovom primjeru se koristi funkcija read() za čitanje prvih 10 karaktera iz fajla "tekst.txt" i smještanje u niz buffer.
Nakon toga, funkcija cout se koristi za ispisivanje sadržaja niza buffer na standardni izlaz.
Važno je napomenuti da se na kraju niza karaktera mora postaviti terminirajuća nula kako bi se osiguralo da niz bude ispravno prikazan na ekranu.

----------------------------------------------------------------------------------------------------------------------------------------------------------------
3. Koji header file je potrebno uključiti da bi mogli da koristimo funkciju find nad nekim objektom kontejnerske klase vector ?

Za korištenje funkcije find nad objektom kontejnerske klase vector potrebno je uključiti zaglavlje <algorithm>.
Ova biblioteka sadrži definiciju mnogih algoritama, uključujući i find, koji omogućava pretraživanje vektora i pronalaženje elementa koji odgovara datom kriterijumu pretrage.

----------------------------------------------------------------------------------------------------------------------------------------------------------------
4. Koje je dejstvo metode seekg ?

Metoda seekg(seek get) omogućava podešavanje pozicije unutar ulaznog toka(npr.fajla) na kojoj će se sledeća operacija čitanja(get) izvršiti.
Pozicija se određuje na osnovu broja bajtova koji se navode kao argument metode.
Na primer, ako se pozove seekg(0), sledeća operacija čitanja će početi od početka fajla, dok bi poziv seekg(10) pomjerio poziciju za 10 bajtova od početka fajla.
----------------------------------------------------------------------------------------------------------------------------------------------------------------
ITERATORI

Iteratori su objekti koji se koriste za obilazak elemenata kontejnera, kao što su nizovi, vektori, liste i sl.
Iteratori omogućavaju kretanje kroz elemente kontejnera i izvršavanje operacija nad njima, poput čitanja i pisanja

Iteratori omogućavaju generički pristup kontejnerima, što znači da se ista logika može koristiti za različite vrste kontejnera, 
što olakšava pisanje modularnog i ponovno upotrebljivog koda.U C++ - u, iteratori se često koriste u kombinaciji sa standardnom 
bibliotekom algoritama(<algorithm>) koja sadrži mnoge funkcije za rad sa kontejnerima, kao što su sort, find, count, itd.

}

SABLONI
{
Deklarisanjem šablona specificira se skup parametrizovanih klasa ili funkcija, i deklaracija moze samo biti globalna.

template <lista_parametara>​
                deklaracija_funkcije_ili_klase​

    Svaka upotreba imena šablonske klase predstavlja deklaraciju konkretne ​šablonske klase​ 
Poziv šablonske funkcije ili uzimanje njene adrese predstavlja deklaraciju konkretne​   šablonske funkcije​
Ako se definiše obična negenerička klasa ili funkcija sa istim imenom i sa potpuno​ odgovarajućim tipovima kao što je šablon klase tj.funkcije, 
onda ova definicija ​predstavlja definiciju konkretne šablonske klase tj.funkcije za date tipove​

Funkcija članica šablonske klase je implicitno šablonska funkcija​
Prijateljske funkcije šablonske klase nisu implicitno šablonske funkcije​

Parametri šablona mogu da budu : ​

A) tipovi​
class Identifikator,​
typename Identifikator,​
template <lista_parametara> ​
class Identifikator​

B) vrednosti(ovakvi parametri ne mogu da budu floating point tipa niti korisnički definisanog tipa – objekti klasa, struktura ili unija.​
C) šabloni mogu da budu parametri šablona​



Generisanje funkcija iz šablona​
Implicitno(automatski)​
Generisanje na zahtev(eksplicitno)​

Eksplicitno generisanje se postiže​
Navođenjem tipova stvarnih argumenata šablona u deklaraciji funkcije​
template tip ime<stvarni argumenti>(lista_tipova); ​
Primer : template int max<int>(int x, int y); ​

Vrednosti kao parametri šablona mogu da budu : ​

A) celobrojnog tipa​
B) enumeratori​
C) reference​
D) pokazivači na objekte​
E) pokazivači na funkcije​
F) pokazivači na članove​
Ovakvi parametri mora da budu poznati u toku faze kompajliranja.
Lokalne promenljive ne mogu da budu vrednosni parametri šablona.​
Kao parametri šablona ne mogu da se koriste realni brojevi(od C++20), objekti klasa, struktura i unija, literali stringova(od C++ 20).​
Nizovi se kao parametri šablona prenose kao pokazivači.​
Funkcije se kao parametri šablona prenose preko pokazivača na funkcije. KAo i f-je clanica

Kao i parametri funkcija i parametri šablona mogu da imaju podrazumevane vrednosti.​

​

DEKLARACIJA PARAMETARA TIPOVA​

class Identifikator[=ImeTipa],​
typename Identifikator[=ImeTipa],​
template <lista_parametara> ​
    class Identifikator[=ImeSablona]​

DEKLARACIJA VREDNOSNIH PARAMETARA​
ImeTipa imeParametra[=Vrednost]​


Postoje tri vrste specijalizacije šablona :

Potpuna specijalizacija(engl.full specialization) - predstavlja specijalizaciju šablona za konkretan tip podataka.
Nju koristimo kada se standardni kod koji je napisan u šablonu ne može primeniti na određeni tip, već moramo napisati poseban kod za taj tip.

Delimična specijalizacija(engl.partial specialization) - predstavlja specijalizaciju šablona za određenu grupu tipova podataka.
Delimična specijalizacija se koristi kada postoje neke zajedničke karakteristike između grupa tipova za koje želimo da napišemo poseban kod.

Funkcijska specijalizacija(engl.function specialization) - predstavlja specijalizaciju šablonske funkcije.
Funkcijska specijalizacija se koristi kada želimo da napišemo poseban kod za konkretne tipove podataka koje funkcija prima kao argumente.​

Specijalizacija je moguća samo ako je prethodno navedena deklaracija / definicija opšte generičke klase​
Specijalizacija određenim parametrima je moguća – samo pre kreiranja bilo koje klase na osnovu opšteg šablona sa istim parametrima​

Pri generisanju se navodi onoliko elemenata koliko ima opšti šablon​

Prevodilac bira šablon : ​
-Najviše specijalizovani(sa najmanje parametara), pa manje specijalizovani, itd.​
-Opšti šablon(ako ni jedan specijalizovani ne odgovara)​
-Ako postoji nejednoznačnost(više podjednako specijalizovanih šablona odgovara) – javlja se greška​
Primer : ​
       Kon<int, 10>  p5 // spec 3​


Za generičke funkcije je moguća samo potpuna specijalizacija​

Deklaracija : template<> tip GSfunkcija<argument>(...); ​
Definicija : template<> tip GSfunkcija<argument>(...) { ... }​
Argumenti(uključujući i < >) mogu da se izostave – ako se oni mogu odrediti na osnovu tipova argumenata funkcije​


----------------------------------------------------------------------------------------------------------------------------------------------------------------

!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Kako se koriste funkcije kao parametri šablona ? !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

U C++ programskom jeziku, funkcije se mogu koristiti kao parametri šablona.Ova tehnika se naziva funkcionalno programiranje 
i koristi se za povećanje fleksibilnosti šablona.Funkcija se obično koristi kao parametar šablona kada je potrebno izvršiti
neku vrstu obrade ili manipulacije nad podacima koji se koriste kao argumenti šablona.

Funkcija se definiše kao tip podataka i može biti bilo koja funkcija koja je kompatibilna sa parametrom šablona.
Na primer, ako se šablon koristi za sortiranje podataka, funkcija koja se koristi kao parametar šablona mora biti funkcija koja vrši poređenje elemenata.

U C++ - u se funkcije kao parametri šablona mogu koristiti pomoću funkcionalnih objekata(lambda funkcija, funkcionalni objekat klase i dr.), 
funkcija pokazivača(engl.function pointers) i funkcija predloška(engl.function templates).Funkcionalni objekti i funkcije pokazivači se koriste 
kada je funkcija kratka i jednostavna, dok se funkcije predloška koriste za složenije operacije.

bool sort_desc(int a, int b) {
    return a > b;
}

// Funkcija koja koristi sortiranje kao parametar šablona
template<typename T>
void print_sorted(std::vector<T>& v, bool (*sort_func)(T, T)) {
    std::sort(v.begin(), v.end(), sort_func);
    for (auto& x : v) {
        std::cout << x << " ";
    }
    std::cout << std::endl;
}


}

IZUZECI
{
U nekim izuzetnim situacijama program “odstupa” od “best case” scenarija izvršavanja tj.dešavaju se događaji koje nazivamo izuzecima(exceptions)

Ako jezik ne podržava obradu izuzetaka, dolazi do sledećih problema : ​
- posle izvršenja dela programa(ili poziva funkcije) u kojem može doći do greške vrši se testiranje statusa,
te se obrada greške smešta u jednu granu a obrada uobičajene situacije u drugu granu if naredbe; -tkz.“migracija u desno”​
- ako imamo više hijerarhijskih poziva funkcija, ukoliko grešku treba propagirati prema višem nivou, svaki nivo pozivanja treba
da izvrši testiranje da li je došlo do greške u nižim nivoima i pomoću return vrati kod greške.– tkz.“propagacija unazad”​

Izazivanje(prijavljivanje, bacanje) izuzetaka se vrši naredbom : ​
throw izraz​ gde izraz svojim tipom određuje koji hendler će biti aktiviran; ​
    vrednost izraza se prenosi hendleru kao argument.​
Izuzetak se možae izazvati iz try bloka ili iz bilo koje funkcije direktno ili indirektno pozvane iz bloka naredbe try.​

Funkcije iz kojih se izaziva izuzetak mogu biti i : ​
članice klasa, ​
operatorske funkcije,
konstruktori, ​
destruktori.​



----------------------------------------------------------------------------------------------------------------------------------
NOEXCEPT

5. Iz kojih funkcija se ne mogu izazvati izuzeci ?
Iz f-ja koje se mogu označiti kao noexcept 

noexcept (kao alternativa za throw() u C++11, u C++17 izbačeno)  i to kao : ​

Specifikator(noexcept(izraz)) ​
- Navodi se iza liste parametara​
- Izraz – konstantan logički, izvršava se u toku prevođenja ako ima vrednost true – funkcija ne baca izuzetke​
- noexcept  ili noexcept(true)  ili throw()​

Operator(noexcept(izraz))​
- Rezultat je logičkog tipa(tačno - ako ne može da baci izuzetak)​
- Proverava se da li bi moglo doći do izuzetka u izrazu​
izraz – ​
- proizvoljnog tipa čak i void​
- Ne izračunava se, samo se proverava u fazi prevođenja​

GDE NE KORISTITI NOEXCEPT

Koristi se za funkcije, metode klase, lambda funkcije i pointere na funkcije​
Od C++17 pointeri na funkcije sa noexcept ne mogu da ukazuju na funkcije koje potencijalno mogu da izazovu izuzetak​
Ne koristiti za virtuelne funkcije u osnovnoj klasi jer su time ograničene i u svim izvedenim klasama​
Virtuelna metoda u izvedenoj klasi ne sme da proširi listu izuzetaka u throw() specifikatoru ali sme da je suzi​
Ako se ipak pojavi izuzetak u funkciji koja je označena kao noexcept onda se pokreće terminate koja poziva abort tj.završava se program nasilno.​

UNISTAVANJE LOKALNIH OBJEKATA
​
Kada se kontrola predaje hendleru definitivno se napušta blok u kome je kreiran izuzetak i tada se uništavaju svi lokalni objekti kao i objekti u ugnježdenim blokovima​
Izuzetak kreiran u konstruktoru – uništavaju se prethodno kreirani atributi i nasleđeni podobjekti​
Nije dobro da rezultat izraza throw pokazuje na lokalni objekat jer će se taj objekat uništiti pre prihvatanja u catch bloku​

9.Kog tipa će biti izuzetak koji je kreiran samo pozivom throw; (bez izraza iza throw) ?
void main()
{
        try {
            try {
                throw 20; ​
                }      ​
            catch (int n) {
                    cout << „Obrada unutrašnja ";​
                    throw; // ponovno bacanje istog int izuzetka​
                            }​
            }​
        catch (int n) {
                    cout << „Obrada spoljašnja ";​
                        }​
    }​
    Da, u ovom kodu se na početku baca izuzetak tipa int sa vrijednošću 20 koristeći throw 20. Taj izuzetak se zatim hvata u unutrašnjem 
   catch bloku.Nakon toga, kada se koristi throw; bez parametra u unutrašnjem catch bloku, to znači da se isti izuzetak tipa int ponovo baca.
    Taj ponovo bačeni izuzetak se zatim hvata u vanjskom catch bloku i obrađuje se ispisom "Obrada spoljašnja".Stoga se vrijednost 20 koristi kao 
    vrijednost izuzetka koji se baca i ponovo baca.
U hendlerima​

Mogu da se koriste parametri funkcije​
Ne mogu da se koriste lokalne promenljive​
Ako funkcija nije tipa void mora da se izvrši return ako se ne kreira izuzetak​​​​

F - JSKi try u konstruktoru
Omogucava hvatanje izuzetaka koji se bacaju iz :
-inicijalizatora atributa primitivng tipa
- konstruktora atributa klasnog tipa
- konstruktora osnovnih klasa

PRIHVATANJE IZUZETAKA
Hendler tipa B može da prihvati izuzetak tipa D ako : ​

B i D su istih tipova​
B je javna osnovna klasa za izvedenu klasu D​
B i D su pokazivački tipovi i D može da se standardnom konverzijom konvertuje  u tip B.​

Na mestu izazivanja izuzetka formira se privremeni objekat sa vrednošću izraza.​
Privremeni objekat se prosleđuje najbližem(prvom na koji se naiđe) hendleru.​
Ako su try naredbe ugnježdene, izuzetak se obrađuje u prvom odgovarajućem hendleru tekuće naredbe try.​
Ako se ne pronađe odgovarajući hendler - izuzetak se prosleđuje hendleru sledećeg(višeg) nivoa naredbe try.​

Prilikom navođenja hendlera treba se držati sledećih pravila : ​

hendlere tipa izvedenog iz neke osnovne klase treba stavljati ispred hendlera tipa te osnovne klase​
univerzalni hendler treba stavljati na poslednje mesto.


NEPRIHVACENI IZUZECI se javljaju

Ako se za neki izuzetak ne pronađe hendler koji može da ga prihvati​
kada se detektuje poremećen stek poziva​
kada se u destruktoru, u toku odmotavanja steka, postavi izuzetak​
Izvršava se sistemska funkcija : ​
    void terminate(); ​
Podrazumeva se da ova funkcija poziva funkciju abort() koja kontrolu vraća operativnom sistemu.​
Ovo se može promeniti pomoću funcije set_terminate.​
Njoj se dostavlja pokazivač na funkciju koju treba da pozove funkcija terminate umesto funkcije abort.​
Pokazivana funkcija mora biti bez argumenata i bez rezultata(void).​
Vrednost funkcije set_terminate je pokazivač na staru funkciju koja je bila pozivana iz terminate.​
Iz korisničke funkcije(*pf) treba pozvati exit() za povratak u operativni sistem.​
Pokušaj povratka sa return iz korisničke funkcije(*pf) dovešće do nasilnog prekida programa sa abort().​

typedef void (*PVF)(); // tip korisničke funkcije koja menja abort​
PF set_terminate(PVF pf); // prototip funkcije set_terminate​

Primjer:
void term_func() {
        cout << "term_func se poziva umesto terminate."; ​
        exit(-1); ​
} ​
int main() {
            try {
                set_terminate(term_func); ​
                    throw “Nema više memorije!"; // Ne postoji handler​
            } ​
                catch (int) {
                    cout << “Bacen izuzetak tipa int." << endl; } ​
                        return 0; ​
                }​
        }
IZLAZ : term_func se poziva umesto terminate.​


NEOCEKIVANI IZUZECI

Ako se u nekoj funkciji izazove izuzetak koji nije na spisku naznačenih izuzetaka, izvršava se funkcija : ​
void unexpected(); ​
Poziva se unexpected_handler() ​
Podrazumeva se da ova funkcija poziva funkciju terminate().​
Ovo se može promeniti pomoću funcije set_unexpected.​
Njoj se dostavlja pokazivač na funkciju koju treba da pozove funkcija unexpected umesto terminate.​
Pokazivana funkcija mora biti bez argumenata i bez rezultata(void).​
Vrednost funkcije set_unexpected je pokazivač na staru funkciju koja je bila pozivana iz unexpected.​
Pokušaj povratka sa return iz korisničke funkcije(*pf) dovešće do nasilnog prekida programa sa abort().​

typedef void (*PF) (); // tip korisničke funkcije​
PF set_unexpected(PF pf); // prototip funkcije set_unexpected​


what()​ vraca pokazivac na tekstualni opis izuzetka(std.ne propisuje tekst poruka)


}