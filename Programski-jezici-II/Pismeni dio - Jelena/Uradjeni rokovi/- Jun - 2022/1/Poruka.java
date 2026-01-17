import java.time.LocalDateTime;

class Poruka{
	String primalac, posiljalac;
	LocalDateTime vrijeme;
	String tekst;
	
	Poruka(String pr, String po, LocalDateTime v, String t){
		primalac = pr;
		posiljalac = po;
		vrijeme = v;
		tekst = t;
	}
}