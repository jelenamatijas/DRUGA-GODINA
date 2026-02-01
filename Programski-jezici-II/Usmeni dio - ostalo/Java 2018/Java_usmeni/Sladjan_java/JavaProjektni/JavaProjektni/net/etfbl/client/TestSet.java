import java.util.*;

public class TestSet {
  public static void main(String[] args) {
    HashSet<String> set = new HashSet<String>();
    
    System.out.println(set.add("Nikola"));
    System.out.println(set.add("Nikola"));
    System.out.println(set.add("Nik" + "ola"));
    System.out.println(set.add("adfasasdfad"));
    System.out.println(set.add("adfas"));
    
    Iterator<String> it = set.iterator();
    
    while(it.hasNext())
      System.out.println(it.next());
  }
}