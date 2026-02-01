package pismeni.R_2020_01_30.Z01;

import java.util.*;

public class AutomobilBuilder implements VoziloBuilder {
	public Automobil build(List<String> lines){
		String id = null;
		String konfig = null;
		ArrayList<Supermoc> moci = new ArrayList<>();
		Motor motor = null;
		Vozac vozac = null;
		for(String line : lines){
			if(line.startsWith("AUTOMOBIL")){
				id = line.split(";")[1];
				konfig = line.split(";")[2];
			}else if(line.startsWith("SUPER_MOC")){
				Supermoc moc = new Supermoc(line.split(";")[2]);
				moci.add(moc);
			}else if(line.startsWith("MOTOR")){
				String tip = line.split(";")[3];
				int snaga = Integer.parseInt(line.split(";")[2]);
				TipMotora tipMotora;
				if("dizel".equals(tip))
					tipMotora = TipMotora.DIZEL;
				else
					tipMotora = TipMotora.BENZIN;
				motor = new Motor(tipMotora, snaga);
			}else if(line.startsWith("VOZAC")){
				vozac = new Vozac(line.split(";")[2], line.split(";")[3]);
			}
		}
		return new Automobil(id, vozac, motor, konfig, moci);
	}
}