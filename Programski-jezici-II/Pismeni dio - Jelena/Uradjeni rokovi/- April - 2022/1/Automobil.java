class Automobil extends Vozilo implements RegularnoInterface{
	boolean pojedinacno;
	
	
	Automobil(){
		super(Main.rand.nextInt(1, 9), Main.rand.nextInt(1000, 10000));
		pojedinacno = Main.rand.nextBoolean();
	}
}