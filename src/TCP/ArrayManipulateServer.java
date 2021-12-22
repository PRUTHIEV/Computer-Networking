package TCP;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ArrayManipulateServer {
    public static void main(String[] args) {
        try{
            ServerSocket ss = new ServerSocket(7000);
            System.out.println("The server is started at port 7000 ....");
            Socket s = ss.accept();
            if (s.isConnected()) {
                System.out.println("The client is connected");
            }
            ObjectInputStream objInput = new ObjectInputStream(s.getInputStream());
            ArrayList<Integer> arr = (ArrayList<Integer>) objInput.readObject();
            System.out.println("The resut is :" + arr);
            System.out.println("Server has read the array!!!");
            while (true) {
                ObjectInputStream objInput1 = new ObjectInputStream(s.getInputStream());
                String query = (String) objInput1.readObject();
                String result = null;
                if(query.contains("min")){
                    Integer min = Integer.MAX_VALUE;
                    for (int i = 0; i < arr.size(); i++) {
                        if (min > arr.get(i))
                            min = arr.get(i);
                    }
                    result = "The result is :" + min;
                }
                else if(query.contains("max")){
                    Integer max = Integer.MIN_VALUE;
                    for (int i = 0; i < arr.size(); i++) {
                        if (max < arr.get(i))
                            max = arr.get(i);
                    }
                    result = "The result is :" + max;
                }
                else if(query.contains("sum")){
                    Integer sum = 0;
                    for (int i = 0; i < arr.size(); i++) {
                        sum += arr.get(i);
                    }
                    result = "The result is :" + sum;
                }
                else {
                    result = "The result is Bye";
                }
                ObjectOutputStream objOutput = new ObjectOutputStream(s.getOutputStream());
                objOutput.writeObject(result);
                if (result.toLowerCase().contains("bye")) {
                    break;
                }
                
            }
            s.close();
            ss.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
