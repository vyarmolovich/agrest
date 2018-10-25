package io.agrest.backend.map;

import java.util.Collection;

/**
 * @author vyarmolovich
 * 10/24/18
 */
public interface DbEntity {

    Collection<DbAttribute> getPrimaryKeys();

    DbAttribute getAttribute(String attributeName);
}
