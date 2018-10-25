package io.agrest.backend.configuration.server;

import io.agrest.backend.ObjectContext;

/**
 * @author vyarmolovich
 * 10/24/18
 */
public interface ServerRuntime {

    /**
     * Returns a new ObjectContext instance based on the runtime's main
     * DataChannel.
     *
     * @since 4.0
     */
//    public ObjectContext newContext() {
//        return injector.getInstance(ObjectContextFactory.class).createContext();
//    }
    ObjectContext newContext();

    /**
     * Returns the runtime {DataChannel}.
     */
//    public DataChannel getChannel() {
//        return injector.getInstance(DataChannel.class);
//    }
    ObjectContext getChannel();
}
