package Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.net.SocketException;

public class MainClient {
    public static void main(String[] args) {
        System.out.println("Client");

        int port = 3000;
        String multicastGroup = "230.0.0.1"; // Stesso indirizzo del server+ù+

        try {
            // 1. Indirizzo del gruppo multicast (non più localhost!)
            InetAddress group = InetAddress.getByName(multicastGroup);

            // 2. Socket normale per inviare al gruppo
            DatagramSocket dSocket = new DatagramSocket();

            String message = "Ciao";

            // 3. Il pacchetto è indirizzato al gruppo multicast
            DatagramPacket dpo = new DatagramPacket(
                    message.getBytes(), message.length(), group, port
            );

            dSocket.send(dpo);

            // 4. Attende la risposta unicast dal server
            byte[] bufferIn = new byte[256];
            DatagramPacket packetIn = new DatagramPacket(bufferIn, bufferIn.length);

            dSocket.receive(packetIn);
            String received = new String(packetIn.getData(), 0, packetIn.getLength());

            System.out.println("Ricezione effettuata: " + received);
            System.out.println("Indirizzo IP: " + packetIn.getAddress().getHostAddress());
            System.out.println("Porta: " + packetIn.getPort());

            dSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}