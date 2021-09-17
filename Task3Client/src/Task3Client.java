import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Task3Client {

	public static void main(String[] args) {
		String hostName = "127.0.0.1";
		int portNum = 5000;
		
		Socket clientSocket;
		PrintWriter outPrint;
		BufferedReader inBuff;
		InputStreamReader inRead;
		
		try
		{
			clientSocket = new Socket(hostName, portNum);
			outPrint = new PrintWriter(clientSocket.getOutputStream(), true);
			inRead = new InputStreamReader(clientSocket.getInputStream());
			inBuff = new BufferedReader(inRead);
			
			outPrint.println("inital calc");
			;
			
			System.out.println("Server says: " + inBuff.readLine());
		}catch(UnknownHostException ex)
		{
			ex.getMessage();
			System.exit(1);
		}catch(IOException ex)
		{
			ex.getMessage();
			System.exit(1);
		} 

	}

}
