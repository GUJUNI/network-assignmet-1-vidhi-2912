/* 
Rollno  :31
Name    :Rathod Vidhi
Sub     :Networking
Course  :MCA-2
Ass     :1
--------------------------------------------------------------------------------------------------------------
-TCP  socket program to find the vowel from the entered word . (client side)
--------------------------------------------------------------------------------------------------------------
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class VowelClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8888);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            String word;
            while (true) {
                System.out.print("Enter a word or 'exit' to quit: ");
                word = stdIn.readLine();

                if (word.equalsIgnoreCase("exit"))
                    break;

                out.println(word);

                String vowels = in.readLine();
                System.out.println("Vowels: " + vowels);
            }

            in.close();
            out.close();
            stdIn.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/*
Output:
Enter a word or 'exit' to quit: Sunday
Vowels: u a
Enter a word or 'exit' to quit: exit
 */