class Krug extends Oblik{
  public double poluprecnik;
  
  public void iscrtaj(){
    System.out.println("Ja sam "+this.getClass()+". Moj poluprecnik je "+poluprecnik+", a moja povrsina je "+povrsina());
  }
  
  public double povrsina(){
    return Math.pow(poluprecnik,2)*Math.PI;
  }
}