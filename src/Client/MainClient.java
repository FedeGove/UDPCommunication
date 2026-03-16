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

            byte[] bufferIn = new byte[256];

            DatagramPacket packetIn = new DatagramPacket(bufferIn, bufferIn.length);

            dSocket.receive(packetIn); //Metodo bloccante
            String received = new String(packetIn.getData(), 0, packetIn.getLength());
            System.out.println("Ricezione effettuata: " + received);
            System.out.println("Indirizzo IP: " +  packetIn.getAddress().getHostAddress());
            System.out.println("Indirizzo porta: " + packetIn.getPort());

            dSocket.close();
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

