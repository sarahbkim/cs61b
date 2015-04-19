package main.java.Hw_6.dict;
import main.java.Hw_5.List.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

    /**
     *  Place any data fields here.
     **/
    int size;
    int maxSize;
    int collissions;
    ArrayList<DList> hash;

    /**
     *  Construct a new empty hash table intended to hold roughly sizeEstimate
     *  entries.  (The precise number of buckets is up to you, but we recommend
     *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
     **/

    public HashTableChained(int sizeEstimate) {
        // Your solution here.
        size = 0;
        collissions = 0;
        maxSize = sizeEstimate;

        // make sure input size is a positive number
        if(sizeEstimate>0) {
            maxSize = sizeEstimate;
        } else {
            maxSize = 109;
        }

        hash = new ArrayList<DList>(maxSize);

        for(int i = 0; i < maxSize; i++) {
            hash.add(null);
        }
    }

    /**
     *  Construct a new empty hash table with a default size.  Say, a prime in
     *  the neighborhood of 100.
     **/

    public HashTableChained() {
        // Your solution here.
        size = 0;
        maxSize = 109;
        collissions = 0;
        hash = new ArrayList<DList>(maxSize);

        for(int i = 0; i < maxSize; i++) {
            hash.add(null);
        }

    }

    /**
     *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
     *  to a value in the range 0...(size of hash table) - 1.
     *
     *  This function should have package protection (so we can test it), and
     *  should be used by insert, find, and remove.
     **/

    int compFunction(int code) {
        // Replace the following line with your solution.
        // (( a * hashCode + b ) mod p) mod N
        return (( 3 * Math.abs(code) + 12) % 15485867) % (maxSize-1);
    }

    /**
     *  Returns the number of entries stored in the dictionary.  Entries with
     *  the same key (or even the same key and value) each still count as
     *  a separate entry.
     *  @return number of entries in the dictionary.
     **/

    public int size() {
        // Replace the following line with your solution.
        return size;
    }

    /**
     *  Tests if the dictionary is empty.
     *
     *  @return true if the dictionary has no entries; false otherwise.
     **/

    public boolean isEmpty() {
        // Replace the following line with your solution.
        if(size==0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *  Create a new Entry object referencing the input key and associated value,
     *  and insert the entry into the dictionary.  Return a reference to the new
     *  entry.  Multiple entries with the same key (or even the same key and
     *  value) can coexist in the dictionary.
     *
     *  This method should run in O(1) time if the number of collisions is small.
     *
     *  @param key the key by which the entry can be retrieved.
     *  @param value an arbitrary object.
     *  @return an entry containing the key and value.
     **/

    public Entry insert(Object key, Object value) {
        // Replace the following line with your solution.
        int compKey = compFunction(key.hashCode());

        // Create new entry instance for new item
        Entry newEntry = new Entry();
        newEntry.key = key;
        newEntry.value = value;

        // check for collision..
        if(hash.isEmpty() || hash.get(compKey)==null) {
            // create a new dlist with entry
            DList l = new DList();
            l.insertBack(newEntry);
            hash.add(compKey, l);
            size++;
        } else {
            // found same key...
            DList l = hash.get(compKey);
            collissions++;
            l.insertBack(newEntry);
        }
        return newEntry;
    }

    /**
     * @return number of times there was a duplicate hashcode
     */
    public int getCollissions() {
        return collissions;
    }

    /**
     *  Search for an entry with the specified key.  If such an entry is found,
     *  return it; otherwise return null.  If several entries have the specified
     *  key, choose one arbitrarily and return it.
     *
     *  This method should run in O(1) time if the number of collisions is small.
     *
     *  @param key the search key.
     *  @return an entry containing the key and an associated value, or null if
     *          no entry contains the specified key.
     **/

    public Entry find(Object key) throws InvalidNodeException {
        // Replace the following line with your solution.
        int compKey = compFunction(key.hashCode());
        DList l = hash.get(compKey);
        DListNode head = (DListNode) l.front();
        while(head!=null) {
            try {
                Entry x = (Entry)head.item();
                if(key==x.key) {
                    return x;
                }
                head = (DListNode) head.next();
            } catch (InvalidNodeException e){
                e.printStackTrace();
            }

        }
        return null;
    }

    /**
     *  Remove an entry with the specified key.  If such an entry is found,
     *  remove it from the table and return it; otherwise return null.
     *  If several entries have the specified key, choose one arbitrarily, then
     *  remove and return it.
     *
     *  This method should run in O(1) time if the number of collisions is small.
     *
     *  @param key the search key.
     *  @return an entry containing the key and an associated value, or null if
     *          no entry contains the specified key.
     */

    public Entry remove(Object key) {
        // Replace the following line with your solution.
        int compKey = compFunction(key.hashCode());
        DList l = hash.get(compKey);
        if(l!=null) {
            DListNode head = (DListNode) l.front();
            while(head!=null){
                try {
                    Entry x = (Entry)head.item();
                    if(key==x.key) {
                        head.remove();
                        size--;
                        return x;
                    }
                    head = (DListNode) head.next();
                } catch (InvalidNodeException e) {
                    e.printStackTrace();
                }
            }
            return null;
        } else {
            return null;
        }


    }

    /**
     *  Remove all entries from the dictionary.
     */
    public void makeEmpty() {
        hash.clear();
        hash = new ArrayList<DList>(maxSize);

        for(int i = 0; i < maxSize; i++) {
            hash.add(null);
        }

        size = 0;
    }

}