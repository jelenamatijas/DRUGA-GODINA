package src;

import src.binarytree.*;

public class ObserveThread extends Thread{
	
	private Stablo tree;
	
	public ObserveThread(Stablo tree){
		this.tree = tree;
	}
	
	public void run(){
			tree.printInorder(tree);
	}
}