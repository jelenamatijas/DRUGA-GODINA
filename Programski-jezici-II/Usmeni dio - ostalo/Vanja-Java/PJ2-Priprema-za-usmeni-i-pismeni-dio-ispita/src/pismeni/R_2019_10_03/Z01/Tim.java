package pismeni.R_2019_10_03.Z01;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tim{

	String ime;
	ArrayList<Kosarkas> igraci;
	static int count;

	public Tim(){
		ime = "TIM " + (++count);
		igraci = Stream.generate(Kosarkas::new).limit(12).collect(Collectors.toCollection(ArrayList::new));
	}

	public String toString(){
		return ime;
	}
}