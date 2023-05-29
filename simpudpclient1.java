/*
Rollno  :31
Name    :Rathod Vidhi
Sub     :Networking
Course  :MCA-2
Ass     :1
--------------------------------------------------------------------------------------------------------------
-UDP  socket program to find the time taken to accept the client request by server (server side)
--------------------------------------------------------------------------------------------------------------
*/
import java.net.*;

class UDPClient {
    public static void main(String[] args) throws Exception {
        // create a UDP socket
        DatagramSocket clientSocket = new DatagramSocket();

        // set the server address and port
        InetAddress IPAddress = InetAddress.getByName("localhost");
        int port = 5000;

        // send the message to the server
        String message = "Hello, server!";
        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
        clientSocket.send(sendPacket);

        // receive the response from the server
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);

        String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Response from server: " + response);

        // close the socket
        clientSocket.close();
    }
}
/*Output:
Response from server: Response received in 43713 ms
 */
