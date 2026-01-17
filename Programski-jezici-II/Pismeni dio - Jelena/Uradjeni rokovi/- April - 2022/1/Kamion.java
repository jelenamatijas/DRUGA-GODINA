class Kamion extends Vozilo implements PosebnaProceduraInterface{
	boolean prevoziAutomobile;
	Vozilo v[] = null;
	
	Kamion(){
		super(Main.rand.nextInt(1, 4), Main.rand.nextInt(1000, 10000));
		prevoziAutomobile = Main.rand.nextBoolean();
		if(prevoziAutomobile){
			v = new Vozilo[2];
			v[0] = new Automobil();
			v[1] = new Automobil();
		}
	}
}