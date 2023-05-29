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

class UDPServer {
    public static void main(String[] args) throws Exception {
        // create a UDP socket
        DatagramSocket serverSocket = new DatagramSocket(5000);

        while (true) {
            // create a buffer to receive incoming data
            byte[] buffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);

            long startTime = System.currentTimeMillis();

            // receive a packet from the client
            serverSocket.receive(receivePacket);

            long endTime = System.currentTimeMillis();

            // create a new thread to handle the request
            Thread thread = new Thread(new ServerThread(serverSocket, receivePacket, endTime - startTime));
            thread.start();
        }
    }
}

class ServerThread implements Runnable {
    private DatagramSocket serverSocket;
    private DatagramPacket receivePacket;
    private long responseTime;

    public ServerThread(DatagramSocket serverSocket, DatagramPacket receivePacket, long responseTime) {
        this.serverSocket = serverSocket;
        this.receivePacket = receivePacket;
        this.responseTime = responseTime;
    }

    @Override
    public void run() {
        InetAddress IPAddress = receivePacket.getAddress();
        int port = receivePacket.getPort();

        String message = new String(receivePacket.getData(), 0, receivePacket.getLength());

        // send the response back to the client
        String response = "Response received in " + responseTime + " ms";
        byte[] sendData = response.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

        try {
            serverSocket.send(sendPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

