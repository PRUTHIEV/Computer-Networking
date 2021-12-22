package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class TwoWayCommunicationClient {
    private static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        try{
            DatagramSocket datagramSocket = new DatagramSocket();
            String msg, msgReceived;
            byte[] msgArray;
            byte[] msgReceviedArray = new byte[1024];
            System.out.print("Enter a message :");
            msg = input.nextLine();
            msgArray = msg.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(msgArray, msgArray.length, InetAddress.getLocalHost(),
                    7000);;
            datagramSocket.send(datagramPacket);

            DatagramPacket datagramPacket2 = new DatagramPacket(msgReceviedArray, msgReceviedArray.length);
            datagramSocket.receive(datagramPacket2);

            msgReceived = new String(datagramPacket2.getData(), 0, datagramPacket2.getLength());
            System.out.println("The recceived messgae is :" + msgReceived);

            datagramSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
