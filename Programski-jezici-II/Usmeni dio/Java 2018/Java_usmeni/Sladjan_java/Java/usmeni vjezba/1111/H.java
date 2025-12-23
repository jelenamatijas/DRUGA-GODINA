

interface I {
 void metoda();
}

class H implements I {
 static H h = new H();
 public static void main(String[] args) {
  H h = new H();
  h.metoda();
 }

 void metoda() {
  System.out.println("abcdef");
 }
}
