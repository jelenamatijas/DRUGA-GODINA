import java.io.Serializable;

public class SerializableClass2 implements Serializable{
    private Integer value;
    
    SerializableClass2(){}
    SerializableClass2(Integer value){
        this.value = value;
    }

    public Integer getValue(){
        return value;
    }

    public void setValue(Integer value){
        this.value = value;
    }

    @Override
    public String toString(){
        return "SerializableClass2 value = " + value;
    }
}
