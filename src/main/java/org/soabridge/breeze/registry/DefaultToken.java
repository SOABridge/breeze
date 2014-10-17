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
    private final Class impl;
    private final Class<T> type;

    DefaultToken(Class impl, Class<T> type) {
        id = UUID.randomUUID();
        issueDate = new Date();
        this.impl = impl;
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
    public Class getImplementation() {
        return impl;
    }

    @Override
    public Class<T> getType() {
        return type;
    }

    @Override
    public int hashCode() {
        int hash = 19;
        hash = 11 * hash + id.hashCode();
        hash = 11 * hash + issueDate.hashCode();
        hash = 11 * hash + impl.hashCode();
        hash = 11 * hash + type.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || !Token.class.isAssignableFrom(obj.getClass()))
            return false;
        // Specific equals
        Token token = (Token) obj;

        return id.equals(token.getID()) &&
                issueDate.equals(token.getIssueDate()) &&
                (impl == null? token.getImplementation() == null : impl == token.getImplementation()) &&
                (type == null? token.getType() == null : type == token.getType());
    }
}
