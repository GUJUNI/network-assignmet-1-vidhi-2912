/* 
Rollno  :31
Name    :Rathod Vidhi
Sub     :Networking
Course  :MCA-2
Ass     :1
--------------------------------------------------------------------------------------------------------------
-TCP  socket program to find the vowel from the entered word . (server side)
--------------------------------------------------------------------------------------------------------------
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class VowelServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("Server is running. Waiting for client connection...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.equalsIgnoreCase("exit"))
                    break;

                String vowels = findVowels(inputLine);
                out.println(vowels);
            }

            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String findVowels(String word) {
        StringBuilder vowelsBuilder = new StringBuilder();

        for (char c : word.toCharArray()) {
            if (isVowel(c)) {
                vowelsBuilder.append(c);
                vowelsBuilder.append(' ');
            }
        }

        return vowelsBuilder.toString().trim();
    }

    private static boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}

/*
  Output:
  Server is running. Waiting for client connection...
Client connected.
 */