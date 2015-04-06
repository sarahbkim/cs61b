package test.java.Hw_5;

import main.java.Hw_5.Set;
import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/*
Junit test for Set class
 */

public class SetTest{
    private Set s;
    private Set s1;
    private Set s2;

    @Before
    public void initializeSet() {
        s = new Set();
        s1 = new Set();
        s2 = new Set();
    }

    @Test
    public void testEmptySet() {
        assertEquals("[  ]", s.toString());
        assertEquals(0, s.cardinality());
    }

    @Test
    public void testInserting() {
        s.insert(new Integer(3));
        s.insert(new Integer(4));
        s.insert(new Integer(5));
        assertEquals(3, s.cardinality());
        assertEquals("[  3  4  5  ]", s.toString());

        s1.insert(new Integer(4));
        s1.insert(new Integer(5));
        s1.insert(new Integer(5));
        assertEquals(2, s1.cardinality());
        assertEquals("[  4  5  ]", s1.toString());

        s2.insert(new Integer(5));
        s2.insert(new Integer(3));
        s2.insert(new Integer(8));
        assertEquals(3, s2.cardinality());
        assertEquals("[  3  5  8  ]", s2.toString());
    }

    @Test
    public void testUnions() {
        s.insert(new Integer(3));
        s.insert(new Integer(4));
        s.insert(new Integer(5));

        s1.insert(new Integer(4));
        s1.insert(new Integer(5));
        s1.insert(new Integer(5));

        s2.insert(new Integer(5));
        s2.insert(new Integer(3));
        s2.insert(new Integer(8));

        s.union(s1);
        assertEquals("[  3  4  5  ]", s.toString());

        s1.union(s);
        assertEquals("[  3  4  5  ]", s1.toString());

        s2.union(s);
        assertEquals("[  3  4  5  8  ]", s2.toString());
        assertThat(s2.cardinality(), is(4));
    }

    @Test
    public void testIntersect() {
        s1.insert(new Integer(4));
        s1.insert(new Integer(5));

        s2.insert(new Integer(5));
        s2.insert(new Integer(3));
        s2.insert(new Integer(10));

        s1.intersect(s2);
        assertEquals("[  5  ]", s1.toString());

        s1.insert(new Integer(10));
        s2.intersect(s1);
        assertEquals("[  5  10  ]", s2.toString());

    }


}