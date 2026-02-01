

public class DateException extends Exception
{
  public DateException()
  {
    super("Nepravilan datum izdavanja filma!!");
  }
  
  public DateException(String msg)
  {
    super(msg);
  }
  
}