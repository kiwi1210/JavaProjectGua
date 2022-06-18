import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

import com.mysql.jdbc.Connection;

import javax.swing.JTextArea;

public class Explain extends JFrame{
	private JTextField tf1 = new JTextField("\u535C\u5366\u6CE8\u610F\u4E8B\u9805");
	private JTextField tf2 = new JTextField("\u535C\u5366\u64CD\u4F5C\u8AAA\u660E");
	private JTextArea textArea = new JTextArea();
	private JTextArea textArea_1 = new JTextArea();
	
	
	public Explain() {
		createTextArea();
		createLabel();
		setTitle("三錢法易經卜算-算卦");
	}
	
	private void createLabel() {
		tf1.setHorizontalAlignment(SwingConstants.LEFT);
		tf1.setEditable(false);
		tf1.setFont(new Font("標楷體", Font.BOLD, 24));
		tf1.setBounds(0, 0, 343, 50);
		tf1.setBackground(new Color(241, 220, 182));
		getContentPane().add(tf1);
		
		
		tf2.setBackground(new Color(253, 245, 230));
		tf2.setEditable(false);
		tf2.setFont(new Font("標楷體", Font.BOLD, 24));
		tf2.setHorizontalAlignment(SwingConstants.LEFT);
		tf2.setBounds(343, 0, 343, 50);
		getContentPane().add(tf2);
	}
	
	private void createTextArea() {
		textArea.setText("1.\u4E00\u5929\u4E00\u4EF6\u4E8B\u53EA\u80FD\u554F\u4E00\u6B21\u3002\r\n \uFF08\u4E5F\u4E0D\u53EF\u4EE5\u540C\u4E00\u4EF6\u4E8B\u7528\u4E0D\u540C\u554F\u6CD5\u91CD\u8986\u554F\uFF0C\r\n  \u9019\u662F\u4E0D\u5C0A\u91CD\uFF09\r\n2.\u91CD\u8981\u4E8B\u60C5\u8ACB\u65BC\u6C90\u6D74\u5F8C\uFF1B\r\n  \u5E73\u6642\u554F\u535C\uFF0C\u5982\u5EC1\u4E4B\u5F8C\u8ACB\u5148\u6D17\u624B\uFF0C\r\n  \u4E26\u7A0D\u5019\u518D\u535C\u3002\r\n3.\u8AA0\u5FC3\u51A5\u60F3\u4F60\u7684\u554F\u984C\u3002 \r\n4.\u82E5\u554F\u67D0\u4E8B\u5409\u51F6\u53EF\u5982\u6B64\u63D0\u554F\uFF1A\r\n  \u6240\u554F\u4F55\u4E8B\uFF0C\u6B32\u505A\u4F55\u9078\u64C7 / \u6C7A\u5B9A\uFF0C\r\n  \u60F3\u554F\u5409\u51F6....\r\n5.\u535C\u5366\u662F\u8207\u81EA\u5DF1\u4EE5\u53CA\u5929\u5730\u7684\u4EA4\u6D41\u5C0D\u8A71\uFF0C\r\n  \u4E0D\u662F\u8981\u8B93\u4EBA\u8FF7\u4FE1\u3001\u63A8\u5378\u8CAC\u4EFB\u3002\r\n  \u535C\u554F\u8005\u8981\u6709\u7406\u6027\u53CA\u81EA\u6211\u8CA0\u8CAC\u7684\u5FC3\u614B\uFF0C\r\n  \u4E0D\u8981\u671F\u5F85\u5B83\u53EF\u4EE5\u544A\u8A34\u4F60\u6240\u6709\u672A\u77E5\u4E4B\u4E8B\uFF0C\r\n6.\u535C\u5366\u4E0D\u6E96\u662F\u5E38\u6709\u7684\u4E8B\u3002\r\n");
		textArea.setFont(new Font("標楷體", Font.PLAIN, 18));
		textArea.setBackground(new Color(241, 220, 181));
		textArea.setEditable(false);
		textArea.setBounds(0, 51, 343, 362);
		getContentPane().add(textArea);
		
		textArea_1.setText("1.\u8ACB\u5728\u9996\u9801\u9078\u64C7\u9810\u5360\u535C\u7684\u985E\u5225\r\n  \uFF08\u611F\u60C5\u6216\u4E8B\u696D\uFF09\r\n2.\u9EDE\u9078\u201C\u958B\u59CB\u7B97\u5366\u201D\r\n3.\u9032\u5165\u5360\u535C\u9801\u9762\u5F8C\uFF0C\r\n  \u8ACB\u9EDE\u9078\u9322\u5E63\u4E2D\u9593\u7684\u65B9\u683C\uFF0C\r\n  \u5206\u5225\u586B\u5165\u516D\u500B\u6B63\u6574\u6578\u3002\r\n  \u6CE8\u610F\uFF0C\r\n  \u540C\u4E00\u8C4E\u6392\u7684\u9322\u5E63\u4E2D\u6240\u586B\u5165\u7684\u6578\u5B57\r\n  \u4E0D\u53EF\u540C\u70BA\u5947\u6578\u6216\u540C\u70BA\u5076\u6578\u3002\r\n4.\u9EDE\u9078\u201C\u6309\u6B64\u751F\u6210\u65B0\u723B\u201D\u516D\u6B21\r\n5.\u82E5\u904E\u7A0B\u4E2D\u9700\u8981\u4E2D\u65B7\uFF0C\u6216\u8DF3\u51FA\u932F\u8AA4\u8A0A\u606F\uFF0C\r\n  \u8ACB\u9EDE\u9078\u201C\u91CD\u65B0\u958B\u59CB\u201D\uFF0C\u4E26\u91CD\u8907\u6B65\u9A5F3~4\u3002\r\n6.\u95B1\u8B80\u5366\u8FAD\u89E3\u91CB\uFF0C\r\n  \u5982\u6709\u611F\u60F3\u8ACB\u9EDE\u9078\u201C\u8A55\u8AD6\u201D");
		textArea_1.setFont(new Font("標楷體", Font.PLAIN, 18));
		textArea_1.setEditable(false);
		textArea_1.setBackground(new Color(253, 245, 230));
		textArea_1.setBounds(343, 51, 343, 362);
		getContentPane().add(textArea_1);
		
	}
		



}
