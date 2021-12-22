package FTP;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FtpServer {
    public static void main(String[] args) throws Exception{
        ServerSocket ss = new ServerSocket(7000);
        System.out.println("The server is started and listening to the port number 7000...");
        Socket s = ss.accept();
        if (s.isConnected()) {
            System.out.println("The client is connected ...");
        }
        ObjectInputStream objInput = new ObjectInputStream(s.getInputStream());
        File file = (File) objInput.readObject();
        System.out.println("The name of the file is :" + file.getAbsolutePath());
        FileInputStream fin = new FileInputStream(file);
        byte[] message = new byte[(int) file.length()];
        fin.read(message);
        String msg = new String(message);
        System.out.println("The message from the file is :" + msg);
        fin.close();
        ss.close();
    }
}
