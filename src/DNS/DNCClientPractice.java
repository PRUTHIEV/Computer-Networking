package DNS;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class DNCClientPractice {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket();
        System.out.println("Enter the url :");
        String url = input.nextLine();
        byte[] urlArray = url.getBytes();
        DatagramPacket dp = new DatagramPacket(urlArray, urlArray.length,InetAddress.getLocalHost(), 7000);
        ds.send(dp);
        System.out.println("The url is successfully sent to teh server ..");
        byte []dnsReceivedArray = new byte[1024];
        DatagramPacket dp1 = new DatagramPacket(dnsReceivedArray, dnsReceivedArray.length);
        ds.receive(dp1);
        String dnsReceived = new String(dnsReceivedArray, 0, dp1.getLength());
        System.out.println("The dns address is :");
        System.out.println(dnsReceived);
        System.out.println("The client is disconnected ...");
        ds.close();
    }
}
