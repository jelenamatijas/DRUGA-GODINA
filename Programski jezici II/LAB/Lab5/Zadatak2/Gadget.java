public class Gadget {
    String gadget;

    Gadget(){
        super();
    }

    Gadget(String gadget){
        this.gadget = gadget;
    }

    public void setGadget(String g){
        gadget = g;
    }

    public String getGadget(){
        return gadget;
    }
}
