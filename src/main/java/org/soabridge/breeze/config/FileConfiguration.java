package org.soabridge.breeze.config;

import java.io.*;
import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;
import java.util.Properties;

import static java.lang.Class.*;


/*
 * @author <a href="geoff.dalelio@soabridge.com" >Geoff d'Alelio</a> 
 * @since 1.0 
 *
 * FileConfiguration will use a properties file to provide:
 *      - PreProcessor Classes
 *      - PostProcessor Classes
 *      - Connectors Classes - TBD Later...
 *
 *      PreProcessors will be added into an array of Classes for the Hive
 *      PostProcessors will be added into an array of Classes for the Hive
 */


public class FileConfiguration implements Configuration {


    public String preProcessors;
    public String postProcessors;




    public void reload() throws IOException, FileSystemNotFoundException {
       /*
       * reload() method will reload the configuration properties from the
       * properties file.  The file must be located in the application directory
       * and the classes for pre/post-processors must also be in the same path
       *
       */

        Properties properties = new Properties();
        File file = new File("/home/geoff/Development/SOABridge.org/breeze/src/main/java/org/soabridge/breeze/config/breeze.properties");
        properties.load(new FileInputStream(file));
        String versionNumber = properties.getProperty("VersionNumber");
        this.preProcessors = properties.getProperty("Pre-Processors");
        String postProcessors = properties.getProperty("Post-Processors");
        String hiveName = properties.getProperty("HiveName");


        //assign the Pre-Processor string to the class variable that is holding them
        this.preProcessors = preProcessors;
        this.postProcessors = postProcessors;


    }

    @Override
    public Class[] getPreProcessor() {
//db-statements begin  -- remove before flight!
//        System.out.println("in getPreProcessor()");
//        System.out.println("PreProcessor String: "+ this.preProcessors);
//db-statements end  -- remove before flight!

        String temp = this.preProcessors;
        String[] preProcessClasses = temp.split(",");
//        for (String strings : preProcessClasses) System.out.println("Parts:"+strings.trim()+":");

        return new Class[0];
    }

    @Override
    public Class[] getPostProcessor() {
//db-statements begin  -- remove before flight!
//        System.out.println("in getPostProcessor()");
//        System.out.println("PostProcessor String: "+ this.preProcessors);
//db-statements end  -- remove before flight!

        String temp = this.postProcessors;
        String[] postProcessClasses = temp.split(",");
        ArrayList al = new ArrayList();


        for (int i = 0; i < postProcessClasses.length; i++)

            try {
                al.add(Class.forName(postProcessClasses[i].trim()));
            } catch (ClassNotFoundException e) {
                System.out.println("Class was not found: " + postProcessClasses[i]);
            }

        return new Class[0];

    }

    @Override
    public Class[] getService() {
//db-statements -- remove before flight!
        System.out.println("in getService()");

        return new Class[0];
    }

    @Override
    public Class[] getConnectors() {
//db-statements -- remove before flight!
        System.out.println("in getConnectors()");

        return new Class[0];
    }


}
