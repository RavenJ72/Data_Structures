package PriorityQueueInterface;

public class PriorityLinkedListQueueTest {
    public static void main(String[] args) {
        PriorityLinkedListQueue<Integer> priorityQueue = new PriorityLinkedListQueue<>();

        // Добавление элементов в очередь
        int[] elementsToAdd = {15, 10, 20, 17, 25};
        for (int element : elementsToAdd) {
            priorityQueue.add(element);
            System.out.println("Добавлен элемент: " + element);
            System.out.println("Текущее состояние очереди: " + priorityQueue);
        }

        // Просмотр верхнего элемента
        System.out.println("Верхний элемент (peek): " + priorityQueue.peek());

        // Удаление элементов из очереди
        while (priorityQueue.size() > 0) {
            System.out.println("Удаленный элемент (poll): " + priorityQueue.poll());
            System.out.println("Состояние очереди после удаления: " + priorityQueue);
        }
    }
}
