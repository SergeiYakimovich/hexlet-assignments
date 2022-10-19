package exercise;

import java.util.Arrays;
import java.util.logging.Level;

import static exercise.App.LOGGER;

// BEGIN
public class MinThread extends Thread {
    int[] numbers;
    int min;

    public MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    public int getMin() {
        return min;
    }

    // В классе нужно переопределить метод run()
    // В методе содержится логика, которую поток будет выполнять
    @Override
    public void run() {
        LOGGER.log(Level.INFO, getName() + " started");
        Arrays.sort(numbers);
        min = numbers[0];
        LOGGER.log(Level.INFO, getName() + " finished");
    }

}
// END

