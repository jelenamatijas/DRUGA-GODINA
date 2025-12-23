class Pravougaonik extends Oblik {
  
  public int a, b;
  
  public void iscrtaj(){
    System.out.println("Ja sam "+this.getClass()+". Moje stranice su a="+a+", b="+b+" a povrisna je "+povrsina());
    System.out.println("A ovako izgledam: ");
    for(int i=0; i<a; i++){
      for(int j=0; j<b; j++){
      System.out.print("* ");
      }
      System.out.println();
    }
  }
  public double povrsina(){
    return a*b;
  }
  
}
