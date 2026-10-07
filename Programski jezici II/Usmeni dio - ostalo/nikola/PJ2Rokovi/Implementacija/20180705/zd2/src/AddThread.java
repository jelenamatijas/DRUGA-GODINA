package src;

import src.binarytree.*;
import java.util.Random;

import static  src.binarytree.Stablo.TreeSize;


public class AddThread extends Thread{
	public static int RND_VAL_CELLING = 50;
	private Stablo tree;
	private int toAdd;
	
	public AddThread(Stablo tree, int toAdd){
		this.tree = tree;
		this.toAdd = toAdd;
	}
	
	public void run(){
		while(TreeSize < toAdd){
			tree.dodaj(tree, new Random().nextInt(toAdd + RND_VAL_CELLING));
			
					try{
			//Thread.sleep(100);	
		} catch(Exception e){
			e.printStackTrace();
		}
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
		//tree.dodaj(tree, 12);*/
		System.out.println(TreeSize);
	}
}