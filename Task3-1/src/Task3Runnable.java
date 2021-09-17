import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class Task3Runnable implements Runnable {
	// Class variables
	protected Socket clientSocket = null;
	protected String rootPath;
	protected String inputString;
	protected String fullPath;

	// Constructor
	public Task3Runnable(Socket clientSocket, String rootPath) {
		this.clientSocket = clientSocket;
		this.rootPath = rootPath;
	}

	// Logic to see if
	public void run() {
		try {
			// Create two way communication between client and server
			DataInputStream fileInputStream;
			PrintStream outputStream = new PrintStream(clientSocket.getOutputStream());
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			// Check if the rootPath + path passed from client server is not null
			inputString = inFromClient.readLine();
			System.out.println("From Client: " + inputString);
			if ((fullPath = rootPath + inputString) != null) {
				// Check if file path is a valid location
				// If not valid location or if file is not found send back fileNotFoundHeader
				File file = new File(fullPath);
				if (file.exists() && !file.isDirectory()) {
					// Try to send the contents of the file with the OK header
					// If not successful send back fileNotFoundHeader
					try {
						fileInputStream = new DataInputStream(new FileInputStream(file));
						fileFoundHeader(outputStream);
						sendFileContents(outputStream, fileInputStream, (int) file.length());
					} catch (Exception ex) {
						fileNotFoundHeader(outputStream, "Cannot find file: " + fullPath + "\n");
					}
				} else {
					fileNotFoundHeader(outputStream, "Cannot find file: " + fullPath + "\n");
				}
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// Add the root and filePath sent from client and return the filePath String
	static String getPath(String msg, String rootPath) {
		if (msg.length() == 0 || msg.substring(0, 3) == "GET") {
			return null;
		}

		String path = rootPath + msg.substring(msg.indexOf(' ') + 1);
		path = path.substring(0, path.indexOf(' '));
		return path;
	}

	// Check the localhost for the file that was determined above in getPath method.
	static void fileFoundHeader(PrintStream outputStream) {
		outputStream.print("HTTP:/1.0 200 OK\n");
		outputStream.print("Content-type: text/html\n");
	}

	// If file is not found return FileNotFound Header
	static void fileNotFoundHeader(PrintStream outputStream, String errormsg) {
		outputStream.print("HTTP:/1.0 404 Not Found\n");
		outputStream.print("Content-type: text/html\n\n");
		outputStream.print(errormsg + "\n");
	}

	static void sendFileContents(PrintStream outputStream, DataInputStream fileInputStream, int fileLength) {

		try {
			byte buff[] = new byte[fileLength];
			fileInputStream.readFully(buff);
			outputStream.write(buff, 0, fileLength);
			fileInputStream.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}
