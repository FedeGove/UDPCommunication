package Client;

import java.io.IOException;
import java.net.*;

public class MainClient {
    public static void main(String[] args) {
        System.out.println("Client");

        InetAddress serverAddress;
        DatagramSocket dSocket;

        int port = 3000;

        try {
            serverAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        try {
            dSocket = new DatagramSocket();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        String message = "Ciao";
        DatagramPacket dpo =  new DatagramPacket(message.getBytes(), message.length(), serverAddress, port);

        try {
            dSocket.send(dpo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

