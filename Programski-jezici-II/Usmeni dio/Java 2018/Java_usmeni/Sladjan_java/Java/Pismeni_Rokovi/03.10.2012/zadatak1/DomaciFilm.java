import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;


public class DomaciFilm extends Film implements Roman,Komedija,Drama
{
  public DomaciFilm(){};
  
  public DomaciFilm(String name,String releaseDate,String scenario,String genre) throws ScenarioException,GenreException,DateException
  {
    if(!"ROMAN".equalsIgnoreCase(scenario)) throw new ScenarioException();
    if(!"KOMEDIJA".equalsIgnoreCase(genre) && !"DRAMA".equalsIgnoreCase(genre)) throw new GenreException();
    
    
     String date = new SimpleDateFormat("YYYY:MM:dd").format(Calendar.getInstance().getTime());
    
    int releaseYear = Integer.valueOf(releaseDate.split(":")[0]);
    int releaseMounth = Integer.valueOf(releaseDate.split(":")[1]);
    int releaseDay = Integer.valueOf(releaseDate.split(":")[2]);
    
    int thisYear = Integer.valueOf(date.split(":")[0]);
    int thisMounth = Integer.valueOf(date.split(":")[1]);
    int thisDay = Integer.valueOf(date.split(":")[2]);
    
    if (thisYear < releaseYear) throw new DateException();
    else if(thisYear == releaseYear && thisMounth < releaseMounth) throw new DateException();
    else if(thisYear == releaseYear && thisMounth == releaseMounth && thisDay < releaseDay) throw new DateException();
    
    durationTime = new String();
    this.durationTime += thisYear - releaseYear;
    this.durationTime += ":";
    this.durationTime += thisMounth - releaseMounth;
    this.durationTime += ":";
    this.durationTime += thisDay - releaseDay;
    
    
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
    return "Naziv filma: "+ name + " || " + "Datum izdavanja: " + releaseDate + " || " + "Vrijeme trajanja: " + durationTime + " || " + "Zanr: " + genre + " || " + 
                               "Scenario: " + scenario;
  }
  
}