import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class tcpclient {
    public static void main(String[] args) {
        try{
            Socket client = new Socket("localhost", 1234);
            BufferedReader k = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter file location: ");
            String filename = k.readLine();
            DataOutputStream sendToServer = new DataOutputStream(client.getOutputStream());
            sendToServer.writeBytes(filename + "\n");

            BufferedReader i = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String string = i.readLine();
            if(string.equals("File Found")){
                while((string = i.readLine()) != null)
                    System.out.println(string);
            }
            else
                System.out.println("File Not Found");
            
            client.close();
            k.close();
            i.close();
            sendToServer.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }   
}
