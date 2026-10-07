public class M1
{
  int id;
  public M1(int id)
  {
    System.out.println("M1: " + id);
    this.id = id;
  }
  
  public static void main(String[] args)
  {
    M1 m = new M1(3);
  }
  
  
}