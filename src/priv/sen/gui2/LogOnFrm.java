package priv.sen.gui2;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import org.apache.log4j.Logger;
import priv.sen.client.Client;
import priv.sen.entry.MYh;
import priv.sen.net.MessageContexts;
import priv.sen.net.MessageObj;
import priv.sen.net.MyHttp;
import priv.sen.net.UrlType;
import priv.sen.util.StringUtil;
import priv.sen.util.ZiTi;
import priv.sen.view.MyModel2;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;
import java.awt.Color;

/**
 * @author sen 登录窗口
 */
public class LogOnFrm extends JFrame {
	private static Logger logger = Logger.getLogger(LogOnFrm.class);
	private JPanel contentPane;
	private JTextField userName;
	private JPasswordField passWord;
	List<Map<String, Object>> findAll;
	private static List<Map<String, Object>> dataYH;//用户信息
	private static List<Map<String, Object>> dataTB;//贴吧信息
	private JTable table;// 贴吧信息表格
	
	public static List<Map<String, Object>> getData() {
		return dataTB;
	}

	public static void setData(List<Map<String, Object>> data) {
		LogOnFrm.dataTB = data;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogOnFrm frame = new LogOnFrm();
					frame.setVisible(true);
					new Thread(new Runnable() {

						@Override
						public void run() {
							new Client().connet("127.0.0.1", 9999);
						}
					}).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setBg(){   
		 ((JPanel)this.getContentPane()).setOpaque(false);   
		 ImageIcon img = new ImageIcon  
		 (".\\tupian\\背景1.jpg");   
		 JLabel background = new JLabel(img);  
		 this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));   
		 background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());   
		 } 
	
	/**
	 * Create the frame.
	 */
	public LogOnFrm() {

		ZiTi ziTi = new ZiTi();
		ziTi.ziTi();
		setBg();
		
		setFont(new Font("Dialog", Font.BOLD, 12));
		setIconImage(Toolkit.getDefaultToolkit().getImage(LogOnFrm.class.getResource("/tupian/\u56FE\u68073.jpg")));
		setResizable(false);
		setTitle("\u767B\u5F55");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 373);
		contentPane = new JPanel();
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u5174\u8DA3\u8D34\u5427");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 18));
		lblNewLabel.setIcon(new ImageIcon(LogOnFrm.class
				.getResource("/tupian/QQ\u56FE\u724720160515203520.png")));
		lblNewLabel.setBounds(96, 41, 274, 68);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(
				"\u2014\u2014\u4E0E\u4E16\u754C\u5206\u4EAB\u4F60\u7684\u6545\u4E8B");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setToolTipText("");
		lblNewLabel_1.setBounds(186, 104, 200, 50);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setIcon(new ImageIcon(LogOnFrm.class
				.getResource("/tupian/userName.png")));
		lblNewLabel_2.setBounds(118, 177, 243, 50);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("\u5BC6  \u7801\uFF1A");
		lblNewLabel_3.setForeground(Color.BLUE);
		lblNewLabel_3.setIcon(new ImageIcon(LogOnFrm.class
				.getResource("/tupian/password.png")));
		lblNewLabel_3.setBounds(118, 223, 200, 50);
		contentPane.add(lblNewLabel_3);

		userName = new JTextField();
		userName.setBounds(214, 192, 128, 21);
		contentPane.add(userName);
		userName.setColumns(10);

		passWord = new JPasswordField();
		passWord.setBounds(214, 238, 128, 21);
		contentPane.add(passWord);

		JButton dengLuButton = new JButton("\u767B\u5F55");
		dengLuButton.setForeground(Color.BLUE);
		
		
		
		
		
		
		String text = "刘德华";
		MessageContexts.getInstance().offerMessage(new MessageObj() {

			@Override
			public void handlerMessage(MyHttp info) {
				// TODO 处理消息
				dataTB = (List<Map<String, Object>>) info.getData();// 拿到服务器响应数据
				new MyModel2(dataTB);
			}

			@Override
			public MyHttp getMessage() {
				// TODO 发送消息
				MyHttp myHttp = new MyHttp();
				myHttp.setData(text);
				myHttp.setMyUrl("M_ZTTBServiece");
				myHttp.setType(UrlType.NALMORE);
				return myHttp;
			}
		});

	
		
		
		
		
		
		
		/**
		 * 登录事件监听
		 */
		dengLuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dengLu(e);
			}
		});
		dengLuButton.setIcon(new ImageIcon(LogOnFrm.class
				.getResource("/tupian/login.png")));
		dengLuButton.setBounds(118, 283, 93, 23);
		contentPane.add(dengLuButton);

		// 注册按钮
		JButton zhuCeButton = new JButton("\u6CE8\u518C");
		zhuCeButton.setForeground(Color.BLUE);
		// 注册监听
		zhuCeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zhuCe(e);
			}
		});
		zhuCeButton.setIcon(new ImageIcon(LogOnFrm.class
				.getResource("/tupian/reset.png")));
		zhuCeButton.setBounds(250, 283, 93, 23);
		contentPane.add(zhuCeButton);
		// 设置窗口居中显示
		this.setLocationRelativeTo(null);
	}

	/**
	 * TODO 登录事件处理
	 * 
	 * @param e
	 */
	private void dengLu(ActionEvent e2) {
		String userName = this.userName.getText();
		String passWord = new String(this.passWord.getPassword());
		MYh.getInstance().setU_name(userName);

		if (StringUtil.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "用户名不能为空！");// swing的提示
			return;
		}
		if (StringUtil.isEmpty(passWord)) {
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return;
		}
		MessageContexts.getInstance().offerMessage(new MessageObj() {

			@Override
			public void handlerMessage(MyHttp info) {
				// TODO 处理消息
				dataYH = (List<Map<String, Object>>) info.getData();// 拿到服务器响应数据
				logger.debug(dataYH);
			}

			@Override
			public MyHttp getMessage() {
				// TODO 发送消息
				MyHttp myHttp = new MyHttp();
				myHttp.setData(userName);
				myHttp.setData(passWord);
				myHttp.setMyUrl("M_YHServiece");
				myHttp.setType(UrlType.NALMORE);
				return myHttp;
			}
		});

		for (int i = 0; i < dataYH.size(); i++) {
			if (userName.equals(dataYH.get(i).get("U_NAME"))
					&& passWord.equals(dataYH.get(i).get("U_PASSWORD"))) {
				MYh.getInstance().setU_id(userName);
				dispose();// 把当前窗体销毁
				new TieBaFrm().setVisible(true);
			}
		}
	}

	/**
	 * 注册事件处理
	 * 
	 * @param e
	 */
	private void zhuCe(ActionEvent e) {
		dispose();// 把当前窗体销毁
		new ZhuCeFrm().setVisible(true);//注册界面
	}
}
