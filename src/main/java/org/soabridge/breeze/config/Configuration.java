package org.soabridge.breeze.config;

/*
 *
 * Configuration is an interface for reading pre-processors, post-processors, and other
 * information required to initialize the hive processes. The methods defined are as follows:
 *      - getPreProcessor  - returns a Class [] of preProcessors
 *      - getPostProcessor - returns a Class [] of postProcessors
 *      - getService    - services perform actions on metadata or message headers, etc.
 *      - getConnectors - interfaces to systems in/out
 *
 * @author <a href="geoff.dalelio@soabridge.com" >Geoff d'Alelio</a>
 * @since 1.0
 *
*/



public interface Configuration {


    public Class[] getPreProcessor();
    public Class[] getPostProcessor();
    public Class[] getService();
    public Class[] getConnectors();
}
