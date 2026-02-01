package src.list;

import java.io.Serializable;

public class Lista <T> implements Serializable {
	private T vrijednost;
	private Lista nextReference;
	
	public Lista(T vrijednost){
		this.vrijednost = vrijednost;
	}
	
	public synchronized void dodaj(T t) throws InterruptedException{
		this.wait(200);
		synchronized(this){
			Lista tail = nextReference;
			
			if(tail == null){
				nextReference = new Lista(t);
				return;
			}
			
			while(tail != null && tail.getNextReference() != null)
				tail = tail.getNextReference();
			
			tail.setNextReference(new Lista(t));
			
			this.notifyAll();
		}
	}
	
	public synchronized T obrisi() throws ListException, InterruptedException{
		this.wait(200);
		synchronized(this){
			Lista tail = nextReference;
			Lista toReturn = null;
			
			if(this == null)
				throw new ListException("Pokusaj brisanja prazne liste");
			
			if(tail == null)
				return null;
			
			while(tail.getNextReference() != null && tail.getNextReference().getNextReference() != null)
				tail = tail.getNextReference();
			toReturn = tail.getNextReference();
			tail.setNextReference(null);
			//this.notifyAll();
			if(toReturn != null)
				return (T)toReturn.getVrijednost();
			return null;
		}
	}
	
	public Lista getNextReference(){
		return nextReference;
	}
	
	public void setNextReference(Lista nextReference){
		this.nextReference = nextReference;
	}
	
	public T getVrijednost(){
		return vrijednost;
	}
	
	@Override
	
	public String toString(){
		String toReturn = vrijednost.toString() + "\n";
		Lista next = nextReference;
		while(next != null){
			toReturn += next.getVrijednost() + "\n";
			next = next.getNextReference();
		}
		return toReturn;
	}
	
}