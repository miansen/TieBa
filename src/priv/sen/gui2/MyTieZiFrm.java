package priv.sen.gui2;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import org.apache.log4j.Logger;
import priv.sen.entry.MYh;
import priv.sen.net.MessageContexts;
import priv.sen.net.MessageObj;
import priv.sen.net.MyHttp;
import priv.sen.net.UrlType;
import priv.sen.util.ZiTi;
import priv.sen.view.MyTieZiModel;
import java.awt.Font;

/**
 * 我的帖子
 * @author sen
 *
 */
public class MyTieZiFrm extends JFrame {

	private static Logger logger = Logger.getLogger(MyTieZiFrm.class);
	private JPanel contentPane;
	private JTable table;
	private List<Map<String,Object>> data;
	String tbzt;
	private String str;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyTieZiFrm frame = new MyTieZiFrm();
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
	public MyTieZiFrm() {
		setResizable(false);
		
		ZiTi ziTi = new ZiTi();
		ziTi.ziTi();
		setBg();
		setIconImage(Toolkit.getDefaultToolkit().getImage(MyTieZiFrm.class.getResource("/tupian/\u56FE\u68073.jpg")));
		setTitle("\u6211\u7684\u5E16\u5B50");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 720);
		contentPane = new JPanel();
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JScrollPane scrollPane = new JScrollPane();
		JLabel MyTieZiLabel = new JLabel("\u6211\u7684\u5E16\u5B50");
		MyTieZiLabel.setForeground(Color.BLUE);
		MyTieZiLabel.setFont(new Font("宋体", Font.BOLD, 18));
		MyTieZiLabel.setIcon(new ImageIcon(MyTieZiFrm.class.getResource("/tupian/me.png")));
		
		JButton jiaZaiButton = new JButton("\u5237\u65B0");
		/**
		 * 加载我的帖子数据监听
		 */
		jiaZaiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMyTieZiData();
			}
		});
		jiaZaiButton.setIcon(new ImageIcon(MyTieZiFrm.class.getResource("/tupian/search.png")));
		
		JButton button = new JButton("\u5220\u9664");
		
		button.setIcon(new ImageIcon(MyTieZiFrm.class.getResource("/tupian/exit.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(MyTieZiLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(jiaZaiButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addGap(365))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(MyTieZiLabel)
						.addComponent(jiaZaiButton)
						.addComponent(button))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table = new JTable();
		table.setRowHeight(25);//表格高度
		//表格栏点击事件监听
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int rowI = table.rowAtPoint(e.getPoint());// 得到table的行数
				if (rowI > -1) {
					str = (String) table.getModel().getValueAt(rowI, 0);
					logger.debug("帖子标题是:" + tbzt);
				}
			}
		});
		
		//删除帖子事件监听
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (str == null) {
							JOptionPane.showMessageDialog(null, "请选择要删除的帖子");
							return;
						} 
						int result = JOptionPane.showConfirmDialog(null, "是否删除？");
						if (result == 0) {
							ShanChuTieZi(e);
							JOptionPane.showMessageDialog(null," 删除成功!");
							getMyTieZiData();
						}
						
					
					}
				});
		
		scrollPane.setViewportView(table);
		table.setModel(new MyTieZiModel(new TieBaFrm().getDataMyTZ()));
		table.getColumnModel().getColumn(0).setPreferredWidth(304);
		table.getColumnModel().getColumn(1).setPreferredWidth(185);
		table.getColumnModel().getColumn(2).setPreferredWidth(143);
		contentPane.setLayout(gl_contentPane);
	
		//设置窗口居中显示
		this.setLocationRelativeTo(null);
		
		//设置只关闭当前窗口
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}


	/**
	 * 删除帖子
	 * @param e
	 */
	private void ShanChuTieZi(ActionEvent e) {
		MessageContexts.getInstance().offerMessage(new MessageObj() {

			@Override
			public void handlerMessage(MyHttp info) {
				// TODO 处理消息
				data = (List<Map<String, Object>>) info.getData();// 拿到服务器响应数据
			}

			@Override
			public MyHttp getMessage() {
				// TODO 发送消息
				MyHttp myHttp = new MyHttp();
				myHttp.setData(str);//贴吧
				myHttp.setMyUrl("DeleteMyTieZiServiece");
				myHttp.setType(UrlType.NALMORE);
				return myHttp;
			}
		});
	
	}


	/**
	 * 获取我的帖子的信息
	 */
	private void getMyTieZiData() {
		MessageContexts.getInstance().offerMessage(new MessageObj() {
			
			@Override
			public void handlerMessage(MyHttp info) {
				//TODO 处理消息
				data  = (List<Map<String, Object>>) info.getData();//拿到服务器响应数据
				table.setModel(new MyTieZiModel(data));
			}
			
			@Override
			public MyHttp getMessage() {
				//TODO 发送消息
				MyHttp myHttp = new MyHttp();
				myHttp.setData(MYh.getInstance().getU_name());//用户名
				myHttp.setMyUrl("W_TZServiece");
				myHttp.setType(UrlType.NALMORE);
				return myHttp;
			}
		});
	}
}
