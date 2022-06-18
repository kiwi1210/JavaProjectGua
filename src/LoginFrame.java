import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

import com.mysql.jdbc.Connection;
import java.util.ArrayList;
public class LoginFrame extends JFrame {
	private JButton enrollButton, loginButton;
	private JLabel uLabel, pLabel;
	private JTextField uTextField, pTextField;
	private int TEXTFIELD_LENGTH = 30;
	private JPanel overallPanel;
	private ArrayList<String> names = new ArrayList<>();
	private CommentFrame frame;
	private Connection conn;

	public LoginFrame() {
		setTitle("三錢法易經卜算-登入");
		getContentPane().setBackground(new Color(241, 220, 182));
		creatGUI();
		creatButton();

		String server = "jdbc:mysql://140.119.19.73:3315/";
		String database = "tuegroup13"; // change to your own database
		String url = server + database + "?useSSL=false";
		String username = "tuegroup13"; // change to your own username
		String password = "dvj4585"; // change to your own password

		try {
			conn = (Connection) DriverManager.getConnection(url, username, password);
			System.out.println("DB Connectd");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void creatGUI() {

		uLabel = new JLabel("\u4F7F\u7528\u8005\u540D\u7A31: ");
		uLabel.setFont(new Font("標楷體", Font.BOLD, 18));
		uLabel.setBounds(30, 35, 124, 30);
		add(uLabel);

		pLabel = new JLabel("\u5BC6\u78BC: ");
		pLabel.setFont(new Font("標楷體", Font.BOLD, 18));
		pLabel.setBounds(90, 94, 62, 30);
		add(pLabel);

		uTextField = new JTextField();
		uTextField.setFont(new Font("標楷體", Font.PLAIN, 20));
		uTextField.setBounds(151, 35, 291, 30);
		add(uTextField);

		pTextField = new JTextField(TEXTFIELD_LENGTH);
		pTextField.setFont(new Font("標楷體", Font.PLAIN, 20));
		pTextField.setBounds(151, 94, 291, 30);
		add(pTextField);

		enrollButton = new JButton("\u8A3B\u518A");
		enrollButton.setBackground(new Color(255, 255, 255));
		enrollButton.setFont(new Font("標楷體", Font.BOLD, 20));
		enrollButton.setBounds(125, 163, 110, 30);
		add(enrollButton);

		loginButton = new JButton("\u767B\u5165");
		loginButton.setBackground(new Color(255, 255, 255));
		loginButton.setFont(new Font("標楷體", Font.BOLD, 20));
		loginButton.setBounds(280, 163, 110, 30);
		add(loginButton);

	}

	public void creatButton() {
		enrollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				testEnrollData();
			}
		});
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				testLoginData();
			}
		});
	}

	public void testEnrollData() {
		String name = uTextField.getText();
		String passeord = pTextField.getText();
		
		if (name .equals("")|| passeord .equals("")) {
			JOptionPane.showMessageDialog(loginButton, "使用者名稱與密碼不可為空白", "Error", JOptionPane.ERROR_MESSAGE);
		}
		if (findName(name)) {
			JOptionPane.showMessageDialog(loginButton, "此使用者已存在", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				Statement stat;
				stat = conn.createStatement();
				int n = 0;
				String query = "SELECT rank FROM `Comment`;";
				if (stat.execute(query)) {
					ResultSet result = stat.getResultSet();
					while (result.next()) {
						n++;
					}
					result.close();
				}
				query = String.format("INSERT  INTO `Comment` (ID,UserName,Password) VALUE (%s,'%s','%s') ;",n+1, name,
						passeord);
				stat.execute(query);
				JOptionPane.showMessageDialog(loginButton, "註冊成功", "info", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void testLoginData() {
		String name = uTextField.getText();
		String password = pTextField.getText();
		boolean b = false;
		ArrayList<String> rPassword =new ArrayList<>();

		if (name.equals("") || password.equals("")) {
			JOptionPane.showMessageDialog(loginButton, "使用者名稱或密碼不可為空白", "Error", JOptionPane.ERROR_MESSAGE);
			b = false;
		} else {
			if (findName(name)) {
				try {
					Statement stat;
					stat = conn.createStatement();
					String query = String.format("SELECT Password FROM `Comment` WHERE UserName='%s' ", name);
					if (stat.execute(query)) {
						ResultSet result = stat.getResultSet();
						rPassword = getPassword(result);
						result.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (rPassword.contains(password)) {
					b=true;
				}
				else {
					JOptionPane.showMessageDialog(loginButton, "密碼錯誤", "Error", JOptionPane.ERROR_MESSAGE);
					b = false;
					
				}
			} else {
				JOptionPane.showMessageDialog(loginButton, "此使用者尚未註冊", "Error", JOptionPane.ERROR_MESSAGE);
				b = false;
			}
		}
		if (b) {
			frame = new CommentFrame(conn, name, password);
			frame.getContentPane().setBackground(new Color(241, 220, 182));
			frame.setBounds(100, 100, 750, 640);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			frame.setVisible(true);
		}

	}

	public void updateNames() {
		names.clear();
		System.out.print(names.size());
		try {
			Statement stat;
			stat = conn.createStatement();
			String query = String.format("SELECT UserName FROM `Comment`");
			if (stat.execute(query)) {
				ResultSet result = stat.getResultSet();
				while(result.next()) {
					names.add(result.getString(1));
				}
				result.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean findName(String name) {
		updateNames();
		boolean b=false;
		for(String n:names) {
			if(n.equals(name)) {
				b=true;
			}
		}
		return b;
	}
	public ArrayList<String> getPassword(ResultSet result) throws SQLException{
		ArrayList<String> rPassword =new ArrayList<>();
		while (result.next()) {
			rPassword.add(result.getString(1));
		}
		return rPassword;
	}
	
}
