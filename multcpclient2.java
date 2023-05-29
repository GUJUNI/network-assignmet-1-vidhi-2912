/* 
Rollno  :31
Name    :Rathod Vidhi
Sub     :Networking
Course  :MCA-2
Ass     :1
--------------------------------------------------------------------------------------------------------------
-TCP  multithreading socket program to  to draw square by any character entered  dynamically . (client side)
--------------------------------------------------------------------------------------------------------------
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

 class SquareClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8888)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            String character;
            int size;
            while (true) {
                System.out.print("Enter a character or 'exit' to quit: ");
                character = stdIn.readLine();

                if (character.equalsIgnoreCase("exit"))
                    break;

                System.out.print("Enter the size of the square: ");
                size = Integer.parseInt(stdIn.readLine());

                out.println(character);
                out.println(size);

                String square = in.readLine();
                System.out.println("Square:\n" + square);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
 Output:
 Enter a character and the size of the square (e.g., *,5) or 'exit' to quit: *,4
* * * *
* * * *
* * * *
* * * *

Enter a character and the size of the square (e.g., *,5) or 'exit' to quit: v,3
v v v
v v v
v v v

Enter a character and the size of the square (e.g., *,5) or 'exit' to quit: #,2
# #
# #

Enter a character and the size of the square (e.g., *,5) or 'exit' to quit: exit
 */