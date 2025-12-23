class SSBool{
  public static void main(String args[]){
    boolean b1 = true;
    boolean b2 = false;
    boolean b3 = true;
    
    if(b1&b2 | b2&b3 | b2)
      System.out.println("ok ");
    if(b1&b2 | b2&b3 | b2| b1)
      System.out.println("dokey");
  }
}