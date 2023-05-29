
/* 
Rollno  :31
Name    :Rathod Vidhi
Sub     :Networking
Course  :MCA-2
Ass     :1
--------------------------------------------------------------------------------------------------------------
-TCP  socket program to calculate the compound interest . (server side)
--------------------------------------------------------------------------------------------------------------
*/
import java.io.*;
import java.net.*;

class CompoundInterestServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234); // create a server socket
            System.out.println("Server is listening on port 1234");

            while (true) {
                Socket socket = serverSocket.accept(); // wait for a client to connect
                System.out.println("Client connected from " + socket.getInetAddress().getHostName());

                // create a new client handler instance
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            while (true) {
                // read the principal, rate, time and frequency from the client
                double principal = in.readDouble();
                double rate = in.readDouble();
                double time = in.readDouble();
                int frequency = in.readInt();

                // calculate the compound interest
                double amount = principal * Math.pow(1 + rate / frequency, frequency * time);
                double interest = amount - principal;

                // send the result back to the client
                out.writeDouble(amount);
                out.writeDouble(interest);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/*Output:
Server is listening on port 1234
 */