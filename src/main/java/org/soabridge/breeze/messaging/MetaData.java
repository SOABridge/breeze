package org.soabridge.breeze.messaging;


/**
 * Message body properties
 * @author <a href="divyesh.vallabh@soabridge.com">Divyesh Vallabh</a>
 * @since 1.0
 *
 */
public class MetaData extends  Data {

    public MetaData(MetaData metaData)
    {
        super();
        if (metaData != null)
            this.properties.putAll(metaData.getProperties());
    }

}