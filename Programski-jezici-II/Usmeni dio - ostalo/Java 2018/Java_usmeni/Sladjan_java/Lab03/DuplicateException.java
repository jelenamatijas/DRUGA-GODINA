public class DuplicateException extends Exception
{
  public DuplicateException()
  {
    super ("Nevazeci tiket, ponovljeni brojevi!!!");
  }
  
  public DuplicateException(String msg)
  {
    super (msg);
  }
  
}