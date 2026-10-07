import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;

public class SerializationUtil {
    public static <T> T readObject(String fullPath){
        ObjectInputStream obj = null;
        T object = null;
        try{
            obj = new ObjectInputStream(new FileInputStream(new File(fullPath)));
            object = (T)obj.readObject();
            obj.close();
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }finally{
            try{
                obj.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        
        return object;
    }

    public static <T> boolean save(T object, String path, String filename){
        ObjectOutputStream obj = null;
        boolean result = false;
        try{
            obj = new ObjectOutputStream(new FileOutputStream(new File(path + File.separator + filename)));
            obj.writeObject(object);
            result=true;
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                obj.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        return result;
    }
}
