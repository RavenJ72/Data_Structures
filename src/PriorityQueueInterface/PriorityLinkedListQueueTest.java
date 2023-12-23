package PriorityQueueInterface;

public class PriorityLinkedListQueueTest {
    public static void main(String[] args) {
        PriorityLinkedListQueue<Integer> priorityQueue = new PriorityLinkedListQueue<>();


        long currentTime = System.currentTimeMillis();
        // Добавление элементов в очередь

        for (int i = 0; i < 100_000; i++) {
            priorityQueue.add(i);
        }
        System.out.println(System.currentTimeMillis()-currentTime + "мс");

    }
}
