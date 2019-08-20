package online.cangjie.comumit.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class FileUtils {
    public static String save(byte bytes[], String suffix, String path){
        String name = getFileName() + "." + suffix;
        try {
            FileOutputStream fos = new FileOutputStream(path + name);
            fos.write(bytes);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path + name;
    }

    private static String getFileName(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
