public class Batman extends GoodCitizen implements Fly{
    public Batman(){
        super();
        setName("Bruce Wayne");
        setNickname("Batman");
        setSuperpower(false);
        setGadget(new Gadget("Batmobile"));
    }

    public Batman(String name, String nickname, Integer x, Integer y, Gadget gadget, boolean superpower){
        super(name, nickname, x, y, gadget, superpower);
    }

    public Batman(String name, Integer x, Integer y){
        super(name, x, y);
    }

    public Batman(String nickname, Gadget gadget, boolean superpower){
        super(nickname, gadget, superpower);
    }

    @Override
    public String fly(){
        return "I am Batman and I fly!";
    }
}
