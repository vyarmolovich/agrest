package io.agrest.backend.exp.parser;

import io.agrest.backend.exp.Expression;

/**
 * @author vyarmolovich
 * 10/24/18
 */
public abstract class ASTPath extends Expression {

    protected String path;

    public String getPath() {
        return path;
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

    public Object getOperand(int index) {
        if (index == 0) {
            return path;
        }

        throw new ArrayIndexOutOfBoundsException(index);
    }
}
