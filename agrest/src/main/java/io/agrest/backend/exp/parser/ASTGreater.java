/* Generated By:JJTree: Do not edit this line. ASTGreater.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package io.agrest.backend.exp.parser;

import io.agrest.backend.exp.Expression;

public class ASTGreater extends SimpleNode {
    public ASTGreater(int id) {
        super(id);
    }

    public ASTGreater(ExpressionParser p, int id) {
        super(p, id);
    }

    @Override
    public Expression shallowCopy() {
        return new ASTGreater(id);
    }
}
/* JavaCC - OriginalChecksum=b5edb65e77757d0c765b86a9aa88717c (do not edit this line) */
