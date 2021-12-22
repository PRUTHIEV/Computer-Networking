package TCP;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatAppClientTCP {
    private static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        try{
            Socket s = new Socket("localhost", 7000);
            System.out.println("The client is connected");
            String msg,msgReceived;
            do{
                System.out.print("The client :");
                msg = input.nextLine();
                OutputStream outputStream = s.getOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(msg);
                InputStream inputStream = s.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                msgReceived = (String) objectInputStream.readObject();
                System.out.println("The server :" + msgReceived);
            } while (!msgReceived.toLowerCase().equals("bye"));
            s.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
