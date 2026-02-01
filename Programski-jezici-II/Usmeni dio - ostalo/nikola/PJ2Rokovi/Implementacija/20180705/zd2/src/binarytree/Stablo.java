package src.binarytree;

//sinhronizacija je omgucena jer samo jedan thread moze da dodaje u jednom trenu u stablo

public class Stablo{
	int vrijednost;
	public static int TreeSize = 0;
	Stablo leftNode = null, rightNode = null;
	public Stablo(int vrijednost){
		this.vrijednost = vrijednost;
		TreeSize++;
	}
	
	public synchronized Stablo dodaj(Stablo root, int toAdd){
		if(root == null){
			return new Stablo(toAdd);
		}
		try{
			if(toAdd == root.vrijednost){
				throw new DodavanjeException();
			}
		} catch(DodavanjeException e){
			//e.printStackTrace();
			System.out.println(toAdd + " vec postoji u stablu");
			return null;
		} 
		if(toAdd < root.vrijednost){
			if(root.leftNode == null)
				root.leftNode = root.dodaj(root.leftNode, toAdd);
			else
				root.leftNode.dodaj(root.leftNode, toAdd);
		}
			
		else{
			if(root.rightNode == null)
				root.rightNode = root.dodaj(root.rightNode, toAdd);
			else
				root.rightNode.dodaj(root.rightNode, toAdd);
		}
			
		return root;
	}
	
	public synchronized void printInorder(Stablo root){
		try{
		if(root == null)
			return;
		Thread.sleep(2000);
		printInorder(root.leftNode);
		System.out.println(root.vrijednost);
		printInorder(root.rightNode);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}

class DodavanjeException extends Exception {
	public DodavanjeException() {}
	public DodavanjeException(String message) {
		super(message);
	}
}