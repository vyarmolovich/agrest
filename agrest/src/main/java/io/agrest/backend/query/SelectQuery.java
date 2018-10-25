package io.agrest.backend.query;

import io.agrest.backend.exp.Expression;

import java.util.List;

/**
 * @author vyarmolovich
 * 10/24/18
 */
public class SelectQuery<T> implements Query {

    /**
     * Creates a SelectQuery that selects all objects of a given persistent
     * class.
     *
     * @param rootClass
     *            the Class of objects fetched by this query.
     */
    public SelectQuery(Class<T> rootClass) {
        this(rootClass, null);
    }

    /**
     * Creates a SelectQuery that selects objects of a given persistent class
     * that match supplied qualifier.
     *
     * @param rootClass
     *            the Class of objects fetched by this query.
     * @param qualifier
     *            an Expression indicating which objects should be fetched.
     */
    public SelectQuery(Class<T> rootClass, Expression qualifier) {
        this(rootClass, qualifier, null);
    }

    /**
     * Creates a SelectQuery that selects objects of a given persistent class
     * that match supplied qualifier.
     *
     * @param rootClass
     *            the Class of objects fetched by this query.
     * @param qualifier
     *            an Expression indicating which objects should be fetched.
     * @param orderings
     *            defines how to order the results, may be null.
     * @since 3.1
     */
    public SelectQuery(Class<T> rootClass, Expression qualifier, List<? extends Ordering> orderings) {
//        init(rootClass, qualifier);
//        addOrderings(orderings);
    }




    /**
     * Creates a SelectQuery that selects objects of a given persistent class.
     *
     * @param rootClass
     *            the Class of objects fetched by this query.
     *
     * @since 4.0
     */
    public static <T> SelectQuery<T> query(Class<T> rootClass) {
        return new SelectQuery<>(rootClass);
    }

    /**
     * Adds specified qualifier to the existing qualifier joining it using
     * "AND".
     */
    public void andQualifier(Expression e) {
//        qualifier = (qualifier != null) ? qualifier.andExp(e) : e;
    }

    /**
     * Sets <code>pageSize</code> property.
     *
     * By setting a page size, the Collection returned by performing a query
     * will return <i>hollow</i> DataObjects. This is considerably faster and
     * uses a tiny fraction of the memory compared to a non-paged query when
     * large numbers of objects are returned in the result. When a hollow
     * DataObject is accessed all DataObjects on the same page will be faulted
     * into memory. There will be a small delay when faulting objects while the
     * data is fetched from the data source, but otherwise you do not need to do
     * anything special to access data in hollow objects. The first page is
     * always faulted into memory immediately.
     *
     * @param pageSize
     *            The pageSize to set
     */
    public void setPageSize(int pageSize) {
//        metaData.setPageSize(pageSize);
    }

    /**
     * Adds ordering specification to this query orderings.
     */
    public void addOrdering(Ordering ordering) {
//        nonNullOrderings().add(ordering);
    }

    public void setPrefetchTree(PrefetchTreeNode prefetchTree) {
//        metaData.setPrefetchTree(prefetchTree);
    }

    /**
     * Sets new query qualifier.
     */
    public void setQualifier(Expression qualifier) {
//        this.qualifier = qualifier;
    }
}
