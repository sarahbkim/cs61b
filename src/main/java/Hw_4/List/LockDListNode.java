package main.java.Hw_4.List;

/**
 * Created by sarahbkim on 3/4/15.
 */
public class LockDListNode extends DListNode {
    protected boolean locked;

    // constructor with params
    LockDListNode(Object i, LockDListNode p, LockDListNode n){
        super(i, p, n);
        locked = false;
    }

    public void changeLocked(){
        locked = true;
    }

    public static void main(String[] args){
        LockDListNode n = new LockDListNode(3, null, null);
        System.out.println(n.item);
        System.out.println(n.locked);
        n.changeLocked();
        System.out.println(n.locked);
    }

}
