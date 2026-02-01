public class PrigradskiAutobus extends Autobus {
	
	public PrigradskiAutobus() {
		super();
	}
	
	@Override
	public String toString() {
		return "PrigradskiAutobus{naziv=" + this.naziv + ", kapacitet=" + this.kapacitet + ", trenutniBrojPutnika=" + this.trenutniBrojPutnika + ", trajanjeSimulacije=" + this.trajanjeSimulacije + ", putnici=" + this.putnici + "}";
	}
}