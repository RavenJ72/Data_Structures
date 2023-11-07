import BasicBinaryTree.BinaryTree;
import CustomBinarySearchTree.BinarySearchTree;
import CustomBinarySearchTree.MaxPathSum;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {


        BinaryTree<Integer> tree = new BinaryTree<>(
                4,
                new BinaryTree<>(2,
                        new BinaryTree<>(1),
                        new BinaryTree<>(3)
                ),
                new BinaryTree<>(6,
                        new BinaryTree<>(5),
                        new BinaryTree<>(7)
                )
        );

//        System.out.println(tree.asMatrixTree());
//
//        tree.inOrder().stream().forEach(e-> System.out.print(e.getKey() + " "));
//        System.out.println();
//        tree.preOrder().stream().forEach(e-> System.out.print(e.getKey() + " "));
//        System.out.println();
//        tree.postOrder().stream().forEach(e-> System.out.print(e.getKey() + " "));
//        System.out.println();
//        tree.breadthFirst().stream().forEach(e-> System.out.print(e.getKey() + " "));




        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        binarySearchTree.insert(10);
        binarySearchTree.insert(2);
        binarySearchTree.insert(12);
        binarySearchTree.insert(1);
        binarySearchTree.insert(3);
        binarySearchTree.insert(4);




        // Выводим структуру дерева
        System.out.println("Структура дерева:");
        System.out.println(binarySearchTree.asTree());

        System.out.println(new MaxPathSum().maxPathSum(binarySearchTree.getRoot()));


    }
}