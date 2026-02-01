import java.util.Random;


public class Konj extends Thread
{
  public String naziv;
  public double snaga;
  public int x;
  public int y;
  public static boolean stop = false;
  
  
  
  public Konj()
  {
  }
  
  public Konj(String naziv,double snaga,int x)
  {
    this.naziv = naziv;
    this.snaga = snaga;
    this.x = x;
    this.y = 0;
    
  }
  
  public void run()
  {
    synchronized(TrkaKonjaServer.konji)
    {
      TrkaKonjaServer.konji.add(this);
    }
    
    System.out.println("Grlo "+this.naziv + " je krenulo...");
    Random rand = new Random();
    while(!stop)
    {
      try
      {
        sleep(1000);
        
        double stotiDioSnage = this.snaga / 100.0;
        
        int slucajnaPromjena = 2 + rand.nextInt(4);
        
        int z = rand.nextInt(2);
        
        int neg =0;
        
        if(z == 0) neg = -1;
        else neg = 1;
        
        this.snaga = this.snaga + (stotiDioSnage* (double) slucajnaPromjena * (double)neg);
        
        
        int brojPolja = 3 + rand.nextInt(4);
        
        this.y += brojPolja;
        
        if (this.y >= 99) 
        {
          stop = true;
          TrkaKonjaServer.finish = true;
        }
        
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }
      
    }
    
  }//zatvoren run
  
  public String getPosition()
  {
    return "Grlo: " + this.naziv + " POZICIJA: " + " [" + x +"] ["+ this.y +"]";
  }
  
  public int brojPozicijaDoKraja()
  {
    return 99-this.y;
    
  }
  
  
}
    
    
    
    
    
    