import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class ClientReceiveThread implements Runnable {
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

	public void run() {
		BufferedReader clientInput;
		String content;
		while(true) {
			try {
				clientInput = new BufferedReader(new InputStreamReader(cSock.getInputStream()));
				content = clientInput.readLine();
				System.out.println("00000: "+content);
				if (content.equals("problem")) {
					String content1, content2, content3;
					System.out.println("test0");
					System.out.println("test00");
					content1 = clientInput.readLine();
					System.out.println("test1");
					content2 = clientInput.readLine();
					System.out.println("test2");
					content3 = clientInput.readLine();
					System.out.println("111111");
					labelProblem.setText(content1);
					labelCountQ1.setText(content2);
					labelCountQ2.setText(content3);
					
					labelProblem.setVisible(true);
					RadioButtonQ1.setVisible(true);
					RadioButtonQ2.setVisible(true);
					labelCountQ1.setVisible(true);
					labelCountQ2.setVisible(true);
				} else if (content.equals("vote")) {
					String content1, content2;
					content1 = clientInput.readLine();
					System.out.println("22222");
					content2 = clientInput.readLine();
					System.out.println("33333");
					System.out.println(content1);
					System.out.println(content2);
					labelCountQ1.setText(content1);
					labelCountQ2.setText(content2);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
