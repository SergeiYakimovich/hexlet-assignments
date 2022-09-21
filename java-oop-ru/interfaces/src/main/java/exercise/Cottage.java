package exercise;

// BEGIN
public class Cottage implements Home {
    private double area;
    private int floorCount;

    public Cottage(double areaValue, int floorCountValue) {
        this.area = areaValue;
        this.floorCount = floorCountValue;
    }

    @Override
    public double getArea() {
        return this.area;
    }

    @Override
    public int compareTo(Home another) {
        return getArea() > another.getArea() ? 1 : getArea() == another.getArea() ? 0 : -1;
    }

    public String toString() {
        return this.floorCount + " этажный коттедж площадью " + getArea() + " метров";
    }
}
// END
