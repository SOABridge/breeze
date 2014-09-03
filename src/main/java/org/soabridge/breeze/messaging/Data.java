package org.soabridge.breeze.messaging;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Message properties
 * @author <a href="divyesh.vallabh@soabridge.com">Divyesh Vallabh</a>
 * @since 1.0
 *
 */
public class Data implements Serializable {


    protected Map<String,Object> properties;

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
    public Map<String,Object> getProperties() {
        return properties;
    }

    /**
     * Gets a property value
     * @param key : Name of the key used to store the property
     * @return Object : Value of property returned
     */
    public <T> T getProperty(String key) {
        return (properties.containsKey(key) ? (T)properties.get(key) : null);
    }

    /**
     * Removes property value
     * @param key : Name of the key used to store the property
     */
    public void removeProperty(String key) { if (properties.containsKey(key)) properties.remove(key); }

    /**
     * Sets the property value
     * @param key : Name of the key used to store the property
     * @param value : Value of property to be stored
     */
    public void setProperty(String key, Object value) {
        properties.put(key, value);
    }



}
