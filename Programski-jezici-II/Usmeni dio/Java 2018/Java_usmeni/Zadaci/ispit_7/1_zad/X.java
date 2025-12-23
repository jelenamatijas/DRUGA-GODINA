class X extends B{
  public static void main(String ka[]){
    X x = new X();
    System.out.println(x.vrati());
    A a = new A();
    System.out.println(a.vrati());
    System.out.println(x.metoda());
    
    //a = (A) x;
    //System.out.println(a.metoda());
  }
}

class A{
  static int i;
  A(){
    ++i;
  }
  
  public int vrati(){
    return i;
  }
  
  private int metoda(){
    return ++i;
  }
};

class B extends A{
  B(){
    i++;
  }
  int metoda(){
    return (i+3);
  }
};

