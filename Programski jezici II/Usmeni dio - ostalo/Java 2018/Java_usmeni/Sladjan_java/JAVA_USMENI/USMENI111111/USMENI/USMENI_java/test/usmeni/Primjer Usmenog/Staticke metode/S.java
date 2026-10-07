class S
{
  public static S metoda(){
    System.out.println("Staticka");
    return new S();
  }
  
  public int nestaticka()
  {
    return 5;
  }
  
  public void izuzeci() throws MyException
  {}
  
  public long iste()
  {
  return 5l;
  }
  
  public static void main(String[] args)
  {
  A a=new A();
  a.fun();
  a.metoda();
  }
  
}
 
class A extends S
{
  public static A metoda() {
    System.out.println("Staticka u naslednici");  
    return new A();  }
  
  {                                 
    
  }
  public void izuzeci() throws MyException1,MyException2 
  {}
  public void fun()
  {
    super.metoda();           
  }
  public long iste()   
  {
  return 12;
  }
}