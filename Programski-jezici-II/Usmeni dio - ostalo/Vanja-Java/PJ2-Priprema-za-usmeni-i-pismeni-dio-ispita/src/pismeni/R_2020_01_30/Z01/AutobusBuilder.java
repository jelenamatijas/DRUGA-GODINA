package pismeni.R_2020_01_30.Z01;

import java.util.*;

public class AutobusBuilder implements VoziloBuilder {
	public Autobus build(List<String> lines){
		String id = null;
		String konfig = null;
		Motor motor = null;
		Vozac vozac = null;
		int mjesta = 0;
		for(String line : lines){
			if(line.startsWith("AUTOBUS")){
				id = line.split(";")[1];
				konfig = line.split(";")[2];
				mjesta = Integer.parseInt(line.split(";")[3]);
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
		return new Autobus(id, vozac, motor, konfig, mjesta);
	}
}