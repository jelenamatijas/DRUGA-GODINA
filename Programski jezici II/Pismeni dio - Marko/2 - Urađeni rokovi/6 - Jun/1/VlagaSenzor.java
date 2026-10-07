import java.io.*;

public class VlagaSenzor extends Senzor {
	
	public Integer vlaga = 25;
	
	public static File txtLogFile = new File("vlagaTxtLog.txt");
	
	public VlagaSenzor() {
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
		Integer randomVlaga = Main.random.nextInt(50) + 1;
		System.out.println("Nova izmjerena vlaga: " + randomVlaga);
		
		this.vlaga = randomVlaga;
		this.upisiMjerenjeUFajl();
	}
	
	public void upisiMjerenjeUFajl() {
		try (PrintWriter pw = new PrintWriter(new FileWriter(txtLogFile, true))) {
			pw.println(this.vlaga + "\n");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "VlagaSenzor{naziv=" + this.naziv + ", status=" + this.status + ", vlaga=" + this.vlaga + "}";
	}
}