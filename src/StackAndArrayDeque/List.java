package StackAndArrayDeque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class List<T> implements Iterable<T>{
    private Node start;
    private Node end;
    private int size;
    public List() {
        this.start = null;
        this.end = null;
        size = 0;
    }
    public boolean isEmpty() {return start == null;}
    public int length() {return size;}

    public boolean remove(T data) {
        if (!isEmpty()) {
            Node<T> currentNode = start;
            while (currentNode != null) {
                if (currentNode.getData().equals(data)) {
                    if (currentNode.getPrevNode() != null && currentNode.getNextNode() != null) {
                        currentNode.getPrevNode().setNextNode(currentNode.getNextNode());
                        currentNode.getNextNode().setPrevNode(currentNode.getPrevNode());
                    } else if (currentNode.getPrevNode() == null) {
                        currentNode.getNextNode().setPrevNode(null);
                    } else if (currentNode.getNextNode() == null) {
                        currentNode.getPrevNode().setNextNode(null);
                    }
                    currentNode.setPrevNode(null);
                    currentNode.setNextNode(null);
                    size--;
                    return true;
                }
                currentNode = currentNode.getNextNode();
            }
        }
        return false;
    }
    public T findByField(String fieldName, Object value) {
        Node<T> current = start;
        while (current != null) {
            try {
                java.lang.reflect.Field field = current.getData().getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Object fieldValue = field.get(current.getData());
                if (fieldValue != null && fieldValue.equals(value)) {
                    return current.getData();
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
            current = current.getNextNode();
        }
        return null;
    }
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Node<T> current = start;
        for (int i = 0; i < index; i++) {
            current = current.getNextNode();
        }
        return current.getData();
    }
    public T addFirst(T data) {
        if (isEmpty()) {
            Node<T> newListStart = new Node<T>(data, null, null);
            setStart(newListStart);
            setEnd(newListStart);
        } else {
            Node<T> newListStart = new Node<T>(data, null, start);
            start.setPrevNode(newListStart);
            setStart(newListStart);
        }
        size++;
        return data;
    }
    public T addLast(T data) {
        if (isEmpty()) {
            Node<T> newListStart = new Node<T>(data, null, null);
            setStart(newListStart);
            setEnd(newListStart);
        } else {
            Node<T> newListEnd = new Node<T>(data, end, null);
            end.setNextNode(newListEnd);
            setEnd(newListEnd);
        }
        size++;
        return data;
    }
    public T peekFirst() {
        if (isEmpty()) {
            return null;
        } else {
            return (T) getStart().getData();
        }
    }
    public T peekLast() {
        if (isEmpty()) {
            return null;
        } else {
            return (T) getEnd().getData();
        }
    }
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            T removedData = (T) getStart().getData();
            if (getStart().getNextNode() == null) {
                setStart(null);
                setEnd(null);
            } else {
                Node newStart = getStart().getNextNode();
                setStart(newStart);
            }
            size--;
            return removedData;
        }
    }
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            T removedData = (T) getEnd().getData();

            if (getEnd().getPrevNode() == null) {
                setStart(null);
                setEnd(null);
            } else {
                Node newEnd = getEnd().getPrevNode();
                getEnd().setNextNode(null);
                getEnd().setPrevNode(null);
                setEnd(newEnd);
                getEnd().setNextNode(null);
            }
            size--;
            return removedData;
        }
    }
    public void clear() {
        setStart(null);
        setEnd(null);
        size = 0;}
    private void setStart(Node start) {
        this.start = start;
    }
    private void setEnd(Node end) {
        this.end = end;
    }
    private Node getStart() {
        return start;
    }
    private Node getEnd() {
        return end;
    }
    public void display() {
        Node<T> current = start;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNextNode();
        }
    }

    // Внутренний класс для реализации итератора
    private class ListIterator implements Iterator<T> {
        private Node<T> current = start;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.getData();
            current = current.getNextNode();
            return data;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }
}
