package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class ChatAppServer {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        try{
            System.out.println("The server is started at port 7000 ...");
            DatagramSocket datagramSocket = new DatagramSocket(7000);
            String msg,msgReceived;
            byte []msgReceivedArray = new byte[1024];
            byte [] msgArray;
            do{
                DatagramPacket datagramPacket1 = new DatagramPacket(msgReceivedArray,msgReceivedArray.length);
                datagramSocket.receive(datagramPacket1);
                msgReceived = new String(datagramPacket1.getData(),0,datagramPacket1.getLength());
                System.out.println("The client :" + msgReceived);
                System.out.print("The server is :");
                msg = input.nextLine();
                msgArray = msg.getBytes();
                DatagramPacket datagramPacket2 = new DatagramPacket(msgArray,msgArray.length,datagramPacket1.getAddress(),datagramPacket1.getPort());
                datagramSocket.send(datagramPacket2);
            } while (!msg.toLowerCase().equals("bye"));
            datagramSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("The server chat is ended");
    }
}
