import java.util.Iterator;
import java.util.NoSuchElementException;
public class Deque<Item> implements Iterable<Item> {

    private int count = 0;

    private class LinkedList {
        LinkedList prev;
        Item data;        
        LinkedList next;
    }
    private LinkedList first, last;

    public Deque() {

    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return count;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        LinkedList newFirst = first;
        first = new LinkedList();
        first.data = item;
        first.prev = null;
        count++;
        first.next = newFirst;
        if (last == null) {  
            last = first;
        } 
        else if (newFirst == null) {
            newFirst = first;
        }
        else {
            newFirst.prev = first;
        }
    }

    public void addLast(Item item) {
        if (item == null) { 
            throw new IllegalArgumentException();
        }
        LinkedList oldLast = last;
        last = new LinkedList();
        last.data = item;
        last.next = null;
        count++;
        last.prev = oldLast;
        if (isEmpty()) {
            first = last;
        } 
        else if (oldLast == null) {
            oldLast = last;
        }
        else{
            oldLast.next = last;
        }
    }

    public Item removeFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        count--;
        Item temp = (Item) first.data;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        else {
            first.prev = null;
        
        }
        return temp;
    }

    public Item removeLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        count--;
        Item temp = (Item) last.data;
        last = last.prev;
        if (last == null) {
            first = null;
        }
        else {
            last.next = null;
        }
        return temp;
    }

    public Iterator<Item> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<Item> {
        
        private LinkedList mylist = first;

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item temp = mylist.data;
            mylist = mylist.next;
            return temp;
        }
        public void remove() {
            throw new UnsupportedOperationException();
            // use at your own risk
        }
        public boolean hasNext() {
            return mylist != null;
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
     

        Deque<Integer> queue = new Deque<>();
        final int size = 1000;
        for (int i = 0; i < size; i++) {
            queue.addFirst(i);
        }
        System.out.println(size + " = " + queue.size());
        Iterator<?> iterator = queue.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        System.out.println(queue.size() + " = " + count);
        for (int i = 0; i < size; i++) {
            queue.removeLast();
        }
        System.out.println("0 = " + queue.size());
    
    }
}