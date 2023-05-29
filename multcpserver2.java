/* 
Rollno  :31
Name    :Rathod Vidhi
Sub     :Networking
Course  :MCA-2
Ass     :1
--------------------------------------------------------------------------------------------------------------
-TCP  multithreading socket program to  to draw square by any character entered  dynamically . (server side)
--------------------------------------------------------------------------------------------------------------
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

 class SquareServer {
    private static final int PORT = 8888;
    private static final int THREAD_POOL_SIZE = 5;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running. Waiting for client connection...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                executorService.execute(clientHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String character;
                int size;
                while (true) {
                    character = in.readLine();
                    size = Integer.parseInt(in.readLine());

                    if (character.equalsIgnoreCase("exit") || size == 0)
                        break;

                    String square = generateSquare(character.charAt(0), size);
                    out.println(square);
                }

                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String generateSquare(char character, int size) {
            StringBuilder squareBuilder = new StringBuilder();

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    squareBuilder.append(character).append(" ");
                }
                squareBuilder.append("\n");
            }

            return squareBuilder.toString();
        }
    }
}

/*
 Output:
 Server is running. Waiting for client connection...
Client connected: 127.0.0.1 */