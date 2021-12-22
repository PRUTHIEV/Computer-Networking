package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ChatAppClient {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        try{
            String msg, msgReceived;
            byte[] msgArray;
            byte[] msgReceviedArray = new byte[1024];
            DatagramSocket datagramSocket = new DatagramSocket();
            do{
                System.out.print("The client is : ");
                msg = input.nextLine();
                msgArray = msg.getBytes();
                DatagramPacket datagramPacket1 = new DatagramPacket(msgArray, msgArray.length,
                        InetAddress.getLocalHost(), 7000);
                datagramSocket.send(datagramPacket1);
                DatagramPacket datagramPacket2 = new DatagramPacket(msgReceviedArray, msgReceviedArray.length);
                datagramSocket.receive(datagramPacket2);
                msgReceived = new String(datagramPacket2.getData(), 0, datagramPacket2.getLength());
                System.out.println("The server :" + msgReceived);
            } while (!msg.toLowerCase().equals("bye"));
            datagramSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("The client chat is ended");
    }
}
