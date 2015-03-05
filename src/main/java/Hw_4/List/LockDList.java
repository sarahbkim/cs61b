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




    public static void main(String[] args){

        LockDList l = new LockDList();
        LockDListNode x = l.newNode(3, null, null);
        x.changeLocked();
        System.out.println(x.locked);
        x.changeLocked();
        System.out.println(x.locked);


        System.out.println("Testing insertBack and insertFront methods: ");

//        l.insertFront(9);
//        l.insertBack(2);
//        l.insertBack(3);
//        l.insertBack(4);
//        DListNode f = l.front();
//        System.out.println(f.getClass());
//
//        l.insertBack(3);
//        String s2 = l.toString();
//        System.out.println(s2);
//

    }
}
