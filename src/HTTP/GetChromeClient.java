package HTTP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Scanner;


public class GetChromeClient {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        String page;
        System.out.print("Enter the target source :");
        page = input.nextLine();
        URL url = new URL("http://localhost/" + page);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Chrome");
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            while (true) {
                BufferedReader buff = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String msg = buff.readLine();
                if (msg == null) {
                    break;
                }
                System.out.println(msg);
            }
            System.out.println("Page is successfully fetched");

        } else {
            System.out.println("Error occured while fetching the page");
        }
    }
}
