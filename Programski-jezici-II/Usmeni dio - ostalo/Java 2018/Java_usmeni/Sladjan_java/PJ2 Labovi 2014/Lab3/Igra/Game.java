import java.util.Random;
public class Game {
  private static final int NUMBER_OF_TICKETS = 3;
  
  public static void main(String[] args) { 
    Ticket [] tickets = new Ticket[NUMBER_OF_TICKETS];
    int i = 0;
    while(i<NUMBER_OF_TICKETS){    
      try{  
        Ticket ticket = new Ticket();      
        System.out.println(ticket);
        tickets[i] = ticket;
        i++;
      }catch(Exception ex){
        System.out.println(ex);
      }
    }
    
    //simulacija izvlacenja brojeva
    int [] winningNumbers = new int[7];
    Random rand = new Random();
    i = 0;
    while(i<7){  
      int t = rand.nextInt(90)+1;
      boolean duplicate = false;
      for(int n : winningNumbers){
        if(t == n){
          duplicate = true;
        }
      }
      if(!duplicate){
        winningNumbers[i] =  t;
        i++;
      }
    }
    
    String winningNumbersString = "";
    for(int n : winningNumbers)
      winningNumbersString += n + " ";
    System.out.println("\nDobitni brojevi: " + winningNumbersString);
    
    for(Ticket t: tickets){      
      System.out.println(t + " Tiket " + (t.checkTicket(winningNumbers)?"je": "nije") + " dobitni"); 
    }
  }
}