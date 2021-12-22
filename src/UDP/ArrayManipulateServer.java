package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class ArrayManipulateServer {
    public static void main(String[] args) {
        try{
            DatagramSocket ds = new DatagramSocket(7000);
            System.out.println("The server is listening to the port 7000 ...");
            byte[]length = new byte[1];
            DatagramPacket dp1 = new DatagramPacket(length, length.length);
            ds.receive(dp1);
            byte[] arr = new byte[(byte)length[0]];
            DatagramPacket dp = new DatagramPacket(arr, arr.length);
            ds.receive(dp);
            System.out.println(Arrays.toString(arr));
            while (true) {
                byte[]str = new byte[1024];
                DatagramPacket dp2 = new DatagramPacket(str, str.length);
                ds.receive(dp2);
                String query = new String(str, 0, dp2.getLength());
                String result = null;
                if (query.contains("min")) {
                    byte min = Byte.MAX_VALUE;
                    for (int i = 0; i < arr.length; i++) {
                        if (min > arr[i]) {
                            min = arr[i];
                        }
                    }
                    result = "The result is :" + min;
                }
                else if (query.contains("max")) {
                    byte max = Byte.MIN_VALUE;
                    for (int i = 0; i < arr.length; i++) {
                        if (max < arr[i]) {
                            max = arr[i];
                        }
                    }
                    result = "The result is :" + max;
                }
                else if (query.contains("sum")) {
                    byte sum = 0;
                    for (int i = 0; i < arr.length; i++)
                        sum += arr[i];
                    result = "The result is :" + sum;
                }
                else {
                    result = "Bye client";
                }
                byte [] resultArray = result.getBytes();
                DatagramPacket dp3 = new DatagramPacket(resultArray, resultArray.length, dp2.getAddress(),
                        dp2.getPort());
                ds.send(dp3);
                if (result.toLowerCase().contains("bye"))
                    break;
            }
            System.out.println("The server chat is ended ....");
            ds.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
