/* Generated By:JJTree: Do not edit this line. ASTBitwiseAnd.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package io.agrest.backend.exp.parser;

import io.agrest.backend.exp.Expression;

public class ASTBitwiseAnd extends SimpleNode {
    public ASTBitwiseAnd(int id) {
        super(id);
    }

    public ASTBitwiseAnd(ExpressionParser p, int id) {
        super(p, id);
    }

    @Override
    public Expression shallowCopy() {
        return new ASTBitwiseAnd(id);
    }
}
/* JavaCC - OriginalChecksum=d514ba46cbbc76e0f5ff7d5705989b81 (do not edit this line) */
