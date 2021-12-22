package TCP;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayManipulateClient {
    private static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 7000);
            ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
            ObjectOutputStream objOutput = new ObjectOutputStream(s.getOutputStream());
            objOutput.writeObject(arr);
            while (true) {
                System.out.print("Enter the query :");
                String query = input.nextLine();
                ObjectOutputStream objOutput1 = new ObjectOutputStream(s.getOutputStream());
                objOutput1.writeObject(query);
                ObjectInputStream objInput = new ObjectInputStream(s.getInputStream());
                String result = (String) objInput.readObject();
                System.out.println(result);
                if (query.toLowerCase().contains("bye") && result.toLowerCase().contains("bye"))
                    break;
            }
            System.out.println("The server chat is ended");
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
