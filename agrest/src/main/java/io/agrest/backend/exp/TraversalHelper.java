
package io.agrest.backend.exp;

import io.agrest.backend.exp.Expression;
import io.agrest.backend.exp.TraversalHandler;

/**
 * Noop implementation of TraversalHandler. 
 * Useful as a superclass for partial implementations 
 * of TraversalHandler. 
 * 
 */
public class TraversalHelper implements TraversalHandler {

    public void startNode(Expression node, Expression parentNode) {
    }

    public void endNode(Expression node, Expression parentNode) {
    }

    public void finishedChild(Expression node, int childIndex, boolean hasMoreChildren) {
    }

    public void objectNode(Object leaf, Expression parentNode) {
    }  
}
