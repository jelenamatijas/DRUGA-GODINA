import java.util.*;
 public class Piles {
 public static void main(String[] args) {
   
   TreeMap<String, String> tm = new TreeMap<String, String>();  
//sortira po kljucevima u rastucem nizu prioritet //brojevi
   
   TreeSet<String> ts = new TreeSet<String>(); 
//sortira u prirodnom poretku
//prvo brojevi pa slova u rastucem tj prirodnom poretku
   String[] k = {"1", "b", "4", "3", "2","d"}; 
   String[] v = {"a", "d", "3", "b", "2","e"}; 
   for(int i=0; i<6; i++) {
     tm.put(k[i], v[i]);
     ts.add(v[i]);
   }
   System.out.print(tm.values() + " ");
   Iterator it2 = ts.iterator();
   while(it2.hasNext()) System.out.print(it2.next() + "-");
 } }
