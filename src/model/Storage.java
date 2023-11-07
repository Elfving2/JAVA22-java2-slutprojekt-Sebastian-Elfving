package model;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Storage {
    public static Queue<Item> storage = new ArrayBlockingQueue<>(100); // size of "array"

    public void add(Item item) {
        storage.offer(item);
    }

    public void restoreStorage(int size) {
        System.out.println("SIZE : " + size);
        // reset storage
        storage = new ArrayBlockingQueue<>(100);
        for (int i = 0; i < size; i++) {
            storage.add(new Item(String.valueOf(i)));
        }
    }

    public void remove() {
        storage.poll();
    }
    // get last "Head" item from storage
    public String get() {
        if(!(storage.peek() == null)) {
            return storage.peek().toString();
        }
        return null;
    }
    public int getCapacityPercent() {
        return storage.size();
    }

}
