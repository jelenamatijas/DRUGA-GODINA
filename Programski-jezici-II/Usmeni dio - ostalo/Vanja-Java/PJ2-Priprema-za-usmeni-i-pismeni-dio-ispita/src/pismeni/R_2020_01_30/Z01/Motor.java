package pismeni.R_2020_01_30.Z01;

import java.util.*;

public class Motor{
	TipMotora tip;
	int snaga;

	public Motor(){
		int br = new Random().nextInt(2);
		switch(br){
			case 0:
				tip = TipMotora.DIZEL;
				break;
			case 1:
				tip = TipMotora.BENZIN;
				break;
		}
	}

	public Motor(TipMotora tip, int snaga){
		this.tip = tip;
		this.snaga = snaga;
	}

	public String toString(){
		return tip.toString();
	}
}