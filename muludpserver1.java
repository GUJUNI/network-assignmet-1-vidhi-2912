/*
Rollno  :31
Name    :Rathod Vidhi
Sub     :Networking
Course  :MCA-2
Ass     :1
--------------------------------------------------------------------------------------------------------------
-UDP  multithreading socket program to check whether the entered IP address is valid or not. (server side)
--------------------------------------------------------------------------------------------------------------
*/
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

 class UDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(9876);

            while (true) {
                byte[] receiveData = new byte[1024];
                byte[] sendData;
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String sentence = new String(receivePacket.getData());
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                String response = validateIPAddress(sentence.trim());

                sendData = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String validateIPAddress(String ipAddress) {
        try {
            InetAddress inetAddress = InetAddress.getByName(ipAddress);
            if (inetAddress.getHostAddress().equals(ipAddress)) {
                return "Valid IP Address";
            } else {
                return "Invalid IP Address";
            }
        } catch (Exception e) {
            return "Invalid IP Address";
        }
    }
}
