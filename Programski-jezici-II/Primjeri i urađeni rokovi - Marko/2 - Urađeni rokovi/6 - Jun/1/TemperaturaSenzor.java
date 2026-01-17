import java.util.*;
import java.io.*;

public class TemperaturaSenzor extends Senzor {
	
	// Posljednjih 10 temperatura
	public ArrayList<Integer> temperature = new ArrayList<>();
	
	public static File txtLogFile = new File("temperaturaTxtLog.txt");
	
	public TemperaturaSenzor() {
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
		Integer randomTemperatura = Main.random.nextInt(50);
		System.out.println("Nova izmjerena temperatura: " + randomTemperatura + "°C");
		
		this.temperature.add(0, randomTemperatura);
		this.upisiMjerenjeUFajl();
		
		if (this.temperature.size() == 11) {
			this.temperature.remove(10);
		}
	}
	
	public void upisiMjerenjeUFajl() {
		try (PrintWriter pw = new PrintWriter(new FileWriter(txtLogFile, true))) {
			pw.println(this.temperature.get(0) + "°C\n");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "TemperaturaSenzor{naziv=" + this.naziv + ", status=" + this.status + ", temperature=" + this.temperature + "}";
	}
}