import java.io.*;

public class VibracijaSenzor extends Senzor {
	
	public Integer vibracija = 25;
	
	public static File txtLogFile = new File("vibracijaTxtLog.txt");
	
	public VibracijaSenzor() {
		super();
		try {
			if (!txtLogFile.exists()) {
				txtLogFile.createNewFile();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public synchronized void mjeri() {
		Integer novaVrijednost = Main.random.nextInt(50) + 1;
		// upisi u fajl
		if (novaVrijednost > this.vibracija) {
			System.out.println("NAPOMENA: Nova ocitana vrijednost vibracije: " + novaVrijednost + ", veca od stare vrijednosti: " + this.vibracija);
		} else {
			System.out.println("Nova ocitana vibracije vrijednost: " + novaVrijednost + ", NIJE veca od stare vrijednosti: " + this.vibracija);
		}
		this.vibracija = novaVrijednost;
		this.upisiMjerenjeUFajl();
	}
	
	public void upisiMjerenjeUFajl() {
		try (PrintWriter pw = new PrintWriter(new FileWriter(txtLogFile, true))) {
			pw.println(this.vibracija + "\n");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "VibracijaSenzor{naziv=" + this.naziv + ", status=" + this.status + ", vibracija=" + this.vibracija + "}";
	}
}