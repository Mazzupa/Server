package client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread extends Thread {

	Socket socket;

	public ClientThread(Socket _socket) {
		this.socket = _socket;
	}

	@Override
	public void run() {
		Scanner inStream = null;
		try {
			inStream = new Scanner(new InputStreamReader(socket.getInputStream()));

			while (true)
				System.out.println(inStream.nextLine());

		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (java.util.NoSuchElementException e) {
			return;
		} finally {
			inStream.close();
		}

	}

}
