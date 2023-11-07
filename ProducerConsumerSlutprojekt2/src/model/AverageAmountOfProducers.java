package model;
import log.*;
public class AverageAmountOfProducers implements Runnable {
    @Override
    public void run() {
        get();
    }
    public void get() {
        while (true) {
            try {
                Thread.sleep(10000);
                Log.write("average amount of units " + (Producer.totalAmountOfCreatedProducts / 10));
                Producer.totalAmountOfCreatedProducts = 0;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
