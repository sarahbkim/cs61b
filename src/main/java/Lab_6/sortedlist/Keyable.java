package main.java.Lab_6.sortedlist;

/* Keyable.java */

public interface Keyable {
    public int getKey();
    public boolean lessThan(Keyable x);
}