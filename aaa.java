import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 static JPanel pan;
 int x;
 int y = x;
 int n = x;

 JButton btn;
 JLabel label;
 JTextField problemText;
 ButtonGroup G1;
 JRadioButton RadioButton1;
 JRadioButton RadioButton2;

 public static void main(String[] args) {
  UI ui = new UI();
 }

 public UI() {
  JFrame frame = new JFrame();
  frame.setSize(375, 812);
  frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

  JPanel panel1 = new JPanel();
  panel1.setSize(375, 500);
  panel1.setVisible(true);

  JButton btn = new JButton("continue");

  MyButtonListener mblistener = new MyButtonListener();
  btn.addActionListener(this);
  btn.setLocation(200, 650);
  btn.setSize(130, 50);

  JLabel label = new JLabel("problem:");
  label.setLocation(40, 100);
  label.setSize(50, 50);

  JTextField problemText = new JTextField(20);
  problemText.setLocation(100, 100);
  problemText.setSize(200, 50);

  RadioButton1 = new JRadioButton("Yes");
  RadioButton2 = new JRadioButton("No");
  RadioButton1.setSelected(true);
  RadioButton1.addActionListener(this);
  RadioButton2.addActionListener(this);
  ButtonGroup G1 = new ButtonGroup();

  panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
  G1.add(RadioButton1);
  G1.add(RadioButton2);
  panel1.add(label);
  panel1.add(problemText);
  panel1.add(btn);
  panel1.add(RadioButton1);
  panel1.add(RadioButton2);
  frame.add(panel1);
  frame.setVisible(true);

  getContentPane().setBackground(Color.PINK);
  // setVisible(true);

 }

 @Override
 public void actionPerformed(ActionEvent e) {
  if (e.getActionCommand().equals("continue")) {
   if (RadioButton1.isSelected() == true) {
    y = 1;
    System.out.println("yes");
   } else if (RadioButton2.isSelected() == true) {
    n = 1;
    switch (x = 1) {
    case '0':
     y = y + 1;
     break;
    case '1':
     n = n + 1;
     break;
    case '2':
     y = y + 1;
     n = n - 1;
     break;
    case '3':
     y = y - 1;
     n = n + 1;
     break;
    }
    System.out.println("no");
   }
  }
 }
}
//test push
//test branch