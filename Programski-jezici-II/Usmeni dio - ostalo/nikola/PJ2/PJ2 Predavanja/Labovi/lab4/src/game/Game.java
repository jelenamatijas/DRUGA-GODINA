package src.game;

import java.util.Random;
import java.util.Scanner;

public class Game {
	private static int []win;
	static {
		win = new int[7];
		int []tmpWin;
		do {
			tmpWin = new int[7];
			for(int i = 0; i < 7; i++) {
				Random rn = new Random();
				tmpWin[i] = rn.nextInt(90) + 1;
				System.out.println(tmpWin[i]);
			}
		}while(Ticket.isDuplicate(tmpWin));
			for(int i = 0; i < 7; i++) {
				win[i] = tmpWin[i];
			}
			
	}
	public static void main(String []args) {
		int currTicket = 0;
		Ticket []tickets = new Ticket[3];
		for(int i = 0; i < tickets.length; i++) {
			System.out.println("Unos " + currTicket + ". tiketa");
			int []tmpTicket = new int[7];
					Scanner scan = new Scanner(System.in);
							for (int j = 0; j < 7; j++) {
			System.out.println((j + 1) + " broj: ");
			int temp = scan.nextInt();
			tmpTicket[j] = temp;
							}
				System.out.println("Simulacija unosa tiketa u sistem");
				try {
					tickets[i] = new Ticket(tmpTicket);
				}
				catch(DoubleValueException | ValueException ex) {
					System.out.println(ex.getMessage());
					System.out.println("Greska pri unosu tiketa");
					return;
				}
				System.out.println(tickets[i].getSerialNum() + " Tiket " + (tickets[i].checkTicket(win) ? "je" : "nije") + " dobitni");
				}
			}
		}