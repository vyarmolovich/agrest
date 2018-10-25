package io.agrest.backend.map;

import java.util.List;

/**
 * @author vyarmolovich
 * 10/24/18
 */
public interface DbRelationship {

    Boolean isToDependentPK();

    DbRelationship getReverseRelationship();

    List<DbJoin> getJoins();
}
