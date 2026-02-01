class X extends B{
  public static void main(String[] args){
    X x = new X();
    A a = new A();
    //a = (A) x;
    System.out.println(a.metoda());
  }
};

class A {
  static int i;
  A(){
    ++i;
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
    System.out.println("m");
    return (i+3);
  }
};

