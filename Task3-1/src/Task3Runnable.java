import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class Task3Runnable implements Runnable{
	protected Socket clientSocket = null;
	
	public Task3Runnable(Socket clientSocket) 
	{
		this.clientSocket = clientSocket;
	}
	
	public void run() 
	{
		try
		{
			//Create two way communication between client and server
			DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
			DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
			
			// Read the GET request from the inputStream
			//String inputStreamString = inputStream;
			System.out.println("Client Sent: " + inputStream);
			long time = System.currentTimeMillis();
			outputStream.write(("HTTP/1.1 200 OK\n\nTask3Runnable\n").getBytes());
			outputStream.close();
			inputStream.close();
			//System.out.println("Request Processed: " + time);
			
		}catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
}
