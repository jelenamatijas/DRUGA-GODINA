class Jebacina implements Algorithm{
  public void metoda(){}
  class Dupe{
  //static int x;
  }
  public static void main(String args[]){
  Algorithm.Inner p = new Algorithm.Inner();
  Jebacina.Inner t = new Jebacina.Inner();
  int x = 0xffffffff;
  long l = 0xffffffffffffffffL;
  char s = (char)-1;
  if (l == x) System.out.println((int)s);
  }
}

interface Algorithm{
  int x = 0;
  void metoda();
  static class Inner{
    static int y;
    static{
    System.out.println("Ne mere");
    }
    Inner(){
    System.out.println("PITA OD GOVANA");
    }
  }
}
