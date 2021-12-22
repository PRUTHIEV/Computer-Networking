package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ArrayManipulateClient {
    private static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        try{
            DatagramSocket ds = new DatagramSocket();
            byte[] arr = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
            byte[] length = new byte[1];
            length[0] = (byte) arr.length;
            DatagramPacket dp1 = new DatagramPacket(length, length.length, InetAddress.getLocalHost(), 7000);
            ds.send(dp1);
            DatagramPacket dp = new DatagramPacket(arr, arr.length, InetAddress.getLocalHost(), 7000);
            ds.send(dp);
            while (true) {
                System.out.print("Enter the query :");
                String query = input.nextLine();
                byte [] queryArray = query.getBytes();
                DatagramPacket dp2 = new DatagramPacket(queryArray, queryArray.length, InetAddress.getLocalHost(),
                        7000);
                ds.send(dp2);
                byte [] resultArray = new byte[1024];
                DatagramPacket dp3 = new DatagramPacket(resultArray, resultArray.length);
                ds.receive(dp3);
                String result = new String(resultArray, 0, dp3.getLength());
                System.out.println(result);
                if (query.toLowerCase().contains("bye") && result.toLowerCase().contains("bye"))
                    break;
            }
            System.out.println("The client chat is ended ...");
            ds.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
