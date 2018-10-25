package io.agrest.backend.map;

/**
 * @author vyarmolovich
 * 10/24/18
 */
public class ObjAttribute {

    private String value;
    private String path;
    private String type;


    public ObjAttribute(String value) {
        this.value = value;
    }

    public DbAttribute getDbAttribute() {
        return null;
    }

    public String getName() {
        return value;
    }

    public void setDbAttributePath(String path) {
        this.path = path;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
