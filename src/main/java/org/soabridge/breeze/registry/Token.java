package org.soabridge.breeze.registry;

import java.util.Date;
import java.util.UUID;

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
public interface Token<T> {

    UUID getID();

    Date getIssueDate();

    Class getImplementation();

    Class<T> getType();

}
