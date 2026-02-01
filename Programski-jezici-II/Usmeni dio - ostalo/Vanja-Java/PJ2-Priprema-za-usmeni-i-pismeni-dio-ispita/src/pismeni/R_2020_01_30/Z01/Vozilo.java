package pismeni.R_2020_01_30.Z01;

import java.util.*;

public abstract class Vozilo extends Thread{
	String id;
	Vozac vozac;
	Motor motor;
	String konfig;
	double koef;
	static int count;

	public Vozilo(){
		id = "" + System.currentTimeMillis();
	}

	public Vozilo(String id, Vozac vozac, Motor motor, String konfig, double koef){
		this.id = id;
		this.vozac = vozac;
		this.motor = motor;
		this.konfig = konfig;
		this.koef = koef;
	}

	public String toString(){
		return id + " " + vozac + " " + konfig + " " + motor;
	}

	public void run(){
		long begin = System.currentTimeMillis();
		ObicnoPolje[] staza = Simulacija.staza;
		for(int i = 0; i<staza.length; i++){
			try{
				if(staza[i] instanceof Klizavo){
					boolean ispada = new Random().nextInt(100) < ((KlizavoPolje)staza[i]).procenat; // !
					if(ispada){
						System.out.println(this + " izletilo sa staze... ");
						return;
					}
					Thread.sleep(brzina());
				}else if(staza[i] instanceof Neravno){
					Thread.sleep(brzinaNeravno());
				}else{
					Thread.sleep(brzina());
				}
				System.out.println(this + " se kreće... na polju " + i + " " + staza[i]);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("ZAVRŠIO >> " + this);
		synchronized(Simulacija.lock){
			Simulacija.poredak.put(this.toString(), (end - begin));
		}
	}

	public long brzinaNeravno(){
		return (long)(100 / motor.snaga * koef * 1000L);
	}

	public long brzina(){
		return (long)(100 / motor.snaga * 1000L);
	}
}