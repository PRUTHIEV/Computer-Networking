package TCP;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatAppServerTCP {
	private static final Scanner uinput = new Scanner(System.in);
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(7000);
			System.out.println("The server is listening to the port 7000");
			Socket s = ss.accept();
			System.out.println("The client is connected");
			String msg,msgReceived;
			do {
				InputStream input = s.getInputStream();
				ObjectInputStream dInput = new ObjectInputStream(input);
				msgReceived = (String) dInput.readObject();
				System.out.println("The client :" + msgReceived);
				System.out.print("The server :");
				msg = uinput.nextLine();
				OutputStream output = s.getOutputStream();
				ObjectOutputStream dOutput = new ObjectOutputStream(output);
				dOutput.writeObject(msg);
			} while (!msgReceived.toLowerCase().equals("bye"));
			System.out.println("Server chat is ended");
			ss.close();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
