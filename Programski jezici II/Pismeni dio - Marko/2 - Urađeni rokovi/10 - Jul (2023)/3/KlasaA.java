public class KlasaA implements Podatak {
	
	Double value;
	String name;
	Status status;
	
	private static int redniBroj = 1;
	
	public KlasaA() {
		value = (double) Main.random.nextInt(100);
		name = "KlasaA" + redniBroj;
		if (redniBroj % 3 == 1) {
			status = Status.NEW;
		} else if (redniBroj % 3 == 2) {
			status = Status.PROCESSING;
		} else {
			status = Status.DONE;
		}
		redniBroj++;
		if (redniBroj == 4) {
			redniBroj = 1;
		}
	}
	
	@Override
	public Double getValue() {
		return value;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public Status getStatus() {
		return status;
	}
	
	@Override
	public String toString() {
		return "{" + name + ", " + value + ", " + status + "}";
	}
}