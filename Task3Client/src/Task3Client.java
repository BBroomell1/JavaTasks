import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class Task3Client {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// Hostname is for localhost.
		String hostName = "127.0.0.1";
		int portNum = 0;

		// Check if port number was passed correctly as a parameter to client
		// application.
		if (args.length != 1) {
			System.out.println("Number of parameters passed to server application wrong.");
			System.out.println("Correct number of parameters is 1.");
			System.out.println("Expected parameter is port number.");
			System.out.println("Client Application Exiting.");
			System.exit(0);
		}
		try {
			portNum = Integer.parseInt(args[0]);
		} catch (Exception ex) {
			System.out.println("Parameter could not be cast as an integer.");
			System.out.println(ex.getMessage());
			System.exit(0);
		}

		// Create clientSocket connection, and open two-way stream connection.
		Socket clientSocket;
		PrintWriter outPrint;
		BufferedReader inBuff;
		InputStreamReader inRead;

		System.out.println("Enter GET command followed by : ");
		String tempString = sc.nextLine();
		String inputString;

		try {
			clientSocket = new Socket(hostName, portNum);
			// Using Data Stream to get and send information back to Server.
			DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
			outputStream.writeChars(tempString);
			
			DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
			
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			inputString = inFromServer.readLine();
			System.out.println("Server Says: " + inputString);
			/*
			 * This uses Readers/Writers outPrint = new
			 * PrintWriter(clientSocket.getOutputStream(), true); inRead = new
			 * InputStreamReader(clientSocket.getInputStream()); inBuff = new
			 * BufferedReader(inRead);
			 * 
			 * 
			 * System.out.println("Server says: " + inBuff.readLine());
			 */
		} catch (UnknownHostException ex) {
			ex.getMessage();
			System.exit(1);
		} catch (IOException ex) {
			ex.getMessage();
			System.exit(1);
		}

	}

}
