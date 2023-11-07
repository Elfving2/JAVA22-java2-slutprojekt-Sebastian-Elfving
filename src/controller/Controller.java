package controller;
import model.Consumer;
import model.Producer;
import model.Storage;
import save.Save;
import view.GUI;
import javax.swing.*;
import log.Log;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
public class Controller  {
    GUI gui;
    Storage storage;
    Consumer consumer;
    Log log;
    private final ArrayList<Producer> producerArrayList = new ArrayList<>();
    public final ArrayList<Consumer> consumerArrayList = new ArrayList<>();
    public Controller(GUI gui, Storage storage, Consumer consumer, Log log) {
        this.gui =  gui;
        this.storage = storage;
        this.consumer = consumer;
        this.log = log;
    }
    public void loadSettings() {
        gui.loadButton.addActionListener(e -> {
            restoreSettings();
        });
    }
    public void saveSettings() {
        gui.saveButton.addActionListener(e -> {
            StringBuilder consumer = new StringBuilder();
            StringBuilder producer = new StringBuilder();

            for (Consumer c : consumerArrayList) {
                System.out.println(c.getWorkTime());
                consumer.append(c.getWorkTime()).append(",");
            }
            // create getter and setter later
            for (Producer c : producerArrayList) {
                producer.append(c.productionWorkTime).append(",");
            }
            Save.saveSettings(
                    consumer +"\n" + producer + "\n" + storage.getCapacityPercent());
        });
    }
    public void removeProducer() {
        gui.removeWorkerButton.addActionListener(e -> Producer.remove(producerArrayList));
    }
    public void addProducer() {
        gui.addWorkerButton.addActionListener(e -> Producer.createProducer(producerArrayList));
    }
    public void writeToTextArea() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> Log.checkFileContent(gui), 0, 1, TimeUnit.MILLISECONDS);
    }
    public void createConsumers() {
        int amountOfConsumers = (int)(Math.random() * (15 - 3)) + 3;
        for (int i = 0; i < amountOfConsumers; i++) {
            Consumer consumer =  new Consumer(storage);
            Thread t = new Thread(consumer);
            t.start();
            consumerArrayList.add(consumer);
            System.out.println(consumerArrayList);
        }
    }
    public void restoreSettings() {
        consumerArrayList.clear();
        producerArrayList.clear();
        ArrayList<Integer> list = (ArrayList<Integer>) Save.recreateSettings();
        int i = 0;

        for (; i < list.size(); i++) {

            if (list.get(i) == -1) {
                i += 1;
                break;
            }

            Consumer consumer = new Consumer(storage);
            consumer.setConsumerWorkTime(list.get(i));
            Thread t = new Thread(consumer);
            t.start();
            consumerArrayList.add(consumer);
            System.out.println(consumerArrayList);
        }

        for (; i < list.size(); i++) {

            if (list.get(i) == -1 ) {
                break;
            }

            Producer producer = new Producer();
            producer.setProducerWorkTime(list.get(i));
            Thread t = new Thread(producer);
            t.start();
            producerArrayList.add(producer);
            System.out.println(producerArrayList);
        }
        storage.restoreStorage(list.get(list.size() -1));
    }
    public void progressBar() {
        Timer timer = new Timer(1000, e -> {

            if (storage.getCapacityPercent() >= 90) {
                Log.write("Production is to high!");
                gui.progressBar.setForeground(Color.GREEN);

            } else if(storage.getCapacityPercent() <= 10) {
                Log.write("Production is to Low!");
                gui.progressBar.setForeground(Color.RED);

            } else {
                float[] hsb = Color.RGBtoHSB(163, 184, 204, null);
                gui.progressBar.setForeground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
            }
            gui.progressBar.setValue(Storage.storage.size());
            gui.progressBar.repaint();
        });
        timer.start();
    }
}
