
public class KonverzijaReferenci {

	public static void main(String[] args) {
		String s = new String("abc");
		Object o = s;
		s = (String) o;
		Integer i = (Integer) o;
	}

}
