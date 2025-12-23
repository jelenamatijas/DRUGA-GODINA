package net.etfbl.pj2.lab11z1;

import java.io.Serializable;

/**
 *
 * @author Igor
 */
public class SerializableClass1 implements Serializable{
    private String value;
    public int id;

    public SerializableClass1() {
    }

    public SerializableClass1(String value, int id) {
        this.value = value;
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SerializableClass1{" + "value=" + value + ", id=" + id + '}';
    }
    
    
}
