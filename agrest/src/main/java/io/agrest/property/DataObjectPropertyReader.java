package io.agrest.property;

import io.agrest.BaseObject;
import io.agrest.meta.AgAttribute;
import io.agrest.meta.DefaultAgAttribute;
import org.apache.cayenne.DataObject;
import org.apache.cayenne.ObjectId;
import org.apache.cayenne.exp.parser.ASTPath;

import java.util.ArrayList;
import java.util.List;

public class DataObjectPropertyReader implements PropertyReader {

	private static final PropertyReader instance = new DataObjectPropertyReader();

	public static PropertyReader reader() {
		return instance;
	}

	@Override
	public Object value(Object root, String name) {
//		return ((DataObject) root).readProperty(name);
		if (root instanceof BaseObject) {
			return getPropertyValue((BaseObject) root, name);
		} else if (root instanceof List) {
			List objects = (List)root;
			if (objects.size() == 1) {
				return getPropertyValue((BaseObject)objects.get(0), name);
			}

			List result = new ArrayList();
			for (Object o : objects) {
				if (o instanceof BaseObject) {
					result.add(getPropertyValue((BaseObject) o, name));
				}
			}
			return result;
		}

		return null;
	}

	private Object getPropertyValue(BaseObject baseObject, String name) {
		Object result = baseObject.getAttributeValue(new DefaultAgAttribute(name, String.class));
		if (result == null) {
			// try to get from children by the name
			result = baseObject.getChildren(new DefaultAgAttribute(name, String.class));
		}

		return result;
	}

}
