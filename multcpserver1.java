/*
Rollno  :31
Name    :Rathod Vidhi
Sub     :Networking
Course  :MCA-2
Ass     :1
--------------------------------------------------------------------------------------------------------------
-TCP multithreading socket program for coin-toss game (server side)
--------------------------------------------------------------------------------------------------------------
*/
import java.net.*;
import java.io.*;

 class CoinTossServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(1234); // Create a server socket and bind it to port 1234
            System.out.println("Server started");
            while (true) {
                Socket client = server.accept(); // Wait for a client to connect
                System.out.println("Client connected");
                // Start a new thread to handle the client
                Thread t = new Thread(new CoinTossHandler(client));
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class CoinTossHandler implements Runnable {
    private Socket client;

    public CoinTossHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                // Parse the client's input
                int clientChoice = Integer.parseInt(inputLine);
                // Generate a random coin toss
                int coinToss = (int) (Math.random() * 2); // 0 for heads, 1 for tails
                // Determine the winner
                String result;
                if (clientChoice == coinToss) {
                    result = "You won!";
                } else {
                    result = "You lost!";
                }
                // Send the result to the client
                out.println(result);
            }
            // Clean up
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/*
Output:
Server started
Client connected
 */ 

