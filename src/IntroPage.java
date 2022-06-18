
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Rectangle;
import java.awt.Color;

public class IntroPage extends JFrame {
		
	private double avgRank=0;
	private JButton start,comment,howBtn;
	private JRadioButton rbtLove,rbtWork;
	private JPanel panel,panelIntro,panelComment,panel_1,commentArea;
	private JLabel titleLabel,bgpic,avgtxtLabel,avgLabel;
	private JTextPane textPane;

	public IntroPage() {
		creatButton();
		creatRButton();
		creatLayout();
		
		String server = "yourServer";
		String database = "tuegroup13"; // change to your own database
		String url = server + database + "?useSSL=false";
		String username = "yourUsername"; // change to your own username
		String password = "yourPassword"; // change to your own password
		Connection conn;

		try {
			conn = (Connection) DriverManager.getConnection(url, username, password);
			System.out.println("DB Connectd");
			Statement stat=(Statement) conn.createStatement();;
			String query="SELECT rank FROM `Comment`;";
			if (stat.execute(query)) {
				ResultSet result=stat.getResultSet();
				ResultSetMetaData metaData = (ResultSetMetaData) result.getMetaData();
				int columnCount = metaData.getColumnCount();
				int ranks=0;
				int n=0;
				
				while (result.next()) {
					n++;
					for (int i = 1; i <= columnCount; i++) {
						ranks += Integer.parseInt(result.getString(i));
					}
				}
				avgRank=ranks/n;
		 }
		 
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	public void creatButton() {
		start = new JButton("\u958B\u59CB\u7B97\u5366");
		comment = new JButton("\u8A55\u8AD6");
		howBtn = new JButton("\u64CD\u4F5C\u8AAA\u660E");

		howBtn.setFont(new Font("夹发砰", Font.PLAIN, 20));
		howBtn.setBackground(new Color(241,220,182));
		howBtn.setBounds(730, 296, 124, 50);
		getContentPane().add(howBtn);
		howBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Explain eFrame = new Explain();
				eFrame.setBounds(100, 100, 700, 450);
				eFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				eFrame.setLayout(null);
				eFrame.setVisible(true);
			}
		});

		start.setFont(new Font("夹发砰", Font.BOLD, 24));
		start.setBackground(new Color(245, 222, 179));
		start.setBounds(649, 394, 287, 69);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				String choised;
				choised = "";
				if (rbtLove.isSelected()) {
					choised = "Love";
				} else if (rbtWork.isSelected()) {
					choised = "Career";
				} else {
					JOptionPane.showMessageDialog(panel, "叫匡拒摸", "Error", JOptionPane.ERROR_MESSAGE);

				}

				if (choised != "") {
					DivinationFrame dFrame = new DivinationFrame(choised);
					dFrame.setBounds(100, 100, 950, 500);
					dFrame.getContentPane().setLayout(null);
					dFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dFrame.setVisible(true);
				}

			}
		});
		
		comment.setFont(new Font("夹发砰", Font.PLAIN, 20));
		comment.setBackground(new Color(245, 222, 179));
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
	}
	public void creatRButton() {
		rbtLove = new JRadioButton("\u611F\u60C5");
		rbtWork = new JRadioButton("\u4E8B\u696D");
		
		rbtLove.setBackground(new Color(241, 220, 182));
		rbtLove.setHorizontalAlignment(SwingConstants.CENTER);
		rbtLove.setFont(new Font("夹发砰", Font.PLAIN, 24));
		rbtLove.setBounds(177, 414, 75, 31);
		
		rbtWork.setFont(new Font("夹发砰", Font.PLAIN, 24));
		rbtWork.setBackground(new Color(241, 220, 182));
		rbtWork.setHorizontalAlignment(SwingConstants.CENTER);
		rbtWork.setBounds(402, 414, 75, 31);

		ButtonGroup group = new ButtonGroup();
		group.add(rbtLove);
		group.add(rbtWork);
		
		rbtLove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				start.setBackground(new Color(255, 192, 203));
			}
		});
		rbtWork.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				start.setBackground(new Color(100, 149, 237));
			}
		});
	}
	public void creatLayout() {
		String server = "yourServer";
		String database = "tuegroup13"; // change to your own database
		String url = server + database + "?useSSL=false";
		String username = "yourUsername"; // change to your own username
		String password = "yourPassword"; // change to your own password
		Connection conn;
		
		try {
			conn = (Connection) DriverManager.getConnection(url, username, password);
			System.out.println("DB Connectd");
			Statement stat=(Statement) conn.createStatement();;
			String query="SELECT rank FROM `Comment`;";
			if (stat.execute(query)) {
				ResultSet result=stat.getResultSet();
				ResultSetMetaData metaData = (ResultSetMetaData) result.getMetaData();
				int columnCount = metaData.getColumnCount();
				int ranks=0;
				int n=0;
				
				while (result.next()) {
					n++;
					for (int i = 1; i <= columnCount; i++) {
						ranks += Integer.parseInt(result.getString(i));
					}
				}
				avgRank=ranks/n;
		 }
		 
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		panel = new JPanel();
		titleLabel = new JLabel("\u653F\u5927\u4ECA\u5929\u6709\u5366\u55CE?");
		panelIntro = new JPanel();
		bgpic = new JLabel();
		avgtxtLabel=new JLabel(String.format("\u5E73\u5747\u8A55\u50F9(1~5): "));
		avgLabel=new JLabel(String.format("%s",avgRank));
		panelComment = new JPanel();
		textPane = new JTextPane();
		panel_1 = new JPanel();
		commentArea = new JPanel();
		
		avgtxtLabel.setBackground(new Color(245, 222, 179));
		avgtxtLabel.setHorizontalAlignment(SwingConstants.CENTER);
		avgtxtLabel.setFont(new Font("夹发砰", Font.PLAIN, 20));
		avgtxtLabel.setBounds(692, 200, 200, 60);
		
		avgLabel.setBackground(new Color(245, 222, 179));
		avgLabel.setFont(new Font("夹发砰", Font.BOLD, 32));
		
		panel.setBackground(new Color(241, 220, 182));
		panel.setBounds(0, 0, 936, 60);
		
		panelIntro.setBackground(new Color(255, 255, 255));
		panelIntro.setBounds(0, 60, 650, 335);
		
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("夹发砰", Font.BOLD, 48));
		
		bgpic.setIcon(new ImageIcon("images//intro1.png"));
		
		panelComment.setBackground(new Color(253, 245, 230));
		panelComment.setBounds(649, 60, 287, 50);
		
		textPane.setBounds(new Rectangle(0, 0, 287, 10));
		textPane.setText("\u8A55\u8AD6\u5340");
		textPane.setEditable(false);
		textPane.setFont(new Font("夹发砰", Font.BOLD, 24));
		
		panel_1.setBackground(new Color(253, 245, 230));
		panel_1.setBounds(649, 107, 287, 41);
		
		commentArea.setBackground(new Color(253, 245, 230));
		commentArea.setBounds(649, 148, 287, 247);
		
		panel.add(titleLabel);
		panelIntro.add(bgpic);
		panelComment.add(textPane);
		panel_1.add(comment);
		add(panel);
		add(panelIntro);
		add(panelComment);
		add(rbtLove);
		add(rbtWork);
		add(start);
		add(panel_1);
		add(commentArea);
		commentArea.add(avgtxtLabel);
		commentArea.add(avgLabel);
		setTitle("窥猭竒衡-ざ残");
	}
		
}
