package io.agrest.backend.exp;

import io.agrest.backend.util.ConversionUtil;
import org.apache.cayenne.exp.ExpressionException;
import org.apache.cayenne.exp.ExpressionParameter;
import org.apache.cayenne.exp.parser.ASTScalar;

import java.io.IOException;
import java.util.Map;
import java.util.function.Function;

/**
 * @author vyarmolovich
 * 10/24/18
 */
public abstract class Expression {

    /**
     * A value that a Transformer might return to indicate that a node has to be
     * pruned from the expression during the transformation.
     */
    public final static Object PRUNED_NODE = new Object();

    public static final int AND = 0;
    public static final int OR = 1;
    public static final int NOT = 2;
    public static final int EQUAL_TO = 3;
    public static final int NOT_EQUAL_TO = 4;
    public static final int LESS_THAN = 5;
    public static final int GREATER_THAN = 6;
    public static final int LESS_THAN_EQUAL_TO = 7;
    public static final int GREATER_THAN_EQUAL_TO = 8;
    public static final int BETWEEN = 9;
    public static final int IN = 10;
    public static final int LIKE = 11;
    public static final int LIKE_IGNORE_CASE = 12;
    public static final int ADD = 16;
    public static final int SUBTRACT = 17;
    public static final int MULTIPLY = 18;
    public static final int DIVIDE = 19;
    public static final int NEGATIVE = 20;
    public static final int TRUE = 21;
    public static final int FALSE = 22;

    /**
     * Expression describes a path relative to an ObjEntity. OBJ_PATH expression
     * is resolved relative to some root ObjEntity. Path expression components
     * are separated by "." (dot). Path can point to either one of these:
     * <ul>
     * <li><i>An attribute of root ObjEntity.</i> For entity Gallery OBJ_PATH
     * expression "galleryName" will point to ObjAttribute "galleryName"
     * <li><i>Another ObjEntity related to root ObjEntity via a chain of
     * relationships.</i> For entity Gallery OBJ_PATH expression
     * "paintingArray.toArtist" will point to ObjEntity "Artist"
     * <li><i>ObjAttribute of another ObjEntity related to root ObjEntity via a
     * chain of relationships.</i> For entity Gallery OBJ_PATH expression
     * "paintingArray.toArtist.artistName" will point to ObjAttribute
     * "artistName"
     * </ul>
     */
    public static final int OBJ_PATH = 26;

    /**
     * Expression describes a path relative to a DbEntity. DB_PATH expression is
     * resolved relative to some root DbEntity. Path expression components are
     * separated by "." (dot). Path can point to either one of these:
     * <ul>
     * <li><i>An attribute of root DbEntity.</i> For entity GALLERY, DB_PATH
     * expression "GALLERY_NAME" will point to a DbAttribute "GALLERY_NAME".</li>
     * <li><i>Another DbEntity related to root DbEntity via a chain of
     * relationships.</i> For entity GALLERY DB_PATH expression
     * "paintingArray.toArtist" will point to DbEntity "ARTIST".</li>
     * <li><i>DbAttribute of another ObjEntity related to root DbEntity via a
     * chain of relationships.</i> For entity GALLERY DB_PATH expression
     * "paintingArray.toArtist.ARTIST_NAME" will point to DbAttribute
     * "ARTIST_NAME".</li>
     * </ul>
     */
    public static final int DB_PATH = 27;

    /**
     * Interpreted as a comma-separated list of literals.
     */
    public static final int LIST = 28;

    public static final int NOT_BETWEEN = 35;
    public static final int NOT_IN = 36;
    public static final int NOT_LIKE = 37;
    public static final int NOT_LIKE_IGNORE_CASE = 38;
    public static final int BITWISE_NOT = 39;
    public static final int BITWISE_AND = 40;
    public static final int BITWISE_OR = 41;
    public static final int BITWISE_XOR = 42;
    public static final int BITWISE_LEFT_SHIFT = 43;
    public static final int BITWISE_RIGHT_SHIFT = 44;
    public static final int FUNCTION_CALL = 45;
    public static final int ASTERISK = 46;
    public static final int FULL_OBJECT = 47;


    protected int type;

    /**
     * Returns a count of operands of this expression. In real life there are
     * unary (count == 1), binary (count == 2) and ternary (count == 3)
     * expressions.
     */
    public abstract int getOperandCount();

    /**
     * Returns a value of operand at <code>index</code>. Operand indexing starts
     * at 0.
     */
    public abstract Object getOperand(int index);

    /**
     * Sets a value of operand at <code>index</code>. Operand indexing starts at
     * 0.
     */
    public abstract void setOperand(int index, Object value);

