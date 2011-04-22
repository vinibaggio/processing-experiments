
import java.util.Iterator;
import java.util.NoSuchElementException;

// suppress unchecked warnings in Java 1.5.0_6 and later
@SuppressWarnings("unchecked")

public class RingBuffer<Item> implements Iterable<Item> {
    private Item[] a;            // queue elements
    private int capacity = 0;
    private int first = 0;       // index of first element of queue
    private int last  = 0;       // index of next available slot
    private int occupiedSlots = 0;

    // cast needed since no generic array creation in Java
    public RingBuffer(int capacity) {
        this.capacity = capacity;
        a = (Item[]) new Object[capacity];
        occupiedSlots = 0;
    }

    public void put(Item item) {
        a[last] = item;
        last = (last + 1) % a.length;

        if(occupiedSlots < capacity) occupiedSlots++;

        if (last == first){
            first = (first + 1) % a.length;
        }
    }

    public Item getFirst() {
        return a[first];
    }

    public Item getLast() {
        return a[last];
    }

    public int getCapacity() {
        return capacity;
    }

    public Iterator<Item> iterator() { return new RingBufferIterator(); }

    // an iterator, doesn't implement remove() since it's optional
    private class RingBufferIterator implements Iterator<Item> {
        private int currentPosition = first;

        public boolean hasNext() {
            return getNextPosition() != last && occupiedSlots > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            currentPosition = getNextPosition();
            return a[currentPosition];
        }

        private int getNextPosition() {
            return (currentPosition + 1) % a.length;
        }
    }
}
