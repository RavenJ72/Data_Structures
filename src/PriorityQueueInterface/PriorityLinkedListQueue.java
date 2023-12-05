package PriorityQueueInterface;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class PriorityLinkedListQueue<E extends Comparable<E>> implements AbstractQueue<E> {
    private LinkedList<E> list;

    public PriorityLinkedListQueue() {
        list = new LinkedList<>();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(E element) {
        int i = 0;
        for (E item : list) {
            if (element.compareTo(item) > 0) {
                break;
            }
            i++;
        }
        list.add(i, element);
    }

    @Override
    public E peek() {
        return list.peek();
    }

    @Override
    public E poll() {
        return list.poll();
    }

    @Override
    public String toString() {
        return "PriorityLinkedListQueue{" +
                "list=" + list.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", ")) +
                '}';
    }
}
