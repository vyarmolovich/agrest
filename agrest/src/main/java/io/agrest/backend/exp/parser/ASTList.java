/* Generated By:JJTree: Do not edit this line. ASTList.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package io.agrest.backend.exp.parser;

import io.agrest.backend.exp.Expression;

import java.util.Collection;

public class ASTList extends SimpleNode {

    protected Object[] values;

    public ASTList(int id) {
        super(id);
    }

    public ASTList(ExpressionParser p, int id) {
        super(p, id);
    }

    /**
     * Initializes a list expression with an Object[].
     */
    public ASTList(Object[] objects) {
        super(ExpressionParserTreeConstants.JJTLIST);

        if (objects != null) {
            int size = objects.length;
            this.values = new Object[size];
            System.arraycopy(objects, 0, this.values, 0, size);
        }
    }

    /**
     * Initializes a list expression with a Java Collection
     */
    public ASTList(Collection<?> objects) {
        super(ExpressionParserTreeConstants.JJTLIST);
        if (objects != null) {
            Collection<?> c = objects;
            this.values = c.toArray(new Object[c.size()]);
        }
    }


    @Override
    public Expression shallowCopy() {
        return new ASTList(id);
    }
}
/* JavaCC - OriginalChecksum=7b50fb9c5962712ce9982bf1519b48e0 (do not edit this line) */
