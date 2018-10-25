package io.agrest.backend.map;

/**
 * @author vyarmolovich
 * 10/24/18
 */
public interface DbJoin {

    String getSourceName();

    String getTargetName();

    DbAttribute getTarget();
}
