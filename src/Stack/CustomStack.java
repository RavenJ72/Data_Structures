package Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomStack<T> implements Iterable<T> {
    private Object[] data;
    private int size;
    private int capacity;

    public CustomStack(int capacity) {
        this.capacity = capacity;
        this.data = new Object[capacity];
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public int size() {
        return size;
    }

    public void push(T item) {
        if (size == capacity) {

            increaseCapacity();
        }
        data[size] = item;
        size++;
    }


    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Стек пуст");
        }
        size--;
        T item = (T) data[size];
        data[size] = null;
        return item;
    }


    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Стек пуст");
        }
        return (T) data[size - 1];
    }


    private void increaseCapacity() {
        int newCapacity = capacity * 2;
        Object[] newData = new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
        capacity = newCapacity;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = size - 1;

            @Override
            public boolean hasNext() {
                return currentIndex >= 0;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) data[currentIndex--];
            }
        };
    }
}
