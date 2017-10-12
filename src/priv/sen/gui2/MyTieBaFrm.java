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
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import priv.sen.entry.MTz;
import priv.sen.entry.MYh;
import priv.sen.net.MessageContexts;
import priv.sen.net.MessageObj;
import priv.sen.net.MyHttp;
import priv.sen.net.UrlType;
import priv.sen.util.ZiTi;
import priv.sen.view.MyModel2;
import priv.sen.view.MyTieBaModel;
import java.awt.Font;

/**
 * 我的贴吧
 * @author sen
 *
 */
public class MyTieBaFrm extends JFrame {

	private static Logger logger = Logger.getLogger(MyModel2.class);
	private JPanel contentPane;
	private JTable table;// 贴吧信息表格
	private List<Map<String, Object>> data;
	private String str;//表格栏点击
	private static boolean flag;// 标记主题贴吧是否已存在

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyTieBaFrm frame = new MyTieBaFrm();
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
	public MyTieBaFrm() {
		setResizable(false);

		ZiTi ziTi = new ZiTi();
		ziTi.ziTi();
		setBg();
		setIconImage(Toolkit.getDefaultToolkit().getImage(MyTieBaFrm.class.getResource("/tupian/\u56FE\u68073.jpg")));
		setTitle("\u6211\u7684\u8D34\u5427");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 661, 720);
		String u_name = MYh.getInstance().getU_name();
		// String yongHuNiCheng = yongHuNiCheng();
		logger.debug("正在登录..." + u_name);
		contentPane = new JPanel();
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("\u6211\u7684\u8D34\u5427");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 18));
		lblNewLabel.setIcon(new ImageIcon(MyTieBaFrm.class.getResource("/tupian/me.png")));
		
		JButton jiaZaiButton = new JButton("\u5237\u65B0");
		/**
		 * 刷新事件监听
		 */
		jiaZaiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				getMyTieBaData();

			
			}
		});
		jiaZaiButton.setIcon(new ImageIcon(MyTieBaFrm.class.getResource("/tupian/search.png")));
		
		JButton button_1 = new JButton("\u5220\u9664");
		
		button_1.setIcon(new ImageIcon(MyTieBaFrm.class.getResource("/tupian/exit.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jiaZaiButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(373))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(14)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(jiaZaiButton, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE))
		);

		table = new JTable();
		table.setRowHeight(25);//表格高度
		//表格栏点击事件
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				

				int rowI = table.rowAtPoint(e.getPoint());// 得到table的行数
				if (rowI > -1) {
					str = (String) table.getModel().getValueAt(rowI, 0);
					logger.debug("单击鼠标 " + str);
					MTz instance = MTz.getInstance();
					instance.setTbzt(str);// 贴吧主题
					String tbzt = instance.getTbzt();
					logger.debug("贴吧主题是：" + tbzt);
//					getTieZiDataByTieBaZhuTi(tbzt);
				}
			
			}
		});
		
		
		
		//删除贴吧事件监听
				button_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						

						
						if (str == null) {
							JOptionPane.showMessageDialog(null, "请选择要删除的贴吧");
							return;
						} 
						
						

						int result = JOptionPane.showConfirmDialog(null, "是否删除？");
						if (result == 0) {
							ShanChuTieBa(e);
							JOptionPane.showMessageDialog(null," 删除成功!");
							getMyTieBaData();
						}
						
					}
				});
		
		
//		str = "";//重置鼠标点击
		table.setToolTipText("");
		scrollPane.setViewportView(table);

		// 在这里添加表格数据
		table.setModel(new MyTieBaModel(new TieBaFrm().getDataMyTB()));
		table.getColumnModel().getColumn(0).setPreferredWidth(172);
		table.getColumnModel().getColumn(0).setMinWidth(18);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(163);
		table.getColumnModel().getColumn(3).setPreferredWidth(146);

		JButton button = new JButton("\u641C\u7D22");
		scrollPane.setColumnHeaderView(button);
		contentPane.setLayout(gl_contentPane);

		// 设置窗口居中显示
		this.setLocationRelativeTo(null);
		
		//设置只关闭当前窗口
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}


	/**
	 * 删除我的贴吧
	 * @param e
	 */
	private void ShanChuTieBa(ActionEvent e) {
		
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
				myHttp.setMyUrl("DeleteMyTieBaServiece");
				myHttp.setType(UrlType.NALMORE);
				return myHttp;
			}
		});
	}


	/**
	 * 获得我的贴吧的信息
	 */
	private void getMyTieBaData() {
		MessageContexts.getInstance().offerMessage(new MessageObj() {

			@Override
			public void handlerMessage(MyHttp info) {
				// TODO 处理消息
				data = (List<Map<String, Object>>) info.getData();// 拿到服务器响应数据
				table.setModel(new MyTieBaModel(data));
			}

			@Override
			public MyHttp getMessage() {
				// TODO 发送消息
				MyHttp myHttp = new MyHttp();
				myHttp.setData(MYh.getInstance().getU_name());//用户名
				myHttp.setMyUrl("W_ZTTBServiece");
				myHttp.setType(UrlType.NALMORE);
				return myHttp;
			}
		});
	}
}
