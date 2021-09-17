import java.io.IOException;

public class Task3Server {

	public static void main(String[] args) {
		int portNum = 0;
		String rootPath = null;
		if(args.length != 2)
		{
			System.out.println("Number of parameters passed to server application wrong.");
			System.out.println("Correct number of parameters is 2.");
			System.out.println("First expected parameter is port number.");
			System.out.println("Second excepted parameter is root path.");
			System.out.println("Application Exiting.");
			System.exit(0);
		}
		
		// Get the parameters and set portNum to args[0]
		// Set rootPath to args[1].
	    try 
	    {
	    	portNum = Integer.parseInt(args[0]);
	    }catch(Exception ex)
	    {
	    	System.out.println("Parameter could not be cast as an integer.");
	    	System.out.println(ex.getMessage());
	    	System.exit(0);
	    }
	    
	    try 
	    {
	    	rootPath = args[1].toString();
	    }catch(Exception ex)
	    {
	    	System.out.println(ex.getMessage());
	    	System.exit(0);
	    }
		
	    
	    	SocketServer socketServer = new SocketServer(portNum, rootPath);
	    	new Thread(socketServer).start();
	    	
	    	try
	    	{
	    		Thread.sleep(20 * 5000);
	    	}catch(InterruptedException ex)
	    	{
	    		ex.printStackTrace();
	    	}
	    	
	    	System.out.println("Stopping server");
	    	socketServer.stop();

	}

}
