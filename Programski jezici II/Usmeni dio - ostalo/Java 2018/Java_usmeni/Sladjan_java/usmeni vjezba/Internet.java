public class Internet {
  private int y = 8;
public static void main(String[] args) {
  new Internet().go();
}
void go() {
  final int x = 7;  
  
 
  class TCPIP {
    void doit() { System.out.println(y + x); }  //pristupili smo x iz unutranje klase moramo je //proglasiti final
  }
  TCPIP ip = new TCPIP();  //prvo deklarisanje pa instanciranje obratno greska
  ip.doit();
}
} 
