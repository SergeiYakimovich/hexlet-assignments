package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection {
    private TcpConnection tcpConnection;

    public Disconnected(TcpConnection newTcpConnection) {
        this.tcpConnection = newTcpConnection;
    }

    @Override
    public String getCurrentState() {
        return "disconnected";
    }

    @Override
    public void connect() {
        System.out.println("Connect");
    }

    @Override
    public void disconnect() {
        System.out.println("Error : Try to disconnect when connection disconnected.");
    }

    @Override
    public void write(String text) {
        System.out.println("Error : Try to write to disconnected connection.");
    }
}
// END
