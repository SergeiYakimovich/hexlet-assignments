package exercise.connections;

public interface Connection {
    String getCurrentState();
    void connect();
    void disconnect();
    void write(String text);
}
