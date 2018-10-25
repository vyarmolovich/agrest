package io.agrest.property;

import io.agrest.backend.DataObject;

public class DataObjectPropertyReader implements PropertyReader {

	private static final PropertyReader instance = new DataObjectPropertyReader();

	public static PropertyReader reader() {
		return instance;
	}

	@Override
	public Object value(Object root, String name) {
		return ((DataObject) root).readProperty(name);
	}

}
