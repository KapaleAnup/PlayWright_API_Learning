package com.api.utilities;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {

    public static String readConfigProperties( String key){
        File file = new File ( "src/main/resources/Config/config.properties" );
        FileReader reader = null;
        try {
              reader = new FileReader ( file );
        } catch (FileNotFoundException exception) {
            exception.printStackTrace ( );
        }
        Properties properties = new Properties (  );
        try {
            properties.load ( reader );
        } catch (IOException exception) {
            exception.printStackTrace ( );
        }
        return properties.getProperty ( key );

    }


}
