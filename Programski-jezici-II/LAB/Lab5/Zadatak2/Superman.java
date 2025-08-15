public class Superman extends GoodCitizen implements Fly, RunFast{
    public Superman(){
        super();
        setName("Clark Kent");
        setNickname("Superman");
        setSuperpower(true);
    }

    public Superman(String name, String nickname, Integer x, Integer y, Gadget gadget, boolean superpower){
        super(name, nickname, x, y, gadget, superpower);
    }

    public Superman(String name, Integer x, Integer y){
        super(name, x, y);
    }

    public Superman(String nickname, Gadget gadget, boolean superpower){
        super(nickname, gadget, superpower);
    }

    @Override
    public String runFast(){
        return "I am Superman and I can run fast!";
    }

    @Override
    public String fly(){
        return "I am Superman and I can fly!";
    }
}
