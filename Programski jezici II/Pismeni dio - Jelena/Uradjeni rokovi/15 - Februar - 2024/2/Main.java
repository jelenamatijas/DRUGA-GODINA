import java.util.*;
import java.io.*;

class Main{
	
	public static <T>T convert(HashMap<String, String> attr, T obj)throws Exception{
		Class<?> c = obj.getClass();
		for(String key : attr.keySet()){
			try{
				var field = c.getDeclaredField(key);
				field.setAccessible(true);
				if(field.getType() == int.class || field.getType() == Integer.class){
					field.set(obj, Integer.parseInt(attr.get(key)));
				}else if(field.getType() == double.class || field.getType() == Double.class){
					field.set(obj, Double.parseDouble(attr.get(key)));
				}else{
					field.set(obj, attr.get(key));
				}
			}catch(NoSuchFieldException e){
				System.out.println("Greska: nije pronadjeno odgovarajuce polje.");
			}
		}
		return obj;
		
	}
	
	public static void main(String args[]){
		try{
			HashMap<String, String> hashMap = JSONParser.load(new File("obj.json"));
			Laptop l = convert(hashMap, new Laptop());
			HashMap<String, String> hashMap1 = JSONParser.load(new File("obj1.json"));
			Vozilo v = convert(hashMap1, new Vozilo());
				
			System.out.println("JSON: ");
			System.out.println(l);
			System.out.println(v);
			
			HashMap<String, String> hashMap2 = XMLParser.load(new File("obj.xml"));
			Laptop l1 = convert(hashMap2, new Laptop());
			HashMap<String, String> hashMap3 = XMLParser.load(new File("obj1.xml"));
			Vozilo v1 = convert(hashMap3, new Vozilo());
			
			System.out.println("XML: ");
			System.out.println(l1);
			System.out.println(v1);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}