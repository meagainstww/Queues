/*************************************************************************
 *  Compilation:  javac Subset.java
 *  Execution:    java Subset k
 *  
 *  Reads in a sequence of N strings and prints out exactly k of them, 
 *  uniformly at random.
 *
 *  % echo A B C D E F G H I | java Subset 3
 *  E
 *  F
 *  G
 *
 *************************************************************************/

public class Subset {
    public static void main(String[] args){
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        int k = Integer.valueOf(args[0]);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            q.enqueue(item);
        }
        while (k > 0){
            StdOut.println(q.dequeue());
            k--;
        }
    }
}