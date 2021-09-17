import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
			InputStream inputStream = clientSocket.getInputStream();
			OutputStream outputStream = clientSocket.getOutputStream();
			long time = System.currentTimeMillis();
			outputStream.write(("HTTP/1.1 200 OK\n\nTask3Runnable\n").getBytes());
			outputStream.close();
			inputStream.close();
			System.out.println("Request Processed: " + time);
			
		}catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
}
