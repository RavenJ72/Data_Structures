package PriorityQueueInterface;

public class MaxPriorityQueueTest {
    public static void main(String[] args) {
        MaxPriorityQueue<Integer> priorityQueue = new MaxPriorityQueue<>();

        // Добавление элементов в очередь
        long currentTime = System.currentTimeMillis();
        // Добавление элементов в очередь

        for (int i = 0; i < 100_000; i++) {
            priorityQueue.add(i);
        }
        System.out.println(System.currentTimeMillis()-currentTime + "мс");

    }
}
