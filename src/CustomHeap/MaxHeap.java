package CustomHeap;

import java.util.ArrayList;
import java.util.Arrays;

public class MaxHeap<E extends Comparable<E>> implements Heap<E> {
    private ArrayList<E> heap;

    public MaxHeap() {
        heap = new ArrayList<>();


    }

    public void heapifyDown(int index) {
        int leftChild, rightChild, largestChild;

        while (index < heap.size() / 2) { // Пока у узла есть дети
            leftChild = 2 * index + 1;
            rightChild = 2 * index + 2;
            largestChild = leftChild;

            if (rightChild < heap.size() && heap.get(rightChild).compareTo(heap.get(leftChild)) > 0) {
                largestChild = rightChild;
            }

            if (heap.get(index).compareTo(heap.get(largestChild)) >= 0) {
                break;
            }

            E temp = heap.get(index);
            heap.set(index, heap.get(largestChild));
            heap.set(largestChild, temp);
            index = largestChild;
        }
    }

    public void removeLast() {
        if (!heap.isEmpty()) {
            heap.remove(heap.size() - 1);
        }
    }

    public void setFirst(E element) {
        if (!heap.isEmpty()) {
            heap.set(0, element);
        }
    }

    @Override
    public int size() {
        return heap.size();
    }
    @Override
    public void add(E element) {
        heap.add(element);
        int index = heap.size() - 1;
        E item = heap.get(index);
        while (index > 0 && item.compareTo(heap.get(checkPosition(index))) > 0) {
            heap.set(index, heap.get(checkPosition(index)));
            index = checkPosition(index);
        }
        heap.set(index, item);
    }
    private int checkPosition(int index) {
        return (index - 1) / 2;
    }
    @Override
    public E peek() {
        if (heap.isEmpty()) {
            return null;
        }
        return heap.get(0);
    }


    public String asTree() {
        int height = getHeight();

        int width = (int) Math.pow(2, height) - 1;
        String[][] matrix = new String[height][width];
        for (String[] row : matrix) {
            Arrays.fill(row, " ");
        }

        fillMatrix(0, matrix, 0, 0, width);

        StringBuilder result = new StringBuilder();
        for (String[] row : matrix) {
            for (String cell : row) {
                result.append(cell);
            }
            result.append("\n");
        }

        return result.toString();
    }

    public E get(int index){
        return heap.get(index);
    }

    public boolean isEmpty(){
        return heap.isEmpty();
    }

    private void fillMatrix(int index, String[][] matrix, int row, int left, int right) {
        if (index >= heap.size()) {
            return;
        }

        int mid = (left + right) / 2;
        matrix[row][mid] = heap.get(index).toString();

        if (row + 1 < matrix.length) {
            fillMatrix(2 * index + 1, matrix, row + 1, left, mid);
            fillMatrix(2 * index + 2, matrix, row + 1, mid, right);
        }
    }

    private int getHeight() {
        return (int) (Math.log(heap.size()) / Math.log(2)) + 1;
    }

    public String toString() {
        if (heap.isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < heap.size(); i++) {
            sb.append(heap.get(i));
            if (i < heap.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }





}

