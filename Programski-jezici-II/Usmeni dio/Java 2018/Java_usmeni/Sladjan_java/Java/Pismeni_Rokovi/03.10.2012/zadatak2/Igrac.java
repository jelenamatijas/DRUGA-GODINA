import java.util.Random;


public class Igrac extends Thread
{
  public String name;
  public static boolean stop = false;
  public static int playNumber = 0;
  public int myPlayNumber;
  public static int token = 1;
  
  {
    ++playNumber;
    myPlayNumber = playNumber;
  }
  
  public Igrac(){};
  
  public Igrac(String name)
  {
    this.name = name;
    System.out.println("Igrac " + name + " myPlayNumber = " +myPlayNumber);
    start();
  }
  
  public void run()
  {
    System.out.println("Igrac " + this.name + " je krenuo...");
    boolean pobjeda = false;
    
    int zbir = 0;
    
    while(!stop)
    {
      
      synchronized(Spil.lock)
      {
        System.out.println("Na potezu je " + this.name + " a vrijednost TOKENA = " + token);
        if(!"".equalsIgnoreCase(Spil.lo) || myPlayNumber != token)
        {
          System.out.println("Igrac " + name + " ceka...");
          try
          {
            Spil.lock.wait();
          }
          catch(InterruptedException iex)
          {
            System.out.println("Igrac " + name + " nije zavrsio prvi. Njegov zbir je " + zbir + " INTERRUPTED");
          }
        }
        else
        {
          Spil.lo = "##BUSY##";
          Random rand = new Random();
          int slucajnaPozicija = rand.nextInt(52);
          zbir += Spil.spil[slucajnaPozicija].value;
          System.out.println("Igrac "+this.name+ " je izvukao kartu sa pozicije " + slucajnaPozicija + " sa vrijednoscu "+ Spil.spil[slucajnaPozicija].value);
          Spil.lo = "";
          
          if(token == playNumber)
          {
            token = 1;
          }
          else
          {
            ++token;
          }
          
          Spil.lock.notifyAll();
          try
          {
            sleep(1000);
          }
          catch(Exception ex)
          {
            ex.printStackTrace();
          }
          
          
          
          if(zbir>= 70 && zbir<= 80)
          {
            stop = true;
            System.out.println("Igrac " + name + " je pobjedio. Njegov zbir je " + zbir);
            pobjeda = true;
            interrupt();
          }
          
        }
        
      }//synchronized
      
    }//while
    
    if (!pobjeda)System.out.println("Igrac " + name + " nije pobjedio. Zbir je " + zbir);
    
  }//run
  
  
}//klasa
          
          
          
          
          
          
          