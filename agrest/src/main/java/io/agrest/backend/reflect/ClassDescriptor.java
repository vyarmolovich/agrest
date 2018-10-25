package io.agrest.backend.reflect;

/**
 * @author vyarmolovich
 * 10/24/18
 */
public interface ClassDescriptor {

    /**
     * Returns a class mapped by this descriptor.
     */
    Class<?> getObjectClass();
}
