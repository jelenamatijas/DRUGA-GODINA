import java.util.*;
import java.util.stream.*;

class Main{
	static Random rand = new Random();
	static Class1[] arr1 = new Class1[10];
	static Class2[] arr2 = new Class2[10];
	static Class3[] arr3 = new Class3[10];
	
	static public class Rezultat{
		String name;
		Double value;
		
		Rezultat(String n, Double v){
			name = n;
			value = v;
		}
		
			@Override
	public String toString(){
		return name +  " " +  value;
	}
	}
	
	static public void main(String args[]){
		for(int i=0;i<10;i++){
			arr1[i] = new Class1();
			arr2[i] = new Class2();
			arr3[i] = new Class3();
		}
		
		Rezultat rezultat[] = filter(Status.NEW, arr1, arr2, arr3);
		Arrays.stream(arr1).forEach(a -> System.out.println(a));
		System.out.println();
		Arrays.stream(arr2).forEach(a -> System.out.println(a));
		System.out.println();
		Arrays.stream(arr3).forEach(a -> System.out.println(a));
		System.out.println();
		Arrays.stream(rezultat).forEach(a -> System.out.println(a));
		
	}
	
	static <T extends MyInterface> Rezultat[] filter(Status s, T[]... nizovi){
		Map<String, Double> rez = new HashMap<>();
		for(T arr[] : nizovi){
			for(T a:arr){
				
				if(a.getStatus() == s){
					rez.put(a.getName(), rez.getOrDefault(a.getName(), 0.0) + a.getValue());
				}
			}
		}
		
		Rezultat []rezultat = new Rezultat[rez.size()];
		int i=0;
		for(Map.Entry<String, Double> entry : rez.entrySet()){
			rezultat[i++] = new Rezultat(entry.getKey(), entry.getValue());
		}
		
		return rezultat;
		
	}
}