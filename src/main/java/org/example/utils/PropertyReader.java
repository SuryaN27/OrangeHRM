package org.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    public static String readkey(String key) {
        Properties p;
        try {
            String user_dir = System.getProperty("user.dir");
            String path = user_dir + "/src/main/resources/data.properties";
            FileInputStream fileInputStream = new FileInputStream(path);
            p = new Properties();
            p.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return p.getProperty(key);

    }
}
// public static String readKey(String key) {
//        Properties p;
//
//        try {
//            String user_dir = System.getProperty("user.dir");
//            // /Users/promode/Documents/TTA/ATB14xSeleniumAdvanceFramework/src/main/resources/data.properties
//            String file_path = user_dir + "/src/main/resources/data.properties";
//            FileInputStream fileInputStream = new FileInputStream(file_path);
//            p = new Properties();
//            p.load(fileInputStream);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        return p.getProperty(key);
//    }
