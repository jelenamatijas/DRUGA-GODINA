class MaskiranjeClanova {
	int x = 5;
	public void metoda(){System.out.println("jedan");}
}
class MaskiranjeClanovaDva extends MaskiranjeClanova {
	int x = 6;
	public void metoda(){System.out.println("dva");}
	public static void main(String[] args) {
		MaskiranjeClanova mc = new MaskiranjeClanova();
		MaskiranjeClanova mcd = new MaskiranjeClanovaDva();
		System.out.println(((MaskiranjeClanovaDva)mcd).x + " " + mc.x);
		((MaskiranjeClanova)mcd).metoda();
		System.out.println(mcd);
}}