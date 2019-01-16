package io.agrest.property;

import io.agrest.AgObjectId;
import io.agrest.BaseObject;
import org.apache.cayenne.ObjectId;
import org.apache.cayenne.Persistent;

import java.util.List;

public class PersistentObjectIdPropertyReader implements PropertyReader {

	private static final PropertyReader instance = new PersistentObjectIdPropertyReader();

	public static PropertyReader reader() {
		return instance;
	}

	@Override
	public Object value(Object root, String name) {

//		ObjectId id = ((Persistent) root).getObjectId();
//		if (id.isTemporary()) {
//			// for now supporting only permanent IDs
//			throw new IllegalArgumentException("Can't read from temporary ObjectId: " + id);
//		}
//		return id.getIdSnapshot().get(name);

		if (root instanceof BaseObject) {
			return ((BaseObject) root).getId().get(name);
		} else if (root instanceof List ) {
			List objects = (List) root;
			if (objects.size() == 1) {
				return ((BaseObject) objects.get(0)).getId().get(name);
			}
		}

		return null;
	}

}