import java.io.Serializable;

public class SerializableClass1 implements Serializable{
    private String value;
    
    SerializableClass1(){}
    SerializableClass1(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return "SerializableClass1 value = " + value;
    }
}
