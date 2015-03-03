package main.java.Hw_4.List;

/**
 * Created by sarahbkim on 2/25/15.
 *  A DList is a mutable doubly-linked list ADT.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 *
 *  DO NOT CHANGE ANY METHOD PROTOTYPES IN THIS FILE.
 */

public class DList {

    /**
     *  head references the sentinel node.
     *  size is the number of items in the list.  (The sentinel node does not
     *       store an item.)
     *
     *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
     */

    protected DListNode head;
    protected int size;

  /* DList invariants:
   *  1)  head != null.
   *  2)  For any DListNode x in a DList, x.next != null.
   *  3)  For any DListNode x in a DList, x.prev != null.
   *  4)  For any DListNode x in a DList, if x.next == y, then y.prev == x.
   *  5)  For any DListNode x in a DList, if x.prev == y, then y.next == x.
   *  6)  size is the number of DListNodes, NOT COUNTING the sentinel,
   *      that can be accessed from the sentinel (head) by a sequence of
   *      "next" references.
   */

    /**
     *  newNode() calls the DListNode constructor.  Use this class to allocate
     *  new DListNodes rather than calling the DListNode constructor directly.
     *  That way, only this method needs to be overridden if a subclass of DList
     *  wants to use a different kind of node.
     *  @param item the item to store in the node.
     *  @param prev the node previous to this node.
     *  @param next the node following this node.
     */
    protected DListNode newNode(Object item, DListNode prev, DListNode next) {
        return new DListNode(item, prev, next);
    }

    /**
     *  DList() constructor for an empty DList.
     */
    public DList() {
        //  Your solution here.
        this.head = new DListNode(null, head, head);
        size = 0;
    }

    /**
     *  isEmpty() returns true if this DList is empty, false otherwise.
     *  @return true if this DList is empty, false otherwise.
     *  Performance:  runs in O(1) time.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     *  length() returns the length of this DList.
     *  @return the length of this DList.
     *  Performance:  runs in O(1) time.
     */
    public int length() {
        return size;
    }

    /**
     *  insertFront() inserts an item at the front of this DList.
     *  @param item is the item to be inserted.
     *  Performance:  runs in O(1) time.
     */
    public void insertFront(Object item) {
        DListNode n = newNode(item, this.head, this.head.next);
        if(size==0) {
            this.head.prev = n;
        }
        this.head.next = n;
        size++;
    }

    /**
     *  insertBack() inserts an item at the back of this DList.
     *  @param item is the item to be inserted.
     *  Performance:  runs in O(1) time.
     */
    public void insertBack(Object item) {
        DListNode n = newNode(item, this.head.prev, this.head);
        // original last item
        if(size==0){
            this.head.next = n;
        } else {
            this.head.prev.next = n;
        }
        this.head.prev = n;
        size++;
    }

    /**
     *  front() returns the node at the front of this DList.  If the DList is
     *  empty, return null.
     *
     *  Do NOT return the sentinel under any circumstances!
     *
     *  @return the node at the front of this DList.
     *  Performance:  runs in O(1) time.
     */
    public DListNode front() {
        if(size==0){
            return null;
        }
        return head.next;
    }

    /**
     *  back() returns the node at the back of this DList.  If the DList is
     *  empty, return null.
     *
     *  Do NOT return the sentinel under any circumstances!
     *
     *  @return the node at the back of this DList.
     *  Performance:  runs in O(1) time.
     */
    public DListNode back() {
        if(size==0){
            return null;
        }
        return head.prev;
    }

    /**
     *  next() returns the node following "node" in this DList.  If "node" is
     *  null, or "node" is the last node in this DList, return null.
     *
     *  Do NOT return the sentinel under any circumstances!
     *
     *  @param node the node whose successor is sought.
     *  @return the node following "node".
     *  Performance:  runs in O(1) time.
     */
    public DListNode next(DListNode node) {
        if(node==null | node.next == head | node.next == null) {
            return null;
        }
        DListNode current = head;
        DListNode next = null;
        while(current.next!=head) {
            if(node==current){
                next = current.next;
                break;
            }
            current = current.next;
        }
        return next;
    }

    /**
     *  prev() returns the node prior to "node" in this DList.  If "node" is
     *  null, or "node" is the first node in this DList, return null.
     *
     *  Do NOT return the sentinel under any circumstances!
     *
     *  @param node the node whose predecessor is sought.
     *  @return the node prior to "node".
     *  Performance:  runs in O(1) time.
     */
    public DListNode prev(DListNode node) {
        if(node==null | node.prev == head) {
            return null;
        }
        DListNode previous = null;
        DListNode current = head;
        while(current.prev != head){
            if(node==current){
                previous = current.prev;
                break;
            }
            current = current.prev;
        }

        return previous;
    }

    /**
     *  insertAfter() inserts an item in this DList immediately following "node".
     *  If "node" is null, do nothing.
     *  @param item the item to be inserted.
     *  @param node the node to insert the item after.
     *  Performance:  runs in O(1) time.
     */
    public void insertAfter(Object item, DListNode node) {
        if(node==null){return;};
        DListNode newNode = new DListNode(item, node, node.next);
        node.next.prev = newNode;
        node.next = newNode;
    }

    /**
     *  insertBefore() inserts an item in this DList immediately before "node".
     *  If "node" is null, do nothing.
     *  @param item the item to be inserted.
     *  @param node the node to insert the item before.
     *  Performance:  runs in O(1) time.
     */
    public void insertBefore(Object item, DListNode node) {
        // Your solution here.
        if(node==null){return;}
        DListNode newNode = new DListNode(item, node.prev, node);
        node.prev.next = newNode;
        node.prev = newNode;
    }

    /**
     *  remove() removes "node" from this DList.  If "node" is null, do nothing.
     *  Performance:  runs in O(1) time.
     */
    public void remove(DListNode node) {
        if(node==null){return;}
        // update the previous node
        node.prev.next = node.next;
        // update the next node
        node.next.prev = node.prev;
    }

    /**
     *  toString() returns a String representation of this DList.
     *
     *  DO NOT CHANGE THIS METHOD.
     *
     *  @return a String representation of this DList.
     *  Performance:  runs in O(n) time, where n is the length of the list.
     */
    public String toString() {
        String result = "[  ";
        DListNode current = head.next;
        while (current != head) {
            result = result + current.item + "  ";
            current = current.next;
        }
        return result + "]";
    }

    public static void main(String[] args){
        DList d = new DList();
        System.out.println("Testing insertBack method: ");
        d.insertBack(1);
        d.insertBack(2);
        d.insertBack(3);
        d.insertBack(4);

        String s = d.toString();
        System.out.println(s);

        System.out.println("Testing insertFront method:");
        d.insertFront(10);
        d.insertFront(9);

        String s2 = d.toString();
        System.out.println(s2);

        System.out.println("Testing back() method: ");
        DListNode x = d.back();
        System.out.println("x should equal 4: " + x.item);
        DListNode f = d.front();
        System.out.println("f should equal 9: " + f.item);

        System.out.println("Testing prev method: ");
        DListNode p = d.prev(x);
        System.out.println("p should equal 3" + p.item);


        System.out.println("Testing insertAfter method: ");
        d.insertAfter(5, d.back());
        String s3 = d.toString();
        System.out.println(s3);
        System.out.println(d.back().item);
        System.out.println("Testing insertBefoe method: ");
        d.insertBefore(11, d.back());
        String s4 = d.toString();
        System.out.println(s4);
        System.out.println("Testing delete method: ");
        d.remove(d.front());
        d.remove(d.back());
        String s5 = d.toString();
        System.out.println(s5);

    }
}