package PING;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class PingServer {
    public static void main(String[] args) {
        try{
            ServerSocket ss = new ServerSocket(7000);
            System.out.println("The server is listening at port number 7000 ...");
            Socket s = ss.accept();
            if (s.isConnected()) {
                System.out.println("The client is connected ");
            }
            int numberOfPackets = 0;
            String addr = null;
            ObjectInputStream objInput = new ObjectInputStream(s.getInputStream());
            System.out.println("get input");
            numberOfPackets = (Integer)objInput.readObject();
            System.out.println("readNum");
            ObjectInputStream objInput1 = new ObjectInputStream(s.getInputStream());
            addr = (String) objInput1.readObject();
            System.out.println("readAdadr");
            System.out.println(numberOfPackets + " " + addr);
            Process p = Runtime.getRuntime().exec("ping -n " + numberOfPackets + " " + addr);            
            while (true) {
                BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String statements = bf.readLine();
                ObjectOutputStream objOutput = new ObjectOutputStream(s.getOutputStream());
                objOutput.writeObject(statements);
                if (statements == null)
                    break;
            }
            ss.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
