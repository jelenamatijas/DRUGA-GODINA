class Partija extends Thread{
	Tim t1;
	Tim t2;
	boolean odigraoProfesionalac1 = false;
	boolean odigraoProfesionalac2 = false;
	Partija(Tim t1, Tim t2){
		this.t1=t1;
		this.t2=t2;
	}
	
	int bodovi1 = 0;
	int bodovi2 = 0;
	
	String tipIgraca(Igrac g){
		if(g instanceof AmaterNapredni){
			return "Amater-Napredni";
		}
		if(g instanceof AmaterPocetnik){
			return "Amater-Pocetnik";
		}
		if(g instanceof PlaceniProfesionalac){
			return "Profesionalac-Placeni";
		}
		return "Profesionalac-Neplaceni";
	}
	
	public void run(){
		System.out.println("Zapocinje partija za timove: " + t1 + " i " + t2 + ".");
		for(int i=0; i<4;i++){
			Igrac g1 = t1.igraci.get(i);
			Igrac g2 = t2.igraci.get(i);
			if(g1 instanceof SpinovanjeKugleInterface){
				odigraoProfesionalac1 = true;
			}
			if(g2 instanceof SpinovanjeKugleInterface){
				odigraoProfesionalac2 = true;
			}
			
			double pogodak = (double)(KuglaskaLiga.rand.nextInt(0, 101)) /100;
			
			System.out.println("Igrac " + g1.redniBroj + "->" + tipIgraca(g1) + " iz tima " + t1.nazivTima + " igra.");
			if(g1.vjerovatnoca >= pogodak){
				if(g1.vjerovatnoca==pogodak){
					System.out.println("Igrac " + g1.redniBroj + "->" + tipIgraca(g1) + " iz tima " + t1.nazivTima + " je ostvario STRAJK.");
					bodovi1+=20;
				}else{
					System.out.println("Igrac " + g1.redniBroj + "->" + tipIgraca(g1) +" iz tima " + t1.nazivTima + " je pogodio.");
					bodovi1++;
				}
				if(odigraoProfesionalac1 && !(g1 instanceof SpinovanjeKugleInterface)){
					System.out.println("Igrac " + g1.redniBroj + "->" + tipIgraca(g1) +" iz tima " + t1.nazivTima + " dobija 5 bodova.");
					bodovi1+=5;
				}
				
			}
			
			System.out.println("Igrac " + g2.redniBroj + "->" + tipIgraca(g2) +" iz tima " + t2.nazivTima + " igra.");
			if(g1.vjerovatnoca >= pogodak){
				if(g2.vjerovatnoca==pogodak){
					System.out.println("Igrac " + g2.redniBroj + "->" + tipIgraca(g2) +" iz tima " + t2.nazivTima + " je ostvario STRAJK.");
					bodovi2+=20;
				}else{
					System.out.println("Igrac " + g2.redniBroj + "->" + tipIgraca(g2) +" iz tima " + t2.nazivTima + " je pogodio.");
					bodovi2++;
				}
				if(odigraoProfesionalac1 && !(g2 instanceof SpinovanjeKugleInterface)){
					System.out.println("Igrac " + g2.redniBroj + "->" + tipIgraca(g2) +" iz tima " + t2.nazivTima + " dobija 5 bodova.");
					bodovi2+=5;
				}
			}
		}
		if(bodovi1 > bodovi2){
			t1.osvojeniBodovi++;
			System.out.println("U partiji izmedju " + t1.nazivTima + " i " + t2.nazivTima + " pobijedio je " + t1.nazivTima + ".");
		}else{
			t2.osvojeniBodovi++;
			System.out.println("U partiji izmedju " + t1.nazivTima + " i " + t2.nazivTima + " pobijedio je " + t1.nazivTima + ".");
		}
	}
}