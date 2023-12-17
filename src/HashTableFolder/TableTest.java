package HashTableFolder;

public class TableTest {
    public static void main(String[] args) {


        HashTable<String, Integer> hashTable = new HashTable<>();
        for (int i = 0; i < 100_000; i++) {
            hashTable.add(String.valueOf(i),i);
        }


        System.out.println(hashTable.collisions);


        System.out.println(hashTable.countFinalCollisions());
        System.out.println(hashTable.loadFactor());

    }
}
