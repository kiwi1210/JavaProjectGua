import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.*;
import java.awt.Color;

import javax.management.loading.PrivateClassLoader;
import javax.swing.*;
import java.util.ArrayList;

public class DivinationFrame extends JFrame {
	private JButton btnA, restartBtn;
	private JLabel la10, la11, la20, la21, la30, la31, coin1, coin2, coin3;
	private JTextField tf10, tf11, tf20, tf21, tf30, tf31;
	private JTextArea sixYio;
	private JPanel panel2;
	private int numClicks = 0;
	private String position = "";
	private ArrayList<String> yioArray = new ArrayList<>();
	private final String choised;

	public DivinationFrame(String ch) {
		choised = ch;
		sixYio = new JTextArea();
		sixYio.setBackground(new Color(255, 255, 255));
		sixYio.setFont(new Font("Arial", Font.BOLD, 28));
		sixYio.setBounds(700, 148, 162, 215);
		sixYio.setEditable(false);

		for (int i = 0; i < 6; i++) {
			yioArray.add("");
		}
		createTextField();
		createButton();
		createLabel();
		createLayout();
		setTitle("三錢法易經卜算-算卦");
	}

	private void createTextField() {
		tf10 = new JTextField("");
		tf10.setHorizontalAlignment(SwingConstants.CENTER);
		tf10.setFont(new Font("Arial", Font.BOLD, 20));
		tf10.setBounds(75, 84, 40, 37);

		tf11 = new JTextField("");
		tf11.setHorizontalAlignment(SwingConstants.CENTER);
		tf11.setFont(new Font("Arial", Font.BOLD, 20));
		tf11.setBounds(75, 284, 40, 37);

		tf20 = new JTextField("");
		tf20.setHorizontalAlignment(SwingConstants.CENTER);
		tf20.setFont(new Font("Arial", Font.BOLD, 20));
		tf20.setBounds(258, 84, 40, 37);

		tf21 = new JTextField("");
		tf21.setHorizontalAlignment(SwingConstants.CENTER);
		tf21.setFont(new Font("Arial", Font.BOLD, 20));
		tf21.setBounds(258, 284, 40, 37);

		tf30 = new JTextField("");
		tf30.setHorizontalAlignment(SwingConstants.CENTER);
		tf30.setFont(new Font("Arial", Font.BOLD, 20));
		tf30.setBounds(440, 84, 40, 37);

		tf31 = new JTextField("");
		tf31.setHorizontalAlignment(SwingConstants.CENTER);
		tf31.setFont(new Font("Arial", Font.BOLD, 20));
		tf31.setBounds(441, 284, 40, 37);
//關於在空格中提供預設值是否能提高易於理解程度及使用便利，試用者看法兩極，故此部分程式碼以註解方式保留		
//		tf10.setText("1");
//		tf11.setText("2");
//		tf20.setText("3");
//		tf21.setText("4");
//		tf30.setText("5");
//		tf31.setText("6");

	}

