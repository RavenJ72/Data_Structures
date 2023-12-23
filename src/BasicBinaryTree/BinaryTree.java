package BasicBinaryTree;

import java.util.*;
import java.util.function.Consumer;

public class BinaryTree<E> implements AbstractBinaryTree<E> {
    private E key;
    private AbstractBinaryTree<E> left;
    private AbstractBinaryTree<E> right;

    public BinaryTree(E key) {
        this.key = key;
        this.left = null;
        this.right = null;
    }

    public BinaryTree(E key, AbstractBinaryTree<E> left, AbstractBinaryTree<E> right) {
        this.key = key;
        this.left = left;
        this.right = right;

    }

    @Override
    public E getKey() {
        return key;
    }

    @Override
    public AbstractBinaryTree<E> getLeft() {
        return left;
    }

    @Override
    public AbstractBinaryTree<E> getRight() {
        return right;
    }

    @Override
    public void setKey(E key) {
        this.key = key;
    }

    @Override
    public String asIndentedPreOrder(int indent) {
        StringBuilder result = new StringBuilder();

        if (indent > 0) {
            //символ для отрисовки
            String branchSymbol = (indent % 2 == 0) ? "├" : "└";

            result.append("  ".repeat(indent - 1));
            result.append(branchSymbol).append(getKey()).append("\n");
        } else {
            result.append(getKey()).append("\n");
        }

        if (getLeft() != null || getRight() != null) {
            if (getLeft() != null) {
                result.append(getLeft().asIndentedPreOrder(indent + 1));
            }
            if (getRight() != null) {
                result.append(getRight().asIndentedPreOrder(indent + 1));
            }
        }

        return result.toString();
    }


    @Override
    public List<AbstractBinaryTree<E>> preOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        result.add(this);
        if (getLeft() != null) {
            result.addAll(getLeft().preOrder());
        }
        if (getRight() != null) {
            result.addAll(getRight().preOrder());
        }
        return result;
    }

    @Override
    public List<AbstractBinaryTree<E>> inOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        if (getLeft() != null) {
            result.addAll(getLeft().inOrder());
        }
        result.add(this);
        if (getRight() != null) {
            result.addAll(getRight().inOrder());
        }
        return result;
    }

    @Override
    public List<AbstractBinaryTree<E>> postOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        if (getLeft() != null) {
            result.addAll(getLeft().postOrder());
        }
        if (getRight() != null) {
            result.addAll(getRight().postOrder());
        }
        result.add(this);
        return result;
    }

    @Override
    public void forEachInOrder(Consumer<E> consumer) {
        if (getLeft() != null) {
            getLeft().forEachInOrder(consumer);
        }
        consumer.accept(getKey());
        if (getRight() != null) {
            getRight().forEachInOrder(consumer);
        }
    }

    @Override
    public List<AbstractBinaryTree<E>> breadthFirst() {
        ArrayList<AbstractBinaryTree<E>> result = new ArrayList<>();
        Deque<AbstractBinaryTree<E>> deque = new ArrayDeque<>();
        deque.add(this);
        while (!deque.isEmpty()){
            AbstractBinaryTree<E> currentTree = deque.poll();
            result.add(currentTree);
            if(currentTree.getLeft()!=null){
                deque.add(currentTree.getLeft());
            }
            if(currentTree.getRight()!=null){
                deque.add(currentTree.getRight());
            }

        }
        return result;
    }

    @Override
    public String asMatrixTree() {

        int height = getHeight(this);


        int width = (int) Math.pow(2, height + 1) - 1;


        String[][] matrix = new String[height][width];


        for (String[] row : matrix) {
            Arrays.fill(row, " ");
        }


        fillMatrix(this, matrix, 0, 0, width/2);


        StringBuilder result = new StringBuilder();
        for (String[] row : matrix) {
            for (String cell : row) {
                result.append(cell);
            }
            result.append("\n");
        }

        return result.toString();
    }

    private void fillMatrix(AbstractBinaryTree<E> node, String[][] matrix, int row, int left, int right) {
        if (node == null) {
            return;
        }
        int mid = (left + right) / 2;

        matrix[row][mid] = node.getKey().toString();

        fillMatrix(node.getLeft(), matrix, row + 1, left, mid - 1);
        fillMatrix(node.getRight(), matrix, row + 1, mid + 1, right);
    }

    private int getHeight(AbstractBinaryTree<E> node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getHeight(node.getLeft());
        int rightHeight = getHeight(node.getRight());
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
