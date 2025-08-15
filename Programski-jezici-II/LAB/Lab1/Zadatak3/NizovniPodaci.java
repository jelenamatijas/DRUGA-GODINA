class NizovniPodaci{
	char slova[];
	int tablicaMnozenja[][];
	
	public NizovniPodaci(){
		tablicaMnozenja = new int[10][10];
		for(int i=0; i<10; i++){
			for(int j=0; j<10; j++){
				tablicaMnozenja[i][j] = (i+1)*(j+1);
			}
		}
	}
	
	public void setSlova(char []s){
		slova = s;
	}
	public void setTablicaMnozenja(int [][]m){
		tablicaMnozenja=m;
	}
	
	public char[] getSlova(){
		return slova;
	}
	
	public int[][] getTablicaMnozenja(){
		return tablicaMnozenja;
	}
	
	public void printSlova(){
		if(slova!=null){
			for(int i=0;i<slova.length;i++){
				System.out.print(slova[i]);
			}
			System.out.println();
		}
	}
	
	public void printTablicaMnozenja(){
		for(int j=0; j<10; j++){
			for(int i=0;i<10;i++){
				System.out.print(tablicaMnozenja[j][i] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String args[]){
		NizovniPodaci objekat = new NizovniPodaci();
		
		char slova[] = {'J','e','l','e','n','a'};
		objekat.setSlova(slova);
		
		objekat.printSlova();
		objekat.printTablicaMnozenja();
	}
}