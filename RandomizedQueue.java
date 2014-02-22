/*************************************************************************
 *  Compilation:  javac RandomizedQueue.java
 *  Execution:    java RandomizedQueue < input.txt
 *  
 *  Queue implementation with a resizing array and sampling.
 *
 *  % java RandomizedQueue < tobe.txt 
 *  to be or not to be (2 left on queue)
 *
 *************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  The <tt>RandomizedQueue</tt> class represents a first-in-first-out (FIFO)
 *  queue of generic items.
 *  It supports the usual <em>enqueue</em> and <em>dequeue</em>
 *  operations, along with methods for checking the size of the queue,
 *  testing if the queue is empty, and iterating through
 *  the items in FIFO order.
 *  <p>
 *  This implementation uses a resizing array, which double the underlying array
 *  when it is full and halves the underlying array when it is one-quarter full.
 *  <em>sample</em>return (but do not delete) a random item
 *  The <em>enqueue</em> and <em>dequeue</em> operations take constant amortized time.
 *  The <em>size</em>, <em>sampleand</em>, and <em>isEmpty</em> operations takes
 *  constant time in the worst case. 
 *  <p>
 *
 *  @author Muruo Liu
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;            // queue elements
    private int N = 0;           // number of elements on queue
   
    // cast needed since no generic array creation in Java
    public RandomizedQueue() {
        q = (Item[]) new Object[2];
    }
   
    public boolean isEmpty(){
        return N == 0;   
    }
   
    public int size(){
        return N;
    }
   
    // resize the underlying array
    private void resize(int max) {
        assert max >= N;
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = q[i];
        }
        q = temp;
    }
   
    public void enqueue(Item item) {
        if (item == null){
            throw new java.lang.NullPointerException();
        }
        // double size of array if necessary and recopy to front of array
        if (N == q.length) resize(2*q.length);   // double size of array if necessary
        q[N] = item;                        // add item
        N++;
    }
   
    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int index = StdRandom.uniform(N);
        Item item = q[index];
        if (index != N-1){
            q[index] = q[N-1];
        }
        q[N-1] = null;                            // to avoid loitering
        N--;

        if (N > 0 && N == q.length/4) resize(q.length/2);
        return item;
    }
    // return (but do not delete) a random item
    public Item sample(){
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int index = (StdRandom.uniform(N));
        return q[index];
    }
   
    public Iterator<Item> iterator() { return new ArrayIterator(); }

    // an iterator, doesn't implement remove() since it's optional
    private class ArrayIterator implements Iterator<Item> {
        private Item[] tempI = (Item[]) new Object[q.length];
        private int tempN = N;
        public ArrayIterator(){
          for (int j = 0; j < N; j++){
               tempI[j] = q[j];
          }
        }
        public boolean hasNext()  { return tempN != 0;                               }
        public void remove()      { throw new UnsupportedOperationException();  }
       
      
        
       
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            int index = (StdRandom.uniform(tempN));
            Item item = tempI[index];
            if (index != tempN-1){
                tempI[index] = tempI[tempN-1];
            }
            tempI[tempN-1] = null;                            // to avoid loitering
            tempN--;
            return item;
        }
    }

   /**
     * Unit tests the <tt>RandomizedQueue</tt> data type.
     */
    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        String item1 = "aa";
        String item2 = "bb";
        String item3 = "cc";
        int num = 0;
        q.enqueue(item1);
        q.enqueue(item2);
        q.enqueue(item3);
        q.enqueue(item1);
        q.enqueue(item2);
        q.enqueue(item3);        
        StdOut.print(q.dequeue() + " ");
        StdOut.print(q.dequeue() + " ");
        StdOut.print(q.dequeue() + " ");
        StdOut.print(q.dequeue() + " ");
        StdOut.print(q.dequeue() + " ");
        StdOut.print(q.dequeue() + " ");        
    }

}