package io.agrest.backend.map;

import java.util.Collection;

/**
 * @author vyarmolovich
 * 10/24/18
 */
public interface ObjEntity {

    String getName();

    Collection<ObjAttribute> getAttributes();

    Collection<ObjRelationship> getRelationships();

    DbEntity getDbEntity();

    ObjAttribute getAttributeForDbAttribute(DbAttribute dbAttribute);

    /**
     * Returns relationship with name <code>relName</code>. Will return null if no
     * relationship with this name exists in the entity.
     */
    ObjRelationship getRelationship(String relName);
}
