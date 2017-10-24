package priv.sen.gui2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import priv.sen.entry.MTz;
import priv.sen.entry.MYh;
import priv.sen.entry.MZttb;
import priv.sen.net.MessageContexts;
import priv.sen.net.MessageObj;
import priv.sen.net.MyHttp;
import priv.sen.net.UrlType;
import priv.sen.util.ResetValueActionPerformed;
import priv.sen.util.SqlDate;
import priv.sen.util.ZiTi;
import priv.sen.view.MyModel2;
import priv.sen.view.MyModel3;

/**
 * 贴吧主页类
 * 
 * @author sen
 *
 */
public class TieBaFrm extends JFrame {

	private static Logger logger = Logger.getLogger(MyModel2.class);
	private JPanel contentPane;
	private JTable table;// 贴吧信息表格
	private JTextArea tbjjArea;// 贴吧简介输入框
	private JTextArea tbztArea;//贴吧主题
	private JTextArea s_tieBa;//搜索贴吧
	private static List<Map<String, Object>> data;//刷新贴吧信息
	private static List<Map<String, Object>> dataTZ;//帖子信息
	private static List<Map<String, Object>> dataMyTB;//我的贴吧信息
	private static List<Map<String, Object>> dataMyTZ;//我的帖子信息
	private static List<Map<String, Object>> dataMyHF;//我的回复信息
	private String str;
	private static boolean flag = false;// 标记主题贴吧是否已存在
	
	
	
	
	public static List<Map<String, Object>> getDataMyHF() {
		return dataMyHF;
	}


	public static void setDataMyHF(List<Map<String, Object>> dataMyHF) {
		TieBaFrm.dataMyHF = dataMyHF;
	}


	public static List<Map<String, Object>> getDataMyTZ() {
		return dataMyTZ;
	}


	public static void setDataMyTZ(List<Map<String, Object>> dataMyTZ) {
		TieBaFrm.dataMyTZ = dataMyTZ;
	}


	public static List<Map<String, Object>> getDataMyTB() {
		return dataMyTB;
	}


	public static void setDataMyTB(List<Map<String, Object>> dataMyTB) {
		TieBaFrm.dataMyTB = dataMyTB;
	}


	public static List<Map<String, Object>> getDataTZ() {
		return dataTZ;
	}


