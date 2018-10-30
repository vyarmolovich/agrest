/* Generated By:JJTree: Do not edit this line. ASTScalar.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package io.agrest.backend.exp.parser;

import org.apache.cayenne.Persistent;

public
class ASTScalar extends SimpleNode {
  public ASTScalar(int id) {
    super(id);
  }

  public ASTScalar(ExpressionParser p, int id) {
    super(p, id);
  }

    public void setValue(Object value) {
        if (value instanceof Persistent){
            this.value = ((Persistent)value).getObjectId();
        } else {
            this.value = value;
        }
    }

    public Object getValue() {
        return value;
    }

}
/* JavaCC - OriginalChecksum=17a4f1b5f34ec85f6b49781e47b4beae (do not edit this line) */