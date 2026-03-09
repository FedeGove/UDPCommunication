package Server;

import java.net.DatagramSocket;
import java.net.SocketException;

public class MainServer {
    public static void main(String[] args) {

        int port = 3000;
        System.out.println("SERVER INIZIATO");
        try {
            DatagramSocket dSocket = new DatagramSocket(port);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }
}
