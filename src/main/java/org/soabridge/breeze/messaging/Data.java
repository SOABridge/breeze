package org.soabridge.breeze.messaging;

import java.util.HashMap;
import java.util.Map;

/**
 * Message properties
 * @author <a href="divyesh.vallabh@soabridge.com">Divyesh Vallabh</a>
 * @since 1.0
 *
 */
public class Data {


    private Map<String,Object> properties;

    /**
     * Default constructor initializing properties of the header
     */
    public Data()
    {
        properties = new HashMap<String,Object>();
    }

    /**
     * Gets the list of properties
     * @return Map : List of properties
     */
    public Map getProperties() {
        return properties;
    }

    /**
     * Sets the list of properties
     * @param properties : List of properties
     */
    public void setProperties(Map<String,Object> properties) {
        this.properties = properties;
    }

    /**
     * Gets a property value
     * @param key : Name of the key used to store the property
     * @return Object : Value of property returned
     */
    public Object getProperty(String key) {
        return (properties.containsKey(key) ? properties.get(key) : null);
    }

    /**
     * Sets the property value
     * @param key : Name of the key used to store the property
     * @param value : Value of property to be stored
     */
    public void setProperty(String key, Object value) {
        properties.put(key, value);
    }



}
