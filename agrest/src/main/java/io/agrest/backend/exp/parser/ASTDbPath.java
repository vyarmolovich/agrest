package io.agrest.backend.exp.parser;

import io.agrest.backend.exp.Expression;

import java.io.IOException;

/**
 * @author vyarmolovich
 * 10/24/18
 */
public class ASTDbPath extends ASTPath {

    public static final String DB_PREFIX = "db:";

    private String value;

    public ASTDbPath(String value) {
        this.value = value;
    }

    @Override
    public void appendAsString(Appendable out) throws IOException {
        out.append(DB_PREFIX).append(path);
    }
}
