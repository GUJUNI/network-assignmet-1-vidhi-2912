/* 
Rollno  :31
Name    :Rathod Vidhi
Sub     :Networking
Course  :MCA-2
Ass     :1
--------------------------------------------------------------------------------------------------------------
-TCP  socket program to calculate the compound interest . (client side)
--------------------------------------------------------------------------------------------------------------
*/

import java.io.*;
import java.net.*;

class CompoundInterestClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234); // connect to the server
            System.out.println("Connected to server");

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                // read the inputs from the user
                System.out.print("Enter principal amount: ");
                double principal = Double.parseDouble(reader.readLine());
                System.out.print("Enter annual interest rate: ");
                double rate = Double.parseDouble(reader.readLine());
                System.out.print("Enter time in years: ");
                double time = Double.parseDouble(reader.readLine());
                System.out.print("Enter compounding frequency (1 for annual, 2 for semi-annual, etc.): ");
                int frequency = Integer.parseInt(reader.readLine());

                // send the inputs to the server
                out.writeDouble(principal);
                out.writeDouble(rate);
                out.writeDouble(time);
                out.writeInt(frequency);

                // read the result from the server
                double amount = in.readDouble();
                double interest = in.readDouble();

                // display the result to the user
                System.out.printf("Amount after %.1f years: %.2f%n", time, amount);
                System.out.printf("Total interest earned: %.2f%n", interest);
                System.out.print("Press enter to continue, or type 'quit' to exit: ");
                String input = reader.readLine();
                if (input.equals("quit")) {
                    break;
                }
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/*
 * Output:
Connected to server
Enter principal amount: 1000
Enter annual interest rate: 8
Enter time in years: 1
Enter compounding frequency (1 for annual, 2 for semi-annual, etc.): 1
Amount after 1.0 years: 9000.00
Total interest earned: 8000.00
Press enter to continue, or type 'quit' to exit: quit
 */