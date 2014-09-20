package org.soabridge.breeze.config.test;

import org.junit.Test;
import org.soabridge.breeze.config.FileConfiguration;

import java.io.FileInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DefaultConfiguratorTest {

/*    todo:
*           This testFile string will be converted to have a better implementation which will use a location that
*           will allow us to pull the full path for the test file from the resource directory that it is currently
*           being held in.
*
*           TESTING THIS CODE REQQUIRES YOU YOU CHANGE THE FILE PATH FOR THE testFile variable below
*
* */


    String testFile = "/Volumes/BigDisk/Development/SOABridge.org/breeze/src/test/java/org/soabridge/breeze/config/test/breeze.config";
//    String testFile = "breeze.config";




    @Test
    public void testGetPreProcessor() throws Exception {
        FileConfiguration fc = new FileConfiguration(testFile);
        Class[] reference = {Pre1.class, Pre2.class};
        Class[] results = fc.getPreProcessor();
        // Check that results are not NULL
        assertNotNull("The resulting Class array must not be null", results);
        // Check that reference Class array and results Class array have same length
        assertEquals("The reference and resulting array must be same length", reference.length, results.length);
        // Check that elements in reference Class array are the same as in results Class array
        for (int i=0; i<reference.length; i++)
            assertEquals("The reference and resulting array must have the same names", reference[i], results[i]);
        // Check that elements in reference Class array are the same class as in results Class array
        for (int i=0; i<reference.length; i++)
            assertEquals("The reference and resulting array must have the same names", reference[i].getClass(), results[i].getClass());
    }


    @Test
    public void testGetPostProcessor() throws Exception {
        FileConfiguration fc = new FileConfiguration(testFile);
        Class[] reference = {Post1.class, Post2.class};
        Class[] results = fc.getPostProcessor();
        // Check that results are not NULL
        assertNotNull("The resulting Class array must not be null", results);
        // Check that reference Class array and results Class array have same length
        assertEquals("The reference and resulting array must be same length", reference.length, results.length);
        // Check that elements in reference Class array are the same as in results Class array
        for (int i=0; i<reference.length; i++)
            assertEquals("", reference[i], results[i]);
        // Check that elements in reference Class array are the same class as in results Class array
        for (int i=0; i<reference.length; i++)
            assertEquals("The reference and resulting array must have the same names", reference[i].getClass(), results[i].getClass());
    }

    @Test
    public void testFileExists() throws Exception {
        //this.testFile = new File();
        FileInputStream fs = new FileInputStream(testFile);
        assertNotNull("The File is not supposed to be null", fs);
    }
}