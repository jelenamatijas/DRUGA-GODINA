public class C{
	int i = 0;
	public static void main(String args[]){
		C c = new C();
	}
	C() {
		while(i < 2 ) {
			System.out.println(i);
			i++;
			continue;
		}
	}
}