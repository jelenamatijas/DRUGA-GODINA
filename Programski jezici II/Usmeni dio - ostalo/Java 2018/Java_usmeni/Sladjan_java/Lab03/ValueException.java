public class ValueException extends Exception
{
  public ValueException ()
  {
    super ("Vrijednost mora biti od 1 do 90");
  }
  
  public ValueException (String msg)
  {
    super (msg);
  }
}