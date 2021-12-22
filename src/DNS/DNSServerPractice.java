package DNS;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URL;

public class DNSServerPractice {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket(7000);
        byte [] receivedArray = new byte[1024];
        DatagramPacket dp = new DatagramPacket(receivedArray, receivedArray.length);
        ds.receive(dp);
        String receivedURL = new String(receivedArray, 0, dp.getLength());
        System.out.println("The received url is :" + receivedURL);
        InetAddress ip = InetAddress.getByName(new URL(receivedURL).getHost());
        System.out.println("The corresponding DNS address is fetched and sending to the client ...");
        byte [] dnsAddress = ip.toString().getBytes();
        DatagramPacket dp1 = new DatagramPacket(dnsAddress, dnsAddress.length, dp.getAddress(), dp.getPort());
        ds.send(dp1);
        ds.close();
        System.out.println("The server connection is closed ");
    }
}
