package src.game;

import java.util.Arrays;

public class Ticket{
	private final int lengthOfTicket = 7;
	static int numOfTickets = 0;
	private int serialNum;
	private int[] ticket;
	
	public Ticket(int []ticket) throws DoubleValueException, ValueException{
				if(isDuplicate(ticket)) 
			throw new DoubleValueException("Dupla vr. pronadjena na tiketu");
		else if(isIncorrect(ticket)) 
			throw new ValueException("Pronadjena vrijednost nekorektnog broja");
		else {
			this.ticket = new int[lengthOfTicket];
			for(int i = 0; i < ticket.length; i++){
				this.ticket[i] = ticket[i];
			}
		++numOfTickets; 
		serialNum = numOfTickets;
		System.out.println("Tiket uspesno inicalizovan");
		}
	}
	
	public int getSerialNum() {
		return serialNum;
	}
	
	public int[] getTicket() {
		return ticket;
	}
	
	public static boolean isDuplicate(int []ticket)  {
		for (int i = 0; i < ticket.length; i++) {
			for (int j = 0; j < ticket.length; j++) {
				if ((i != j ) && ( ticket[i] == ticket[j]))
					return true;
			}
		}
		return false;
	}
	
	public static boolean isIncorrect(int []ticket) {
		for (int i : ticket){
			if(i > 91 || i < 0)
				return true;
		}
		return false;
	}
		public boolean checkTicket(int[] winningNumbers) {
		Arrays.sort(winningNumbers);
		Arrays.sort(ticket);
		return Arrays.equals(winningNumbers, ticket);
	}
}