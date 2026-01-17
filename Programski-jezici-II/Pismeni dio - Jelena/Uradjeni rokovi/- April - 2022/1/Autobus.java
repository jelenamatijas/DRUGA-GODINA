class Autobus extends Vozilo implements PosebnaProceduraInterface2{
	
	Autobus(){
		super(Main.rand.nextInt(8, 53), Main.rand.nextInt(1000, 10000));
	}
}