
/*
Rollno  :31
Name    :Rathod Vidhi
Sub     :Networking
Course  :MCA-2
Ass     :1
--------------------------------------------------------------------------------------------------------------
-UDP multithreading  socket program to find the mean of the entered data.(server side)
--------------------------------------------------------------------------------------------------------------
*/
import java.net.DatagramSocket;
import java.net.DatagramPacket;

 class MeanServer {
    private static final int BUFFER_SIZE = 1024;
    
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(12345);
            System.out.println("Server started and listening on port 12345...");
            
            while (true) {
                byte[] buffer = new byte[BUFFER_SIZE];
                DatagramPacket packet = new DatagramPacket(buffer, BUFFER_SIZE);
                
                socket.receive(packet);
                
                String data = new String(packet.getData()).trim();
                System.out.println("Received data: " + data);
                
                // Calculate mean
                double mean = calculateMean(data);
                
                // Send mean back to client
                byte[] response = String.valueOf(mean).getBytes();
                DatagramPacket responsePacket = new DatagramPacket(response, response.length, packet.getAddress(), packet.getPort());
                socket.send(responsePacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static double calculateMean(String data) {
        // Assuming data is a comma-separated list of numbers
        String[] numbers = data.split(",");
        double sum = 0;
        
        for (String number : numbers) {
            sum += Double.parseDouble(number);
        }
        
        return sum / numbers.length;
    }
}
