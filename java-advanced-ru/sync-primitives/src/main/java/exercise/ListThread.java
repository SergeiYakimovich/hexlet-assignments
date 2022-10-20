package exercise;

import java.util.Arrays;

// BEGIN
public class ListThread extends Thread {

    // Разделяемый ресурс
    SafetyList safetyList;

    ListThread(SafetyList safetyList) {
        this.safetyList = safetyList;
    }

    // Метод увеличивает счетчик на 1
    @Override
    public void run() {
        for(int i = 0; i < 1000; i++ ) {
            try {
                Thread.currentThread().sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            safetyList.add(i);
        }
    }
}
// END
