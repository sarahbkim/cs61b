/* DListNode2 */

package Lab_4;

/**
 * Created by sarahbkim on 2/18/15.
 */

public class DListNode2 {

    /**
     *  item references the item stored in the current node.
     *  prev references the previous node in the DList.
     *  next references the next node in the DList.
     *
     *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
     */

    public int item;
    public DListNode2 prev;
    public DListNode2 next;

    /**
     *  DListNode2() constructor.
     */
    DListNode2() {
        item = 0;
        prev = null;
        next = null;
    }

    DListNode2(int i) {
        item = i;
        prev = null;
        next = null;
    }

    DListNode2(int i, DListNode2 prev) {
        item = i;
        this.prev = prev;
        this.next = null;
    }

    DListNode2(int i, DListNode2 prev, DListNode2 next) {
        item = i;
        this.prev = prev;
        this.next = next;
    }

}
