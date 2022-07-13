package exceptions;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class exceptions {

	private static void copyStream(InputStream in, OutputStream out) 
			throws IOException {
		int oneByte = in.read();
		while (oneByte >= 0) { // negative value indicates end-of-stream
			out.write(oneByte);
			oneByte = in.read();
		}
	}

	public static void main(String[] args) throws IOException {
		InputStream input = null;
		OutputStream output = null;
		try (Scanner sc =new Scanner(System.in)){
			
			System.out.println("Enter the input data url:");
			String urlString = sc.nextLine();
			System.out.println("Enter the output data file name:");
			String fileName = sc.nextLine();
			
			URL url = new URL(urlString);
			input = url.openStream();
			output = new FileOutputStream(fileName);
			
			copyStream(input, output);
			System.out.println("Copied!");
			
		}
		catch(MalformedURLException e) {
			System.out.println("The URL you entered is not valid.");
			System.out.println("The URL must be complete, starting with http://");
			System.out.println("Please restart the program.");
		}
		catch (FileNotFoundException e) {
			System.out.println("The file you specified is not found, or you don't have permission to access it.");
		}
		catch (IOException e) {
			System.out.println("We couldn't reach the page you're looking for.");
			System.out.println("Try the program again with the anoter URL.");
		}
		finally {
			if(input != null) input.close();
			if(output != null) output.close();
		}	

	}

}
