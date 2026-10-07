
public class ScenarioException extends Exception
{
  public ScenarioException ()
  {
    super("Nepravilan scenario filma");
  }
  
  public ScenarioException(String msg)
  {
    super(msg);
  }
  
}