class Class1 implements MyInterface{
	Double value;
	String name;
	Status status;
	
	Class1(Double v, String n, Status s){
		value = v;
		name = n;
		status = s;
	}
	
	public Double getValue(){return value;}
	public String getName(){return name;}
	public Status getStatus(){return status;}
}