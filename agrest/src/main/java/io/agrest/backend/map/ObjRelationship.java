package io.agrest.backend.map;

import java.util.List;

/**
 * @author vyarmolovich
 * 10/24/18
 */
public interface ObjRelationship {

    String getName();

    Boolean isToMany();

    List<DbRelationship> getDbRelationships();

    String getTargetEntityName();

    /**
     * Returns a reversed dbRelationship path.
     */
    String getReverseDbRelationshipPath();
}
