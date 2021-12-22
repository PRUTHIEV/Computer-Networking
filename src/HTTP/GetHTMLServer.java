package HTTP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class GetHTMLServer {
    private static void handleClient(ServerSocket ss, Socket s) throws Exception {
        BufferedReader buff = new BufferedReader(new InputStreamReader(s.getInputStream()));
        StringBuilder res = new StringBuilder();
        String msg = null;
        while (!(msg = buff.readLine()).isBlank()) {
            res.append(msg);
        }
        System.out.println();
        String content = "<h1>Hello World</h1>";
        OutputStream out = s.getOutputStream();
        out.write(("HTTP/1.1 " + "200" + "\r\n").getBytes());
        out.write(("ContentType " + "text/html" + "\r\n").getBytes());
        out.write(("\r\n").getBytes());
        out.write((content + "\r\n").getBytes());
        out.write(("\r\n\r\n").getBytes());
    }

    public static void main(String[] args) throws Exception{
        ServerSocket ss = new ServerSocket(80);
        System.out.println("The server is listening at port 80 ..");
        while (true) {
            Socket s = ss.accept();
            if (s.isConnected()) {
                System.out.println("The client is conneted ...");
                handleClient(ss, s);
            }
        }
    }
}
