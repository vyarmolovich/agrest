package io.agrest.backend.exp.parser;

import io.agrest.backend.exp.Expression;

import java.io.IOException;

/**
 * @author vyarmolovich
 * 10/24/18
 */
public class ASTObjPath extends ASTPath {

    private String value;

    public ASTObjPath(String value) {
        this.value = value;
    }

    @Override
    public void appendAsString(Appendable out) throws IOException {
        out.append(path);
    }
}
