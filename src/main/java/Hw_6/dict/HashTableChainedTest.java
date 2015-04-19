package main.java.Hw_6.dict;

import main.java.Hw_6.SimpleBoard;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import main.java.Hw_6.SimpleBoard;

import static org.junit.Assert.*;

/**
 * Created by sarahbkim on 4/18/15.
 */
public class HashTableChainedTest {
    @Test
    public void testCompFunction() throws Exception {
        HashTableChained h = new HashTableChained(100);
        int converted = h.compFunction(513413890);
        int high = 100-1;
        int low = 0;
        assertTrue("Error, converted is too high", high >= converted);
        assertTrue("Error, converted is too low", low <= converted);

        HashTableChained h2 = new HashTableChained();
        int converted2 = h.compFunction(123589);
        int high2 = h2.maxSize -1;
        int low2 = 0;
        assertTrue("Error, converted is too high", high2 >= converted);
        assertTrue("Error, converted is too low", low <= converted);

    }

    @Test
    public void testSize() throws Exception {
        HashTableChained h = new HashTableChained();
        int size = h.size();
        assertEquals(0, size);

        HashTableChained h2 = new HashTableChained(100);
        int size2 = h2.size();
        assertEquals(0, size2);

        assertEquals(100, h2.maxSize);
    }

    @Test
    public void testIsEmpty() throws Exception {
        HashTableChained h = new HashTableChained();
        assertTrue(h.isEmpty());

        Object a = "key1";
        Object aval = "value1";
        h.insert(a, aval);
        assertFalse(h.isEmpty());
        assertEquals(1, h.size());
    }

    private static SimpleBoard randomBoard() {
        SimpleBoard board = new SimpleBoard();
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                double fval = Math.random() * 12;
                int value = (int) fval;
                board.setElementAt(x, y, value);
            }
        }
        return board;
    }

    private static void initTable(HashTableChained table, int numBoards) {
        table.makeEmpty();
        for(int i=0;i<numBoards;i++) {
            table.insert(randomBoard(), new Integer(i));
        }
        System.out.println("colls: " + table.getCollissions());
    }


    @Test
    public void testInsert() throws Exception {

        HashTableChained h = new HashTableChained();
        Object a = "key1";
        Object aval = "value1";
        Object b = "key1";
        Object bval = "value2";

        Object c = "1290389015190839210461288129";

        h.insert(a, aval);
        assertEquals(1, h.size());
        h.insert(b, bval);
        assertEquals(1, h.size());
        h.insert(c, bval);
        assertEquals(2, h.size());

        HashTableChained table = new HashTableChained(100);
        initTable(table, 100);
        assertFalse(table.isEmpty());

    }

    @Test
    public void testFind() throws Exception {
        HashTableChained h = new HashTableChained();
        Object a = "key1";
        Object aval = "value1";
        Object b = "key1";
        Object bval = "value2";
        Object c = "key2";
        Object cval = "value3";

        h.insert(a, aval);
        h.find(a);
        assertEquals(a, h.find(a).key());

        h.insert(b, bval);
        h.insert(c, cval);
        assertEquals(b, h.find(b).key());
        assertEquals(a, h.find(a).key());
        assertEquals(c, h.find(c).key());
    }

    @Test
    public void testRemove() throws Exception {
        HashTableChained h = new HashTableChained();
        Object a = "key1";
        Object aval = "value1";
        Object b = "key1";
        Object bval = "value2";
        Object c = "key2";
        Object cval = "value3";

        h.insert(a, aval);
        h.insert(b, bval);
        h.insert(c, cval);

        assertEquals(a, h.remove(a).key());
        assertEquals(1, h.size());
        assertEquals(c, h.remove(c).key());
        assertEquals(0, h.size());

    }

    @Test
    public void testMakeEmpty() throws Exception {
        HashTableChained h = new HashTableChained();
        Object a = "key1";
        Object aval = "value1";
        Object b = "key1";
        Object bval = "value2";
        Object c = "key2";
        Object cval = "value3";

        h.insert(a, aval);
        h.insert(b, bval);
        h.insert(c, cval);

        assertFalse(h.isEmpty());
        assertEquals(2, h.size());

        h.makeEmpty();
        assertTrue(h.isEmpty());
        assertEquals(0, h.size());

    }
}