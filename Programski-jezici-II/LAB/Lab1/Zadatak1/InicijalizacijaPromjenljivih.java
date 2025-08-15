class InicijalizacijaPromjenljivih{
	public static void main(String args[]){
		int vrijednost = 7, duplaVrijednost = vrijednost*2;
		double kolicnikSaDva = vrijednost/2.0;
		char znakPodatka = 'J';
		
		System.out.println("Vrijednost = " + vrijednost + "\n" + 
							"Dupla vrijednost = " + duplaVrijednost + "\n" + 
							"Kolicnik sa dva = " + kolicnikSaDva + "\n" + 
							"Znak podatka = " + znakPodatka);
			
		long vrijednostL = vrijednost;
		short vrijednostS = (short)vrijednost;
		System.out.println("VrijednostL = " + vrijednostL + "\n" + 
							"VrijednostS = " + vrijednostS);
	}
}