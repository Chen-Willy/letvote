import java.net.Socket;

public interface MutiBoardcast {
	public void sendProblem(String problem);
	public void sendMsg(Socket socket, String num);
}
