import java.io.*;

public abstract class Film implements Serializable
{
  public String name;
  public String releaseDate;
  public String durationTime;
  public String scenario;
  public String genre;
  
  
  public abstract void serialization(File f);
  @Override
  public abstract String toString();
}