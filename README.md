Queues
======

Coursera: Algorithms, Part I, Princeton University---Programming Assignments, Week 1
------------------------------------------------------------------------------------

### 1. classpath stdlib.jar

### 2. Deque.java creates double-ended queue that supports inserting and removing items from 
    either the front or the back of the data structure. It implements the following API:
    public class Deque<Item> implements Iterable<Item> {
        public Deque()                           // construct an empty deque
        public boolean isEmpty()                 // is the deque empty?
        public int size()                        // return the number of items on the deque
        public void addFirst(Item item)          // insert the item at the front
        public void addLast(Item item)           // insert the item at the end
        public Item removeFirst()                // delete and return the item at the front
        public Item removeLast()                 // delete and return the item at the end
        public Iterator<Item> iterator()         // return an iterator over items in order from front to end
        public static void main(String[] args)   // unit testing
    }
    In this implement, please pay attention to Node.previous and Node.next, for when adding from the front, 
    besides setting first.previous as null, we also need to assign first.next according to different condition. 
    When the queue is empty, first.next is null, meanwhile last equals to first. However, when the queue is 
    not empty, first.next is oldfirst, oldfirst.previous is first. If we don't consider these condition 
    seperately, nullPointerException may occur.
    
### 3. RandomizedQueue.java creates a queue that the item removed is chosen uniformly at random from items in 
    the data structure. It implements the following API:
    public class RandomizedQueue<Item> implements Iterable<Item> {
        public RandomizedQueue()                 // construct an empty randomized queue
        public boolean isEmpty()                 // is the queue empty?
        public int size()                        // return the number of items on the queue
        public void enqueue(Item item)           // add the item
        public Item dequeue()                    // delete and return a random item
        public Item sample()                     // return (but do not delete) a random item
        public Iterator<Item> iterator()         // return an independent iterator over items in random order
        public static void main(String[] args)   // unit testing
    }
    In RandomizedQueue.java，since items are chosen randomly, to perform this in constant amortized time,we
    need to use array rather than linklist (use linear time to search for an item randomly). Since dequeue()
    choose items randomly, after choosing an item to delete, we can assign the value of the last item to the
    deleted location and then assign the last to null. In this case, we don't need to move a part of the queue
    and meanwhile realize randomly dequeue.
    
### 4. Subset.java. It is a client program that takes a command-line integer k; reads in a sequence of N strings 
    from standard input using StdIn.readString(); and prints out exactly k of them, uniformly at random. Each 
    item from the sequence can be printed out at most once. This client uses RandomizedQueue.dequeue() to realize 
    its function.
    
### 5. [More specific details](http://blog.sina.com.cn/s/blog_73b055450101j8hf.html)
