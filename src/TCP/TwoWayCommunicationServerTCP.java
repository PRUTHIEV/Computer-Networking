package TCP;
import java.io.*;
import java.net.*;
import java.util.*;
public class TwoWayCommunicationServerTCP {
    private static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        try{
            System.out.println("The server is ready to accept the clients");
            ServerSocket serverSocket = new ServerSocket(7000);
            System.out.println("The client is connected");
            Socket clientSocket = serverSocket.accept();
            InputStream inputStream = clientSocket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            String msgReceived = (String)objectInputStream.readObject();
            System.out.println("The message receved is :" + msgReceived);
            System.out.print("Enter a string :");
            String msg = input.nextLine();
            OutputStream outputStream = clientSocket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(msg);
            System.out.println("Server chat is ended");
            serverSocket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    
    }
}
