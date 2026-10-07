public class GradskiAutobus extends Autobus implements BaterijskoPunjenjeInterface {
	
	public int nivoBaterije;
	
	public GradskiAutobus() {
		super();
		this.nivoBaterije = Main.random.nextInt(51) + 50;
	}
	
	@Override
	public void smanjiBateriju(int kolicina) {
		this.nivoBaterije -= kolicina;
	}
	
	@Override
	public String toString() {
		return "GradskiAutobus{naziv=" + this.naziv + ", kapacitet=" + this.kapacitet + ", trenutniBrojPutnika=" + this.trenutniBrojPutnika + ", trajanjeSimulacije=" + this.trajanjeSimulacije + ", putnici=" + this.putnici + ", nivoBaterije=" + this.nivoBaterije + "}";
	}
}