    /**
     * Restructures expression to make sure that there are no children of the
     * same type as this expression.
     *
     */
    protected abstract void flattenTree();

//    /**
//     * Calculates expression value with object as a context for path
//     * expressions.
//     *
//     */
//    public abstract Object evaluate(Object o);
//
//    /**
//     * Appends own content as a String to the provided Appendable.
//     *
//     * @throws IOException
//     */
//    public abstract void appendAsString(Appendable out) throws IOException;

    /**
     * Creates a copy of this expression node, without copying children.
     */
    public abstract Expression shallowCopy();

    /**
     * Returns true if this node should be pruned from expression tree in the
     * event a child is removed.
     */
    protected abstract boolean pruneNodeForPrunedChild(Object prunedChild);


    /**
     * Returns String label for this expression. Used for debugging.
     */
    public String expName() {
        switch (type) {
            case AND:
                return "AND";
            case OR:
                return "OR";
            case NOT:
                return "NOT";
            case EQUAL_TO:
                return "=";
            case NOT_EQUAL_TO:
                return "<>";
            case LESS_THAN:
                return "<";
            case LESS_THAN_EQUAL_TO:
                return "<=";
            case GREATER_THAN:
                return ">";
            case GREATER_THAN_EQUAL_TO:
                return ">=";
            case BETWEEN:
                return "BETWEEN";
            case IN:
                return "IN";
            case LIKE:
                return "LIKE";
            case LIKE_IGNORE_CASE:
                return "LIKE_IGNORE_CASE";
            case OBJ_PATH:
                return "OBJ_PATH";
            case DB_PATH:
                return "DB_PATH";
            case LIST:
                return "LIST";
            case NOT_BETWEEN:
                return "NOT BETWEEN";
            case NOT_IN:
                return "NOT IN";
            case NOT_LIKE:
                return "NOT LIKE";
            case NOT_LIKE_IGNORE_CASE:
                return "NOT LIKE IGNORE CASE";
            case FUNCTION_CALL:
                return "FUNCTION_CALL";
            default:
                return "other";
        }
    }

    /**
     * Returns a type of expression. Most common types are defined as public
     * static fields of this interface.
     */
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    /**
     * Chains this expression with another expression using "and".
     */
    public Expression andExp(Expression exp) {
        return joinExp(Expression.AND, exp);
    }

    /**
     * Chains this expression with another expression using "or".
     */
    public Expression orExp(Expression exp) {
        return joinExp(Expression.OR, exp);
    }

    /**
     * Calculates expression boolean value with object as a context for path
     * expressions.
     *
     */
    public boolean match(Object o) {
//        return ConversionUtil.toBoolean(evaluate(o));
        return false;
    }

    /**
     * Creates a new expression that joins this object with another expression,
     * using specified join type. It is very useful for incrementally building
     * chained expressions, like long AND or OR statements.
     */
    public Expression joinExp(int type, Expression exp) {
        return joinExp(type, exp, new Expression[0]);
    }

    /**
     * Creates a new expression that joins this object with other expressions,
     * using specified join type. It is very useful for incrementally building
     * chained expressions, like long AND or OR statements.
     */
    public Expression joinExp(int type, Expression exp, Expression... expressions) {
        Expression join = ExpressionFactory.expressionOfType(type);
        join.setOperand(0, this);
        join.setOperand(1, exp);
        for (int i = 0; i < expressions.length; i++) {
            Expression expressionInArray = expressions[i];
            join.setOperand(2 + i, expressionInArray);
        }
        join.flattenTree();
        return join;
    }

    /**
     * Creates and returns a new Expression instance based on this expression,
     * but with named parameters substituted with provided values. Any
     * subexpressions containing parameters not matching the "name" argument
     * will be pruned.
     * <p>
     * Note that if you want matching against nulls to be preserved, you must
     * place NULL values for the corresponding keys in the map.
     *
     * @since 4.0
     */
    public Expression params(Map<String, ?> parameters) {
        return transform(new NamedParamTransformer(parameters, true));
    }

    /**
     * Creates and returns a new Expression instance based on this expression,
     * but with named parameters substituted with provided values.If any
     * subexpressions containing parameters not matching the "name" argument are
     * found, the behavior depends on "pruneMissing" argument. If it is false an
     * Exception will be thrown, otherwise subexpressions with missing
     * parameters will be pruned from the resulting expression.
     * <p>
     * Note that if you want matching against nulls to be preserved, you must
     * place NULL values for the corresponding keys in the map.
     *
     * @since 4.0
     */
    public Expression params(Map<String, ?> parameters, boolean pruneMissing) {
        return transform(new NamedParamTransformer(parameters, pruneMissing));
    }

//    @Override
//    public String toString() {
//        StringBuilder out = new StringBuilder();
//        try {
//            appendAsString(out);
//        } catch (IOException e) {
//            throw new RuntimeException("Unexpected IO exception appending to StringBuilder", e);
//        }
//        return out.toString();
//    }


