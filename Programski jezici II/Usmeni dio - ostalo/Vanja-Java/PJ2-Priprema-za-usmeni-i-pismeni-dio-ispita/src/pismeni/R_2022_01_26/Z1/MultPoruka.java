public class MultPoruka extends Poruka{
	
	String audioVideo;
	
	public MultPoruka(String audioVideo){
		super();
		this.audioVideo = audioVideo;
	}
	
	@Override
	public String toString(){
		return super.toString() + " " + audioVideo;
	}
}