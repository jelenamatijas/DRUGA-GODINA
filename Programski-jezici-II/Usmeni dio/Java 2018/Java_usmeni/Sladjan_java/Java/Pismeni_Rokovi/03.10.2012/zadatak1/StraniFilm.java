import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;


public class StraniFilm extends Film implements Drama,NaucnaFantastika,
                                  Komedija,Roman,IstinitaPrica
{
  public StraniFilm(){};
  
  public StraniFilm(String name,String releaseDate,String scenario,String genre) throws ScenarioException,GenreException,DateException
  {
    if(!"ISTINITA PRICA".equalsIgnoreCase(scenario) && !"ROMAN".equalsIgnoreCase(scenario)) throw new ScenarioException();
    if(!"NAUCNA FANTASTIKA".equalsIgnoreCase(genre) && !"KOMEDIJA".equalsIgnoreCase(genre) && !"DRAMA".equalsIgnoreCase(genre)) throw new GenreException();
    
    String date = new SimpleDateFormat("YYYY:MM:dd").format(Calendar.getInstance().getTime());
    
    int releaseYear = Integer.valueOf(releaseDate.split(":")[0]);
    int releaseMounth = Integer.valueOf(releaseDate.split(":")[1]);
    int releaseDay = Integer.valueOf(releaseDate.split(":")[2]);
    
    int thisYear = Integer.valueOf(date.split(":")[0]);
    int thisMounth = Integer.valueOf(date.split(":")[1]);
    int thisDay = Integer.valueOf(date.split(":")[2]);
    
    if (thisYear < releaseYear) throw new DateException();
    else if(thisMounth < releaseMounth) throw new DateException();
    else if(thisDay < releaseDay) throw new DateException();
    
    
    
    
    this.name = name;
    this.releaseDate  = releaseDate;
    this.scenario = scenario;
    this.genre = genre;
    
    
    
  }
  
  @Override
  public void serialization(File f)
  {
    if (!f.exists()) System.out.println("Zadata putanja " + f + " ne postoji!");
    else
    {
      try
      {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f,true));
        System.out.println("Objekat uspjesno upisan");
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }
    }
  }
  
   @Override
  public String toString()
  {
    return "Naziv filma: "+ name + "\n" + "Datum izdavanja: " + releaseDate + "\n" + "Vrijeme trajanja: " + durationTime + "\n" + "Zanr: " + genre + "\n" + 
                               "Scenario: " + scenario;
  }
  
}