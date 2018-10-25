package io.agrest.backend;

import io.agrest.backend.map.EntityResolver;
import io.agrest.backend.query.ObjectSelect;
import io.agrest.backend.query.Query;
import io.agrest.backend.query.SelectQuery;

import java.util.Collection;
import java.util.List;

/**
 * @author vyarmolovich
 * 10/24/18
 */
public interface ObjectContext {

    /**
     * Returns EntityResolver that stores all mapping information accessible by
     * this ObjectContext.
     */
    EntityResolver getEntityResolver();

    /**
     * Schedules deletion of a persistent object.
     *
     */
    void deleteObject(Object object);

    /**
     * Schedules deletion of a collection of persistent objects.
     *
     */
    void deleteObjects(Collection<?> objects);

    /**
     * Flushes all changes to objects in this context to the parent DataChannel,
     * cascading flush operation all the way through the stack, ultimately
     * saving data in the database.
     */
    void commitChanges();

    /**
     * Executes a selecting query, returning a list of persistent objects or
     * data rows.
     *
     * @since 4.0
     */
    <T> List<T> select(SelectQuery<T> query);

    /**
     * Executes a selecting query, returning either NULL if query matched no
     * objects, or a single object. If query matches more than one object,
     * {@link RuntimeException} is thrown.
     *
     * @since 4.0
     */
    <T> T selectOne(ObjectSelect<T> query);

    /**
     * Executes a selecting query, returning a list of persistent objects or
     * data rows.
     */
    List performQuery(Query query);

    /**
     * Creates a new persistent object of a given class scheduled to be inserted
     * to the database on next commit.
     */
    <T> T newObject(Class<T> persistentClass);

}
