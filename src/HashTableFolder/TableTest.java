package HashTableFolder;

public class TableTest {
    public static void main(String[] args) {

        System.out.println("Добавим 100к элементов");
        HashTable<String, Integer> hashTable = new HashTable<>();
        for (int i = 0; i < 100_000; i++) {
            hashTable.add(String.valueOf(i),i);
        }
        System.out.println("Кол-во коллизий в списке за все время");
        System.out.println(hashTable.collisions);

        System.out.println("Кол-во коллизий в списке на данный момент");
        System.out.println(hashTable.countFinalCollisions());
        System.out.println("Коэффициент загрузки на данный момент в HashTable");
        System.out.println(hashTable.loadFactor());

    }
}
