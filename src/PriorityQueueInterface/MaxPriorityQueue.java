package PriorityQueueInterface;

import CustomHeap.MaxHeap;

public class MaxPriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {
    private MaxHeap<E> maxHeap;

    public MaxPriorityQueue() {
        maxHeap = new MaxHeap<>();
    }

    @Override
    public int size() {
        return maxHeap.size();
    }

    @Override
    public void add(E element) {

        maxHeap.add(element);
    }

    @Override
    public E peek() {
        return maxHeap.peek();
    }

    @Override
    public E poll() {
        if (maxHeap.isEmpty()) {
            return null;
        }
        E result = maxHeap.peek();
        E lastElement = maxHeap.get(maxHeap.size() - 1);
        maxHeap.setFirst(lastElement);
        maxHeap.removeLast();
        if (!maxHeap.isEmpty()) {
            maxHeap.heapifyDown(0);
        }

        return result;
    }

    @Override
    public String toString() {
        return maxHeap.asTree();
    }
}
