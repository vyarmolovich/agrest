/* Generated By:JJTree: Do not edit this line. ASTAsterisk.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package io.agrest.backend.exp.parser;

import io.agrest.backend.exp.Expression;

public class ASTAsterisk extends SimpleNode {
    public ASTAsterisk(int id) {
        super(id);
    }

    public ASTAsterisk(ExpressionParser p, int id) {
        super(p, id);
    }

    @Override
    public Expression shallowCopy() {
        return new ASTAsterisk(id);
    }
}
/* JavaCC - OriginalChecksum=3d693f47e8d3d1abe95584e29f6f511c (do not edit this line) */