	private void createButton() {
		btnA = new JButton("\u6309\u6B64\u751F\u6210\u65B0\u723B (0/6)");
		btnA.setBackground(new Color(245, 222, 179));
		btnA.setFont(new Font("標楷體", Font.BOLD, 24));

		restartBtn = new JButton("重新再來");
		restartBtn.setFont(new Font("標楷體", Font.BOLD, 24));
		restartBtn.setBackground(new Color(245, 222, 179));

		btnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				numClicks++;
				btnA.setText(String.format("按此生成新爻 (%s/6)", numClicks));
				boolean tf1 = false, tf2 = false, tf3 = false;
				int c1, c2, c3;
				int sum1 = Integer.parseInt(tf10.getText()) + Integer.parseInt(tf11.getText());
				int sum2 = Integer.parseInt(tf20.getText()) + Integer.parseInt(tf21.getText());
				int sum3 = Integer.parseInt(tf30.getText()) + Integer.parseInt(tf31.getText());

				if (sum1 % 2 == 0 || sum2 % 2 == 0 || sum3 % 2 == 0) {
					JOptionPane.showMessageDialog(panel2, "正面與反面不可同為奇(偶)數", "Error", JOptionPane.ERROR_MESSAGE);
					restartBtn.doClick();
				}
				if (Math.random() >= 0.5) {
					tf1 = true;
				}
				if (Math.random() >= 0.5) {
					tf2 = true;
				}
				if (Math.random() >= 0.5) {
					tf3 = true;
				}

				if (tf1) {
					c1 = Integer.parseInt(tf10.getText());
					coin1.setIcon(new ImageIcon("images//coin2small.png"));
				} else {
					c1 = Integer.parseInt(tf11.getText());
					coin1.setIcon(new ImageIcon("images//coin2BackSmall.png"));
				}

				if (tf2) {
					c2 = Integer.parseInt(tf20.getText());
					coin2.setIcon(new ImageIcon("images//coin2small.png"));
				} else {
					c2 = Integer.parseInt(tf21.getText());
					coin2.setIcon(new ImageIcon("images//coin2BackSmall.png"));
				}

				if (tf3) {
					c3 = Integer.parseInt(tf30.getText());
					coin3.setIcon(new ImageIcon("images//coin2small.png"));
				} else {
					c3 = Integer.parseInt(tf31.getText());
					coin3.setIcon(new ImageIcon("images//coin2BackSmall.png"));
				}

				int n = 6 - numClicks;
				if ((c1 + c2 + c3) % 2 == 0) {
					yioArray.set(n, "__  __");
					position += "0";
				} else {
					yioArray.set(n, "_____");
					position += "1";
				}

				sixYio.setText("");
				for (int j = 0; j < 6; j++) {
					sixYio.append(String.format("%s\n", yioArray.get(j)));
				}

				if (numClicks >= 6) {
					btnA.setEnabled(false);
					numClicks = 0;
					for (int i = 0; i < 6; i++) {
						yioArray.set(i, " ");
					}
					InterpretFrame interpret;
					try {
						interpret = new InterpretFrame(choised, position);
						interpret.setBackground(new Color(241, 220, 182));
						interpret.setBounds(100, 100, 620, 510);
						interpret.getContentPane().setLayout(null);
						interpret.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						interpret.setVisible(true);

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}

		});

		restartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnA.setEnabled(true);
				sixYio.setText("");
				tf10.setText("");
				tf11.setText("");
				tf20.setText("");
				tf21.setText("");
				tf30.setText("");
				tf31.setText("");
				sixYio.setText("");
				for (int i = 0; i < 6; i++) {
					yioArray.set(i, " ");
				}
				
				position = "";
				numClicks = 0;
				btnA.setText(String.format("按此生成新爻 (%s/6)", numClicks));
			}
		});
	}

	public void createLabel() {
		la10 = new JLabel("");
		la10.setIcon(new ImageIcon("images//coin2.png"));
		la11 = new JLabel("");
		la11.setIcon(new ImageIcon("images//coin2Back.png"));
		la20 = new JLabel("");
		la20.setIcon(new ImageIcon("images//coin2.png"));
		la21 = new JLabel("");
		la21.setIcon(new ImageIcon("images//coin2Back.png"));
		la30 = new JLabel("");
		la30.setIcon(new ImageIcon("images//coin2.png"));
		la31 = new JLabel("");
		la31.setIcon(new ImageIcon("images//coin2Back.png"));

		coin1 = new JLabel();
//		coin1.setIcon(new ImageIcon("C:\\eclipse-workspace\\1102FinalProject\\src\\coin2small.png"));
		coin1.setHorizontalAlignment(SwingConstants.CENTER);

		coin2 = new JLabel();
//		coin2.setIcon(new ImageIcon("C:\\eclipse-workspace\\1102FinalProject\\src\\coin2small.png"));
		coin2.setHorizontalAlignment(SwingConstants.CENTER);

		coin3 = new JLabel();
//		coin3.setIcon(new ImageIcon("C:\\eclipse-workspace\\1102FinalProject\\src\\coin2small.png"));
		coin3.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public void createLayout() {
		add(tf10);
		add(tf11);
		add(tf20);
		add(tf21);
		add(tf30);
		add(tf31);

		add(sixYio);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 550, 400);
		add(panel);
		panel.setLayout(new GridLayout(2, 3));

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 400, 936, 63);
		add(panel_1);
		panel_1.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(550, 0, 386, 120);
		add(panel_2);
		panel_2.setLayout(new GridLayout(1, 3, 0, 0));

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBounds(550, 120, 386, 280);
		add(panel_3);

		panel.add(la10);
		panel.add(la20);
		panel.add(la30);
		panel.add(la11);
		panel.add(la21);
		panel.add(la31);

		panel_1.add(btnA);
		panel_1.add(restartBtn);

		panel_2.add(coin1);
		panel_2.add(coin2);
		panel_2.add(coin3);
	}

}
