package io.agrest.backend;

import io.agrest.backend.map.DbEntity;
import io.agrest.backend.map.ObjEntity;

import java.util.Map;

/**
 * @author vyarmolovich
 * 10/24/18
 */
public class Backend {

    /**
     * Returns a primary key value for a persistent object. Only works for
     * single column primary keys. If an object is transient or has a compound
     * ObjectId, an exception is thrown.
     */
    public static Object pkForObject(Persistent dataObject) {
        Map<String, Object> pk = extractObjectId(dataObject);

        if (pk.size() != 1) {
            throw new RuntimeException(String.format("Expected single column PK, got %d columns, ID: %s", pk.size(), pk));
        }

        return pk.entrySet().iterator().next().getValue();
    }

    /**
     * Returns an object matching an Object primary key. If the object is mapped
     * to use a compound PK, CayenneRuntimeException is thrown.
     * <p>
     * If this object is already cached in the ObjectStore, it is returned
     * without a query. Otherwise a query is built and executed against the
     * database.
     * </p>
     *
     */
    @SuppressWarnings("unchecked")
    public static <T> T objectForPK(ObjectContext context, Class<T> dataObjectClass, Object pk) {
        return (T) new Object();
    }




    static Map<String, Object> extractObjectId(Persistent dataObject) {
        if (dataObject == null) {
            throw new IllegalArgumentException("Null DataObject");
        }

        ObjectId id = dataObject.getObjectId();
        if (!id.isTemporary()) {
            return id.getIdSnapshot();
        }

        // replacement ID is more tricky... do some sanity check...
//        if (id.isReplacementIdAttached()) {
//            ObjEntity objEntity = dataObject.getObjectContext().getEntityResolver().getObjEntity(dataObject);
//
//            if (objEntity != null) {
//                DbEntity entity = objEntity.getDbEntity();
//                if (entity != null && entity.isFullReplacementIdAttached(id)) {
//                    return id.getReplacementIdMap();
//                }
//            }
//        }

        throw new RuntimeException("Can't get primary key from temporary id.");
    }

    protected Backend() {}
}
