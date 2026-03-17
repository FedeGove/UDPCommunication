package Client;

import java.io.IOException;
import java.net.*;

public class MainClient {
    public static void main(String[] args) {
        System.out.println("Client");

        int port = 3000;
        String multicastGroup = "230.0.0.1";

        try {
            InetAddress group = InetAddress.getByName(multicastGroup);

            // MulticastSocket invece di DatagramSocket
            MulticastSocket mSocket = new MulticastSocket();

            // Join del gruppo
            mSocket.joinGroup(group);

            String message = "Ciao";
            DatagramPacket dpo = new DatagramPacket(
                    message.getBytes(), message.length(), group, port
            );

            mSocket.send(dpo);

            byte[] bufferIn = new byte[256];
            DatagramPacket packetIn = new DatagramPacket(bufferIn, bufferIn.length);

            mSocket.receive(packetIn);
            String received = new String(packetIn.getData(), 0, packetIn.getLength());

            System.out.println("Ricezione effettuata: " + received);
            System.out.println("Indirizzo IP: " + packetIn.getAddress().getHostAddress());
            System.out.println("Porta: " + packetIn.getPort());

            // Lascia il gruppo e chiudi
            mSocket.leaveGroup(group);
            mSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}