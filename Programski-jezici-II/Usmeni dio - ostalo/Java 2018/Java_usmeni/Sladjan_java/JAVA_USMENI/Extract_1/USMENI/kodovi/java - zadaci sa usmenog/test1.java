 public class test1 { 
  public static void add3 (Integer i) { //pravi se nova referenca na stari objekat
        int val = i.intValue ( ); // val=0
         System.out.println ("val = "+val); 
           val += 3; // val=3
           System.out.println ("val = "+val); 
           i = new Integer (val); // i=3  //ovde referenca i dobija novi objekat!!!
           System.out.println ("i = "+i); 
      } 
   
   public static void main (String args [ ] )  { 
    Integer  i = new Integer (0); 
    add3 (i);  
     System.out.println ("i.intValue() = " + i.intValue()); 
   } 
   } 