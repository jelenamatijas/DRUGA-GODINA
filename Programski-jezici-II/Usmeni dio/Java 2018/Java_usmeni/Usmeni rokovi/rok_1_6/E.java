interface I1 extends I2, I3{
  void metoda();
}
interface I2{
  void metoda();
}
interface I3{
  void metodaI3();
}

class D implements I1, I3{
  public void metoda(){
    System.out.println("metoda");
  }
  public void metodaI3(){
    System.out.println("metoda I3");
  }
}

class E{
  public static void main(String [] args){
    D d = new D();
    I1 i = new D();
    I2 i2 = d;
    i.metoda();
    d.metodaI3();
    i.metodaI3();
    i2.metoda();
  }
}