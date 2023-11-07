package save;
import java.io.*;
import java.util.*;

public class Save {
    private final static String filePath = "src\\save\\SaveFile.txt";
    public static void saveSettings(String toSave) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))){
            bufferedWriter.write(toSave);
            bufferedWriter.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<Integer> recreateSettings() {
        List<Integer> restoredArray = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String consumer = reader.readLine();
            String producer = reader.readLine();
            String storageItems = reader.readLine();

            String[] consumerInterval = consumer.split(","); // Delar upp strängen i enskilda nummer
            String[] producerInterval = producer.split(","); // Delar upp strängen i enskilda nummer

            Arrays.stream(consumerInterval)
                    .mapToInt(Integer::parseInt)
                    .forEach(restoredArray::add);

            restoredArray.add(-1);

            Arrays.stream(producerInterval)
                    .mapToInt(Integer::parseInt)
                    .forEach(restoredArray::add);

            restoredArray.add(-1);

            restoredArray.add(Integer.parseInt(storageItems));




        } catch (IOException e) {
            e.printStackTrace();
        }
        return restoredArray;
    }
}
