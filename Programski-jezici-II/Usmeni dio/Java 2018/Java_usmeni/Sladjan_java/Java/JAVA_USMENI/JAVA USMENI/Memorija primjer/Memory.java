public class Memory{
    Memory m;
    double[] d=new double[1000000]; // to je priblizno 8 MB (double zauzima 8 bajta)
    long[] l=new long[1000000]; // to je priblizno 8 MB (long zauzima 8 bajta)
    int[] i=new int[1000000]; // to je priblizno 4 MB (int zauzima 4 bajta)
    char[] c=new char[1000000]; // to je priblizno 2 MB (char zauzima 2 bajta(unicode))
    
    public Memory(){
        super();
    }
    
    public Memory(Memory m){
        this.m=new Memory();
    }
    
    /* Dobijes u zadatku velicinu heap-a npr. 70 MB
     * i onda gledas kad se kreiraju objekti koliko koji zauzima i
     * racunas */
    
    public static void main(String[] args){
        Memory m1=new Memory(); // zauzima priblizno 8 + 8 + 4 + 2 = 22 MB
        /* Kod m1 i m3 clan m (linija 2) nista nece zauzimati jer je null 
         * (nije inicijalizovan)*/
        Memory m2=new Memory(m1);
        /* 22 MB zauzima m2.m (u liniji 2) jer se kreira u konstruktoru u liniji 13
         * + 22 MB ostali clanovi klase (niz D,L,I i C)
         * znaci sve skupa m2 zauzima 44 MB*/
        Memory m3=new Memory(); //ovaj takodje zauzima 22 MB 
        /*u liniji 26 desice se OutOfMemoryError jer je velicina heap-a 70 MB
         * a kada smo kreirali m3 dobili smo ukupno 22+44+22=88 MB*/
        
        /* I da nije potrebno racunati da je MB=1024KB nego moze MB=1000KB
         * tako je reko profesor da nam ne bude komplikovano.
         * I naravno bude dosta tezi zadatak koji ima npr. metode u kojima
         * se pravi novi objekat klase Memory pa onda i to trebas racunati
         * ali samo ides poredu i dodajes koliko sta zauzima*/
    }
    
}