public class Aquaman extends GoodCitizen implements RunFast, Swim{
    public Aquaman(){
        super();
        setName("Arthur Curry");
        setNickname("Aquaman");
        setSuperpower(true);
    }

    public Aquaman(String name, String nickname, Integer x, Integer y, Gadget gadget, boolean superpower){
        super(name, nickname, x, y, gadget, superpower);
    }

    public Aquaman(String name, Integer x, Integer y){
        super(name, x, y);
    }

    public Aquaman(String nickname, Gadget gadget, boolean superpower){
        super(nickname, gadget, superpower);
    }

    @Override
    public String runFast(){
        return "I am Aquaman and I run fast!";
    }

    @Override
    public String swim(){
        return "I am Aquaman and I swim!";
    }
}
