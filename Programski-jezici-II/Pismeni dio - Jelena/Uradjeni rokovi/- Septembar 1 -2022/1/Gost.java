class Gost{
	int iznos;
	int id;
	static int ID = 1;
	
	Gost(){
		iznos = Main.rand.nextInt(100, 200);
		id = ID++;
	}
}