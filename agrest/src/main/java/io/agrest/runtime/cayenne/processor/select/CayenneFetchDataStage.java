package io.agrest.runtime.cayenne.processor.select;

import io.agrest.AgException;
import io.agrest.AgObject;
import io.agrest.AgObjectId;
import io.agrest.BaseObject;
import io.agrest.CompoundObjectId;
import io.agrest.ResourceEntity;
import io.agrest.SimpleObjectId;
import io.agrest.meta.AgAttribute;
import io.agrest.meta.AgEntity;
import io.agrest.meta.AgRelationship;
import io.agrest.meta.DefaultAgAttribute;
import io.agrest.processor.Processor;
import io.agrest.processor.ProcessorOutcome;
import io.agrest.runtime.cayenne.ICayennePersister;
import io.agrest.runtime.processor.select.SelectContext;
import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.ObjectId;
import org.apache.cayenne.di.Inject;
import org.apache.cayenne.query.SelectQuery;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @since 2.7
 */
public class CayenneFetchDataStage implements Processor<SelectContext<?>> {

    private ICayennePersister persister;

    public CayenneFetchDataStage(@Inject ICayennePersister persister) {

        // Store persister, don't extract ObjectContext from it right away.
        // Such deferred initialization may help building POJO pipelines.

        this.persister = persister;
    }

    @Override
    public ProcessorOutcome execute(SelectContext<?> context) {
        doExecute(context);
        return ProcessorOutcome.CONTINUE;
    }

    protected <T> void doExecute(SelectContext<T> context) {

        List<T> objects = fetchEntity(context.getEntity());

        if (context.isAtMostOneObject() && objects.size() != 1) {

            AgEntity<?> entity = context.getEntity().getAgEntity();

            if (objects.isEmpty()) {
                throw new AgException(Response.Status.NOT_FOUND,
                        String.format("No object for ID '%s' and entity '%s'", context.getId(), entity.getName()));
            } else {
                throw new AgException(Response.Status.INTERNAL_SERVER_ERROR, String.format(
                        "Found more than one object for ID '%s' and entity '%s'", context.getId(), entity.getName()));
            }
        }

        context.setAgObjects(context.getEntity().getResult());

        context.setObjects(objects);
    }

    protected <T> List<T>  fetchEntity(ResourceEntity<T> resourceEntity) {
        SelectQuery<T> select = resourceEntity.getSelect();

        List<T> objects = persister.sharedContext().select(select);

        resourceEntity.setResult(toAgObjects(objects, resourceEntity.getAgEntity()));

        if (!resourceEntity.getChildren().isEmpty()) {
            for (Map.Entry<String, ResourceEntity<?>> e : resourceEntity.getChildren().entrySet()) {
                ResourceEntity childEntity = e.getValue();

                List childObjects = fetchEntity(childEntity);

                assignChildrenToParent(resourceEntity, childEntity, childObjects);
            }
        }

        return objects;
    }

    // Assigns child items to the appropriate parent item
    protected <T> void assignChildrenToParent(ResourceEntity<T> resourceEntity, ResourceEntity childEntity, List children) {

        AgRelationship relationship = resourceEntity.getAgEntity().getRelationship(childEntity.getAgEntity());
        AgAttribute childrenId = new DefaultAgAttribute(relationship.getName(), List.class);

        for (AgObject parent : resourceEntity.getResult()) {
                for (int i = 0; i < children.size(); i++) {
                    Object child = children.get(i);
                    if (child instanceof Object[]) {
                        Object[] ids = (Object[])child;
                        for (Object id : ids) {
                            if (!(id instanceof CayenneDataObject) && parent.getId().get().equals(id)) {
                                if (!childEntity.getResult().isEmpty() && childEntity.getResult().size() > i) {
                                    parent.addChild(childrenId, (AgObject) childEntity.getResult().get(i));
                                } else {
                                    parent.addChild(childrenId, toAgObject((CayenneDataObject) ids[0], childEntity.getAgEntity()));
                                }
//                                AgObjectId agObjectId = toAgObjectId(((CayenneDataObject)ids[0]).getObjectId());
//                                   for (AgObject agObject : (List<AgObject>)childEntity.getResult()) {
//                                       if (agObject.getId().equals(agObjectId)) {
//                                           parent.addChild(childrenId, agObject);
//                                       }
//                                   }
                            }
                        }
                    }
                }


//                ((CayenneDataObject) parent).writePropertyDirectly(
//                        relationship.getName(),
//                        relationship.isToMany() ? relations : relations.isEmpty() ? null : relations.iterator().next());

        }

//        return parents;
    }

    private <T> List<AgObject> toAgObjects(List<T> objects, AgEntity<T> entity) {
        List<AgObject> result = new ArrayList<>();

        for (T t : objects) {
            CayenneDataObject dataObject = null;
            if (t instanceof CayenneDataObject) {
                dataObject = (CayenneDataObject)t;
            } else if (t instanceof Object[] && ((Object[])t)[0] instanceof CayenneDataObject) {
                dataObject = (CayenneDataObject)((Object[])t)[0];
            }

            result.add(toAgObject(dataObject, entity));
        }

        return result;
    }

    private <T> AgObject toAgObject(CayenneDataObject object, AgEntity<T> entity) {
        if (object == null) {
            return null;
        }

        AgObject agObject = new BaseObject(toAgObjectId(object.getObjectId()));

        for (AgAttribute attribute : entity.getAttributes()) {
            agObject.addAttribute(attribute, object.readProperty(attribute.getName()));
        }

        return agObject;
    }

    private AgObjectId toAgObjectId(ObjectId objectId) {
        Map<String, Object> ids = objectId.getIdSnapshot();

        return ids.size() == 1
                ? new SimpleObjectId(ids.values().iterator().next())
                : new CompoundObjectId(ids);
    }
}