	public static void setDataTZ(List<Map<String, Object>> dataTZ) {
		TieBaFrm.dataTZ = dataTZ;
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TieBaFrm frame = new TieBaFrm();
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
		 (".\\tupian\\背景2.jpg");   
		 JLabel background = new JLabel(img);  
		 this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));   
		 background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());   
		 } 
	
	/**
	 * Create the frame.
	 */
	public TieBaFrm() {

		ZiTi ziTi = new ZiTi();
		ziTi.ziTi();
//		setBg();
		setIconImage(Toolkit.getDefaultToolkit().getImage(TieBaFrm.class.getResource("/tupian/\u56FE\u68073.jpg")));
		setTitle("\u5174\u8DA3\u8D34\u5427-\u4E3B\u9875");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 659, 720);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		String u_name = MYh.getInstance().getU_name();
		// String yongHuNiCheng = yongHuNiCheng();
		logger.debug("正在登录..." + u_name);
		JMenu menu = new JMenu(u_name);// TODO 获取用户的昵称
		menu.setIcon(new ImageIcon(TieBaFrm.class
				.getResource("/tupian/userName.png")));
		menuBar.add(menu);

		JMenuItem geRenZhongXinmenuItem = new JMenuItem(
				"\u4E2A\u4EBA\u4E2D\u5FC3");
		geRenZhongXinmenuItem.addActionListener(new ActionListener() {
			/**
			 * 个人中心
			 */
			public void actionPerformed(ActionEvent e) {
				new GeRenZhongXinFrm().setVisible(true);
			}
		});
		geRenZhongXinmenuItem.setIcon(new ImageIcon(TieBaFrm.class
				.getResource("/tupian/userName.png")));
		menu.add(geRenZhongXinmenuItem);

		JMenuItem tieBaMenuItem = new JMenuItem("\u6211\u7684\u8D34\u5427");
		
		/**
		 * 我的贴吧监听
		 */
		tieBaMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MessageContexts.getInstance().offerMessage(new MessageObj() {

					@Override
					public void handlerMessage(MyHttp info) {
						// TODO 处理消息
						dataMyTB = (List<Map<String, Object>>) info.getData();// 拿到服务器响应数据
//						table.setModel(new MyTieBaModel(dataMyTB));
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
				
				new MyTieBaFrm().setVisible(true);
			}
		});
		tieBaMenuItem.setIcon(new ImageIcon(TieBaFrm.class
				.getResource("/tupian/reset.png")));
		menu.add(tieBaMenuItem);

		JMenuItem tieZiMenuItem = new JMenuItem("\u6211\u7684\u5E16\u5B50");
		
		/**
		 * 我的帖子事件监听
		 */
		tieZiMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MessageContexts.getInstance().offerMessage(new MessageObj() {
					
					@Override
					public void handlerMessage(MyHttp info) {
						//TODO 处理消息
						dataMyTZ  = (List<Map<String, Object>>) info.getData();//拿到服务器响应数据
//						table.setModel(new MyTieZiModel(dataMyTZ));
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
			
			
				
				new MyTieZiFrm().setVisible(true);
			}
		});
		tieZiMenuItem.setIcon(new ImageIcon(TieBaFrm.class
				.getResource("/tupian/edit.png")));
		menu.add(tieZiMenuItem);

		JMenuItem qieHuanMenuItem = new JMenuItem("\u5207\u6362\u5E10\u53F7");
		qieHuanMenuItem.addActionListener(new ActionListener() {
			// 注销监听
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "是否切换帐号？");
				if (result == 0) {
					LogOnFrm logOnFrm = new LogOnFrm();
					dispose();// 把当前窗体销毁
					logOnFrm.setVisible(true);
				}
			}
		});
		
		JMenuItem huiFuMenuItem = new JMenuItem("\u6211\u7684\u56DE\u590D");
		//我的回复事件监听
		huiFuMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				MessageContexts.getInstance().offerMessage(new MessageObj() {
					
					@Override
					public void handlerMessage(MyHttp info) {
						//TODO 处理消息
						dataMyHF  = (List<Map<String, Object>>) info.getData();//拿到服务器响应数据
//						table.setModel(new MyTieZiModel(dataMyTZ));
					}
					
					@Override
					public MyHttp getMessage() {
						//TODO 发送消息
						MyHttp myHttp = new MyHttp();
						myHttp.setData(MYh.getInstance().getU_name());//用户名
						myHttp.setMyUrl("W_HFServiece");
						myHttp.setType(UrlType.NALMORE);
						return myHttp;
					}
				});
				new MyHuiFuFrm().setVisible(true);
			}
		});
		huiFuMenuItem.setIcon(new ImageIcon(TieBaFrm.class.getResource("/tupian/me.png")));
		menu.add(huiFuMenuItem);
		qieHuanMenuItem.setIcon(new ImageIcon(TieBaFrm.class
				.getResource("/tupian/login.png")));
		menu.add(qieHuanMenuItem);

		JButton lxButton = new JButton("\u5173\u4E8E\u6211\u4EEC");
		/**
		 * 关于我们
		 */
		lxButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LianXiWoMenFrm().setVisible(true);
			}
		});
		lxButton.setIcon(new ImageIcon(TieBaFrm.class
				.getResource("/tupian/about.png")));
		menuBar.add(lxButton);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "\u641C\u7D22\u8D34\u5427",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"),
				"\u521B\u5EFA\u4E3B\u9898\u8D34\u5427", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 192, Short.MAX_VALUE)
					.addContainerGap())
		);

		JLabel tbztLabel = new JLabel("\u8D34\u5427\u4E3B\u9898\uFF1A");
		tbztLabel.setIcon(new ImageIcon(TieBaFrm.class
				.getResource("/tupian/me.png")));
		JLabel tbjjLabel = new JLabel("\u8D34\u5427\u7B80\u4ECB\uFF1A");
		tbjjLabel.setIcon(new ImageIcon(TieBaFrm.class
				.getResource("/tupian/edit.png")));

		/**
		 * TODO 贴吧简介编辑区
		 */

		JButton cjButton = new JButton("\u521B\u5EFA");// 创建贴吧按钮

		cjButton.setIcon(new ImageIcon(TieBaFrm.class
				.getResource("/tupian/password.png")));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(tbztLabel)
						.addComponent(tbjjLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(cjButton)
						.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(tbztLabel)
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(tbjjLabel)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cjButton)
					.addGap(16))
		);
		tbjjArea = new JTextArea();
		scrollPane_1.setViewportView(tbjjArea);
		tbjjArea.setLineWrap(true);
		
				// 设置文本区边框
				tbjjArea.setBorder(new LineBorder(new java.awt.Color(127, 157, 185), 1,
						false));
		
		tbztArea = new JTextArea();
		tbztArea.setLineWrap(true);
		scrollPane_2.setViewportView(tbztArea);
		panel_1.setLayout(gl_panel_1);

		JLabel tbmcLabel = new JLabel("\u8D34\u5427\u540D\u79F0\uFF1A");
		tbmcLabel.setIcon(new ImageIcon(TieBaFrm.class
				.getResource("/tupian/reset.png")));

		// TODO 搜索按钮
		JButton ssButton = new JButton("\u641C\u7D22");
		ssButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				souSuoTieBa(e);
			}
		});
		ssButton.setIcon(new ImageIcon(TieBaFrm.class
				.getResource("/tupian/search.png")));

		JButton sxButton = new JButton("\u5237\u65B0");// TODO 刷新按钮

		sxButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTieBaData();
			}
		});

		/**
		 * TODO 创建贴吧监听事件
		 */
		cjButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				String text = tbztArea.getText();//贴吧主题
