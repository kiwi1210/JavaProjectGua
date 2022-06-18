import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.mysql.jdbc.Connection;

public class CommentFrame extends JFrame {

	private JPanel title_panel, outputPanel, operateTitlePanel, operatePanel, executepanel;
	private JTextArea title, outputArea;
	private JLabel column1, column2, column3, column4;
	private JTextField text1, text2, text4;
	private JComboBox<String> rankBox;
	private JRadioButton rbtnAdd, rbtnDelete;
	private JButton commitButton;
	private ButtonGroup group;
	private String name, password;
	private Connection conn;
	private Statement stat;

	public CommentFrame(Connection conn, String name, String password) {
		setTitle("三錢法易經卜算-評論");
		this.name = name;
		this.password = password;
		this.conn = conn;

		showHistoricalComments();
		createLabel();
		createTextField();
		createCombo();
		createRadio();
		createButton();
		createLayout();

	}

	public void showHistoricalComments() {

		outputArea = new JTextArea();
		outputArea.setBackground(new Color(253, 245, 230));
		outputArea.setBounds(10, 60, 716, 275);
		outputArea.setEditable(false);
//		try {
//			stat = conn.createStatement();
//			String query = String.format("SELECT ID,guaID,rank,Comment FROM `Comment` WHERE UserName='%s';", name);
//			if (stat.execute(query)) {
//				ResultSet result = stat.getResultSet();
//				outputArea.setLineWrap(true);
//				outputArea.setText(showResultSet(result));
//				result.close();
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		int n = 0;
		try {
			stat = conn.createStatement();
			String query = String.format("SELECT ID,guaID,rank,Comment FROM `Comment` WHERE UserName='%s';", name);
			if (stat.execute(query)) {
				ResultSet result = stat.getResultSet();
				outputArea.setLineWrap(true);
				outputArea.setText(showResultSet(result));
				result.close();
			}
//			int n = 0;
			query = "SELECT ID FROM `Comment`;";
			if (stat.execute(query)) {
				ResultSet result = stat.getResultSet();
				while (result.next()) {
					n++;
				}
				result.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		outputArea.append("以上為您過去給予的評論，非常感謝您的支持與指教");
		outputArea.append(String.format("\n系統目前總評論數為%s", n));

	}

	private void createLabel() {
		column1 = new JLabel("\u8A55\u8AD6\u7DE8\u865F(\u975E\u5FC5\u586B):  ");
		column1.setHorizontalAlignment(SwingConstants.CENTER);
		column1.setFont(new Font("標楷體", Font.BOLD, 20));

		column2 = new JLabel("\u5366\u7DE8\u865F: ");
		column2.setFont(new Font("標楷體", Font.BOLD, 20));
		column2.setHorizontalAlignment(SwingConstants.CENTER);

		column3 = new JLabel("\u8A55\u5206: ");
		column3.setFont(new Font("標楷體", Font.BOLD, 20));
		column3.setHorizontalAlignment(SwingConstants.CENTER);

		column4 = new JLabel("\u6B61\u8FCE\u7559\u4E0B\u5EFA\u8B70\u3001\u60F3\u6CD5\u6216\u554F\u984C");
		column4.setHorizontalAlignment(SwingConstants.CENTER);
		column4.setFont(new Font("標楷體", Font.BOLD, 20));
		column4.setBounds(0, 426, 736, 45);
	}

	private void createTextField() {
		text1 = new JTextField();
		try {
			stat = conn.createStatement();
			int n = 0;
			String query = "SELECT ID FROM `Comment`;";
			if (stat.execute(query)) {
				ResultSet result = stat.getResultSet();
				while (result.next()) {
					n++;
				}
				result.close();
			}
			text1.setText(String.format("%d", n + 1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		text2 = new JTextField();

		text4 = new JTextField();
		text4.setBounds(0, 472, 736, 70);

	}

	private void createCombo() {
		rankBox = new JComboBox<String>();
		rankBox.addItem("1");
		rankBox.addItem("2");
		rankBox.addItem("3");
		rankBox.addItem("4");
		rankBox.addItem("5");
		rankBox.setFont(new Font("標楷體", Font.BOLD, 24));
	}

	private void createRadio() {
		rbtnAdd = new JRadioButton("\u65B0\u589E");
		rbtnAdd.setBackground(new Color(241, 220, 182));
		rbtnAdd.setFont(new Font("標楷體", Font.BOLD, 24));
		rbtnAdd.setHorizontalAlignment(SwingConstants.CENTER);

		rbtnDelete = new JRadioButton("\u522A\u9664");
		rbtnDelete.setBackground(new Color(241, 220, 182));
		rbtnDelete.setFont(new Font("標楷體", Font.BOLD, 24));
		rbtnDelete.setHorizontalAlignment(SwingConstants.CENTER);

		group = new ButtonGroup();
		group.add(rbtnAdd);
		group.add(rbtnDelete);

		rbtnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				commitButton.setBackground(new Color(127, 255, 0));
			}
		});
		rbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				commitButton.setBackground(new Color(255, 69, 0));
			}
		});

	}

	public void createButton() {

		commitButton = new JButton("\u57F7\u884C");
		commitButton.setFont(new Font("標楷體", Font.BOLD, 30));
		commitButton.setBackground(new Color(241, 220, 182));

		commitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				String op = "";
				String query = "";
				String ID = text1.getText();
				String guaID = text2.getText();
				String rank = (String) rankBox.getSelectedItem();
				String Comment = text4.getText();

				if (rbtnAdd.isSelected()) {
					op = "新增";
				} else if (rbtnDelete.isSelected()) {
					op = "刪除";
				}

				byte[] utf8Bytes;
				try {
					utf8Bytes = Comment.getBytes("UTF-8");
					Comment = new String(utf8Bytes, "UTF-8");
					System.out.print("success");
					System.out.print(Comment);
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					stat = conn.createStatement();
					if (op == "新增") {
						query = String.format("INSERT INTO `Comment` VALUES (%s,%s,%s,'%s','%s','%s');", ID, guaID,
								rank, name, password, Comment);
						stat.execute(query);
					} else if (op == "刪除") {
						query = String.format("DELETE FROM `Comment` WHERE ID=%s", ID);
						stat.execute(query);
					} else {
						JOptionPane.showMessageDialog(outputPanel, "請選擇類別", "Error", JOptionPane.ERROR_MESSAGE);
					}
					query = String.format("SELECT ID,guaID,rank,Comment FROM `Comment` WHERE UserName='%s';", name);
					if (stat.execute(query)) {
						ResultSet result = stat.getResultSet();
						outputArea.setText(showResultSet(result));
						result.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				outputArea.append("以上為您過去給予的評論，非常感謝您的支持與指教");
			}
		});
	}

	private void createLayout() {
		getContentPane().setBackground(new Color(241, 220, 182));

		title_panel = new JPanel();
		title_panel.setBackground(new Color(241, 220, 182));
		title_panel.setBounds(0, 0, 736, 55);
		add(title_panel);

		title = new JTextArea();
		title.setEditable(false);
		title.setFont(new Font("標楷體", Font.BOLD, 36));
		String tString = String.format("\u6B61\u8FCE\uFF0C %s", name);
		title.setText(tString);
		title.setBackground(new Color(241, 220, 182));

//		outputPanel = new JPanel();
//		outputPanel.setBackground(new Color(253, 245, 230));
//		outputPanel.setBounds(0, 55, 736, 280);
//		add(outputPanel);

		JScrollPane scrollPane = new JScrollPane(outputArea);
		scrollPane.setBounds(10, 60, 716, 275);
		scrollPane.setBackground(new Color(253, 245, 230));

		operateTitlePanel = new JPanel();
		operateTitlePanel.setBounds(0, 335, 736, 45);
		operateTitlePanel.setLayout(new GridLayout(1, 3, 0, 0));
		operateTitlePanel.setBackground(new Color(241, 220, 182));
		add(operateTitlePanel);

		operatePanel = new JPanel();
		operatePanel.setBounds(0, 381, 736, 45);
		operatePanel.setLayout(new GridLayout(1, 3, 0, 0));
		add(operatePanel);

		executepanel = new JPanel();
		executepanel.setBackground(new Color(241, 220, 182));
		executepanel.setBounds(0, 540, 736, 63);
		executepanel.setLayout(new GridLayout(1, 3, 0, 0));
		add(executepanel);

		title_panel.add(title);
//		outputPanel.add(scrollPane);
		add(scrollPane);
		operateTitlePanel.add(column1);
		operateTitlePanel.add(column2);
		operateTitlePanel.add(column3);
		operateTitlePanel.add(column4);
		operatePanel.add(text1);
		operatePanel.add(text2);
		operatePanel.add(rankBox);
		add(column4);
		add(text4);
		executepanel.add(rbtnAdd);
		executepanel.add(rbtnDelete);
		executepanel.add(commitButton);
	}

	public static String showResultSet(ResultSet result) throws SQLException {

		ResultSetMetaData metaData = result.getMetaData();
		int columnCount = metaData.getColumnCount();
		String output = "";

		for (int i = 1; i <= columnCount; i++) {
			output += String.format("%15s", metaData.getColumnLabel(i));
		}
		output += "\n";

		while (result.next()) {
			for (int i = 1; i <= columnCount; i++) {
				output += String.format("%15s", result.getString(i));
			}
			output += "\n";
		}
		return output;
	}

}
