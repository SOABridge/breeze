package org.soabridge.breeze.config;

/*
 * @author <a href="geoff.dalelio@soabridge.com">Geoff d'Alelio</a>
 * @since 1.0
 *
 */

public abstract class DefaultConfigurator implements Configuration {
   @Override
   public Class[] getPreProcessor() {
     return new Class[0];
    }

    @Override
    public Class[] getPostProcessor() {
        return new Class[0];
    }

    @Override
    public Class[] getService() {
        return new Class[0];
    }

    @Override
    public Class[] getConnectors(){
        return new Class [0];
    }

}
