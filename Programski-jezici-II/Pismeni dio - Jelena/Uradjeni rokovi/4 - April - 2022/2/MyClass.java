class MyClass implements MyInterface{
	double value;
	String name;
	Status status;
	static int id = 1;
	
	MyClass(){
		value = Main.rand.nextDouble(100, 200);
		name = "Name_" + id;
		status = Status.values()[id%2];
		id++;
	}
	
	public Double getValue(){return value;}
	
	public String getName(){return name;}
	public Status getStatus(){return status;}
}