package io.agrest.property;

import io.agrest.backend.reflect.Accessor;
import io.agrest.backend.reflect.PropertyUtils;

public class BeanPropertyReader implements PropertyReader {

    private static final PropertyReader instance = new BeanPropertyReader();

    public static PropertyReader reader() {
        return instance;
    }

    public static PropertyReader reader(String fixedPropertyName) {
        Accessor accessor = PropertyUtils.accessor(fixedPropertyName);
        return (root, name) -> accessor.getValue(root);
    }

    @Override
    public Object value(Object root, String name) {
        return PropertyUtils.getProperty(root, name);
    }
}
