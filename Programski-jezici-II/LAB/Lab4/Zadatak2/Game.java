import java.util.Random;

class Game{
	private static Ticket tickets[];
	private static int NUMBER_OF_TICKETS = 3;
	Game(){
		tickets = new Ticket[NUMBER_OF_TICKETS];
		
		for(int i=0; i< NUMBER_OF_TICKETS; ){
			try{
				Ticket ticket = new Ticket();
				System.out.println(ticket);
				tickets[i++] = ticket;
			}catch(Exception e){
				System.out.println(e);
			}
		}
	}
	
	private static int[] generateWinningCombination(){
		int winningNumbers[] = new int[7];
		Random rand = new Random();
		int i=0; 
		boolean duplicate = false;
		while(i<7){
			int n = rand.nextInt(90)+1;
			for(int num : winningNumbers){
				if(num == n){
					duplicate = true;
				}
			}
			if(!duplicate){
					winningNumbers[i++] = n;
				}
		}
		return winningNumbers;
	}
	
	private static void checkTickets(int []winningNumbers){
		for(Ticket t : tickets){
			System.out.println("Tiket " + t + (t.checkTicket(winningNumbers) ? "je" : "nije") + " dobitni tiket.");
		}
	}

	
	public static void main(String args[]){
		Game game = new Game();
		int winningNumbers[] = generateWinningCombination();
		
		String numbersString = "";
		for(int n : winningNumbers){
			numbersString += n + " ";
		}
		System.out.println("Dobitna kombinacija: " + numbersString);
		
		System.out.println("Provjera tiketa: ");
		checkTickets(winningNumbers);
	}
}