//				List<Map<String,Object>> data2 = LogOnFrm.getData();//贴吧信息
//				for (int i = 0; i < data2.size(); i++) {
//					if (text.equals(data2.get(i))) {
//						JOptionPane.showMessageDialog(null, tbztArea.getText()
//							+ "已存在!");
//						return;
//				}
//			}
				
				if (flag) {
//					JOptionPane.showMessageDialog(null, tbztArea.getText()
//							+ "已存在!");
					logger.debug("已存在");
					return;
				} else if (tbztArea.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入贴吧主题!");
					return;
				} else if (tbjjArea.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入贴吧简介!");
					return;
				}else if(tbztArea.getText().length() > 50){
					JOptionPane.showMessageDialog(null, "贴吧主题不能超过50个字!");
					return;
				}
				chuangJianTieBa(e);
				TieBaFrm.this.getContentPane().revalidate();//重绘
				
				JOptionPane.showMessageDialog(null, tbztArea.getText()
						+ " 创建成功!");
				ResetValueActionPerformed.resetValueActionPerformed(e,
						tbjjArea, tbztArea);
				
				
				
				
				
				getTieBaData();

				
				
				
				
				

			}
		});
		sxButton.setIcon(new ImageIcon(TieBaFrm.class
				.getResource("/tupian/login.png")));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(7)
					.addComponent(tbmcLabel)
					.addGap(18)
					.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(ssButton)
					.addGap(18)
					.addComponent(sxButton)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(tbmcLabel)
						.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(ssButton)
						.addComponent(sxButton, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		s_tieBa = new JTextArea();
		s_tieBa.setLineWrap(true);
		scrollPane_3.setViewportView(s_tieBa);
		panel.setLayout(gl_panel);

		table = new JTable();
		table.setToolTipText("");
		table.setRowHeight(25);//表格高度
		table.addMouseListener(new MouseAdapter() {

			// @Override
			// public void mousePressed(MouseEvent me) {
			// bookTableMousePressed(me);
			// }
			// TODO 表格栏鼠标单击事件
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
					getTieZiDataByTieBaZhuTi(tbzt);
				}
			}
		});
		scrollPane.setViewportView(table);

		// 在这里添加表格数据
		table.setModel(new MyModel2(new LogOnFrm().getData()));
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
		
		contentPane.setOpaque(false);
	}

	/**
	 * 创建贴吧事件处理
	 * 
	 * @param e
	 */
	private void chuangJianTieBa(ActionEvent e) {
		MZttb instance = MZttb.getInstance();
		String text = tbztArea.getText();// 获取用户输入的贴吧主题
		String text2 = tbjjArea.getText();// 获取用户输入的贴吧简介
		instance.setU_zt(text);
		instance.setU_jianjie(text2);
		// instance.setU_name(yongHuNiCheng());

//		String text = tbztArea.getText();//贴吧主题
		List<Map<String,Object>> data2 = LogOnFrm.getData();//贴吧信息
		for (int i = 0; i < data2.size(); i++) {
			if (text.equals(data2.get(i))) {
				JOptionPane.showMessageDialog(null, tbztArea.getText()
					+ "已存在!");
				return;
		}
	}

		
		
		
		
		
		
		MessageContexts.getInstance().offerMessage(new MessageObj() {

			@Override
			public void handlerMessage(MyHttp info) {
				// TODO 处理消息
				data = (List<Map<String, Object>>) info.getData();// 拿到服务器响应数据
				// new TieZiFrm().setVisible(true);
			}

			@Override
			public MyHttp getMessage() {
				// TODO 发送消息
				MyHttp myHttp = new MyHttp();
				myHttp.setData(text+"吧");// 贴吧主题
				myHttp.setData2(MYh.getInstance().getU_name());// 吧主名字
				myHttp.setData3(text2);// 贴吧简介
				myHttp.setData4(SqlDate.getDate());// 创吧时间
				myHttp.setMyUrl("C_TBServiece");
				myHttp.setType(UrlType.NALMORE);
				return myHttp;
			}
		});
	}

	/**
	 * 搜索贴吧事件处理
	 * 
	 * @param evt
	 */
	private void souSuoTieBa(ActionEvent evt) {
		String s_tieBaName = this.s_tieBa.getText();// 获取用户输入的字段

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
				myHttp.setData(s_tieBaName);//用户搜索的字段
				myHttp.setMyUrl("S_ZTTBServiece");
				myHttp.setType(UrlType.NALMORE);
				return myHttp;
			}
		});
		table.setModel(new MyModel2(data));
		logger.debug("搜索贴吧结果："+data);
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	/**
	 * 获取贴吧信息
	 */
	private void getTieBaData() {
		MessageContexts.getInstance().offerMessage(new MessageObj() {

			@Override
			public void handlerMessage(MyHttp info) {
				// TODO 处理消息
				// JOptionPane.showMessageDialog(null, info);
				data = (List<Map<String, Object>>) info.getData();// 拿到服务器响应数据
				for (int i = 0; i < data.size(); i++) {
					if (tbztArea.getText().equals(
							data.get(i).get("U_ZT"))) {
						flag = true;
					}
				}
				Object object = data.get(0).get("U_ZT");
				table.setModel(new MyModel2(data));
				// infoLabel.setText(object.toString());
			}

			@Override
			public MyHttp getMessage() {
				// TODO 发送消息
				MyHttp myHttp = new MyHttp();
				myHttp.setMyUrl("M_ZTTBServiece");
				myHttp.setType(UrlType.NALMORE);
				return myHttp;
			}
		});
	}


	/**
	 * 根据点击的贴吧主题获取帖子的信息
	 * @param tbzt
	 */
	private void getTieZiDataByTieBaZhuTi(String tbzt) {
		MessageContexts.getInstance().offerMessage(
				new MessageObj() {

					@Override
					public void handlerMessage(MyHttp info) {
						// TODO 处理消息
						dataTZ = (List<Map<String, Object>>) info.getData();// 拿到服务器响应数据
						new MyModel3(dataTZ);
						new TieZiFrm().setVisible(true);
					}

					@Override
					public MyHttp getMessage() {
						// TODO 发送消息
						MyHttp myHttp = new MyHttp();
						myHttp.setData(tbzt);//主题贴吧
						myHttp.setMyUrl("M_TZServiece");
						myHttp.setType(UrlType.NALMORE);
						return myHttp;
					}
				});
	}
}
