package HTTP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

public class GetServerMethod {
    public static void main(String[] args) {
        try{
            ServerSocket ss = new ServerSocket(7000);
            System.out.println("The server is listening at port 7000");
            Socket s = ss.accept();
            System.out.println("The client is connected ...");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter printWritter = new PrintWriter(s.getOutputStream(),true);
            String msg = bufferedReader.readLine();
            System.out.println("The msg received from client :" + msg);
            URL url = new URL(msg);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("User-Agent", "Chrome");
            int responceCode = httpURLConnection.getResponseCode();
            if (responceCode != 200)
                System.out.println("Error occured while fetching the data !!");
            else {
                bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String result = "";
                while (true) {
                    result = bufferedReader.readLine();
                    printWritter.println(result);
                    if (result == null)
                        break;
                }
            }
            ss.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
