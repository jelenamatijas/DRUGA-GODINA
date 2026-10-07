class Igrac{
	static int id = 1;
	int redniBroj;
	double vjerovatnoca;
	
	Igrac(double vjerovatnoca){
		redniBroj = id;
		id++;
		this.vjerovatnoca = vjerovatnoca;
	}
	
}