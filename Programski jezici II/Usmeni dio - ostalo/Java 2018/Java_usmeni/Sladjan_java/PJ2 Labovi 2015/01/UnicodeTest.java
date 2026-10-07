
public class UnicodeTest {

	public static void main(String[] args) {
		String прваПромјенљива = "абц";
		String drugaPromjenljiva = "abc";
		System.out.println(прваПромјенљива);
		System.out.println(drugaPromjenljiva);
		ЈуникодТест тест = new ЈуникодТест();
		System.out.println(тест.promjenljiva);
	}

}

class ЈуникодТест{
	String promjenljiva = "def";
}