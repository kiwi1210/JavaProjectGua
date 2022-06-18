import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

import com.mysql.jdbc.Connection;



public class InterpretFrame extends JFrame {
	public InterpretFrame(String choised, String position) throws SQLException {

		setTitle("三錢法易經卜算-解卦");
		
		String server = "yourServer";
		String database = "tuegroup13"; // change to your own database
		String url = server + database + "?useSSL=false";
		String username = "yourUsername"; // change to your own username
		String password = "yourPassword"; // change to your own password
		
		JPanel panelGraph = new JPanel();
		panelGraph.setBackground(new Color(255, 255, 255));
		panelGraph.setBounds(0, 0, 606, 200);
		
		JTextArea interpret = new JTextArea();
		
		interpret.setFont(new Font("Arial", Font.BOLD, 24));
		String graph = "";
		
		for (int i = 6; i > 0; i--) {
			if (position.substring(i - 1, i).equals("0")) {
				graph += String.format("%s\n", "__  __");
			} else if (position.substring(i - 1, i).equals("1")) {
				graph += String.format("%s\n", "_____");
			}
		}
		
//		String[] sixyio = getYio();
//		for (int j = 0; j < 6; j++) {
//			sixYio.append(String.format("%s\n", yioArray[j]));
//		}
		
//		for (int j = 0; j < 6; j++) {
//			interpret.append(String.format("%s\n", yioArray[j]));
//		}
		interpret.append(graph);
		interpret.setEditable(false);
		// System.out.print(position.substring(0,1));
		
		JTextArea GuaName = new JTextArea();;
		GuaName.setBackground(new Color(241, 220, 182));
		GuaName.setEditable(false);
		GuaName.setBounds(75, 205, 506, 38);
		GuaName.setColumns(10);
		GuaName.setFont(new Font("標楷體", Font.PLAIN, 30));
		
		JTextArea txt = new JTextArea();
		txt.setBackground(new Color(253, 245, 230));
		txt.setBounds(10, 246, 586, 172);
		txt.setFont(new Font("標楷體", Font.PLAIN, 18));
		txt.setLineWrap(true);
		
		JTextArea labelNum = new JTextArea();
		labelNum.setEditable(false);
		labelNum.setBackground(new Color(241, 220, 182));
		labelNum.setFont(new Font("標楷體", Font.BOLD, 32));
		labelNum.setBounds(10, 205, 74, 38);
		
		
		try {
			Connection conn = (Connection) DriverManager.getConnection(url, username, password);
			System.out.println("DB Connectd");
			Statement stat;
			stat = conn.createStatement();
			String query = String.format("SELECT NAME FROM `64gua` WHERE Position='%s';", position);
			if (stat.execute(query)) {
				ResultSet result = stat.getResultSet();
				GuaName.append(showResultSet(result));
				GuaName.setEditable(false);
			}
			query = String.format("SELECT %s FROM `64gua` WHERE Position='%s';", choised, position);
			if (stat.execute(query)) {
				ResultSet result = stat.getResultSet();
				txt.setLineWrap(true);
				txt.append(showResultSet(result));
				System.out.println(showResultSet(result));
				txt.setEditable(false);
				result.close();
			}
			query = String.format("SELECT ID FROM `64gua` WHERE Position='%s';", position);
			String id = "";
			if (stat.execute(query)) {
				ResultSet result = stat.getResultSet();
				id = showResultSet(result);
				labelNum.append(id);
				result.close();
			}
			query = String.format("SELECT ID,rank,Username,Comment FROM `Comment` WHERE guaID='%s';", id);
			if (stat.execute(query)) {
				
				String output = "\n關於此卦，使用者們曾給予的評論:\n";
				String[] columnLable = { "評論編號", "評分", "使用者", "評論" };
				for (int i = 0; i < columnLable.length; i++) {
					if(i==0) {
						output += String.format("%1s", columnLable[i]);						
					}else {
						output += String.format("%10s", columnLable[i]);
					}
				}
				output += "\n";
				txt.append(output);
				ResultSet result = stat.getResultSet();
				txt.setLineWrap(true);
				txt.append(showResultSet(result));
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
				
		JButton comment = new JButton("\u8A55\u8AD6");
		comment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				LoginFrame lFrame = new LoginFrame();
				lFrame.setBackground(new Color(241, 220, 182));
				lFrame.setBounds(100, 100, 500, 250);
				lFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				lFrame.setLayout(null);
				lFrame.setVisible(true);
			}
		});
		getContentPane().setBackground(new Color(241, 220, 182));
		getContentPane().add(GuaName);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 244, 606, 229);
		panel_1.setBackground(new Color(241, 220, 182));
		
		JScrollPane scrollPane = new JScrollPane(txt);
		scrollPane.setBackground(new Color(241, 220, 182));
		scrollPane.setBounds(10, 246, 586, 172);
		scrollPane.setFont(new Font("標楷體", Font.PLAIN, 18));
		
//		add(panel_1);
//		panel_1.add(scrollPane);
		add(scrollPane);
		add(panelGraph);
//		panel_1.add(comment);
		add(comment);

		comment.setFont(new Font("標楷體", Font.PLAIN, 18));
		comment.setBounds(260, 440, 85, 23);
		panelGraph.add(interpret);
		getContentPane().add(labelNum);
	}

	public static String showResultSet(ResultSet result) throws SQLException {

		ResultSetMetaData metaData = result.getMetaData();
		int columnCount = metaData.getColumnCount();
		String output = "";

		while (result.next()) {
			for (int i = 1; i <= columnCount; i++) {
				if(i==1) {
					output += result.getString(i);
				}else if(i==2){
					output += String.format("%16s", result.getString(i));
				}else if(i==3) {
					output += String.format("%12s", result.getString(i));
				}else {
					output += String.format("%15s", result.getString(i));
				}
				
			}
			output += "\n";
		}
		return output;
		 
	}

}
