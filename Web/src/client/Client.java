package client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static final int PORT = 1518;

	public static void main(String[] args) {
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(), PORT);
			Scanner in = new Scanner(System.in);
			Scanner inStream = new Scanner(new InputStreamReader(socket.getInputStream()));
			PrintWriter outStream = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
			String msg;

			System.out.println(inStream.nextLine()); // benvenuto
			while (true) {
				System.out.println(inStream.nextLine()); // username
				outStream.println(in.nextLine());
				String accesso = inStream.nextLine();
				System.out.println(accesso);
				if (accesso.equals("Accesso effetuato!"))
					break;
			}

			ClientThread clientThread = new ClientThread(socket);
			clientThread.start();

			while (true) {
				msg = in.nextLine();
				if (msg.equals("/end")) {
					outStream.println("§CONNESSIONE_TERMINATA_DAL_CLIENT");
					socket.close();
					break;
				}
				outStream.println(msg);
			}

			inStream.close();
			in.close();
			outStream.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

	}

}
