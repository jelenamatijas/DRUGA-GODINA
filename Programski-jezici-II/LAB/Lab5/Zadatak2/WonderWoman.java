public class WonderWoman extends GoodCitizen implements RunFast, Fly{
    public WonderWoman(){
        super();
        setName("Princess Diana of Themyscira");
        setNickname("Wonder Woman");
        setSuperpower(true);
    }

    public WonderWoman(String name, String nickname, Integer x, Integer y, Gadget gadget, boolean superpower){
        super(name, nickname, x, y, gadget, superpower);
    }

    public WonderWoman(String name, Integer x, Integer y){
        super(name, x, y);
    }

    public WonderWoman(String nickname, Gadget gadget, boolean superpower){
        super(nickname, gadget, superpower);
    }

    @Override
    public String runFast(){
        return "I am Wonder Woman and I can run fast!";
    }

    @Override
    public String fly(){
        return "I am Wonder Woman and I can fly!";
    }
}
