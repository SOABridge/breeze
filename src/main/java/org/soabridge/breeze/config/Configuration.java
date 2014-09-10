package org.soabridge.breeze.config;

/**
 * Created by Geoff d'Alelio  on 8/13/14.
 * ${Author}
 */
public interface Configuration {


    public Class[] getPreProcessor();
    public Class[] getPostProcessor();
    public Class[] getService();
    public Class[] getConnectors();
}
