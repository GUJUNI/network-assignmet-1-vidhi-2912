/*
Rollno  :31
Name    :Rathod Vidhi
Sub     :Networking
Course  :MCA-2
Ass     :1
--------------------------------------------------------------------------------------------------------------
-UDP  socket program to find the word from the text and highlight that word with "_".(server side)
--------------------------------------------------------------------------------------------------------------
*/

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

 class UDPServer {
    private static final int PORT = 12345;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            System.out.println("Server started. Listening on port " + PORT);

            while (true) {
                byte[] buffer = new byte[BUFFER_SIZE];
                DatagramPacket packet = new DatagramPacket(buffer, BUFFER_SIZE);
                socket.receive(packet);

                String receivedData = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received request: " + receivedData);

                // Split the received data into text and word
                String[] parts = receivedData.split(",", 2);
                String text = parts[0];
                String wordToFind = parts[1];

                // Search for the word in the text and highlight it
                String highlightedText = text.replaceAll(wordToFind, "__" + wordToFind + "__");

                byte[] responseData = highlightedText.getBytes();

                DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length,
                        packet.getAddress(), packet.getPort());
                socket.send(responsePacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/*
  Output:
 Server started. Listening on port 12345
Received request: Today is a rainy day,rainy
 */