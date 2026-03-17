package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MainServer {
    public static void main(String[] args) {

        int port = 3000;
        String multicastGroup = "230.0.0.1"; // Indirizzo multicast (range 224.x.x.x - 239.x.x.x)

        System.out.println("SERVER INIZIATO");

        try {
            // 1. Crea il socket multicast
            MulticastSocket mSocket = new MulticastSocket(port);

            // 2. Ottieni l'indirizzo del gruppo multicast
            InetAddress group = InetAddress.getByName(multicastGroup);

            // 3. Unisciti al gruppo multicast
            mSocket.joinGroup(group);
            System.out.println("Server unito al gruppo: " + multicastGroup);

            byte[] bufferIn = new byte[256];
            DatagramPacket packetIn = new DatagramPacket(bufferIn, bufferIn.length);

            // Riceve il messaggio (bloccante)
            mSocket.receive(packetIn);
            System.out.println("Ricezione effettuata");

            String message = new String(packetIn.getData(), 0, packetIn.getLength());

            InetAddress clientAddress = packetIn.getAddress();
            int clientPort = packetIn.getPort();

            // Risponde direttamente al client (unicast)
            DatagramPacket packetOut = new DatagramPacket(
                    message.getBytes(), message.length(), clientAddress, clientPort
            );
            mSocket.send(packetOut);

            // 4. Abbandona il gruppo e chiudi
            mSocket.leaveGroup(group);
            mSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}