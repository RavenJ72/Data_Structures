package HashTableFolder;

import StackAndArrayDeque.List;



import java.util.Iterator;

public class MultiMap<K, V> implements Iterable<KeyValue<K, List<V>>> {
    private static final int INITIAL_CAPACITY = 16;
    public static final double LOAD_FACTOR = 0.80d;
    private List<KeyValue<K, List<V>>>[] slots;

    private int count;
    private int capacity;
    public int collisions;

    // Конструктор без параметров
    public MultiMap() {
        this(INITIAL_CAPACITY);
    }

    // Конструктор с указанной емкостью
    public MultiMap(int capacity) {
        this.capacity = capacity;
        this.slots = new List[capacity];
        for (int i = 0; i < capacity; i++) {
            this.slots[i] = new List<>();
        }
        this.count = 0;
    }

    public void add(K key, V value) {
        this.growIfNeeded();
        int slotNumber = this.findSlotNumber(key);
        List<KeyValue<K, List<V>>> slot = this.slots[slotNumber];

        for (KeyValue<K, List<V>> keyValue : slot) {
            if (keyValue.getKey().equals(key)) {
                keyValue.getValue().addLast(value);
                this.count++;
                return;
            }
        }

        // Если слот не пустой, значит произошла коллизия
        if (!slot.isEmpty()) {
            collisions++;
        }

        List<V> newValuesList = new List<>();
        newValuesList.addLast(value);
        slot.addLast(new KeyValue<>(key, newValuesList));
        this.count++;
    }

    public List<V> getValues(K key) {
        int slotNumber = this.findSlotNumber(key);

        List<KeyValue<K, List<V>>> slot = this.slots[slotNumber];

        for (KeyValue<K, List<V>> keyValue : slot) {
            if (keyValue.getKey().equals(key)) {
                return keyValue.getValue();
            }
        }
        return null;
    }

    public List<V> getValues(){
        List<V> values = new List<>();
        for(List<KeyValue<K, List<V>>> slot : slots){
            for (KeyValue<K, List<V>> keyValue : slot) {
                values.addAll(keyValue.getValue());
            }
        }
        return values;
    }

    // Методы для вычисления номера слота и проверки необходимости роста хеш-таблицы
    private int findSlotNumber(K key) {
        return Math.abs(key.hashCode()) % this.capacity;
    }

    private void growIfNeeded() {
        if ((double) (this.size() + 1) / this.capacity > LOAD_FACTOR) {
            this.grow();
        }
    }

    // Метод для увеличения размера хеш-таблицы
    private void grow() {
        MultiMap<K, V> newTable = new MultiMap<>(this.capacity * 2);
        for (List<KeyValue<K, List<V>>> slot : this.slots) {
            for (KeyValue<K, List<V>> keyValue : slot) {
                for (V value : keyValue.getValue()) {
                    newTable.add(keyValue.getKey(), value);
                }
            }
        }
        this.slots = newTable.slots;
        this.capacity = newTable.capacity;
        this.collisions = newTable.collisions; // Обновление счетчика коллизий
    }



    public int size() {
        return this.count;
    }

    public boolean containsKey(K key) {
        return getValues(key) != null;
    }

    public boolean remove(K key) {
        int slotNumber = this.findSlotNumber(key);
        List<KeyValue<K, List<V>>> slot = this.slots[slotNumber];

        for (KeyValue<K, List<V>> keyValue : slot) {
            if (keyValue.getKey().equals(key)) {
                slot.remove(keyValue);
                this.count--;
                return true;
            }
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < this.capacity; i++) {
            this.slots[i].clear();
        }
        this.count = 0;
    }

    public Iterable<K> keys() {
        List<K> keysList = new List<>();
        for (List<KeyValue<K, List<V>>> slot : this.slots) {
            for (KeyValue<K, List<V>> keyValue : slot) {
                keysList.addLast(keyValue.getKey());
            }
        }
        return keysList;
    }

    public Iterable<List<V>> values() {
        List<List<V>> valuesList = new List<>();
        for (List<KeyValue<K, List<V>>> slot : this.slots) {
            for (KeyValue<K, List<V>> keyValue : slot) {
                valuesList.addLast(keyValue.getValue());
            }
        }
        return valuesList;
    }

    public double loadFactor() {
        return (double) count / capacity;
    }

    // Итератор для обхода всех элементов хеш-таблицы
    @Override
    public Iterator<KeyValue<K, List<V>>> iterator() {
        return new Iterator<KeyValue<K, List<V>>>() {
            private int currentSlot = 0;
            private Iterator<KeyValue<K, List<V>>> slotIterator = slots[0].iterator();

            @Override
            public boolean hasNext() {
                if (slotIterator.hasNext()) {
                    return true;
                }
                while (++currentSlot < capacity) {
                    slotIterator = slots[currentSlot].iterator();
                    if (slotIterator.hasNext()) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public KeyValue<K, List<V>> next() {
                return slotIterator.next();
            }
        };
    }
}
