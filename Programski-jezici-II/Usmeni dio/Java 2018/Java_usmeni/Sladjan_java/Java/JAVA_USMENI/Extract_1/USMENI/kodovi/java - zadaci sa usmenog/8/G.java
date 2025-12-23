public class G{
 static int x=3;
 
 public static void main(String args[]){
  new G();
 }
 
 G() {  G(2); }  //poziv metode, a ne konstruktora, trebalo bi new G(2);, 
 
 G(int x){    //ovo je konstruktor sa argumentom
  System.out.println(x);
 }
}

//ne moze se kompajlirati ili treba new G(2) ili void G(int x) da napravimo metodu