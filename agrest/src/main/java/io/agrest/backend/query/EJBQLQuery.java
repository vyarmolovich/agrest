package io.agrest.backend.query;

/**
 * @author vyarmolovich
 * 10/24/18
 */
public class EJBQLQuery implements Query {

    private String ejbqlStatement;

    public EJBQLQuery(String ejbqlStatement) {
        this.ejbqlStatement = ejbqlStatement;
    }
}
