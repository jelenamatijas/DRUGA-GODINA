package net.etfbl.exception3;

import java.util.Optional;

public class ExceptionTest9 {

	public static void main(String[] args) {
		Optional<Computer> computer = Optional.empty();
		String usbVersion = computer.flatMap(Computer::getSoundCard)
				.flatMap(SoundCard::getUsb)
				.map(USB::getVersion)
				.orElse("UNKNOWN");
	
		Computer comp = computer.orElse(new Computer());
		System.out.println(comp);
		
//		String usbVersion = "UNKNOWN";
//		if(computer != null){
//		  SoundCard soundcard = computer.getSoundCard();
//		  if(soundcard != null){
//		    USB usb = soundcard.getUsb();
//		    if(usb != null){
//		    	usbVersion = usb.getVersion();
//		    }
//		  }
//		}	

		
		System.out.println(usbVersion);
	}

}

class Computer{
	private Optional<SoundCard> soundCard;

	public Optional<SoundCard> getSoundCard() {
		return soundCard;
	}

	public void setSoundCard(Optional<SoundCard> soundCard) {
		this.soundCard = soundCard;
	}
}

class SoundCard{
	private Optional<USB> usb;

	public Optional<USB> getUsb() {
		return usb;
	}

	public void setUsb(Optional<USB> usb) {
		this.usb = usb;
	}
}

class USB{
	private String version;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}