package main;

import model.AverageAmountOfProducers;
import model.Consumer;
import model.Storage;
import view.GUI;
import controller.Controller;
import log.*;

public class Main {
    public static void main(String[] args) {
        Log log = new Log();
        Storage storage = new Storage();
        AverageAmountOfProducers avg = new AverageAmountOfProducers();
        // Producer producer = new Producer(storage);
        Consumer consumer = new Consumer(storage);


        Thread thread = new Thread(avg);
        GUI gui = new GUI();

        // Controller
        Controller controller = new Controller(gui, storage, consumer, log);
        controller.createConsumers();
        controller.addProducer();
        controller.progressBar();
        controller.removeProducer();
        controller.writeToTextArea();
        controller.saveSettings();
        controller.loadSettings();
        thread.start();

    }
}