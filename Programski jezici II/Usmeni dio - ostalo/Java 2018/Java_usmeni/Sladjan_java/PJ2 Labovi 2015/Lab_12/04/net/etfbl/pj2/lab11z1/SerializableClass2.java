package net.etfbl.pj2.lab11z1;

import java.io.Serializable;

/**
 *
 * @author Igor
 */
public class SerializableClass2 implements Serializable{
    private String value;
    public boolean active;
    public int id;

    public SerializableClass2() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SerializableClass2{" + "value=" + value + ", active=" + active + ", id=" + id + '}';
    }
    
}
