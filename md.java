/* 
Rollno  :31
Name    :Rathod Vidhi
Sub     :Networking
Course  :MCA-2
Ass     :1
--------------------------------------------------------------------------------------------------------------
-write a program to compute a message digest for a file of any type and size.
--------------------------------------------------------------------------------------------------------------
*/
import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

 class FileMessageDigest {
    public static void main(String[] args) {
        try {
            // Prompt user for file path
            String filePath = getInput("Enter the file path: ");

            // Compute message digest
            byte[] digest = computeMessageDigest(filePath);

            // Display result
            System.out.println("Message Digest (SHA-256): " + bytesToHexString(digest));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Prompt user for input
    private static String getInput(String prompt) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // Compute message digest for file
    private static byte[] computeMessageDigest(String filePath) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        try (DigestInputStream dis = new DigestInputStream(new FileInputStream(filePath), messageDigest)) {
            byte[] buffer = new byte[4096];
            while (dis.read(buffer) != -1) ;
            return messageDigest.digest();
        }
    }

    // Convert byte array to hexadecimal string
    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
/*
 Output:
 Enter the file path: C:\Users\vidhi\Downloads\networking\simptcpclient2.java
Message Digest (SHA-256): 6ebd92407a78ecba64e014971fcee45b4a8ae02ed3e677d284ac11cb194c55d6
 */