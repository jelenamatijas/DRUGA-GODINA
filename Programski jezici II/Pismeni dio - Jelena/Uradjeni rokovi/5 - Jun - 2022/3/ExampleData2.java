import java.util.*;
class ExampleData2 implements Data{
	String type;
	Object value;
	int size;
	
	ExampleData2(String t, Object o, int c){
		type = t;
		value =o;
		size = c;
	}
	
	@Override
	public String getType(){
		return type;
	}
	
	@Override
	public Object getValue(){return value;}
	
	@Override
	public String toString(){
		return type + " " + value + " " + size;
	}
	
	@Override
	public int hashCode(){
		return Objects.hash(type, value, size);
	}
}