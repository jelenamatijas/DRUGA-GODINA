package net.etfbl.exception2;


public class ExceptionTest8 {

	public static void main(String[] args) {
		Computer computer = new Computer();
		String usbVersion = computer.getSoundCard().getUsb().getVersion();
		
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
	private SoundCard soundCard;

	public SoundCard getSoundCard() {
		return soundCard;
	}

	public void setSoundCard(SoundCard soundCard) {
		this.soundCard = soundCard;
	}
}

class SoundCard{
	private USB usb;

	public USB getUsb() {
		return usb;
	}

	public void setUsb(USB usb) {
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