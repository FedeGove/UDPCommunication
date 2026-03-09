package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class MainServer {
    public static void main(String[] args) {

        int port = 3000;
        System.out.println("SERVER INIZIATO");
        try {
            DatagramSocket dSocket = new DatagramSocket(port);
            byte[] bufferIn = new byte[256];

            DatagramPacket packetIn = new DatagramPacket(bufferIn, bufferIn.length);

            dSocket.receive(packetIn); //Metodo bloccante
            System.out.println("Ricezione effettuata");

            String message = new String(packetIn.getData(), 0, packetIn.getLength());

            InetAddress clientAddress = packetIn.getAddress();
            int clientPort = packetIn.getPort();

            DatagramPacket packetOut = new DatagramPacket(message.getBytes(), message.length(), clientAddress, clientPort);
            dSocket.send(packetOut);

        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
