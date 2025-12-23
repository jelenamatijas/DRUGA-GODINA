public class J{
  static int i;
  J(){
    ++i
  }
  private int metoda(){
    return ++i;
  }
};

class K extends J{
  K(){
    i++;
  }
  
  int metoda(){
    return (i+3);
  }
};

class L extends K{
  public static void main(String ka[]){
    L l = new L();
    J j = new J();
    j = (J) l;
    System.out.println(j.metoda());
  }
};