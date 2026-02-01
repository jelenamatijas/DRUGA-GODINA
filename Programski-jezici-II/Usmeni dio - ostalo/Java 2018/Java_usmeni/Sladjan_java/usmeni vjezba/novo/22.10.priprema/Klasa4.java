public class Klasa4 {
 int j = 1;
 
 public static void main(String args[]){
  metoda();
  Klasa4 a = new Klasa4();
  a.metoda();
  a.main(null);
 }
 public static void metoda(){
  char digit = 'a';
  for (int i = 0; i < 10; i++){
   switch (digit){
    case 'x':
    {
     int j = 0;
     System.out.println(j);
    }
    default:
    {
     int j = 100;
     System.out.println(j);
    }
   }
  }
  //int i = j;
  //System.out.println(i);
 }
}

