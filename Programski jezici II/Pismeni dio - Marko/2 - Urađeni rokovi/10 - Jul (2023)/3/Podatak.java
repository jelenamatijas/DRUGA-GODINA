public interface Podatak {
	
	Double getValue();
	
	String getName();
	
	Status getStatus();
}

enum Status {
	NEW, PROCESSING, DONE;
}