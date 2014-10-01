package org.soabridge.breeze.config.test;

import org.junit.Test;
import org.soabridge.breeze.config.FileConfiguration;

import java.io.FileInputStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DefaultConfiguratorTest {

/*    todo:
*           This testFile string will be converted to have a better implementation which will use a location that
*           will allow us to pull the full path for the test file from the resource directory that it is currently
*           being held in.
*
*           TESTING THIS CODE REQUIRES YOU TO PLACE THE BREEZE.CONFIG FILE IN THE SAME FOLDER AS YOUR pom.xml file
*
* */



    String testFile = "breeze.config";


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
        assertArrayEquals(reference, results);
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
        assertArrayEquals(reference, results);
    }

    @Test
    public void testFileExists() throws Exception {
        //this.testFile = new File();
        FileInputStream fs = new FileInputStream(testFile);
        assertNotNull("The File is not supposed to be null", fs);
    }

    @Test
    public void testgetVersionNumber() throws Exception {
        FileConfiguration fc = new FileConfiguration(testFile);
        fc.reload();
        String versionNumber = fc.getVersionNumber();
        //test that the version number is not null
        assertNotNull("The version number can not be null.", versionNumber);

    }

    @Test
    public void testgetHiveName() throws Exception {
        FileConfiguration fc = new FileConfiguration(testFile);
        fc.reload();
        String hiveName = fc.getHiveName();
        //test that the hiveName is not null
        assertNotNull("The hive name  can not be null.", hiveName );

    }
}