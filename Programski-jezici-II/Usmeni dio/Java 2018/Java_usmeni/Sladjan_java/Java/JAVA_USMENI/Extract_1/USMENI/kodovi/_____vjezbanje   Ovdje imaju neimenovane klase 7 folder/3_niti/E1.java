public class E1 extends Thread{
  
private String name;

public E1(String name){
this.name=name;
}

synchronized public void run(){ //ako sinhronizujemo run prva nit koja udje(inace one se utrkuju jer nema join metode) 
                   //zavrsice sve pa ce predati run metodu drugoj ???!!!
 Runnable r=new Runnable(){ //  neimenovana klasa
public void run(){
  for(int i=0;i<5;i++){
  System.out.println(i + " **** " + name );
  }
}                                     //ako je runnable redefinisan,a u Thread() nismo stavili r nece nista ispisat
};                                   //ako nismo redefinisali i ako ne stavimo r ispisace 0 4
new Thread(r).start();   //startujemo novi tred koji ispisuje 0 1 2 3 4 
                         //ako izostavimo r necemo startovati redefinisanu run tj necemo startovati nist

synchronized(this){  //sinhronizuje samu sebe nema nikakvog uticaja na ispis ona ce uvijek ispisati samo niti a i b
  for(int i=0;i<50;i++){
  System.out.println(i+" "+this.name);
      //System.out.println(i);
  }
}

}

public static void main(String args[]){
E1 a=new E1("A");
E1 b=new E1("B");
a.start();
b.start();
}

}
// postoji 5 niti