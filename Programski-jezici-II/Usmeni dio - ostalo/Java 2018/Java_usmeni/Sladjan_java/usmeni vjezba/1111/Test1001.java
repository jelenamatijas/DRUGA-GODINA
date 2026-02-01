public class Test1001
{
  public String toString()
  {
    return "Test1001";
  }
  
  public static void main(String[] a)
  {
    System.out.println(new Test1003());
  }
  
}

class Test1002 extends Test1001
{
  public String toString()
  {
    return super.toString();
  }
}

class Test1003 extends Test1002
{
  public String toString()
  {
    return super.toString();
  }
}