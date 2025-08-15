import java.io.File;

public class Test {
    public static void main(String[] args) {
        SerializableClass1 class1 = new SerializableClass1("JAVA");
        SerializableClass2 class2 = new SerializableClass2();
        class2.setValue(6);

        String path = "C:\\Users\\JM\\Desktop\\DRUGA-GODINA\\Programski-jezici-II\\LAB\\Lab9\\03";
        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }

        SerializationUtil.save(class1, path, "class1.ser");
        SerializationUtil.save(class2, path, "class2.ser");

        SerializableClass1 cl1 = SerializationUtil.readObject(path + File.separator + "class1.ser");
        SerializableClass2 cl2 = SerializationUtil.readObject(path + File.separator + "class2.ser");

        System.out.println(cl1);
        System.out.println(cl2);
    }
}
