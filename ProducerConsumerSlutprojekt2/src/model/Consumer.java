package model;

public class Consumer implements Runnable{
    static Storage storage;
    int consumerWorkTime = (int) (Math.floor(Math.random() * 10) + 1) * 1000;
    boolean isRunning = true;
    public Consumer(Storage storage) {
        Consumer.storage = storage;
    }
    public void setConsumerWorkTime(int time) {
        this.consumerWorkTime = time;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                Thread.sleep(consumerWorkTime);
                System.out.println(storage.get());
                System.out.println("Consumed: " + storage.get());
                System.out.println("Time " + consumerWorkTime);
                storage.remove();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public int getWorkTime() {
        return consumerWorkTime;
    }
}
