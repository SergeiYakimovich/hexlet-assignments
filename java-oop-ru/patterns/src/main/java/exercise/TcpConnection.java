package exercise;

import exercise.connections.Connected;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

// BEGIN
public class TcpConnection implements Connection {
    private String ipAdress;
    private int port;
    private Connection state;

    public TcpConnection(String ipAdressValue, int portValue) {
        this.ipAdress = ipAdressValue;
        this.port = portValue;
        this.state = new Disconnected(this);
    }

    @Override
    public void connect() {
        state.connect();
        state = new Connected(this);
    }

    @Override
    public void disconnect() {
        state.disconnect();
        state = new Disconnected(this);
    }

    @Override
    public void write(String text) {
        state.write(text);
    }

    @Override
    public String getCurrentState() {
        return state.getCurrentState();
    }
}
// END
