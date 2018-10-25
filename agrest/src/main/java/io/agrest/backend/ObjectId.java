package io.agrest.backend;

import java.util.Map;

/**
 * @author vyarmolovich
 * 10/24/18
 */
public interface ObjectId {

    boolean isTemporary();

    Map<String, Object> getIdSnapshot();

    /**
     * Returns a non-null mutable map that can be used to append replacement id
     * values. This allows to incrementally build a replacement GlobalID.
     *
     * @since 1.2
     */
    Map<String, Object> getReplacementIdMap();
}
