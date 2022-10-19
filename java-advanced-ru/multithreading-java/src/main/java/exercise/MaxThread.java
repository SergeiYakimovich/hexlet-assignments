package exercise;

import java.util.Arrays;
import java.util.logging.Level;

import static exercise.App.LOGGER;

// BEGIN
public class MaxThread extends Thread {
    int[] numbers;
    int max;

    public MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    public int getMax() {
        return max;
    }

    // В классе нужно переопределить метод run()
    // В методе содержится логика, которую поток будет выполнять
    @Override
    public void run() {
        LOGGER.log(Level.INFO, getName() + " started");
        Arrays.sort(numbers);
        max = numbers[numbers.length - 1];
        LOGGER.log(Level.INFO, getName() + " finished");
    }

}
// END
