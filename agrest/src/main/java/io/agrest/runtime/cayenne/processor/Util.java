package io.agrest.runtime.cayenne.processor;

import io.agrest.AgException;
import io.agrest.meta.AgAttribute;
import io.agrest.meta.AgEntity;
import io.agrest.backend.ObjectContext;
import io.agrest.backend.exp.ExpressionFactory;
import io.agrest.backend.exp.parser.ASTEqual;
import io.agrest.backend.map.ObjEntity;
import io.agrest.backend.query.ObjectSelect;

import javax.ws.rs.core.Response.Status;
import java.util.Map;

/**
 * @since 1.7
 */
public final class Util {

	private Util() {
	}

	@SuppressWarnings("unchecked")
	public static <A> A findById(ObjectContext context, Class<A> type, AgEntity<?> agEntity, Object id) {
		ObjEntity entity = context.getEntityResolver().getObjEntity(type);

		// sanity checking...
		if (entity == null) {
			throw new AgException(Status.INTERNAL_SERVER_ERROR, "Unknown entity class: " + type);
		}

		if (id == null) {
			throw new AgException(Status.BAD_REQUEST, "No id specified");
		}

		if (id instanceof Map) {
			Map<String, Object> ids = (Map<String, Object>) id;
			ObjectSelect<A> query = ObjectSelect.query(type);
			for (Map.Entry<String, Object> entry : ids.entrySet()) {
				query.and(ExpressionFactory.matchDbExp(
						entity.getDbEntity().getAttribute(entry.getKey()).getName(), entry.getValue()
				));
			}
			return query.selectOne(context);
		} else {
			AgAttribute attribute = agEntity.getIds().iterator().next();
			return ObjectSelect.query(type, new ASTEqual(attribute.getPathExp(), id)).selectOne(context);
		}
	}
}
