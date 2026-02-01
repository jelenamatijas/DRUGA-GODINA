package net.etfbl.pj2.lab11z1;

/**
 *
 * @author Igor
 */
public class Test {
    public static void main(String [] args){
        SerializableClass1 s1 = new SerializableClass1("test", 1);
        SerializableClass2 s2 = new SerializableClass2();
        s2.setValue("Test 2");
        s2.active = true;
        s2.id = 2;
        
        SerializationUtil.save(s1, "C:\\java", "s1.ser");
        SerializationUtil.save(s2, "C:\\java", "s2.ser");
        
        SerializableClass1 s3 = SerializationUtil.readObject("C:\\java\\s1.ser");
        SerializableClass2 s4 = SerializationUtil.readObject("C:\\java\\s2.ser");
        
        System.out.println(s3);
        System.out.println(s4);
    }
}
