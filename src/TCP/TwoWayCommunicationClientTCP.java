/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hema InduKan
 */
package TCP;
import java.io.*;
import java.net.*;
import java.util.*;
public class TwoWayCommunicationClientTCP {
    private final static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        try{
            System.out.print("Enter a message :");
            Socket socket = new Socket("localhost",7000);
            String msg = input.nextLine();
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(msg);
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            String msgReceived = (String)objectInputStream.readObject();
            System.out.println("The message received is :" + msgReceived);
            socket.close();
        }
        catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
