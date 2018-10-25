package io.agrest.backend.reflect;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author vyarmolovich
 * 10/24/18
 */
public interface PropertyUtils {

    static final ConcurrentMap<String, Accessor> PATH_ACCESSORS = new ConcurrentHashMap<>();
    static final ConcurrentMap<Class<?>, ConcurrentMap<String, Accessor>> SEGMENT_ACCESSORS = new ConcurrentHashMap<>();

    static Accessor accessor(String nestedPropertyName) {
        if (nestedPropertyName == null) {
            throw new IllegalArgumentException("Null property name.");
        }

        if (nestedPropertyName.length() == 0) {
            throw new IllegalArgumentException("Empty property name.");
        }

        return new PathAccessor();
    }



    static Object getProperty(Object object, String nestedPropertyName) {
        return new Object();
    }

    static final class PathAccessor implements Accessor {

        @Override
        public Object getValue(Object object) {
            return object;
        }
    }
}
