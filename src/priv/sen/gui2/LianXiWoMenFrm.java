package priv.sen.gui2;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import priv.sen.util.ZiTi;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

/**
 * @author sen
 * 联系我们
 */
public class LianXiWoMenFrm extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LianXiWoMenFrm frame = new LianXiWoMenFrm();
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
	public LianXiWoMenFrm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LianXiWoMenFrm.class.getResource("/tupian/\u56FE\u68073.jpg")));
		setTitle("\u5173\u4E8E\u6211\u4EEC");
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
		
		JLabel lblNewLabel = new JLabel("\u843D\u971E\u4E0E\u5B64\u9E5C\u9F50\u98DE");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 20));
		lblNewLabel.setForeground(Color.MAGENTA);
		lblNewLabel.setBounds(141, 84, 242, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u79CB\u6C34\u5171\u957F\u5929\u4E00\u8272");
		lblNewLabel_1.setForeground(Color.MAGENTA);
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 20));
		lblNewLabel_1.setBounds(141, 127, 189, 33);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u5174\u8DA3\u8D34\u5427");
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 20));
		lblNewLabel_2.setForeground(Color.MAGENTA);
		lblNewLabel_2.setBounds(173, 17, 189, 57);
		contentPane.add(lblNewLabel_2);
		// 设置窗口居中显示
		this.setLocationRelativeTo(null);
		//设置只关闭当前窗口
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
