package org.soabridge.breeze.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.util.Properties;


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


    public String preProcessors  = null;
    public String postProcessors = null;
    public File propertiesFile = null;
    public String hiveName ;
    public String versionNumber ;


    public FileConfiguration(String configName) {
         this(new File(configName));



    }

    public FileConfiguration(File config) {
         this.propertiesFile = config;

    }

         /*
       * reload() method will reload the configuration properties from the
       * properties file.  The file must be located in the application directory
       * and the classes for pre/post-processors must also be in the same path  */

    public void reload() throws IOException, FileSystemNotFoundException {

        Properties properties = new Properties();
        properties.load(new FileInputStream(propertiesFile));
        String versionNumber = properties.getProperty("VersionNumber");
        this.preProcessors = properties.getProperty("Pre-Processors");
        this.postProcessors = properties.getProperty("Post-Processors");
        String hiveName = properties.getProperty("HiveName");
        this.versionNumber = properties.getProperty("VersionNumber");
        this.hiveName = properties.getProperty("HiveName");
    }


     /* The getPreProcessor() method will return the Pre-Processor class names
      * which have been pulled from the properties file and will be separated
      * into an array for holding the names of the classes which will then be
      * returned to the caller as an array of Classes. */

     @Override
    public Class[] getPreProcessor() {
        //assign a temp string to the retrieved string of properties from the properties file


         if (this.preProcessors == null) {
             try {
                 this.reload();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
         String temp = this.preProcessors;

        //separate the strings separated by the comma delimiter
        String[] preProcessClasses = temp.split(",");

        /* set up the Class array to the length of the number of classes that are
        defined in the properties file */
        Class[] processors = new Class[preProcessClasses.length];

        /*create the class objects to return in the processors array.
         the trim method will remove spaces in the front and back of
         the processor classes in the properties file.*/
        try {
            for (int i = 0; i < preProcessClasses.length; i++)
                processors[i] = Class.forName(preProcessClasses[i].trim());
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return  processors;

    }

    @Override
    public Class[] getPostProcessor() {

        if (this.preProcessors == null) {
            try {
                this.reload();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //assign a temp string to the retrieved string of properties from the properties file
        String temp = this.postProcessors;

        //separate the strings separated by the comma delimiter
        String[] postProcessClasses = temp.split(",");

       /* set up the Class array to the length of the number of classes that are
        defined in the properties file */
        Class[] processors = new Class[postProcessClasses.length];


         /*create the class objects to return in the processors array.
         the trim method will remove spaces in the front and back of
         the processor classes in the properties file.*/

        try {
            for (int i = 0; i < postProcessClasses.length; i++)
                processors[i] = Class.forName(postProcessClasses[i].trim());
        } catch (ClassNotFoundException e) {
                System.out.println(e);
            }
        return  processors;

    }


    //This is a place holder for implementation later
    @Override
    public Class[] getService() {

        System.out.println("in getService()");

        return new Class[0];
    }


    //This is a place holder for implementation later
    @Override
    public Class[] getConnectors() {
        System.out.println("in getConnectors()");

        return new Class[0];
    }


     public String getHiveName(){
         return this.hiveName;
     }

    public String getVersionNumber(){
        return this.versionNumber;
    }

}
