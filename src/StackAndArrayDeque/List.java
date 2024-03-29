package StackAndArrayDeque;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class List<T> implements Iterable<T> {
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
                        // Узел в середине списка
                        currentNode.getPrevNode().setNextNode(currentNode.getNextNode());
                        currentNode.getNextNode().setPrevNode(currentNode.getPrevNode());
                    } else if (currentNode.getPrevNode() == null) {
                        // Удаляем первый узел
                        start = currentNode.getNextNode();
                        if (start != null) {
                            start.setPrevNode(null);
                        }
                    } else if (currentNode.getNextNode() == null) {
                        // Удаляем последний узел
                        end = currentNode.getPrevNode();
                        if (end != null) {
                            end.setNextNode(null);
                        }
                    }
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

    public void addAll(List<T> elements){
        for(T data : elements){
            this.addLast(data);
        }
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




    public void sort(Comparator<T> comparator) {
        if (size > 1) {
            start = mergeSort(start, comparator);
        }
    }

    private Node<T> mergeSort(Node<T> head, Comparator<T> comparator) {
        if (head == null || head.getNextNode() == null) {
            return head;
        }

        Node<T> middle = getMiddle(head);
        Node<T> nextOfMiddle = middle.getNextNode();

        middle.setNextNode(null);

        Node<T> left = mergeSort(head, comparator);
        Node<T> right = mergeSort(nextOfMiddle, comparator);

        return sortedMerge(left, right, comparator);
    }

    private Node<T> sortedMerge(Node<T> a, Node<T> b, Comparator<T> comparator) {
        Node<T> result;
        if (a == null) return b;
        if (b == null) return a;

        if (comparator.compare(a.getData(), b.getData()) <= 0) {
            result = a;
            result.setNextNode(sortedMerge(a.getNextNode(), b, comparator));
        } else {
            result = b;
            result.setNextNode(sortedMerge(a, b.getNextNode(), comparator));
        }
        return result;
    }

    private Node<T> getMiddle(Node<T> head) {
        if (head == null) {
            return head;
        }
        Node<T> slow = head, fast = head;

        while (fast.getNextNode() != null && fast.getNextNode().getNextNode() != null) {
            slow = slow.getNextNode();
            fast = fast.getNextNode().getNextNode();
        }
        return slow;
    }


}
