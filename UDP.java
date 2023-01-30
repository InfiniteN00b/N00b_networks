import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

//server
// public class UDP {
//     public static void main(String[] args) {
//         try {
//             DatagramSocket server = new DatagramSocket();
//             System.out.println("Enter server message: ");
//             Scanner sc = new Scanner(System.in);
//             while(true){
//                 byte[] buffer = new byte[1024];
//                 DatagramPacket request = new DatagramPacket(buffer, buffer.length);
//                 server.receive(request);

//                 String msg = sc.nextLine();
//                 byte[] sendMessage = msg.getBytes();
//                 DatagramPacket reply = new DatagramPacket(sendMessage, sendMessage.length, request.getAddress(), request.getPort());
//                 server.send(reply);

//                 server.close();
//                 sc.close();
//             }
            
            
//         } catch (Exception e) {
//             System.out.println(e);
//         }
//     }
// }

//client
public class UDP{
    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();
            int port = 1234;
            InetAddress host = InetAddress.getByName("localhost");

            String msg = "text message";
            byte sendMessage = msg.getBytes();
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
