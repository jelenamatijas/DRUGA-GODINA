public class test {     
     private static int j = 0; 
         
     private static boolean methodB(int k) { 
        j += k; 
        return true; 
     } 
  
 public static void methodA(int  i) { 
      boolean b;    
      b = i < 10 | methodB (4); // < ima prioritet  //prioriteti: = 1=min,< 9,| 5,|| 3,metoda() 15=max
      System.out.println(j); 
      b = i < 10 || methodB (8); 
      //System.out.println(j); 
      //methodB(8);
      //System.out.println(j); 
 } 
   
 public static void main (String args[] )   {
          methodA (0); 
          System.out.println(j); 
 }
} 