package HTTP;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GetClientMethod {
    private static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        try{
            Socket s = new Socket("localhost", 7000);
            System.out.print("Enter url :");
            String url = input.nextLine();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter printWriter = new PrintWriter(s.getOutputStream(), true);
            printWriter.println(url);
            while (true) {
                String msgReceived = bufferedReader.readLine();
                if (msgReceived == null)
                    break;
                System.out.println(msgReceived);
            }
            s.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
