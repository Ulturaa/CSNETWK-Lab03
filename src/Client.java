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
            // Ask for input again if non-integer input.

            int num;
            while(true) {
                System.out.println("Client | Input a number (1-100): ");
                try{
                    num = Integer.parseInt(userIn.readLine());
                    break;
                } catch(Exception e) {
                    System.out.println("Client | Input invalid! Please enter a valid input");
                }
            }

            // Send the client number to server
            out.println(num);

            // If number is out of range, the server will terminate
            if(num < 1 || num > 100){
                System.out.println("Client | Number out of range!");
                System.out.println("Client | Server will now begin termination!");
                soc.close();
                return;
            }

            // Receiving the server's response
            BufferedReader serverIn = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            String serverName = serverIn.readLine();
            int serverNum = Integer.parseInt(serverIn.readLine());

            // Displaying the server's response
            System.out.println();
            System.out.println("Client | Client Name = " + clientName);
            System.out.println("Client | Server Name = " + serverName);
            System.out.println("Client | Client Number = " + num);
            System.out.println("Client | Server Number = " + serverNum);

            // Compute the Sum
            int sum = num + serverNum;
            System.out.println("Client | Sum = " + sum);
            soc.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}


