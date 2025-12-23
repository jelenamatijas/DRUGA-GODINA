public class ExamQuestion7{
	static int j;
	static void methodA(int i){
		boolean b;
		do{
			b = i<10 | methodB(4);
			System.out.println(b);
			b = i<10 || methodB(8);
			System.out.println(b);
		}while (!b);
	}
	static boolean methodB(int i){
		j += i;
		return true;
	}
	public static void main(String[] args){
		methodA(0);
		System.out.println( "j = " + j );
	}
}