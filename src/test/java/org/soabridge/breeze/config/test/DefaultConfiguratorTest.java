package org.soabridge.breeze.config.test;

import org.junit.Test;
import org.soabridge.breeze.config.FileConfiguration;
import org.soabridge.breeze.config.Pre1;
import org.soabridge.breeze.config.Pre2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DefaultConfiguratorTest {

    @Test
    public void testGetPreProcessor() throws Exception {
        FileConfiguration fc = new FileConfiguration();
        Class[] reference = {Pre1.class, Pre2.class};
        Class[] results = fc.getPreProcessor();
        // Check that results are not NULL
        assertNotNull("The resulting Class array must not be null", results);
        // Check that reference Class array and results Class array have same length
        assertEquals("The reference and resulting array must be same length", reference.length, results.length);
        // Check that elements in reference Class array are the same as in results Class array
        for (int i=0; i<reference.length; i++)
            assertEquals("", reference[i], results[i]);
    }

    public void testGetPostProcessor() throws Exception {

    }
}