package priv.sen.gui2;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import priv.sen.entry.MYh;
import priv.sen.util.ZiTi;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

/**
 * @author sen
 * 个人中心
 */
public class GeRenZhongXinFrm extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GeRenZhongXinFrm frame = new GeRenZhongXinFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public void setBg(){   
		 ((JPanel)this.getContentPane()).setOpaque(false);   
		 ImageIcon img = new ImageIcon  
		 (".\\tupian\\背景3.jpg");   
		 JLabel background = new JLabel(img);  
		 this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));   
		 background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());   
		 } 

	
	/**
	 * Create the frame.
	 */
	public GeRenZhongXinFrm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GeRenZhongXinFrm.class.getResource("/tupian/\u56FE\u68073.jpg")));
		setTitle("\u4E2A\u4EBA\u4E2D\u5FC3");
		ZiTi ziTi = new ZiTi();
		ziTi.ziTi();
		setResizable(false);
		setBg();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLUE);
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D:");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 20));
		lblNewLabel.setForeground(Color.MAGENTA);
		lblNewLabel.setBounds(122, 116, 242, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("\u4E2A\u4EBA\u4E2D\u5FC3");
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 20));
		lblNewLabel_2.setForeground(Color.MAGENTA);
		lblNewLabel_2.setBounds(165, 36, 189, 57);
		contentPane.add(lblNewLabel_2);
		String u_name = MYh.getInstance().getU_name();
		JLabel userNameLabel = new JLabel(u_name);
		userNameLabel.setForeground(Color.MAGENTA);
		userNameLabel.setFont(new Font("宋体", Font.BOLD, 20));
		userNameLabel.setBounds(205, 119, 75, 26);
		contentPane.add(userNameLabel);
		// 设置窗口居中显示
		this.setLocationRelativeTo(null);
		//设置只关闭当前窗口
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
