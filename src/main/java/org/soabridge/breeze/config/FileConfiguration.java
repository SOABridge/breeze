package org.soabridge.breeze.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.util.Properties;


/*
 * FileConfiguration will use a properties file to provide:
 *      - PreProcessor Classes
 *      - PostProcessor Classes
 *      - Connectors Classes - TBD Later...
 *
 *      PreProcessors will be added into an array of Classes for the Hive
 *      PostProcessors will be added into an array of Classes for the Hive
 *
 * @author <a href="geoff.dalelio@soabridge.com" >Geoff d'Alelio</a>
 * @since 1.0 
 *
 */


public class FileConfiguration implements Configuration {

    //Configuration Constants
    public static final String PROP_PRE_PROCESSORS = "Pre-Processors";
    public static final String PROP_POST_PROCESSORS = "Post-Processors";

    /*  These will be used later for Connectors and Services - if pulled from Properties File
    public static final String PROP_CONNECTORS = "Connectors";
    public static final String PROP_SERVICES = "Services";
    */
    public static  final String PROP_HIVE_NAME  = "HiveName";
    public static  final String PROP_VERSION_NUMBER = "VersionNumber" ;


    protected String preProcessors  = null;
    protected String postProcessors = null;
    protected String hiveName = null;
    protected String versionNumber = null;
    protected File propertiesFile = null;
    private Properties properties = new Properties();



    public FileConfiguration(String configName) {
         this(new File(configName));



    }

    public FileConfiguration(File config) {
         this.propertiesFile = config;
        try {
            reload();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
         /*
       * reload() method will reload the configuration properties from the
       * properties file.  The file must be located in the application directory
       * and the classes for pre/post-processors must also be in the same path  */

    public void reload() throws IOException, FileSystemNotFoundException {

        properties.load(new FileInputStream(propertiesFile));
        this.preProcessors  = properties.getProperty(PROP_PRE_PROCESSORS);
        this.postProcessors = properties.getProperty(PROP_POST_PROCESSORS);
        this.versionNumber  = properties.getProperty(PROP_VERSION_NUMBER);
        this.hiveName       = properties.getProperty(PROP_HIVE_NAME);
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
         return PROP_HIVE_NAME;
     }

    public String getVersionNumber(){
        return PROP_VERSION_NUMBER;
    }

}
