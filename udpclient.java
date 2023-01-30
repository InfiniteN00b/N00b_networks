import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class udpclient {
    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();
            int port = 1234;
            InetAddress host = InetAddress.getByName("localhost");

            String msg = "text message";
            byte[] sendMessage = msg.getBytes();
            DatagramPacket request = new DatagramPacket(sendMessage, sendMessage.length, host, port);
            client.send(request);
            byte[] buffer = new byte[1024];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            client.receive(reply);
            System.out.println("Received msg: " + new String(reply.getData()));
            client.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
