package io.agrest.runtime.cayenne;

import io.agrest.backend.ObjectContext;
import io.agrest.backend.configuration.server.ServerRuntime;
import io.agrest.backend.map.EntityResolver;

public class CayennePersister implements ICayennePersister {

	private ServerRuntime runtime;
	private ObjectContext sharedContext;

	public CayennePersister(ServerRuntime runtime) {

		if (runtime == null) {
			throw new NullPointerException("Null runtime");
		}

		this.runtime = runtime;
		this.sharedContext = runtime.newContext();
	}

	@Override
	public ObjectContext sharedContext() {
		return sharedContext;
	}

	@Override
	public ObjectContext newContext() {
		return runtime.newContext();
	}

	@Override
	public EntityResolver entityResolver() {
		return runtime.getChannel().getEntityResolver();
	}
}
