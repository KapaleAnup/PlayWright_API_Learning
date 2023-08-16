package com.api.utilities;


import java.io.*;
import java.util.Properties;

public class ConfigProperties {
    static Properties properties;
    static FileInputStream inputStream;

    public static String readConfigProperties( String key){
        File file = new File ( "src/main/resources/Config/config.properties" );
         inputStream = null;
        try {
             inputStream = new FileInputStream ( file );
        } catch (FileNotFoundException exception) {
            exception.printStackTrace ( );
        }
        properties = new Properties (  );
        try {
            properties.load ( inputStream );
        } catch (IOException exception) {
            exception.printStackTrace ( );
        }
        return properties.getProperty ( key );

    }

}
