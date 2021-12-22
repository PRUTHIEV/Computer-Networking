package HTTP;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class GetURLServer {
    public static void main(String[] args) throws Exception {
        while (true) {
            String stringUrl = "http://localhost/pruthiev";
            URL url = new URL(stringUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Chrome");
            connection.setDoOutput(true);
            Map<String, List<String>> headers = connection.getHeaderFields();
            for (Map.Entry<String, List<String>> values : headers.entrySet()) {
                System.out.println(values.getKey() + " : " + values.getValue());
            }
            System.out.println(connection.getURL());
            System.out.println(url.getDefaultPort());
            System.out.println(url.getProtocol());
            System.out.println(url.getHost());
            System.out.println(url.getQuery());
            System.out.println(url.getFile());
            System.out.println(url.getRef());
            String content = "<h1>Pruthiev A S </h1>";
            java.io.OutputStream opt = connection.getOutputStream();
            opt.write(("HTTP/1.1 " + "200" + "\n").getBytes());
            opt.write(("ContentType: " + "text/html" + "\r\n").getBytes());
            opt.write("\r\n".getBytes());
            opt.write((content).getBytes());
            opt.write("\r\n\r\n".getBytes());
            opt.flush();
        }
    }
}
