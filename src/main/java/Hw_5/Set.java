package main.java.Hw_5;

/* Set.java */

import main.java.Hw_5.List.*;
import org.omg.CORBA.DynAnyPackage.Invalid;
import org.omg.CORBA.INV_FLAG;

import java.util.Comparator;

/**
 *  A Set is a collection of Comparable elements stored in sorted order.
 *  Duplicate elements are not permitted in a Set.
 **/
public class Set{
  /* Fill in the data fields here. */
    List setItems;
    int size;

    /**
     * Set ADT invariants:
     *  1)  The Set's elements must be precisely the elements of the List.
     *  2)  The List must always contain Comparable elements, and those elements
     *      must always be sorted in ascending order.
     *  3)  No two elements in the List may be equal according to compareTo().
     **/

    /**
     *  Constructs an empty Set.
     *
     *  Performance:  runs in O(1) time.
     **/
    public Set() {
        // Your solution here.
        setItems = new SList();
        size = 0;
    }

    /**
     *  cardinality() returns the number of elements in this Set.
     *
     *  Performance:  runs in O(1) time.
     **/
    public int cardinality() {
        // Replace the following line with your solution.
        return size;
    }

    /**
     *  insert() inserts a Comparable element into this Set.
     *
     *  Sets are maintained in sorted order.  The ordering is specified by the
     *  compareTo() method of the java.lang.Comparable interface.
     *
     *  Performance:  runs in O(this.cardinality()) time.
     **/
    public void insert(Comparable c) {
        // if it's not an empty set...

        if(size==0){
            setItems.insertFront(c);
            size++;
        } else {
            ListNode curr = setItems.front();
            while(curr.isValidNode()){
                try {
                    // check if unique
                    if(curr.compareTo(c)==0) { break; }

                    // check if curr is larger c
                    if(curr.compareTo(c)>0) {
                        setItems.insertFront(c);
                        size++;
                        break;
                    } else if (curr.compareTo(c)<0 && !(curr.next().isValidNode())){
                        setItems.insertBack(c);
                        size++;
                        break;
                    }
                    curr = curr.next();
                } catch (InvalidNodeException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    /**
     *  union() modifies this Set so that it contains all the elements it
     *  started with, plus all the elements of s.  The Set s is NOT modified.
     *  Make sure that duplicate elements are not created.
     *
     *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
     *
     *  Your implementation should NOT copy elements of s or "this", though it
     *  will copy _references_ to the elements of s.  Your implementation will
     *  create new _nodes_ for the elements of s that are added to "this", but
     *  you should reuse the nodes that are already part of "this".
     *
     *  DO NOT MODIFY THE SET s.
     *  DO NOT ATTEMPT TO COPY ELEMENTS; just copy _references_ to them.
     **/
    public void union(Set s) {
        ListNode head1 = this.setItems.front();
        ListNode head2 = s.setItems.front();

        while(head1.isValidNode() && head2.isValidNode()){
            try{
                if(head1.compareTo(head2.item())<0){
                    if(head1.next().isValidNode()==false){
                        head1.insertAfter(head2.item());
                    } else if (head1.next().compareTo(head2.item())!=0){
                        head1.insertAfter(head2.item());
                    }
                    size++;
                    head1 = head1.next();
                } else if(head1.compareTo(head2.item())>0){
                    if(head1.prev().isValidNode()==false){
                        head1.insertBefore(head2.item());
                    } else if(head1.prev().compareTo(head2.item())!=0){
                        head1.insertBefore(head2.item());
                    }
                    head2 = head2.next();
                    size++;
                } else {
                    if(head1.next().isValidNode()==false){
                        head2 = head2.next();
                        while(head2.isValidNode()){
                            head1.insertAfter(head2.item());
                            head2 = head2.next();
                            size++;
                        }
                        break;
                    }
                    if(head2.next().isValidNode()==false){
                        head1 = head1.next();
                        break;
                    } else {
                        head1 = head1.next();
                        head2 = head2.next();
                    }

                }

            } catch (InvalidNodeException e){
                e.printStackTrace();
            }
        }
    }

    /**
     *  intersect() modifies this Set so that it contains the intersection of
     *  its own elements and the elements of s.  The Set s is NOT modified.
     *
     *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
     *
     *  Do not construct any new ListNodes during the execution of intersect.
     *  Reuse the nodes of "this" that will be in the intersection.
     *
     *  DO NOT MODIFY THE SET s.
     *  DO NOT CONSTRUCT ANY NEW NODES.
     *  DO NOT ATTEMPT TO COPY ELEMENTS.
     **/
    public void intersect(Set s) {
        // Your solution here.
        ListNode h1 = this.setItems.front();
        ListNode h2 = s.setItems.front();
        while (h1.isValidNode() && h2.isValidNode()) {
            try {
                if (h1.item().equals(h2.item())) {
                    // just move the pointers
                    h2 = h2.next();
                    h1 = h1.next();
                } else if ((Integer) h1.item() < (Integer) h2.item()) {
                    ListNode temp = h1;
                    h1 = h1.next();
                    temp.remove();
                    size--;
                } else if ((Integer) h1.item() > (Integer) h2.item()) {
                    h2 = h2.next();
                }
            } catch (InvalidNodeException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *  toString() returns a String representation of this Set.  The String must
     *  have the following format:
     *    {  } for an empty Set.  No spaces before "{" or after "}"; two spaces
     *            between them.
     *    {  1  2  3  } for a Set of three Integer elements.  No spaces before
     *            "{" or after "}"; two spaces before and after each element.
     *            Elements are printed with their own toString method, whatever
     *            that may be.  The elements must appear in sorted order, from
     *            lowest to highest according to the compareTo() method.
     *
     *  WARNING:  THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS
     *            FORMAT, RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS.  ANY
     *            DEVIATIONS WILL LOSE POINTS.
     **/
    public String toString() {
        // Replace the following line with your solution.
        String s = setItems.toString();
        return s;
    }


}