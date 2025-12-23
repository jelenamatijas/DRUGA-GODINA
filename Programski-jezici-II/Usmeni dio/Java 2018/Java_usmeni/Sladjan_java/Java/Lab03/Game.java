import java.lang.Object;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class Game
{
  private int numberOfTickets = 3;
  private Ticket[] ticketArray;
  
  
  public Game ()
  {
    ticketArray = new Ticket [3];
    for (int i =0;i<3;i++)
    {
      ticketArray[i] = new Ticket();
      System.out.println("Serial = " + ticketArray[i].serial());
    }
   numberOfTickets = 3;
  }
  
  public Game (int a)
  {
    ticketArray = new Ticket [a];
    numberOfTickets = a;
    for (int i=0;i<a;i++) ticketArray[i] = new Ticket();
  }
  
  
  public static void main (String args[])
  {
    Game g1 = new Game();
    Random ran = new Random();
    int[] winningTicket = new int [7];
    
    for (int i=0;i<7;i++)
    {
      winningTicket[i] = ran.nextInt(90)+1;
    }
    
  
  
  for (int i=0;i<g1.numberOfTickets;i++)
  {
    try
    {
      
      (g1.ticketArray[i]).input();
      System.out.println("Game serial "+ i +". je "+ g1.ticketArray[i].serial());
      
    }
    catch (Exception ex)
    {
      System.out.println (ex);
    }
  }
  
  for (int i=0;i<g1.numberOfTickets;i++)
  {
    if (g1.ticketArray[i].check(winningTicket)) System.out.println("Tiket sa rednim brojem " + g1.ticketArray[i].serial() + ". je dobitni!!!!");
      else System.out.println("Tiket sa rednim brojem " + g1.ticketArray[i].serial() + " nije dobitni!!!!");
  }
  
  String winningNumbersString = "";
    for(int n : winningTicket)
      winningNumbersString += n + " ";
    System.out.println("\nDobitni brojevi: " + winningNumbersString);
  
  }
  
}