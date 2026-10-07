class PravougliTrougao extends Oblik{
  public int a,b;
  
  public void iscrtaj(){
    System.out.println("Ja sam "+this.getClass()+". Moje stranice su a= "+a+", b="+b+", a moja povrsina je "+povrsina());
  }
  
  public double povrsina(){
    return a*b/2.0;
  }
}