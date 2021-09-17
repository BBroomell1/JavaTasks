import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer implements Runnable{
	protected int portNum;
	protected String rootPath;
	protected ServerSocket serverSocket = null;
	protected boolean stopped = false;
	protected Thread runningThread = null;
	protected ExecutorService threadPool = Executors.newFixedThreadPool(10);
	
	public SocketServer(int portNum, String rootPath)
	{
		this.portNum = portNum;
		this.rootPath = rootPath;
	}
	
	public void run() {
		
		synchronized(this)
		{
			this.runningThread = Thread.currentThread();
		}
		openServerSocket();
		while(stopped() == false)
		{
			Socket clientSocket = null;
			try
			{
				clientSocket = this.serverSocket.accept();
			}catch(IOException ex)
			{
				if(stopped() == true)
				{
					System.out.println("Server has been stopped.");
					break;
				}
				else 
				{
					throw new RuntimeException("Could not establish client connection", ex);
				}
			} // End try catch
			this.threadPool.execute(new Task3Runnable(clientSocket));
		} //  End while
	}
		
		private synchronized boolean stopped()
		{
			return this.stopped;
		}
		
		public synchronized void stop()
		{
			this.stopped = true;
			try
			{
				this.serverSocket.close();
			}catch(IOException ex)
			{
				throw new RuntimeException("Could not close server.", ex);
			}
		}
		
		private void openServerSocket()
		{
			try
			{
				this.serverSocket = new ServerSocket(this.portNum);	
			}catch(IOException ex)
			{
				throw new RuntimeException("Can not open port: " + this.portNum);
			}
		}
	}


/*try {
serverSocket = new ServerSocket(portNum);
} catch(IOException ex)
{
System.out.println("Server Socket could not connect to port number: " + portNum);
System.out.println(ex.getMessage());
}

while(true)
{
try
{
	Socket clientSocket = serverSocket.accept();
	new Thread(new Task3Runnable(clientSocket)).start(); 
} catch(IOException ex)
{
	System.out.println("Could not connect client socket");
	System.out.println(ex.getMessage());
}

}*/
