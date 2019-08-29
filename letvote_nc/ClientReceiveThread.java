import java.net.Socket;
import javax.swing.*;
import java.io.*;

public class ClientReceiveThread implements Runnable {//宣告
	JLabel labelProblem, labelCountQ1, labelCountQ2;
	JRadioButton RadioButtonQ1, RadioButtonQ2;
	Socket cSock;
	int count = 0;

	public ClientReceiveThread(JLabel labelProblem, JLabel labelCountQ1, JLabel labelCountQ2,
			JRadioButton RadioButtonQ1, JRadioButton RadioButtonQ2, Socket socket) {
		this.labelProblem = labelProblem;
		this.labelCountQ1 = labelCountQ1;
		this.labelCountQ2 = labelCountQ2;
		this.RadioButtonQ1 = RadioButtonQ1;
		this.RadioButtonQ2 = RadioButtonQ2;
		this.cSock = socket;
	}

	public void run() {//接收資料(thread)
		BufferedReader clientInput;
		String content;
		while(true) {
			try {
				clientInput = new BufferedReader(new InputStreamReader(cSock.getInputStream()));
				content = clientInput.readLine();
				System.out.println("00000: "+content);
				if (content.equals("problem")) {
					String problemContent, countQ1Content, countQ2Content;
					System.out.println("test0");
					System.out.println("test00");
					problemContent = clientInput.readLine();
					System.out.println("test1");
					countQ1Content = clientInput.readLine();
					System.out.println("test2");
					countQ2Content = clientInput.readLine();
					System.out.println("111111");
					labelProblem.setText(problemContent);
					labelCountQ1.setText(countQ1Content);
					labelCountQ2.setText(countQ2Content);
					
					labelProblem.setVisible(true);
					RadioButtonQ1.setVisible(true);
					RadioButtonQ2.setVisible(true);
					labelCountQ1.setVisible(true);
					labelCountQ2.setVisible(true);
				} else if (content.equals("vote")) {
					String countQ1Content, countQ2Content;
					countQ1Content = clientInput.readLine();
					System.out.println("22222");
					countQ2Content = clientInput.readLine();
					System.out.println("33333");
					System.out.println(countQ1Content);
					System.out.println(countQ2Content);
					labelCountQ1.setText(countQ1Content);
					labelCountQ2.setText(countQ2Content);
				}
			} catch (IOException error) {
				error.printStackTrace();
			}
		}
		
	}

}
