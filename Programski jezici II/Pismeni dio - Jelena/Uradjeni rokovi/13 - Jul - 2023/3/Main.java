import java.util.*;

class Main{
	
	static Result[] filter(Status status, MyInterface[][] arrays){
		Map<String, Double> map = new HashMap<>();
		
		for(MyInterface[] array: arrays){
			for(MyInterface a : array){
				if(a.getStatus() == status){
					map.put(a.getName(), map.getOrDefault(a.getName(), 0.0) + a.getValue());
				}
			}
		}
		
		Result []result = new Result[map.size()];
		int i=0;
		for(Map.Entry<String, Double> entry : map.entrySet()){
			result[i++] = new Result(entry.getValue(),entry.getKey());
		}
		
		return result;
	}
	
	static public void main(String argsp[]){
		Random rand = new Random();
		
		MyInterface []n1 = new MyInterface[10];
		MyInterface []n2 = new MyInterface[10];
		MyInterface []n3 = new MyInterface[10];
		
		Status statuses[] = Status.values();
		
		for(int i=0;i<10;i++){
			n1[i] = new Class1(rand.nextDouble(100, 400), "Name_" + rand.nextInt(5, 15), statuses[i%3]);
			n2[i] = new Class2(rand.nextDouble(200, 400), "Name_" + rand.nextInt(10, 20), statuses[i%3]);
			n3[i] = new Class3(rand.nextDouble(150, 300), "Name_" + rand.nextInt(1, 10), statuses[i%3]);
		}
		
		MyInterface[][] n = {n1,n2,n3};
		Result[] array = filter(Status.NEW, n);
		for(Result o : array){
			System.out.println(o);
		}
	}
}