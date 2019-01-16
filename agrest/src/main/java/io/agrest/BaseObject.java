package io.agrest;

import io.agrest.meta.AgAttribute;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * It is default implementation of AgObject interface
 */
public class BaseObject implements AgObject {

    private AgObjectId id;
    private Map<String, Object> attributeValues = new HashMap<>();
    private Map<String, List<AgObject>> children = new HashMap<>();

    public BaseObject(AgObjectId id) {
        this.id = id;
    }

    public BaseObject(AgObjectId id, Map<String, Object> attributeValues) {
        this.id = id;
        this.attributeValues = attributeValues;
    }

    public BaseObject(AgObjectId id, Map<String, Object> attributeValues, Map<String, List<AgObject>> children) {
        this.id = id;
        this.attributeValues = attributeValues;
        this.children = children;
    }

    @Override
    public AgObjectId getId() {
        return id;
    }

    @Override
    public Collection<AgObject> getChildren(AgAttribute attribute) {
        return children.get(attribute.getName());
    }

    @Override
    public void addChildren(AgAttribute attribute, List<AgObject> children) {
        this.children.put(attribute.getName(), children);
    }

    @Override
    public void addChild(AgAttribute attribute, AgObject child) {
        if (!this.children.containsKey(attribute.getName())) {
            this.children.put(attribute.getName(), new ArrayList<>());
        }
        this.children.get(attribute.getName()).add(child);
    }

    @Override
    public Object getAttributeValue(AgAttribute attribute) {
        return this.attributeValues.get(attribute.getName());
    }

    @Override
    public Object[] getAttributeValues() {
        return this.attributeValues.values().toArray();
    }

    @Override
    public void addAttribute(AgAttribute attribute, Object value) {
        this.attributeValues.put(attribute.getName(), value);
    }
}
