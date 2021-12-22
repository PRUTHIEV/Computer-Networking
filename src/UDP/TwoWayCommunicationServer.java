package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class TwoWayCommunicationServer {
    private static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            System.out.println("The server is listening at port 7000");
            DatagramSocket datagramSocket = new DatagramSocket(7000);
            byte[] msgReceviedArray = new byte[1024];
            byte[] msgArray;
            DatagramPacket datagramPacket = new DatagramPacket(msgReceviedArray, msgReceviedArray.length);
            datagramSocket.receive(datagramPacket);
            String msgReceived = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
            System.out.println("The received string is :" + msgReceived);
            System.out.print("Enter a string :");
            String msg = input.nextLine();
            msgArray = msg.getBytes();
            DatagramPacket datagramPacket2 = new DatagramPacket(msgArray, msgArray.length, datagramPacket.getAddress(),
                    datagramPacket.getPort());
            datagramSocket.send(datagramPacket2);
            datagramSocket.close();
            System.out.println("The chat is ended");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
