package PING;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class PingClient {
    private static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 7000);
            System.out.print("Enter the number of packets :");
            int numberOfPackets = input.nextInt();
            input.nextLine();
            System.out.print("Enter the web url :");
            String webUrl = input.nextLine();
            ObjectOutputStream objOutput = new ObjectOutputStream(s.getOutputStream());
            objOutput.writeObject(numberOfPackets);
            ObjectOutputStream objOutput1 = new ObjectOutputStream(s.getOutputStream());
            objOutput1.writeObject(webUrl);
            System.out.println("web");
            String readStatements = null;
            while (true) {
                ObjectInputStream objInput = new ObjectInputStream(s.getInputStream());
                readStatements = (String) objInput.readObject();
                if(readStatements == null) break;
                System.out.println(readStatements);
            }
            s.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
