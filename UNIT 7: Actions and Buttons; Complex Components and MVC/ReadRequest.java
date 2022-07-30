import java.io.*;
import java.net.*;
import java.util.*;

public class ReadRequest {
	private final static int LISTENING_PORT = 5055;
	private final static String ROOT_DIRECTORY  = System.getProperty("user.dir");

	public static void main(String[] args) {
		ServerSocket serverSocket;

		try {
			serverSocket = new ServerSocket(LISTENING_PORT);
		}
		catch (Exception e) {
			System.out.println("Failed to create listening socket.");
			return;
		}

		System.out.println("Listening on port " + LISTENING_PORT);

		try {
			while (true) {
				Socket connection = serverSocket.accept();
				System.out.println("\nConnection from "
						+ connection.getRemoteSocketAddress());
				ConnectionThread thread = new ConnectionThread(connection);
				thread.start();
			}
		}
		catch (Exception e) {
			System.out.println("Server socket shut down unexpectedly!");
			System.out.println("Error: " + e);
			System.out.println("Exiting.");
		}

	}

	static void handleConnection(Socket connection) {
		try {
			Scanner in = new Scanner(connection.getInputStream());
			OutputStream socketOut = connection.getOutputStream();
			PrintWriter out = new PrintWriter(socketOut, true);

			String token = in.next();         //first token
			if (!token.equals("GET")) {
				sendErrorResponse(501, "Not Implemented", socketOut);
				return;
			}
			String path = in.next();                //second token
			token = in.next();                //third token
			if (!token.equals("HTTP/1.1") && !token.equals("HTTP/1.0")) {
				sendErrorResponse(400, "Bad Request", socketOut);
				return;
			}

			File file = new File(ROOT_DIRECTORY+path);
			if (!file.exists()) {
				sendErrorResponse(404, "Not Found", socketOut);
				return;
			}
			if (!file.canRead()) {
				sendErrorResponse(403, "Forbidden", socketOut);
				return;
			}
			if (file.isDirectory()) file = new File(ROOT_DIRECTORY+ "\\index.html" );

			//sending the status, headers, empty line
			out.println("HTTP\r\n");
			out.print("HTTP/1.1 200 OK\n");
			out.print("Connection: close\n");
			out.print("Content-Length: "+file.length()+"\n");
			out.print("Content-Type: "+getMimeType(path)+"\n");
			out.print("\n");
			out.flush();

			sendFile(file, connection.getOutputStream());
		}

		catch (Exception e) {
			System.out.println("HTTP/1.1 500 Internal Server Error");
		}

		finally {
			try {
				connection.close();
			}
			catch(Exception e) {}
			System.out.println("Connection finally closed");
		}
	}

	static void sendErrorResponse(int errorCode, String errorMsg, OutputStream socketOut) {
		PrintWriter out = new PrintWriter(socketOut);
		out.println("HTTP\r\n");
		out.print("HTTP/1.1 "+errorCode+" "+errorMsg+"\r\n");
		out.print("Connection: close\n");
		out.flush();
	}

	private static String getMimeType(String fileName) {
		int pos = fileName.lastIndexOf('.');
		if (pos < 0)  // no file extension in name
			return "x-application/x-unknown";
		String ext = fileName.substring(pos+1).toLowerCase();
		if (ext.equals("txt")) return "text/plain";
		else if (ext.equals("html")) return "text/html";
		else if (ext.equals("htm")) return "text/html";
		else if (ext.equals("css")) return "text/css";
		else if (ext.equals("js")) return "text/javascript";
		else if (ext.equals("java")) return "text/x-java";
		else if (ext.equals("jpeg")) return "image/jpeg";
		else if (ext.equals("jpg")) return "image/jpeg";
		else if (ext.equals("png")) return "image/png";
		else if (ext.equals("gif")) return "image/gif";
		else if (ext.equals("ico")) return "image/x-icon";
		else if (ext.equals("class")) return "application/java-vm";
		else if (ext.equals("jar")) return "application/java-archive";
		else if (ext.equals("zip")) return "application/zip";
		else if (ext.equals("xml")) return "application/xml";
		else if (ext.equals("xhtml")) return"application/xhtml+xml";
		else return "x-application/x-unknown";
		// Note:  x-application/x-unknown  is something made up;
		// it will probably make the browser offer to save the file.
	}

	private static void sendFile(File file, OutputStream socketOut) throws
	IOException {
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		OutputStream out = new BufferedOutputStream(socketOut);
		while (true) {
			int x = in.read(); // read one byte from file
			if (x < 0)
				break; // end of file reached
			out.write(x);  // write the byte to the socket
		}
		out.flush();
	}

	private static class ConnectionThread extends Thread {
		Socket connection;
		ConnectionThread(Socket connection) {
			this.connection = connection;
		}
		public void run() {
			handleConnection(connection);
		}
	}

}


