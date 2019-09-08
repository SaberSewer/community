package online.cangjie.comumit.utils;

import org.apache.commons.io.FilenameUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class FileUtils {
    public static String saveWithLocal(byte bytes[], String suffix, String path){
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

    public static String saveWithCloud(byte[] bytes, String fileName, Long size){
        String suffix = FilenameUtils.getExtension(fileName);
        try {
            ClientGlobal.initByProperties("fast.properties");
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageClient1 storageClient1 = new StorageClient1(trackerServer, null);
            NameValuePair[] nameValuePairs = new NameValuePair[3];
            nameValuePairs[0] = new NameValuePair("fileName", fileName);
            nameValuePairs[1] = new NameValuePair("fileExt", suffix);
            nameValuePairs[2] = new NameValuePair("fileSize", String.valueOf(size));
            return storageClient1.upload_file1(bytes, suffix, nameValuePairs);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getFileName(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        saveWithCloud(null,null,null);
    }
}
