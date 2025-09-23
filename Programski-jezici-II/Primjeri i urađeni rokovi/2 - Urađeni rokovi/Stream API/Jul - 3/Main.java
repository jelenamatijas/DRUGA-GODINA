import java.util.*;
import java.util.stream.*;

public class Main {
	
	public static Random random = new Random();
	
	public static void main(String[] args) {
		ArrayList<Oglas> listaOglasa = new ArrayList<>();
		for (int i = 0; i < 12; i++) {
			listaOglasa.add(new Oglas(Oglas.Kategorija.IT));
		}
		
		for (int i = 0; i < 12; i++) {
			listaOglasa.add(new Oglas(Oglas.Kategorija.EKONOMIJA));
		}
		
		for (int i = 0; i < 12; i++) {
			listaOglasa.add(new Oglas(Oglas.Kategorija.MEDICINA));
		}
		
		for (int i = 0; i < 12; i++) {
			listaOglasa.add(new Oglas(Oglas.Kategorija.NOVINARSTVO));
		}
		
		for (int i = 0; i < 12; i++) {
			listaOglasa.add(new Oglas(Oglas.Kategorija.PRAVO));
		}
		
		System.out.println("ISPIS SVIH OGLASA\n======================");
		listaOglasa.forEach(System.out::println);
		System.out.println("======================");
		
		Map<String, List<Oglas>> oglasiPoDatumu = listaOglasa.stream().collect(Collectors.groupingBy(oglas -> oglas.datumObjavljivanja));
		System.out.println("OGLASI GRUPISANI PO DATUMIMA:");
		int ukupno = oglasiPoDatumu.values().stream().mapToInt(List::size).sum();
		oglasiPoDatumu.forEach((datum, oglasi) -> System.out.println(datum + ": " + oglasi.size()));
		System.out.println("Ukupno oglasa: " + ukupno);
		
		double prosjecnaPlataIT = listaOglasa.stream().filter(oglas -> oglas.kategorija == Oglas.Kategorija.IT).mapToInt(oglas -> oglas.plata).average().getAsDouble();
		System.out.println("Prosjecna plata IT: " + prosjecnaPlataIT);
		
		double prosjecnaPlataEKONOMIJA = listaOglasa.stream().filter(oglas -> oglas.kategorija == Oglas.Kategorija.EKONOMIJA).mapToInt(oglas -> oglas.plata).average().getAsDouble();
		System.out.println("Prosjecna plata EKONOMIJA: " + prosjecnaPlataEKONOMIJA);
		
		double prosjecnaPlataMEDICINA = listaOglasa.stream().filter(oglas -> oglas.kategorija == Oglas.Kategorija.MEDICINA).mapToInt(oglas -> oglas.plata).average().getAsDouble();
		System.out.println("Prosjecna plata MEDICINA: " + prosjecnaPlataMEDICINA);
		
		double prosjecnaPlataNOVINARSTVO = listaOglasa.stream().filter(oglas -> oglas.kategorija == Oglas.Kategorija.NOVINARSTVO).mapToInt(oglas -> oglas.plata).average().getAsDouble();
		System.out.println("Prosjecna plata NOVINARSTVO: " + prosjecnaPlataNOVINARSTVO);
		
		double prosjecnaPlataPRAVO = listaOglasa.stream().filter(oglas -> oglas.kategorija == Oglas.Kategorija.PRAVO).mapToInt(oglas -> oglas.plata).average().getAsDouble();
		System.out.println("Prosjecna plata PRAVO: " + prosjecnaPlataPRAVO);
		
		Map<String, List<Oglas>> oglasiPoGradu = listaOglasa.stream().collect(Collectors.groupingBy(oglas -> oglas.grad));
		String gradSaNajvisePoslova = oglasiPoGradu.entrySet().stream().max(Comparator.comparingInt(entry -> entry.getValue().size())).map(Map.Entry::getKey).orElse("Nema grada");
		
		System.out.println("Grad sa najvise oglasa: " + gradSaNajvisePoslova);
		
		Map<String, List<Oglas>> oglasiPoGodinama = listaOglasa.stream().collect(Collectors.groupingBy(oglas -> izdvojiGodinu(oglas.datumObjavljivanja)));
		oglasiPoGodinama.forEach((godina, oglasi) -> System.out.println("\nGODINA " + godina + ": \n" + oglasi));
		
		List<Oglas> oglasiSortirani = listaOglasa.stream().sorted(Comparator.comparingInt(oglas -> -oglas.vrijemeTrajanja)).collect(Collectors.toList());
		
		System.out.println("\nOglasi sortirani po vremenu trajanja u opadajucem redoslijedu:\n\n" + oglasiSortirani);
		
		String najboljePlacenPosaoIT = listaOglasa.stream().filter(oglas -> oglas.kategorija == Oglas.Kategorija.IT).max(Comparator.comparingInt(oglas -> oglas.plata)).get().opisPosla;
		
		System.out.println("\nNajbolje placen posao IT: " + najboljePlacenPosaoIT);
		
		String najboljePlacenPosaoEKONOMIJA = listaOglasa.stream().filter(oglas -> oglas.kategorija == Oglas.Kategorija.EKONOMIJA).max(Comparator.comparingInt(oglas -> oglas.plata)).get().opisPosla;
		
		System.out.println("\nNajbolje placen posao EKONOMIJA: " + najboljePlacenPosaoEKONOMIJA);
		
		String najboljePlacenPosaoMEDICINA = listaOglasa.stream().filter(oglas -> oglas.kategorija == Oglas.Kategorija.MEDICINA).max(Comparator.comparingInt(oglas -> oglas.plata)).get().opisPosla;
		
		System.out.println("\nNajbolje placen posao MEDICINA: " + najboljePlacenPosaoMEDICINA);
		
		String najboljePlacenPosaoNOVINARSTVO = listaOglasa.stream().filter(oglas -> oglas.kategorija == Oglas.Kategorija.NOVINARSTVO).max(Comparator.comparingInt(oglas -> oglas.plata)).get().opisPosla;
		
		System.out.println("\nNajbolje placen posao NOVINARSTVO: " + najboljePlacenPosaoNOVINARSTVO);
		
		String najboljePlacenPosaoPRAVO = listaOglasa.stream().filter(oglas -> oglas.kategorija == Oglas.Kategorija.PRAVO).max(Comparator.comparingInt(oglas -> oglas.plata)).get().opisPosla;
		
		System.out.println("\nNajbolje placen posao PRAVO: " + najboljePlacenPosaoPRAVO);
		
		double prosjecanBrojGodinaRadnogIskustva = listaOglasa.stream().mapToInt(oglas -> oglas.godineIskustva).average().getAsDouble();
		System.out.println("\nProsjecan broj godina radnog iskustva: " + prosjecanBrojGodinaRadnogIskustva);
		
		long brojRemotePoslovaIT = listaOglasa.stream().filter(oglas -> oglas.kategorija == Oglas.Kategorija.IT && oglas.remote).count();
		System.out.println("Procenat remote poslova IT: " + ((double) brojRemotePoslovaIT / 12) + "%");
		
		long brojRemotePoslovaEKONOMIJA = listaOglasa.stream().filter(oglas -> oglas.kategorija == Oglas.Kategorija.EKONOMIJA && oglas.remote).count();
		System.out.println("Procenat remote poslova EKONOMIJA: " + ((double) brojRemotePoslovaEKONOMIJA / 12) + "%");
		
		long brojRemotePoslovaMEDICINA = listaOglasa.stream().filter(oglas -> oglas.kategorija == Oglas.Kategorija.MEDICINA && oglas.remote).count();
		System.out.println("Procenat remote poslova MEDICINA: " + ((double) brojRemotePoslovaMEDICINA / 12) + "%");
		
		long brojRemotePoslovaNOVINARSTVO = listaOglasa.stream().filter(oglas -> oglas.kategorija == Oglas.Kategorija.NOVINARSTVO && oglas.remote).count();
		System.out.println("Procenat remote poslova NOVINARSTVO: " + ((double) brojRemotePoslovaNOVINARSTVO / 12) + "%");
		
		long brojRemotePoslovaPRAVO = listaOglasa.stream().filter(oglas -> oglas.kategorija == Oglas.Kategorija.PRAVO && oglas.remote).count();
		System.out.println("Procenat remote poslova PRAVO: " + ((double) brojRemotePoslovaPRAVO / 12) + "%");
	}
	
	private static String izdvojiGodinu(String datum) {
		return datum.substring(datum.indexOf(".20") + 1, datum.lastIndexOf('.'));
	}
}