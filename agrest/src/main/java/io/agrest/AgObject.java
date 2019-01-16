package io.agrest;

import io.agrest.meta.AgAttribute;

import java.util.Collection;
import java.util.List;

/**
 * Agrest type that represent an entity from backend level (Cayenne, JPA, etc.)
 *
 */
public interface AgObject {

    AgObjectId getId();

    Collection<AgObject> getChildren(AgAttribute attribute);

    void addChildren(AgAttribute attribute, List<AgObject> children);

    void addChild(AgAttribute attribute, AgObject child);

    Object getAttributeValue(AgAttribute attribute);

    Object[] getAttributeValues();

    void addAttribute(AgAttribute attribute, Object value);
}
