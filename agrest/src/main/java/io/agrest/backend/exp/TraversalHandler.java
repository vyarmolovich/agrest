package io.agrest.backend.exp;

import io.agrest.backend.exp.Expression;

/**
 * Expression visitor interface. Defines callback methods invoked when 
 * walking the expression using {@link Expression#traverse(io.agrest.backend.exp.TraversalHandler)}.
 *
 */
public interface TraversalHandler {

    /**
     * Called during traversal after a child of expression
     * has been visited.
     */
    public void finishedChild(
            Expression node,
            int childIndex,
            boolean hasMoreChildren);

    /**
     * Called during the traversal before an expression node children
     * processing is started.
     *
     * @since 1.1
     */
    public void startNode(Expression node, Expression parentNode);

    /**
     * Called during the traversal after an expression node children
     * processing is finished.
     *
     * @since 1.1
     */
    public void endNode(Expression node, Expression parentNode);
    
    /** 
     * Called during the traversal when a leaf non-expression node 
     * is encountered.
     */
    public void objectNode(Object leaf, Expression parentNode);
}
