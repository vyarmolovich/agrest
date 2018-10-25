package io.agrest.backend;

/**
 * @author vyarmolovich
 * 10/24/18
 */
public interface DataObject extends Persistent {

    Object readProperty(String propName);

    /**
     * Removes an object from a to-many relationship.
     */
    void removeToManyTarget(
            String relationshipName,
            DataObject target,
            boolean unsetReverse);


    /**
     * Sets to-one relationship to a new value. Resolves faults if needed. This method can
     * safely be used instead of or in addition to the auto-generated property modifiers
     * to set properties that are to-one relationships.
     *
     * @param relationshipName a name of the bean property being modified - same as the
     *            name of ObjRelationship.
     * @param value a new value of the property.
     * @param setReverse whether to update the reverse relationship pointing from the old
     *            and new values of the property to this object.
     */
    void setToOneTarget(
            String relationshipName,
            DataObject value,
            boolean setReverse);

    /**
     * Flushes all changes to objects in this context to the parent DataChannel,
     * cascading flush operation all the way through the stack, ultimately
     * saving data in the database.
     */
    void commitChanges();

    /**
     * Sets the property to the new value. Resolves faults if needed. This method can be
     * safely used instead of or in addition to the auto-generated property modifiers to
     * set simple properties. Note that to set to-one relationships use
     * {@link #setToOneTarget(String, DataObject, boolean)}.
     *
     * @param propertyName a name of the bean property being modified.
     * @param value a new value of the property.
     */
    void writeProperty(String propertyName, Object value);

    ObjectId getObjectId();

    ObjectContext getObjectContext();

    /**
     * Adds an object to a to-many relationship.
     */
    void addToManyTarget(
            String relationshipName,
            DataObject target,
            boolean setReverse);
}
