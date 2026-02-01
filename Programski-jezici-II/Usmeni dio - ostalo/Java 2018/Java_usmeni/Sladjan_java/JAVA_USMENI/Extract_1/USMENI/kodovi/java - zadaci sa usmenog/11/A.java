public class A{
  int j=1; // nestaticka promjenjiva, ne moze joj se pristupiti iz statickog konteksta
 
 public static void main(String args[]){
  metoda();
  A a=new A();
  a.metoda();
 }
 
 public static void metoda(){
  char digit = 'a';
  for(int i=0; i<10; i++){
   switch(digit){
    case 'x':
     {
      int j=0;
      System.out.println(j);
     }
    default:
     {
      int j=100;
      System.out.println(j);
     }
    }
   }
  int i=j;  // j nestaticka, i staticka promjenjiva, a iz statickog konteksta se NE MOZE pristupiti nestatickom
  System.out.println(i);
 }
}