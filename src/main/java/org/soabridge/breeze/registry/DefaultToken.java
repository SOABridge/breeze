package org.soabridge.breeze.registry;

import java.util.Date;
import java.util.UUID;

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class DefaultToken<T> implements Token<T> {

    private final UUID id;
    private final Date issueDate;
    private final Class<T> type;

    DefaultToken(Class<T> type) {
        id = UUID.randomUUID();
        issueDate = new Date();
        this.type = type;
    }

    @Override
    public UUID getID() {
        return id;
    }

    @Override
    public Date getIssueDate() {
        return issueDate;
    }

    @Override
    public Class<T> getType() {
        return type;
    }
}
