import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args){

        String serverName = "Ceres Fauna";

        try{

            // Start the server
            System.out.println("Server of " + serverName + " starting...");
            ServerSocket ss = new ServerSocket(3901);
            System.out.println("Server of " + serverName + " | Waiting for client...");

            Socket soc = ss.accept();

            // Establish connection with client and retrieve client name
            BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            String clientName = in.readLine();
            System.out.println("Server of " + serverName + " | Connection established with Client " + clientName + "!");
            
            // Generate a random number between 1 to 100
            int rand = (int)(Math.random() * 100) + 1;
            System.out.println("Server of " + serverName + " | Random number = " + rand);

            // Retrieve user input from client
            in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            int num = Integer.parseInt(in.readLine());
            System.out.println("Client of " + serverName + " | Input number = " + num);

            // Compute for the sum and displays it
            System.out.println("Server | Computing for sum");
            int sum = rand + num;
            String retMsg = "The sum of " + rand + " + " + num + " = " + sum;
            System.out.println(retMsg);

            // Return the server name and chosen number to the client
            PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
            out.println(serverName);
            out.println(rand);
            
            // Close the socket after executing
            ss.close();
        } catch(Exception e){
            // Catch any error that may occur
            e.printStackTrace();
        }

    }
}