    /**
     * Traverses itself and child expressions, notifying visitor via callback
     * methods as it goes. This is an Expression-specific implementation of the
     * "Visitor" design pattern.
     */
    public void traverse(TraversalHandler visitor) {
        if (visitor == null) {
            throw new NullPointerException("Null Visitor.");
        }

        traverse(null, visitor);
    }

    /**
     * Traverses itself and child expressions, notifying visitor via callback
     * methods as it goes.
     */
    protected void traverse(Expression parentExp, TraversalHandler visitor) {

        visitor.startNode(this, parentExp);

        // recursively traverse each child
        int count = getOperandCount();
        for (int i = 0; i < count; i++) {
            Object child = getOperand(i);

            if (child instanceof Expression && !(child instanceof ASTScalar)) {
                Expression childExp = (Expression) child;
                childExp.traverse(this, visitor);
            } else {
                visitor.objectNode(child, this);
            }

            visitor.finishedChild(this, i, i < count - 1);
        }

        visitor.endNode(this, parentExp);
    }


    /**
     * Creates a transformed copy of this expression, applying transformation
     * provided by Transformer to all its nodes. Null transformer will result in
     * an identical deep copy of this expression.
     * <p>
     * To force a node and its children to be pruned from the copy, Transformer
     * should return Expression.PRUNED_NODE. Otherwise an expectation is that if
     * a node is an Expression it must be transformed to null or another
     * Expression. Any other object type would result in a ExpressionException.
     *
     * @since 1.1
     */
    public Expression transform(Function<Object, Object> transformer) {
        Object transformed = transformExpression(transformer);

        if (transformed == PRUNED_NODE || transformed == null) {
            return null;
        } else if (transformed instanceof Expression) {
            return (Expression) transformed;
        }

        throw new ExpressionException("Invalid transformed expression: " + transformed);
    }

    /**
     * A recursive method called from "transform" to do the actual
     * transformation.
     *
     * @return null, Expression.PRUNED_NODE or transformed expression.
     * @since 1.2
     */
    protected Object transformExpression(Function<Object, Object> transformer) {
        Expression copy = shallowCopy();
        int count = getOperandCount();
        for (int i = 0, j = 0; i < count; i++) {
            Object operand = getOperand(i);
            Object transformedChild;

            if (operand instanceof Expression) {
                transformedChild = ((Expression) operand).transformExpression(transformer);
            } else if (transformer != null) {
                transformedChild = transformer.apply(operand);
            } else {
                transformedChild = operand;
            }

            // prune null children only if there is a transformer and it
            // indicated so
            boolean prune = transformer != null && transformedChild == PRUNED_NODE;

            if (!prune) {
                copy.setOperand(j, transformedChild);
                j++;
            }

            if (prune && pruneNodeForPrunedChild(operand)) {
                // bail out early...
                return PRUNED_NODE;
            }
        }

        // all the children are processed, only now transform this copy
        return (transformer != null) ? transformer.apply(copy) : copy;
    }


    final class NamedParamTransformer implements Function<Object, Object> {

        private Map<String, ?> parameters;
        private boolean pruneMissing;

        NamedParamTransformer(Map<String, ?> parameters, boolean pruneMissing) {
            this.parameters = parameters;
            this.pruneMissing = pruneMissing;
        }

        @Override
        public Object apply(Object object) {
            if (!(object instanceof ExpressionParameter)) {

                // normally Object[] is an ASTList child
                if (object instanceof Object[]) {

                    Object[] source = (Object[]) object;
                    int len = source.length;
                    Object[] target = new Object[len];

                    for (int i = 0; i < len; i++) {
                        target[i] = apply(source[i]);
                    }

                    return target;
                }

                return object;
            }

            String name = ((ExpressionParameter) object).getName();
            if (!parameters.containsKey(name)) {
                if (pruneMissing) {
                    return PRUNED_NODE;
                } else {
                    throw new ExpressionException("Missing required parameter: $" + name);
                }
            } else {
                Object value = parameters.get(name);

                // wrap lists (for now); also support null parameters
                // TODO: andrus 8/14/2007 - shouldn't we also wrap non-null
                // object
                // values in ASTScalars?
                return (value != null) ? ExpressionFactory.wrapPathOperand(value) : new ASTScalar(null);
            }
        }

    }
}
