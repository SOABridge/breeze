package org.soabridge.breeze.registry.test;

import org.soabridge.breeze.comm.Endpoint;
import org.soabridge.breeze.registry.Token;

import java.util.Date;
import java.util.UUID;

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
public class ClonedEndpointToken implements Token<Endpoint> {

    private UUID id;
    private Date issueDate;
    private Class impl;
    private Class type;

    public ClonedEndpointToken(Token<Endpoint> endpoint) {
        this.id = endpoint.getID();
        this.issueDate = endpoint.getIssueDate();
        this.impl = endpoint.getImplementation();
        this.type = endpoint.getType();
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
    public Class getType() {
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
