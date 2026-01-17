class Nit extends Thread{
	static int ID =1;
	int id;
	
	Nit(){
		id = ID++;
	}
	
	public void run(){
		int i, j;
		if(Main.rand.nextBoolean()){
			i = 0;
			j = 2000;
		}else{
			j = 0;
			i = 2000;
		}
		
		while(i<j && Main.odabrani.size()<2){
			System.out.println("Nit " + id + " procesuira studenta: " + Main.studenti.get(i));
			if(Main.rand.nextInt(0,101) < 2){
				synchronized(Main.lock){
					try{
						if(Main.odabrani.size()<2 && !Main.odabrani.contains(Main.studenti.get(i))){
							Main.odabrani.add(Main.studenti.get(i));
						}
					}catch(Exception e){}
				}
			}
			try{
				sleep(100);
			}catch(InterruptedException e){
				System.out.println("Greska pri pauziranju niti " + id);
			}
			i++;
		}
	}
}