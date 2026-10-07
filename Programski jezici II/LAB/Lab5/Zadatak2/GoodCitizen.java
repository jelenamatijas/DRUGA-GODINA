public class GoodCitizen extends Citizen{
    private String nickname;
    private Gadget gadget;
    private boolean superpower;

    GoodCitizen(){
        super();
    }

    GoodCitizen(String name, String nickname, Integer x, Integer y, Gadget gadget, boolean superpower){
        super(name, x, y);
        this.nickname = nickname;
        this.gadget = gadget;
        this.superpower = superpower;
    }

    GoodCitizen(String name, Integer x, Integer y){
        super(name, x, y);
    }

    GoodCitizen(String nickname, Gadget gadget, boolean superpower){
        super();
        this.nickname = nickname;
        this.gadget = gadget;
        this.superpower = superpower;
    }

    public String getNickname(){
        return nickname;
    }

    public Gadget getGadget(){
        return gadget;
    }

    public boolean getSuperpower(){
        return superpower;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public void setGadget(Gadget gadget){
        this.gadget = gadget;
    }

    public void setSuperpower(boolean superpower){
        this.superpower = superpower;
    }
}
