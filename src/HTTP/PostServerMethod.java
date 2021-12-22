package HTTP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class PostServerMethod {
    private static void handleClient(ServerSocket ss, Socket s) throws IOException {
        BufferedReader buff = new BufferedReader(new InputStreamReader(s.getInputStream()));
        StringBuilder res = new StringBuilder();
        String msg = null;
        while (!(msg = buff.readLine()).isBlank()) {
            System.out.println(msg);
            res.append(msg);
        }
        String content = res.toString().split(" ")[1];
        OutputStream opt = s.getOutputStream();
        opt.write(("HTTP/1.1 " + "200" +  "\n" ).getBytes());
        opt.write(("ContentType: " + "text/html" + "\r\n").getBytes());
        opt.write("\r\n".getBytes());
        opt.write((content).getBytes());
        opt.write("\r\n\r\n".getBytes());
        opt.flush();
    }
   public static void main(String[] args) throws IOException {
       ServerSocket ss = new ServerSocket(80);
       Socket s = ss.accept();
       while (true) {
           if (s.isConnected()) {
               System.out.println("The client is connected ...");
               handleClient(ss, s);
           }
       }
       
   }
}