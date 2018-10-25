package io.agrest.backend.reflect;

/**
 * @author vyarmolovich
 * 10/24/18
 */
public interface Accessor {

    /**
     * Returns a property value of an object without disturbing the object fault status.
     */
    Object getValue(Object object);
}
