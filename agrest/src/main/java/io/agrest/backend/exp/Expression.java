package io.agrest.backend.exp;

import io.agrest.backend.util.ConversionUtil;

import java.io.IOException;
import java.util.Map;
import java.util.function.Function;

/**
 * @author vyarmolovich
 * 10/24/18
 */
public abstract class Expression {

    public static final int AND = 0;
    public static final int OR = 1;
//    public static final int NOT = 2;
//    public static final int EQUAL_TO = 3;
//    public static final int NOT_EQUAL_TO = 4;
//    public static final int LESS_THAN = 5;
//    public static final int GREATER_THAN = 6;
//    public static final int LESS_THAN_EQUAL_TO = 7;
//    public static final int GREATER_THAN_EQUAL_TO = 8;
//    public static final int BETWEEN = 9;
//    public static final int IN = 10;
//    public static final int LIKE = 11;
//    public static final int LIKE_IGNORE_CASE = 12;
//    public static final int ADD = 16;
//    public static final int SUBTRACT = 17;
//    public static final int MULTIPLY = 18;
//    public static final int DIVIDE = 19;
//    public static final int NEGATIVE = 20;
//    public static final int TRUE = 21;
//    public static final int FALSE = 22;

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

    /**
     * Calculates expression value with object as a context for path
     * expressions.
     *
     */
    public abstract Object evaluate(Object o);

    /**
     * Appends own content as a String to the provided Appendable.
     *
     * @throws IOException
     */
    public abstract void appendAsString(Appendable out) throws IOException;



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
        return ConversionUtil.toBoolean(evaluate(o));
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
     *
     * @since 4.0
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
     */
    public Expression params(Map<String, ?> parameters) {
        return new Expression() {
            @Override
            public void setOperand(int index, Object value) { }
            @Override
            protected void flattenTree() { }
            @Override
            public Object evaluate(Object o) { return null; }

            @Override
            public void appendAsString(Appendable out) throws IOException { }
        };
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        try {
            appendAsString(out);
        } catch (IOException e) {
            throw new RuntimeException("Unexpected IO exception appending to StringBuilder", e);
        }
        return out.toString();
    }
}
