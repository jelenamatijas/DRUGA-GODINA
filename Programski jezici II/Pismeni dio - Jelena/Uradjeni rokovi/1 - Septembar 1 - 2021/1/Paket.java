import java.util.*;

class Paket <T extends Posiljka>{
	List<T> posiljke;
	int tezina;
	
	Paket(){
		posiljke = new ArrayList<>();
	}
	
	public void add(T posiljka){
		posiljke.add(posiljka);
		tezina+= posiljka.tezina;
	}
}