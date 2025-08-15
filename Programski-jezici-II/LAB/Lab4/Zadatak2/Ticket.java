import java.util.Arrays;
import java.util.Scanner;

public class Ticket{
	private static int count=0;
	private int index;
	private int numbers[];
	
	Ticket()throws Exception{
		index = ++count;
		numbers = new int[7];
		Scanner input = new Scanner(System.in);
		System.out.println("Unesi 7 brojeva:");
		
		for(int i=0;i<7;){
			System.out.print((i+1) + ". broj: ");
			int numb = input.nextInt();
			System.out.println();
			if(numb<1 || numb>90){
				throw new ValueException();
			}
			else{
				for(int n : numbers){
					if(n==numb){
						throw new DuplicateValueException();
					}
				}
				numbers[i++] = numb;
			}
		}
		
		input.close();
	}
	
	public int[] getNumbers(){
		return numbers;
	}
	
	@Override
	public String toString(){
		String numbersString = "";
		for(int n : numbers){
			numbersString += n + " ";
		}
		return "Tiket broj: " + index + ". Brojevi: " + numbersString;
	}
	
	public boolean checkTicket(int []numbers){
		Arrays.sort(this.numbers);
		Arrays.sort(numbers);
		return Arrays.equals(this.numbers, numbers);
	}
}