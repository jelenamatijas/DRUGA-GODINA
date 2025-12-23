package net.etfbl.oop.package1;

public class ClassD extends Base {
	
	public static void main(String[] args) {
		Base classes[] = {new ClassA(), new ClassB(), new ClassC(), new ClassD()};
		for (Base base : classes) {
			if(base instanceof ClassA)
				System.out.println("ClassA");
			if(base instanceof ClassB)
				System.out.println("ClassB");
			if(base instanceof ClassC)
				System.out.println("ClassC");
			if(base instanceof ClassD)
				System.out.println("ClassD");
			if(base instanceof Base)
				System.out.println("Base");
//			if(base instanceof String)
//				System.out.println("String");
		}
	}
}
