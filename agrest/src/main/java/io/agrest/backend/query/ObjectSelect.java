package io.agrest.backend.query;

import io.agrest.backend.ObjectContext;
import io.agrest.backend.exp.Expression;
import io.agrest.backend.exp.ExpressionFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author vyarmolovich
 * 10/24/18
 */
public class ObjectSelect<T> {

    protected Class<?> entityType;
    protected String entityName;
    protected String dbEntityName;
    protected Expression where;

    /**
     * Creates a ObjectSelect that selects objects of a given persistent class.
     */
    public static <T> ObjectSelect<T> query(Class<T> entityType) {
        return new ObjectSelect<T>().entityType(entityType);
    }

    /**
     * Creates a ObjectSelect that selects objects of a given persistent class
     * and uses provided expression for its qualifier.
     */
    public static <T> ObjectSelect<T> query(Class<T> entityType, Expression expression) {
        return new ObjectSelect<T>().entityType(entityType).where(expression);
    }

    /**
     * Sets the type of the entity to fetch without changing the return type of
     * the query.
     *
     * @return this object
     */
    public ObjectSelect<T> entityType(Class<?> entityType) {
        return resetEntity(entityType, null, null);
    }

    private ObjectSelect<T> resetEntity(Class<?> entityType, String entityName, String dbEntityName) {
        this.entityType = entityType;
        this.entityName = entityName;
        this.dbEntityName = dbEntityName;
        return this;
    }

    /**
     * Appends a qualifier expression of this query. An equivalent to
     * {@link #and(Expression...)} that can be used a syntactic sugar.
     *
     * @return this object
     */
    public ObjectSelect<T> where(Expression expression) {
        return and(expression);
    }

    /**
     * AND's provided expressions to the existing WHERE clause expression.
     *
     * @return this object
     */
    public ObjectSelect<T> and(Expression... expressions) {
        if (expressions == null || expressions.length == 0) {
            return this;
        }

        return and(Arrays.asList(expressions));
    }

    /**
     * AND's provided expressions to the existing WHERE clause expression.
     *
     * @return this object
     */
    public ObjectSelect<T> and(Collection<Expression> expressions) {

        if (expressions == null || expressions.isEmpty()) {
            return this;
        }

        Collection<Expression> all;

        if (where != null) {
            all = new ArrayList<>(expressions.size() + 1);
            all.add(where);
            all.addAll(expressions);
        } else {
            all = expressions;
        }

        where = ExpressionFactory.and(all);
        return this;
    }

    public T selectOne(ObjectContext context) {
        return context.selectOne(this);
    }

    protected ObjectSelect() {
    }
}
