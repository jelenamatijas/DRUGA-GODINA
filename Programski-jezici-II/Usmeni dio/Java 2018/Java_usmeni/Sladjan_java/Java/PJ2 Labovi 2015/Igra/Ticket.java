import java.util.Scanner;
import java.util.Arrays;

public class Ticket {
  
  private int [] numbers;
  private int serialNumber;
  private static int numOfTickets;
  
  public Ticket() throws Exception{ 
    Scanner scan = new Scanner(System.in);
    numbers = new int[7];
    System.out.println("Unesite 7 brojeva:");
    for(int i=0; i<7; i++){
      System.out.println((i+1) + " broj: ");
      int temp = scan.nextInt();
      if(temp > 0 && temp <=90){
        for(int n : numbers){
          if(n == temp){
           throw new DuplicateValueException();
          }
        }
        numbers[i] = temp;
      } else {
        throw new ValueException();
      }
    }
    scan.close();
    serialNumber = ++numOfTickets;
  }
  
  public int[] getNumbers(){
    return numbers;
  }
  
  @Override
  public String toString(){
    String numbersString = "";
    for(int n : numbers)
      numbersString += n + " ";
    return "Tiket broj: " + serialNumber + ". Brojevi: " + numbersString;
  }
  
  public boolean checkTicket(int [] winningNumbers){
    for(int number: numbers){
      boolean t = false;
      for(int winningNumber : winningNumbers){        
        if(number == winningNumber){
          t = true;
        }
      }
      if(t == false){
        return t;
      }
    }
    //ili
   // Arrays.sort(winningNumbers);
   // Arrays.sort(numbers);
   // return Arrays.equals(winningNumbers, numbers);
    return true;
  }  
  
}
