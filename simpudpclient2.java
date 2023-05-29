/*
Rollno  :31
Name    :Rathod Vidhi
Sub     :Networking
Course  :MCA-2
Ass     :1
--------------------------------------------------------------------------------------------------------------
-UDP  socket program to find the word from the text and highlight that word with "_".(client side)
--------------------------------------------------------------------------------------------------------------
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

 class UDPClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName(SERVER_ADDRESS);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the text: ");
            String text = reader.readLine();

            System.out.print("Enter the word to find: ");
            String wordToFind = reader.readLine();

            String requestData = text + "," + wordToFind;
            byte[] requestDataBytes = requestData.getBytes();

            DatagramPacket requestPacket = new DatagramPacket(requestDataBytes, requestDataBytes.length,
                    serverAddress, SERVER_PORT);
            socket.send(requestPacket);

            byte[] buffer = new byte[BUFFER_SIZE];
            DatagramPacket responsePacket = new DatagramPacket(buffer, BUFFER_SIZE);
            socket.receive(responsePacket);

            String responseData = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println("Server response: " + responseData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/*Output:
 Enter the text: Today is a rainy day
Enter the word to find: rainy
Server response: Today is a __rainy__ day
 */