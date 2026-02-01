public class KriptPoruka extends Poruka{
	
	int shift = 3;
	
	public KriptPoruka(){
		super();
		kriptuj();
	}
	
	@Override
	public String toString(){
		return super.toString();
	}
	
	// Cezarova sifra
	private void kriptuj(){
		StringBuilder cipherText = new StringBuilder();
        for (char c : sadrzaj.toCharArray()) {
            if (Character.isUpperCase(c)) {
                cipherText.append((char) ('A' + (c - 'A' + shift) % 26));
            } else if (Character.isLowerCase(c)) {
                cipherText.append((char) ('a' + (c - 'a' + shift) % 26));
            } else {
                cipherText.append(c);
            }
        }
        this.sadrzaj = cipherText.toString();
	}
}