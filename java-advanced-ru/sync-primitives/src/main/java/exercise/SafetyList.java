package exercise;

import java.util.Arrays;

class SafetyList {
    // BEGIN
    private Integer[] mas = new Integer[0];

    public synchronized void add(Integer n) {
        mas = Arrays.copyOf(mas, mas.length + 1);
        mas[mas.length - 1] = n;
    }

    public Integer get(Integer i) {
        return mas[i];
    }

    public Integer getSize() {
        return mas.length;
    }

    public Integer[] getMas() {
        return mas;
    }
    // END
}
