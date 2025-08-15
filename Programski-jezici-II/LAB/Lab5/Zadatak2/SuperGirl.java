public class SuperGirl extends GoodCitizen implements Fly, RunFast{
    public SuperGirl(){
        super();
        setName("Kara-Zor-El");
        setNickname("Super Girl");
        setSuperpower(true);
    }

    public SuperGirl(String name, String nickname, Integer x, Integer y, Gadget gadget, boolean superpower){
        super(name, nickname, x, y, gadget, superpower);
    }

    public SuperGirl(String name, Integer x, Integer y){
        super(name, x, y);
    }

    public SuperGirl(String nickname, Gadget gadget, boolean superpower){
        super(nickname, gadget, superpower);
    }

    @Override
    public String runFast(){
        return "I am Super Girl and I can run fast!";
    }

    @Override
    public String fly(){
        return "I am Super Girl and I can fly!";
    }
}
