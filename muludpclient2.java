/*
Rollno  :31
Name    :Rathod Vidhi
Sub     :Networking
Course  :MCA-2
Ass     :1
--------------------------------------------------------------------------------------------------------------
-UDP multithreading  socket program to find the mean of the entered data.(client side)
--------------------------------------------------------------------------------------------------------------
*/
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Scanner;

 class MeanClient {
    private static final int BUFFER_SIZE = 1024;
    
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 12345;
            
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter data (comma-separated numbers): ");
            String data = scanner.nextLine();
            
            byte[] requestData = data.getBytes();
            DatagramPacket requestPacket = new DatagramPacket(requestData, requestData.length, serverAddress, serverPort);
            
            socket.send(requestPacket);
            
            byte[] responseBuffer = new byte[BUFFER_SIZE];
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, BUFFER_SIZE);
            
            socket.receive(responsePacket);
            
            String mean = new String(responsePacket.getData()).trim();
            System.out.println("Mean: " + mean);
            
            socket.close();
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/*
 Output:
 Enter data (comma-separated numbers): 2, 4, 6, 8, 10
Mean: 6.0

 */