import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class serverthread implements Runnable {
	Socket socket;
	MutiBoardcast mutiBoardcast;

	public serverthread(Socket socket, MutiBoardcast mutiBoardcast) {
		this.socket = socket;
		this.mutiBoardcast = mutiBoardcast;
	}

	public void run() { // 創建一個P1
		BufferedReader clientInput;
		String content, content2;
		while (true) {
			try {
				clientInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				content = clientInput.readLine();
				content2 = clientInput.readLine();
				System.out.println(content + " " + content2);
				if (content.equals("problem")) {
					System.out.println("problem in");
					mutiBoardcast.sendProblem(content2);
				} else {
					mutiBoardcast.sendMsg(socket, content2);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

//			String P1 = name;
//			System.out.println(number);
//			switch (number) {
//			case 0:
//				Answer = "0";
//				n = n + 1;
//				break;
//			case 1:
//				Answer = "1";
//				y = y + 1;
//				break;
//			case 2:
//				Answer = "2";
//				y = y - 1;
//				n = n + 1;
//				break;
//			case 3:
//				Answer = "3";
//				y = y + 1;
//				n = n - 1;
//				break;
//			case 4:
//				Answer = "4";
//				break;
//			default:
//				break;
//			}
//
//			System.out.println(P1 + " " + Answer);
//			System.out.println(y + " " + n);
//
//			X.Ssend(name, y, n);

	}
}