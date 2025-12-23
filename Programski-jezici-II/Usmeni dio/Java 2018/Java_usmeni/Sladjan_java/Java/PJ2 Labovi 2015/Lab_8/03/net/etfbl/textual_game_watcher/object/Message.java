package net.etfbl.textual_game_watcher.object;

public class Message {

	// polja
	private String imeIgraca;
	private String imeTima;
	public long trenutakVremena;
	public int dobioPoena;
	public static int rezultat;

	public Message() {
		super();
	}

	public Message(String imeIgraca, String imeTima) {
		super();
		this.imeIgraca = imeIgraca;
		this.imeTima = imeTima;
	}

	public String toString() {
		return "Message [imeIgraca=" + imeIgraca + ", imeTima=" + imeTima
				+ ", trenutakVremena=" + trenutakVremena + ", dobioPoena="
				+ dobioPoena + ", rezultat=" + rezultat + "]";
	}
}
