package FTP;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class FtpClient {
    private static final Scanner input = new Scanner(System.in);

    private static File writeToFile() throws Exception{
        File file = new File("./Input.txt");
        FileOutputStream fout = new FileOutputStream(file);
        System.out.println("Enter a message :");
        String inputMessage = input.nextLine();
        byte[] inputArray = inputMessage.getBytes();
        fout.write(inputArray);
        fout.flush();
        fout.close();
        return file;
    }
    public static void main(String[] args) throws Exception{
        File file = writeToFile();
        Socket s = new Socket("localhost", 7000);
        ObjectOutputStream objOutput = new ObjectOutputStream(s.getOutputStream());
        objOutput.writeObject(file);
        s.close();
    }
}
