package io.agrest.backend.query;

import io.agrest.backend.exp.Expression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * @author vyarmolovich
 * 10/24/18
 */
public class Ordering {

    protected String sortSpecString;
    protected transient Expression sortSpec;
    protected SortOrder sortOrder;

    /**
     * Orders a given list of objects, using a List of Orderings applied
     * according the default iteration order of the Orderings list. I.e. each
     * Ordering with lower index is more significant than any other Ordering
     * with higher index. List being ordered is modified in place.
     *
     * @param objects elements to sort
     * @param orderings list of Orderings to be applied
     */
    @SuppressWarnings("unchecked")
    public static void orderList(List<?> objects, List<? extends Ordering> orderings) {
        if(objects == null || orderings == null || orderings.isEmpty()) {
            return;
        }
        Comparator<Object> comparator = (Comparator<Object>) orderings.get(0);
        for(int i=1; i<orderings.size(); i++) {
            comparator = comparator.thenComparing((Comparator<? super Object>) orderings.get(i));
        }
        objects.sort(comparator);
    }

    /**
     * Orders a given list of objects, using a List of Orderings applied
     * according the default iteration order of the Orderings list. I.e. each
     * Ordering with lower index is more significant than any other Ordering
     * with higher index.
     *
     * @param objects elements to sort
     * @param orderings list of Orderings to be applied
     * @return new List with ordered elements
     *
     * @since 4.0
     */
    public static <E> List<E> orderedList(final Collection<E> objects, List<? extends Ordering> orderings) {
        List<E> newList = new ArrayList<>(objects);

        orderList(newList, orderings);

        return newList;
    }

    public Ordering() {
    }

    public Ordering(String sortPathSpec, SortOrder sortOrder) {
        setSortSpecString(sortPathSpec);
        setSortOrder(sortOrder);
    }

    public void setSortSpecString(String sortSpecString) {
        if (sortSpecString != null && !sortSpecString.equalsIgnoreCase(this.sortSpecString)) {
            this.sortSpecString = sortSpecString;
            this.sortSpec = null;
        }
    }

    /**
     * Sets the sort order for this ordering.
     *
     */
    public void setSortOrder(SortOrder order) {
        this.sortOrder = order;
    }

    /**
     * Returns sortSpec string representation.
     *
     */
    public String getSortSpecString() {
        return sortSpecString;
    }

}
