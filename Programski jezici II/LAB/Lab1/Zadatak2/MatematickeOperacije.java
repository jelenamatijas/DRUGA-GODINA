class MatematickeOperacije{
	private int operand1, operand2;
	
	public MatematickeOperacije(){
		operand1=operand2=1;
	}
	
	public MatematickeOperacije(int operand1, int operand2){
		this.operand1 = operand1;
		this.operand2 = operand2;
	}
	
	public int proizvod(){
		return operand1*operand2;
	}
	
	public double kolicnik(){
		return (double) operand1/operand2;
	}
	
	public boolean prviJeVeci(){
		return operand1>operand2;
	}
	
	public int sumaPrvih20CijelihBrojeva(){
		int suma = 0;
		for(int i=1;i<=20; i++){
			suma += i;
		}
		return suma;
	}
	
	public int razlikaBrojevaDjeljivihSa3(){
		int razlika=99;
		for(int i = 96; i>=3; i-=3){
			razlika -= i;
		}
		return razlika;
	}
	
	public static void main(String args[]){
		MatematickeOperacije matob1 = new MatematickeOperacije();
		MatematickeOperacije matob2 = new MatematickeOperacije(3,4);
		
		// Prvi objekat
		System.out.println("Proizvod = " + matob1.proizvod() + "\n" + 
							"Kolicnik = " + matob1.kolicnik() + "\n" + 
							"Prvi je veci = " + matob1.prviJeVeci() + "\n" + 
							"Suma prvih 20 cijelih brojeva = " + matob1.sumaPrvih20CijelihBrojeva() + "\n" + 
							"Razlika brojeva djeljivih sa 3 = " + matob1.razlikaBrojevaDjeljivihSa3());
		
		// Drugi objekat
		System.out.println("Proizvod = " + matob2.proizvod() + "\n" + 
							"Kolicnik = " + matob2.kolicnik() + "\n" + 
							"Prvi je veci = " + matob2.prviJeVeci() + "\n" + 
							"Suma prvih 20 cijelih brojeva = " + matob2.sumaPrvih20CijelihBrojeva() + "\n" + 
							"Razlika brojeva djeljivih sa 3 = " + matob2.razlikaBrojevaDjeljivihSa3());
	}
}