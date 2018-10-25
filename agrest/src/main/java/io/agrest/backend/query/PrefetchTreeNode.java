package io.agrest.backend.query;

/**
 * @author vyarmolovich
 * 10/24/18
 */
public class PrefetchTreeNode {

    public static final int JOINT_PREFETCH_SEMANTICS = 1;
    public static final int DISJOINT_PREFETCH_SEMANTICS = 2;

    public PrefetchTreeNode() {
    }

    /**
     * Adds a "path" with specified semantics to this prefetch node. All yet
     * non-existent nodes in the created path will be marked as phantom.
     *
     * @return the last segment in the created path.
     */
    public PrefetchTreeNode addPath(String path) {
        return this;
    }

    public void setPhantom(boolean phantom) {
//        this.phantom = phantom;
    }

    public void setSemantics(int semantics) {
//        this.semantics = semantics;
    }
}
