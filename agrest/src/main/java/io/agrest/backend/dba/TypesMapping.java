package io.agrest.backend.dba;

/**
 * @author vyarmolovich
 * 10/24/18
 */
public interface TypesMapping {

    // char constants for Java data types
    static final String JAVA_BYTES = "byte[]";

    static String getJavaBySqlType(int type) {
        return "";
    }
}
