package main.java.Hw_4.List;

/**
 * Created by sarahbkim on 3/4/15.
 * Your LockDList class should override just enough methods to ensure that
    (1)  LockDListNodes are always used in LockDLists (instead of DListNodes), and
    (2)  locked nodes cannot be removed from a list.
 */
public class LockDList extends DList {
    protected LockDListNode head;

    /**
     * constructor that calls the super's constructor..
     */
    public LockDList() {
        head = new LockDListNode(null, head, head);
        System.out.println(head.getClass());
    }

    /** creating a new newNode method
     * @param item
     * @param prev
     * @param next
     * @return
     */
    protected LockDListNode newNode(Object item, DListNode prev, DListNode next) {
        return new LockDListNode(item, (LockDListNode)prev, (LockDListNode)next);
    }

    public void insertFront(Object item){
        LockDListNode n = newNode(item, this.head, this.head.next);
        if(size==0) {
            this.head.prev = n;
        }
        this.head.next = n;
        size++;
    }

    public void insertBack(Object item) {
        LockDListNode n = newNode(item, this.head.prev, this.head);
        // original last item
        if(size==0){
            this.head.next = n;
        } else {
            this.head.prev.next = n;
        }
        this.head.prev = n;
        size++;
    }

    public void insertAfter(Object item, DListNode node) {
        if(node==null){return;};
        LockDListNode n = newNode(item, node, node.next);
        node.next.prev = n;
        node.next = n;
    }

    public void insertBefore(Object item, DListNode node) {
        // Your solution here.
        if(node==null){return;}
        LockDListNode n = newNode(item, node.prev, node);
        node.prev.next = n;
        node.prev = n;
    }


    public void remove(DListNode node) {
        if(((LockDListNode) node).locked==false){
            super.remove(node);
        }
    }

    public LockDListNode front() {
        if(size==0){
            return null;
        }
        return (LockDListNode)head.next;
    }

    public LockDListNode back() {
        if(size==0){
            return null;
        }
        return (LockDListNode)head.prev;
    }

    public LockDListNode next(DListNode node) {
        if(node==null | node.next == head | node.next == null) {
            return null;
        }
        LockDListNode current = head;
        LockDListNode next = null;
        while(current.next!=head) {
            if(node==current){
                next = (LockDListNode)current.next;
                break;
            }
            current = (LockDListNode)current.next;
        }
        return next;
    }


    public LockDListNode prev(DListNode node) {
        if(node==null | node.prev == head) {
            return null;
        }
        LockDListNode previous = null;
        LockDListNode current = head;
        while(current.prev != head){
            if(node==current){
                previous = (LockDListNode)current.prev;
                break;
            }
            current = (LockDListNode)current.prev;
        }

        return previous;
    }

    public String toString() {
        String result = "[  ";
        LockDListNode current = (LockDListNode)head.next;
        while (current != head) {
            result = result + current.item + "  ";
            current = (LockDListNode)current.next;
        }
        return result + "]";
    }

    public void lockNode(DListNode node){
        ((LockDListNode) node).locked = true;
    }

    public static void main(String[] args){

        LockDList l = new LockDList();
        LockDListNode x = l.newNode(3, null, null);
        x.changeLocked();
        System.out.println(x.locked);
        x.changeLocked();
        System.out.println(x.locked);


        System.out.println("Testing insertBack and insertFront methods: ");

        l.insertFront(9);
        l.insertFront(8);
        LockDListNode f = l.front();
        System.out.println(f.item);
        DListNode n = l.next(f);
        System.out.println(n);

        l.insertBack(2);
        l.insertBack(3);
        l.insertBack(4);
        DListNode b = l.back();
        l.insertAfter(3, b);
        String s = l.toString();
        System.out.println(s);

        DListNode r = l.back();
        l.remove(r);
        String s2 = l.toString();
        System.out. println(s2);

        l.lockNode(f);
        l.remove(f);
        String s3 = l.toString();
        System.out.println(s3);


    }
}
