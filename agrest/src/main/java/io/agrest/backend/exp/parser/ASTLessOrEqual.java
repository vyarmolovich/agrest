/* Generated By:JJTree: Do not edit this line. ASTLessOrEqual.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package io.agrest.backend.exp.parser;

import io.agrest.backend.exp.Expression;

public class ASTLessOrEqual extends SimpleNode {
    public ASTLessOrEqual(int id) {
        super(id);
    }

    public ASTLessOrEqual(ExpressionParser p, int id) {
        super(p, id);
    }

    @Override
    public Expression shallowCopy() {
        return new ASTLessOrEqual(id);
    }
}
/* JavaCC - OriginalChecksum=5e61943feef38b3eabab385951d8b5f9 (do not edit this line) */
