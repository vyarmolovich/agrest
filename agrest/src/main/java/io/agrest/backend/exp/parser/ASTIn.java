/* Generated By:JJTree: Do not edit this line. ASTIn.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package io.agrest.backend.exp.parser;

import io.agrest.backend.exp.Expression;

public class ASTIn extends SimpleNode {
    public ASTIn(int id) {
        super(id);
    }

    public ASTIn(ExpressionParser p, int id) {
        super(p, id);
    }

    @Override
    public Expression shallowCopy() {
        return new ASTIn(id);
    }
}
/* JavaCC - OriginalChecksum=dedebc4c45ea6bea10c3e83ec314e25f (do not edit this line) */
