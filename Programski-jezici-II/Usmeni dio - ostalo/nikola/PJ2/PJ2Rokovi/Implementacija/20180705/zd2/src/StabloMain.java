package src;

import src.binarytree.*;

import static src.AddThread.RND_VAL_CELLING;

public class StabloMain {
	
	private static final int  TO_ADD = 80;
	
	public static void main(String args[]){
		long startTime = System.currentTimeMillis();
		
		Stablo tree = new Stablo(RND_VAL_CELLING);
		AddThread addThread = new AddThread(tree, TO_ADD);
		ObserveThread observeThread = new ObserveThread(tree);
		addThread.start();
		try{
			Thread.sleep(20000);	
		} catch(Exception e){
			e.printStackTrace();
		}
		
		/*tree.dodaj(tree, 23);
		tree.dodaj(tree, 12);
		tree.dodaj(tree, 123);
		tree.dodaj(tree, 23);
		tree.dodaj(tree, 15);
		tree.dodaj(tree, 23);
		tree.dodaj(tree, 50);
		tree.dodaj(tree, 17);
		tree.dodaj(tree, 5);
		*/
		observeThread.start();
		try{
			observeThread.join();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		//tree.printInorder(tree);
		
		long endTime = System.currentTimeMillis();
		
		double runTime = (double)(endTime - startTime)/60_000;
		
		System.out.println("Run time " + runTime);
	}
}