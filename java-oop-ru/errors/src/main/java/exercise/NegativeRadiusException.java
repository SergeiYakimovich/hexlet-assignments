package exercise;

// BEGIN
public class NegativeRadiusException extends Exception {
    private String message;

    public NegativeRadiusException(String newMessage) {
        message = newMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
// END
