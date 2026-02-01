import java.util.*;

class Restoran extends Thread{
	List<Sto> stolovi = new ArrayList<>();
	boolean radi =true;
	Meni meni;
	Meni meni2;
	static int ID = 1;
	int id;
	
	Restoran(){
		int idStola = 1;
		if(ID==1){
			meni = new Meni(false);
		}else{
			meni = new Meni(true);
		}
		id = ID++;
		meni2 = new Meni(true);
		for(int i=0;i<10;i++){
			stolovi.add(new Sto(2,idStola++,false, meni, meni2));
			stolovi.add(new Sto(4,idStola++,false, meni, meni2));
			stolovi.add(new Sto(6,idStola++,false, meni, meni2));
		}
		for(int i=0;i<3;i++){
			stolovi.add(new Sto(2,idStola++,true, meni, meni2));
			stolovi.add(new Sto(4,idStola++,true, meni, meni2));
			stolovi.add(new Sto(6,idStola++,true, meni, meni2));
		}
		
	}
	
	public void run(){
		while(radi){
			GrupaGostiju grupa;
			synchronized(Main.lock){
				if(Main.grupeGostiju.size()>0){
					grupa = Main.grupeGostiju.remove(0);
				}else{
					radi = false;
					break;
				}
			}
			
			String sjeli = null;
			for(Sto s: stolovi){
				if(grupa.imaPusaca() && s.zaPusace && s.prazan){
					if(s.brojMjesta>=grupa.gosti.size()){
						if((grupa.imaVegetarijanaca() && meni.imaVegetarijansko) || !grupa.imaVegetarijanaca()){
							s.grupa = grupa;
							System.out.println("Grupa " +grupa.id + " je sjela za sto");
							s.start();
							sjeli = null;
							break;
						}else{
							sjeli = " restoran nema vegetarijanski meni.";
						}
					}else{
						sjeli = " restoran nema slobodan sto za pusace.";
					}
				}else if(!grupa.imaPusaca() && !s.zaPusace && s.prazan){
					if(s.brojMjesta>=grupa.gosti.size()){
						if((grupa.imaVegetarijanaca() && meni.imaVegetarijansko) || !grupa.imaVegetarijanaca()){
							s.grupa = grupa;
							System.out.println("Grupa " +grupa.id + " je sjela za sto");
							s.start();
							sjeli = null;
							break;
						}else{
							sjeli = " restoran nema vegetarijanski meni.";
						}
					}else{
						sjeli = " restoran nema slobodan sto za nepusace.";
					}
				}
			}
			
			if(sjeli !=null){
				System.out.println("Grupa " +grupa.id + " nije sjela za sto jer " +  sjeli);
			}
			
		}
		
	}
}