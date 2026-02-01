public class GenreException extends Exception
{
  public GenreException ()
  {
    super("Nepravilan zanr filma!!!");
  }
  
  public GenreException (String msg)
  {
    super(msg);
  }
  
  
}