package exercise;

// BEGIN
public class App {

    public static void printSquare(Circle circle) {
        try {
            System.out.print(Math.round(circle.getSquare()));
        } catch (NegativeRadiusException e) {
            System.out.print(e.getMessage());
        }
        System.out.print("\nВычисление окончено");
    }

}
// END
