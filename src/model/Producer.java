package model;
import log.Log;
import java.util.ArrayList;
public class Producer implements Runnable {
    static Storage storage = new Storage();
    boolean isRunning = true;
    public int productionWorkTime;
    public static double totalAmountOfCreatedProducts = 0;
    public static int produceWorkTime() {
        return (int) (Math.floor(Math.random() * 10) + 1) * 1000;
    }
    public void setProducerWorkTime(int time) {
        this.productionWorkTime = time;
    }
    @Override
    public void run() {
        while (isRunning) {
            try {
                Thread.sleep(productionWorkTime);
                int random = (int) Math.floor(Math.random() * 101);
                totalAmountOfCreatedProducts++;
                storage.add(new Item(String.valueOf(random)));
            } catch (InterruptedException e) {
                isRunning = false;
                System.out.println("Removed");
            }
        }
    }
    public static void createProducer(ArrayList<Producer> arrayList) {
        Producer p = new Producer();
        p.productionWorkTime = produceWorkTime();
        Thread t = new Thread(p);
        t.start();
        arrayList.add(p);
        Log.write("Created producer : " + t.getName() + " production interval: " + p.productionWorkTime + " Amount of workers: " + arrayList.size());
        //System.out.println("Created producer : " + t.getName() + " production interval: " + p.productionWorkTime + " Amount of workers: " + arrayList.size());
    }
    public static void remove(ArrayList<Producer> arrayList) {
        if (!arrayList.isEmpty()) {
            arrayList.get(0).isRunning = false;
            Producer p = arrayList.remove(0);
            Log.write("producer removed  : " + p.productionWorkTime + "  Amount of workers: " + arrayList.size());
           // System.out.println("updated list: " + arrayList.size());
        }
    }
}
