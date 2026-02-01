class Result{
	Double value;
	String name;
	
	Result(Double v, String n){
		value = v;
		name = n;
	}

	@Override
	public String toString(){
		return name + " -> " + value;
	}
}