package DNS;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URL;

public class DNSServer {
    public static void main(String[] args) {
        try{
            DatagramSocket ds = new DatagramSocket(7000);
            byte[] urlReceivedArray = new byte[1024];
            DatagramPacket dp = new DatagramPacket(urlReceivedArray, urlReceivedArray.length);
            ds.receive(dp);
            String urlReceived = new String(dp.getData(), 0, dp.getLength());
            InetAddress ip = InetAddress.getByName(new URL(urlReceived).getHost());
            String ipAddr = ip.toString();
            byte[]ipAddrarr = ipAddr.getBytes();
            DatagramPacket dp1 = new DatagramPacket(ipAddrarr, ipAddrarr.length, dp.getAddress(), dp.getPort());
            ds.send(dp1);
            ds.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Server chat is ended");
    }
}
