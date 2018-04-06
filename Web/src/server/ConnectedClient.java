package server;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ConnectedClient extends Thread {

	ServerSocket serverSocket;
	Socket clientSocket;
	String username;

	public ConnectedClient(ServerSocket _serverSocket, Socket _clientsocket, String _username) {

		this.serverSocket = _serverSocket;
		this.clientSocket = _clientsocket;
		this.username = _username;
		this.start();
	}

	@Override
	public void run() {
		Scanner inStream = null;
		try {
			inStream = new Scanner(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter outStream = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

			String msg;
			while (true) {
				msg = inStream.nextLine();
				outStream.println("echo>> " + msg);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			inStream.close();
		}
	}

}
