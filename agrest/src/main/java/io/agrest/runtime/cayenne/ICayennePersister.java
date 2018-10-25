package io.agrest.runtime.cayenne;

import io.agrest.backend.ObjectContext;
import io.agrest.backend.map.EntityResolver;

public interface ICayennePersister {

	ObjectContext sharedContext();

	ObjectContext newContext();

	EntityResolver entityResolver();

}
