import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
    public static void main(String[] args){

        String clientName = "Nanashi Mumei";

        try {
            System.out.println("Client started");
            Socket soc = new Socket("localhost", 3901);

            PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
            out.println(clientName);

            BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));

            // Get integer input from 1-100
            // Ask for input again if out of range or non-integer input.
            while(true) {
                System.out.println("Client | Input a number (1-100): ");
                try{
                    int num = Integer.parseInt(userIn.readLine());
                    if(num >=1 && num <= 100) {
                        out = new PrintWriter(soc.getOutputStream(), true);
                        out.println(num);
                        break;
                    } else{
                        System.out.println("Client | Input invalid! Number must be between 1 and 100");
                    }
                } catch(Exception e) {
                    System.out.println("Client | Input invalid! Please enter a valid input");
                }
            }

            // Implement the return of the sum from server here

            soc.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}


