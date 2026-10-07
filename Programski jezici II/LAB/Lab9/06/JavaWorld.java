import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class JavaWorld {
    private HashMap<String, Integer> partneri = new HashMap<String, Integer>();

    JavaWorld(){
        partneri.put("partner 1", 25000);
        partneri.put("partner 2", 300526);
        partneri.put("partner 3", 1256593);
    }

    public Set<String> getKeys(){
        return partneri.keySet(); 
    }

    public Collection<Integer> getValues(){
        return partneri.values();
    }

    public int izmijeniUgovor(String naziv, Integer vrijednost){
        return partneri.replace(naziv, vrijednost);
    }

    public static void main(String args[]){
        JavaWorld javaWorld = new JavaWorld();
        Set<String> keys = javaWorld.getKeys();
        Collection<Integer> values = javaWorld.getValues();

        Iterator<String> ik = keys.iterator();
        Iterator<Integer> ii = values.iterator();

        System.out.println("Naziv partnera: vrijednost ugovora");
        while(ik.hasNext()){
            System.out.println(ik.next() + ": " + ii.next());
        }

        System.out.println(javaWorld.izmijeniUgovor("partner 1", 44444444));

        ik = keys.iterator();
        ii = values.iterator();

        System.out.println("Naziv partnera: vrijednost ugovora");
        while(ik.hasNext()){
            System.out.println(ik.next() + ": " + ii.next());
        }
    }
}
