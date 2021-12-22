package HTTP;

import java.net.HttpURLConnection;
import java.net.URL;

public class GetHTMLClient {
    public static void main(String[] args) throws Exception{
        String urlText = "http://localhost/home";
        URL url = new URL(urlText);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Chrome");
        connection.setDoOutput(true);
        int responseCode = connection.getResponseCode();
        System.out.println(responseCode);
        System.out.println("The client is disconnected !!!");

    }
}
