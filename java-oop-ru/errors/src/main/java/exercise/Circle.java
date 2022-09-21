package exercise;

// BEGIN
public class Circle {
    private Point point;
    private int radius;

    public Circle(Point newPoint, int newRadius) {
        point = newPoint;
        radius = newRadius;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException("Не удалось посчитать площадь");
        } else {
            return Math.PI * radius * radius;
        }
    }
}
// END
