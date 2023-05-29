/*
Rollno  :31
Name    :Rathod Vidhi
Sub     :Networking
Course  :MCA-2
Ass     :1
--------------------------------------------------------------------------------------------------------------
-UDP  multithreading socket program to check whether the entered IP address is valid or not. (client side)
--------------------------------------------------------------------------------------------------------------
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

 class UDPClient {
    public static void main(String[] args) {
        try {
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            byte[] sendData;
            byte[] receiveData = new byte[1024];

            while (true) {
                System.out.print("Enter an IP address (or 'exit' to quit): ");
                String ipAddress = inFromUser.readLine();

                if (ipAddress.equalsIgnoreCase("exit")) {
                    break;
                }

                sendData = ipAddress.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
                clientSocket.send(sendPacket);

                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String response = new String(receivePacket.getData());
                System.out.println("Server response: " + response.trim());
            }

            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/*
 Output:
 Enter an IP address (or 'exit' to quit): 121.12.121.03
Server response: Invalid IP Address
Enter an IP address (or 'exit' to quit): 255.255.255.255
Server response: Valid IP Addressss
Enter an IP address (or 'exit' to quit): anc.erd.dg
Server response: Invalid IP Address
Enter an IP address (or 'exit' to quit): exit
 */