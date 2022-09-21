package exercise;

// BEGIN
public class Flat implements Home {
    private double area;
    private double balconyArea;
    private int floor;

    public Flat(double areaValue, double balconyAreaValue, int floorValue) {
        this.area = areaValue;
        this.balconyArea = balconyAreaValue;
        this.floor = floorValue;
    }

    @Override
    public double getArea() {
        return this.area + this.balconyArea;
    }

    @Override
    public int compareTo(Home another) {
        return getArea() > another.getArea() ? 1 : getArea() == another.getArea() ? 0 : -1;
    }

    public String toString() {
        return "Квартира площадью " + getArea() + " метров на " + this.floor + " этаже";
    }

}
// END
