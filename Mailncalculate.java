import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Mailncalculate {

	public static void main(String[] args) {
		ServerMain serverMain = new ServerMain();
		serverMain.start();
	}

}

class ServerMain implements MutiBoardcast {
	private static int y = 0;
	private static int n = 0;
	private static ArrayList<Integer> count1 = new ArrayList<>();

	public static String name;
	public static int content;
	static ArrayList<Socket> clients = new ArrayList<>();

	public void start() {

		ServerSocket serverSock = null;
		Socket cSock = null;
		try {
			serverSock = new ServerSocket(8000);
			while (true) {
				System.out.println("Server started...(Scatchy)");
				cSock = serverSock.accept(); 
				clients.add(cSock);
				count1.add(0);
				new Thread(new serverthread(cSock, this)).start();
				System.out.println("client connect...");
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void sendProblem(String problem) {
		PrintStream writer;
		System.out.println(clients.size());
		for (int i = 0; i < clients.size(); i++) {
			try {
				writer = new PrintStream(clients.get(i).getOutputStream(), true);
				writer.println("problem");
				writer.println(problem);
				writer.println(0);
				writer.println(0);
				System.out.println("problem send");
			} catch (IOException e) { // �ǦW��
				e.printStackTrace();
			}
		}
	}

	@Override
	public void sendMsg(Socket socket, String num) {
		PrintStream writer;
		int flag = 0;
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i) == socket && count1.get(i) == 0) {

				flag = 1;
				count1.set(i, 1);
			}
		}
		if (flag == 1) {
			switch (num) {
			case "0":
				n += 1;
				break;
			case "1":
				y += 1;
				break;
			}
		} else {
			switch (num) {
			case "0":
				n += 1;
				y -= 1;
				break;
			case "1":
				y += 1;
				n -= 1;
				break;
			}
		}
		for (int i = 0; i < clients.size(); i++) {
			try {
				System.out.println("n:" + n);
				System.out.println("y:" + y);
				writer = new PrintStream(clients.get(i).getOutputStream(), true);
				writer.println("vote");
				writer.println(y + "");
				writer.println(n + "");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}