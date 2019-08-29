import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UI extends JFrame implements ActionListener {
	int count = 0;

	JButton btn;
	JLabel label;
	JTextField problemText;
	ButtonGroup G1;
	ButtonGroup G2;

	JLabel labelProblem, labelCountQ1, labelCountQ2;
	JRadioButton RadioButtonQ1;
	JRadioButton RadioButtonQ2;
	Socket cSock;
	ClientReceiveThread clientReceiveThread;

	public static void main(String[] args) {
		UI ui = new UI();
	}

	public UI() {
		setSize(375, 812);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		label = new JLabel("problem:");
		label.setLocation(4, 20);
		label.setSize(70, 50);

		problemText = new JTextField(20);
		problemText.setLocation(60, 30);
		problemText.setSize(200, 30);

		btn = new JButton("continue");
		btn.addActionListener(this);
		btn.setLocation(270, 30);
		btn.setSize(85, 35);
		btn.setVisible(true);

		G1 = new ButtonGroup();

		JPanel panel = new JPanel();
		panel.setSize(375, 80);
		panel.setLocation(-30, 60);

		JPanel panel2 = new JPanel();
		panel2.setSize(375, 160);
		panel2.setLocation(-20, 170);
		panel2.setVisible(true);

		labelProblem = new JLabel();
		labelProblem.setSize(300, 100);
		labelProblem.setLocation(135, 110);
		RadioButtonQ1 = new JRadioButton("yes");
		RadioButtonQ1.setName("RadioButtonQ1");
		RadioButtonQ1.setVisible(false);
		RadioButtonQ2 = new JRadioButton("no");
		RadioButtonQ2.setName("RadioButtonQ2");
		RadioButtonQ2.setVisible(false);

		G2 = new ButtonGroup();
		G2.add(RadioButtonQ1);
		G2.add(RadioButtonQ2);

		RadioButtonQ1.addActionListener(this);
		RadioButtonQ2.addActionListener(this);

		labelCountQ1 = new JLabel("0");
		labelCountQ1.setSize(300, 100);
		labelCountQ1.setLocation(70, 220);
		labelCountQ1.setVisible(false);
		labelCountQ2 = new JLabel("0");
		labelCountQ2.setSize(300, 100);
		labelCountQ2.setLocation(70, 220);
		labelCountQ2.setVisible(false);

		panel2.add(labelProblem);
		panel2.add(RadioButtonQ1);
		panel2.add(RadioButtonQ2);
		panel2.add(labelCountQ1);
		panel2.add(labelCountQ2);

		add(label);
		add(problemText);
		add(btn);
		add(panel);
		add(labelProblem);
		add(panel2);
		setVisible(true);

		try {

			cSock = new Socket("127.0.0.1", 8000);
			clientReceiveThread = new ClientReceiveThread(labelProblem, labelCountQ1, labelCountQ2, RadioButtonQ1,
					RadioButtonQ2, cSock);
			System.out.println(cSock);
			new Thread(clientReceiveThread).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		PrintStream writer;
		try {
			writer = new PrintStream(cSock.getOutputStream(), true);
			if (e.getActionCommand().equals("continue")) {
				
				System.out.println("continue");
				writer.println("problem");
				writer.println(problemText.getText());
				

				labelProblem.setText(problemText.getText());
				labelProblem.setVisible(true);
				RadioButtonQ1.setVisible(true);
				RadioButtonQ2.setVisible(true);
				labelCountQ1.setVisible(true);
				labelCountQ2.setVisible(true);
			}
			if (e.getActionCommand().equals("yes")) {
				writer.println("vote");
				writer.println("1");
				System.out.println("yes");
			} else if (e.getActionCommand().equals("no")) {
				writer.println("vote");
				writer.println("0");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
