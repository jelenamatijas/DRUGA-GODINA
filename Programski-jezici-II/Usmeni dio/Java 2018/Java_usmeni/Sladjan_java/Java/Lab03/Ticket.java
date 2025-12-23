import java.lang.Object;
import java.util.Scanner;
import java.util.Arrays;

public class Ticket
{
  private static int serialNumber = 0;
  private  int[] numbers = new int [7];
  
  Ticket ()
  {
    serialNumber++;
    numbers = new int [7];
  }
  
  public void input() throws Exception
  {
    Scanner scan = new Scanner(System.in);
    int temp = 0;
    int t=0;
    System.out.println ("Unesite brojeve: ");
    for (int i=0;i<7;i++)
    {
      System.out.print("Unesite "+(i+1)+". broj: ");
      temp = scan.nextInt();
      if (temp>=1 && temp<90)
      {
        for (int j=0;j<i;j++)
        {
          if (temp == numbers[j])
          {
            throw new DuplicateException();
          }
          else numbers[i]=temp;
          
        }
      }
      else throw new ValueException();
     
    }
    scan.close();
    }
  
  public int[] returnNumbers ()
  {
    return numbers;
  }
  
  
  public boolean check (int [] winningNumbers)
  {
    int t=0;
    
   while (t == 0)
   {
     for (int i = 0;i<7;i++)
     {
       if (numbers[i]!=winningNumbers[i])
       {
         t=1;
         break;
       }
     }
   }
   
   
   if (t == 1) return false;
   else return true;
  }
  
   public int serial()
   {
     return serialNumber;
   }
    
  
  public static void main(String args[])
  {
    Ticket t = new Ticket();
    System.out.println(t.serialNumber);
    Ticket t1 = new Ticket();
    System.out.println(t1.serialNumber);
  }
  
}