/*************************************************************************
 *  Compilation:  javac Deque.java
 *  Execution:    java Deque < input.txt
 *  
 *  Double-ended queue implementation with a resizing array.
 *
 *
 *************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  The <tt>Deque</tt> class represents a queue of generic items. The queue
 *  that supports inserting and removing items from either the front or the 
 *  back of the data structure  .
 *  It supports <em>addFirst</em>, <em>addLast</em>, <em>removeFirst</em>, and
 *  <em>removeLast</em> operations, along with methods for checking the size of 
 *  the queue, testing if the stack is empty, and iterating through the items in 
 *  FIFO order.
 *  <p>
 *  This implementation uses a resizing array, which double the underlying array
 *  when it is full and halves the underlying array when it is one-quarter full.
 *  The <em>addFirst</em>, <em>addLast</em>, <em>removeFirst</em>, 
 *  <em>removeLast</em>, <em>size</em>, and <em>is-empty</em> operations 
 *  take constant time in the worst case.  
 *  <p>
 *
 *  @author Muruo Liu
 */
public class Deque<Item> implements Iterable<Item> {
    private int N;               // number of elements on queue
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> previous;
    }

    /**
     * Initializes an empty queue.
     */
    public Deque() {
        first = null;
        last  = null;
        N = 0;
    }

    /**
     * Is this queue empty?
     * @return true if this queue is empty; false otherwise
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * Returns the number of items in this queue.
     * @return the number of items in this queue
     */
    public int size() {
        return N;     
    }


    /**
     * insert the item at the front.
     * @param item the item to add
     * @throws java.lang.NullPointerException if the client attempts to add a null item
     */
    public void addFirst(Item item) {
        if (item == null){
            throw new java.lang.NullPointerException();
        }
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.previous = null;
        if (isEmpty()) {
            first.next = null;
            last = first;
        }
        else{
            first.next = oldfirst;
            oldfirst.previous = first;
        }
        N++;
    }
    
    /**
     * Insert the item at the end.
     * @param item the item to add
     * @throws java.lang.NullPointerException if the client attempts to add a null item
     */
    public void addLast(Item item) {
        if (item == null){
            throw new java.lang.NullPointerException();
        }
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()){
            last.previous = null;
            first = last;
        }
        else{
            oldlast.next = last;
            last.previous = oldlast;
        }
        N++;
    }

    /**
     * Delete and return the item at the front.
     * @return the item at the front
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        
        N--;
        if (isEmpty()) {
            last = null;   // to avoid loitering
        }
        else{
            first.previous = null;
        } 
        return item;
    }

    /**
     * Delete and return the item at the end.
     * @return the item at the end
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = last.item;
        last = last.previous;
        
        N--;
        if (isEmpty()) first = null;   // to avoid loitering
        else last.next = null;
        return item;
    }
    

    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<Item> iterator()  {
        return new ListIterator<Item>(first);  
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }


    /**
     * Unit tests the <tt>Deque</tt> data type.
     */
    public static void main(String[] args) {
        Deque<String> qFirst = new Deque<String>();
        Deque<String> qLast = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                qFirst.addFirst(item);
                qLast.addLast(item);
            }
        }
        StdOut.println(qFirst.size());
        for (String s : qFirst){
            StdOut.print(s + " ");
        }
        StdOut.println("");
        for (String s : qLast){
            StdOut.print(s + " ");
        }
    }
}
