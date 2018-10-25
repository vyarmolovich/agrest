package io.agrest.meta;

import java.util.Collection;

import io.agrest.backend.map.ObjEntity;

/**
 * An entity model shared across Agrest stack.
 * 
 * @since 1.12
 */
public interface AgPersistentEntity<T> extends AgEntity<T> {

	ObjEntity getObjEntity();

	AgPersistentAttribute getPersistentAttribute(String name);

	Collection<AgPersistentAttribute> getPersistentAttributes();
}
