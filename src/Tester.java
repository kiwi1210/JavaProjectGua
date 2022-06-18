import javax.swing.JFrame;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IntroPage intro = new IntroPage();
		intro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		intro.getContentPane().setLayout(null);
		intro.setVisible(true);
		intro.getContentPane().setBackground(new Color(241, 220, 182));
		intro.setBounds(100, 100, 950, 500);
		
		

	}
}
