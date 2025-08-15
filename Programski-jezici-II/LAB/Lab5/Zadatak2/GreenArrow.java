public class GreenArrow extends GoodCitizen{
    public GreenArrow(){
        super();
        setName("Oliver Queen");
        setNickname("Green Arrow");
        setSuperpower(false);
        setGadget(new Gadget("Arrows"));
    }

    public GreenArrow(String name, String nickname, Integer x, Integer y, Gadget gadget, boolean superpower){
        super(name, nickname, x, y, gadget, superpower);
    }

    public GreenArrow(String name, Integer x, Integer y){
        super(name, x, y);
    }

    public GreenArrow(String nickname, Gadget gadget, boolean superpower){
        super(nickname, gadget, superpower);
    }
    
}
