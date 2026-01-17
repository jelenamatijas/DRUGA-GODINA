import java.util.*;
class ExampleData1 implements Data{
	String type;
	Object value;
	String color;
	
	ExampleData1(String t, Object o, String c){
		type = t;
		value=o;
		color = c;
	}
	
	@Override
	public String getType(){
		return type;
	}
	
	@Override
	public Object getValue(){return value;}
	
	@Override
	public String toString(){
		return type + " " + value + " " + color;
	}
	
	@Override
	public int hashCode(){
		return Objects.hash(type, value, color);
	}
}