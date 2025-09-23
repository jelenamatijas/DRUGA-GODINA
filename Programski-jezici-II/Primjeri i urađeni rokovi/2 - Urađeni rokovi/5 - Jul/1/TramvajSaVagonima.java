public class TramvajSaVagonima extends Tramvaj implements TramvajSaVagonimaInterface {
	
	public TramvajSaVagonima() {
		super();
	}
	
	@Override
	public String toString() {
		return "TramvajSaVagonima{naziv=" + this.naziv + ", kapacitet=" + this.kapacitet + ", trenutniBrojPutnika=" + this.trenutniBrojPutnika + ", trajanjeSimulacije=" + this.trajanjeSimulacije + ", putnici=" + this.putnici + "}";
	}
}