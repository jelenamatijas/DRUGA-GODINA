package pismeni.R_2019_10_03.Z01;

import java.util.*;
import java.util.stream.*;

public class Liga{

	String ime;
	ArrayList<Tim> timovi;
	static int count;

	public Liga(){
		ime = "LIGA " + (++count);
		timovi = Stream.generate(Tim::new).limit(16).collect(Collectors.toCollection(ArrayList::new));
	}

	public String toString(){
		return ime;
	}
}