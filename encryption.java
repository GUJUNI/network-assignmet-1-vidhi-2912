/* 
Rollno  :31
Name    :Rathod Vidhi
Sub     :Networking
Course  :MCA-2
Ass     :1
--------------------------------------------------------------------------------------------------------------
-Write a program to that performs encryption/decryption.
--------------------------------------------------------------------------------------------------------------
*/

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.util.Base64;
import java.util.Scanner;

 class EncryptionDecryptionProgram {
    public static void main(String[] args) {
        try {
            // Get user input
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a message: ");
            String message = scanner.nextLine();

            // Generate secret key
            SecretKey secretKey = generateSecretKey();

            // Encryption
            String encryptedMessage = encrypt(message, secretKey);

            // Decryption
            String decryptedMessage = decrypt(encryptedMessage, secretKey);

            // Display results
            System.out.println("\nOriginal message: " + message);
            System.out.println("Encrypted message: " + encryptedMessage);
            System.out.println("Decrypted message: " + decryptedMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Generate secret key
    private static SecretKey generateSecretKey() throws Exception {
        String keyString = "MySecretKey"; // You can modify this key as per your requirement
        byte[] keyBytes = keyString.getBytes();
        DESKeySpec keySpec = new DESKeySpec(keyBytes);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        return keyFactory.generateSecret(keySpec);
    }

    // Encryption method
    private static String encrypt(String message, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Decryption method
    private static String decrypt(String encryptedMessage, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedMessage);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
}
/*
 Output:
 Enter a message: Hello, World!

Original message: Hello, World!
Encrypted message: WOOt+3RCR19Fm2Cgey3FFw==
Decrypted message: Hello, World!

 */
