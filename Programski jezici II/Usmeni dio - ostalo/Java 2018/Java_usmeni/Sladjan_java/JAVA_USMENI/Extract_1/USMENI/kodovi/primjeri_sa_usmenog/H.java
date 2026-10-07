class H implements I{
  static H h = new H();
  static {System.out.println("111");}
  
  public static void main(String args[]){
    H h = new H();
    h.metoda();
  }
  
  public void metoda(){
    System.out.println("abcdef");
  }
};
  