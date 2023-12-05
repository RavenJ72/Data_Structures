package CustomHeap;

public class MaxHeapTest {
    public static void main(String[] args) {
        MaxHeap<Integer> maxHeap = new MaxHeap<>();

        // Добавление элементов в кучу
        int[] elementsToAdd = {15, 10, 20, 17, 25};
        for (int element : elementsToAdd) {
            maxHeap.add(element);
            System.out.println("Добавлен элемент: " + element);
            System.out.println("Текущее состояние кучи: ");
            System.out.println(maxHeap.asTree());
        }

        // Просмотр верхнего элемента
        System.out.println("Верхний элемент (peek): " + maxHeap.peek());

        // Удаление последнего элемента и проверка свойства кучи
        System.out.println("Удаление последнего элемента.");
        maxHeap.removeLast();

        System.out.println("Состояние кучи после удаления: ");
        System.out.println(maxHeap.asTree());


    }
}
