package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection {
    private TcpConnection tcpConnection;

    public Connected(TcpConnection newTcpConnection) {
        this.tcpConnection = newTcpConnection;
    }


    @Override
    public String getCurrentState() {
        return "connected";
    }

    @Override
    public void connect() {
        System.out.println("Error : Try to connect when connection already established.");
    }

    @Override
    public void disconnect() {
        System.out.println("Disconnect");
    }

    @Override
    public void write(String text) {
        System.out.println(text);
    }
}
// END
