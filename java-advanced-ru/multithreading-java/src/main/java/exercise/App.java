package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    public static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numbers) {
        Map<String, Integer> result = new HashMap<>();
        MaxThread maxThread = new MaxThread(numbers);
        MinThread minThread = new MinThread(numbers);

        maxThread.start();
        minThread.start();

        try {
            maxThread.join();
        } catch (InterruptedException e) {
            System.out.println("Поток maxThread был прерван");
        }
        result.put("max", maxThread.getMax());

        try {
            minThread.join();
        } catch (InterruptedException e) {
            System.out.println("Поток minThread был прерван");
        }
        result.put("min", minThread.getMin());

        return result;
    }
    // END
}
