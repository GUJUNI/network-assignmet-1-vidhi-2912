/*
Rollno  :31
Name    :Rathod Vidhi
Sub     :Networking
Course  :MCA-2
Ass     :1
--------------------------------------------------------------------------------------------------------------
-TCP multithreading socket program for coin-toss game (client side)
--------------------------------------------------------------------------------------------------------------
*/

import java.net.*;
import java.io.*;
import java.util.Scanner;

class CoinTossClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234); // Connect to the server
            System.out.println("Connected to server");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                // Prompt the user for their choice
                System.out.print("Enter 0 for heads, 1 for tails, or -1 to quit: ");
                int choice = scanner.nextInt();
                if (choice == -1) {
                    break;
                }
                // Send the choice to the server
                out.println(choice);
                // Read the result from the server
                String result = in.readLine();
                System.out.println(result);
            }
            // Clean up
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/*
Output:
Connected to server
Enter 0 for heads, 1 for tails, or -1 to quit: 0
You lost!
Enter 0 for heads, 1 for tails, or -1 to quit: 1
You won!
Enter 0 for heads, 1 for tails, or -1 to quit: -1
 */