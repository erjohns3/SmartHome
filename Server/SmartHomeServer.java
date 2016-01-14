import java.io.*;
import java.net.*;

public class SmartHomeServer {

	static Device powerStrip = new Device(false, true, false);

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		Socket clientSocket = null;

		try {
			InetAddress host = InetAddress.getLocalHost();
			System.out.println("SERVER ACTIVATED");
			System.out.println("Host Name: " + host.getHostName());
			System.out.println("IP Address: " + host.getHostAddress());
			System.out.println();
		} catch (UnknownHostException uhe) {
			System.out.println("Unknown Host");
		}

		while (true) {
			try {
				serverSocket = new ServerSocket(4444);
				clientSocket = serverSocket.accept();
				in = new BufferedReader(new InputStreamReader(
						clientSocket.getInputStream()));
				out = new PrintWriter(
						new BufferedWriter(new OutputStreamWriter(
								clientSocket.getOutputStream())), true);

				powerStrip.convertFromString(in.readLine());
				out.println(powerStrip.convertToString());

				System.out.println(powerStrip.getStatus("A") + "\t"
						+ powerStrip.getStatus("B") + "\t"
						+ powerStrip.getStatus("C"));

			} catch (UnknownHostException uhe) {
				System.out.println("Unknown Host");
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				try {
					clientSocket.close();
				} catch (IOException ioe1) {
				}
				try {
					serverSocket.close();
				} catch (IOException ioe1) {
				}
			}
		}
	}

}
