package io.agrest.backend.exp.parser;

import io.agrest.backend.exp.Expression;

import java.io.IOException;

/**
 * @author vyarmolovich
 * 10/24/18
 */
public class ASTEqual extends Expression {

    /**
     * Creates "Equal To" expression.
     */
    public ASTEqual(Expression path, Object value) {

    }

    @Override
    public void setOperand(int index, Object value) {

    }

    @Override
    protected void flattenTree() {

    }

    @Override
    public Object evaluate(Object o) {
        return null;
    }

    @Override
    public void appendAsString(Appendable out) throws IOException {

    }
}
