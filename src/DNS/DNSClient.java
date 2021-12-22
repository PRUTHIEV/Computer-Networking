package DNS;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class DNSClient {
    private static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        try{
            DatagramSocket ds = new DatagramSocket();
            System.out.print("Enter a url :");
            String url = input.nextLine();
            byte[]urlArray = url.getBytes();
            DatagramPacket dp = new DatagramPacket(urlArray, urlArray.length, InetAddress.getLocalHost(), 7000);
            ds.send(dp);
            byte[] urlReceivedArray = new byte[1024];
            DatagramPacket dp1 = new DatagramPacket(urlReceivedArray, urlReceivedArray.length);
            ds.receive(dp1);
            String urlRecevied = new String(dp1.getData(), 0, dp1.getLength());
            System.out.println("The domain name address of " + url + " is :" + urlRecevied);
            ds.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("The client chat is ended");
    }
}
