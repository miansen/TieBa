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
import javax.swing.JOptionPane;
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
import org.apache.log4j.Logger;
import priv.sen.entry.MTz;
import priv.sen.entry.MYh;
import priv.sen.entry.Tiezi;
import priv.sen.net.MessageContexts;
import priv.sen.net.MessageObj;
import priv.sen.net.MyHttp;
import priv.sen.net.UrlType;
import priv.sen.util.ResetValueActionPerformed;
import priv.sen.util.SqlDate;
import priv.sen.util.StringUtil;
import priv.sen.util.ZiTi;
import priv.sen.view.MyModel3;
import java.awt.Font;

/**
 * 帖子列表的界面
 * @author sen
 *
 */
public class TieZiFrm extends JFrame {

	private static Logger logger = Logger.getLogger(TieZiFrm.class);
	private JPanel contentPane;
	private JTable table;
	JTextArea tzzwArea;//帖子正文
	private JTextArea tzbtField;//发表帖子输入框
	private JTextArea s_tieZi;//搜索帖子输入框
	private static List<Map<String,Object>> dataZW;//帖子内容信息
	private static List<Map<String,Object>> dataHF;//帖子内容信息
	private List<Map<String,Object>> data;//刷新帖子信息
	String tbzt;
	private String str;
	private static String zhengWen;//正文
	
	
	
	public static String getZhengWen() {
		return zhengWen;
	}

	public static void setZhengWen(String zhengWen) {
		TieZiFrm.zhengWen = zhengWen;
	}

	public static List<Map<String, Object>> getDataZW() {
		return dataZW;
	}

	public static void setDataZW(List<Map<String, Object>> dataNR) {
		TieZiFrm.dataZW = dataNR;
	}

	
	public static List<Map<String, Object>> getDataHF() {
		return dataHF;
	}

	public static void setDataHF(List<Map<String, Object>> dataHF) {
		TieZiFrm.dataHF = dataHF;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TieZiFrm frame = new TieZiFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TieZiFrm() {
		setResizable(false);
		
		ZiTi ziTi = new ZiTi();
		ziTi.ziTi();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(TieZiFrm.class.getResource("/tupian/\u56FE\u68073.jpg")));
		setTitle("\u5E16\u5B50\u5217\u8868");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 716);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u641C\u7D22\u5E16\u5B50", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u53D1\u5E16", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
//		TieBaZhuYe tieBaZhuYe = new TieBaZhuYe();
		MTz instance = MTz.getInstance();
//		String str = tieBaZhuYe.getStr();//TODO 拿到用户点击的贴吧名称
		tbzt = instance.getTbzt();
		logger.debug("拿到用户点击的贴吧名称:"+tbzt);
		JLabel tbmcLabel = new JLabel(tbzt);
		tbmcLabel.setIcon(new ImageIcon(TieZiFrm.class.getResource("/tupian/userName.png")));
		tbmcLabel.setFont(new Font("宋体", Font.BOLD, 16));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 639, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(tbmcLabel))
						.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(18)
					.addComponent(tbmcLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		
		JLabel tzbtLabel = new JLabel("\u5E16\u5B50\u6807\u9898\uFF1A");
		tzbtLabel.setIcon(new ImageIcon(TieZiFrm.class.getResource("/tupian/me.png")));
		
		JLabel tzzwLabel = new JLabel("\u5E16\u5B50\u6B63\u6587\uFF1A");
		tzzwLabel.setIcon(new ImageIcon(TieZiFrm.class.getResource("/tupian/edit.png")));
		
		/**
		 * 文本编辑区
		 */
		JButton fbButton = new JButton("\u53D1\u8868");
		/**
		 * 发表帖子监听事件
		 */
		fbButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tzbtField.getText().equals("")){
					JOptionPane.showMessageDialog(null,"请输入帖子标题!");
					return;
				}else if(tzzwArea.getText().equals("")){
					JOptionPane.showMessageDialog(null,"请输入帖子正文!");
					return;
				}
				faBiaoTieZi(e);
				JOptionPane.showMessageDialog(null,"发帖成功!");
				getTieZiData();
				ResetValueActionPerformed.resetValueActionPerformed(e,tzzwArea,tzbtField);
			}
		});
		fbButton.setIcon(new ImageIcon(TieZiFrm.class.getResource("/tupian/reset.png")));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(tzbtLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 542, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(tzzwLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_1, 0, 0, Short.MAX_VALUE)
								.addComponent(fbButton))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(tzbtLabel)
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(tzzwLabel)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(fbButton)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		tzbtField = new JTextArea();
		scrollPane_2.setViewportView(tzbtField);
		tzzwArea = new JTextArea();
		tzzwArea.setLineWrap(true);
		scrollPane_1.setViewportView(tzzwArea);
		tzzwArea.setSize(610, 600);
		
		//设置文本区边框
		tzzwArea.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		panel_1.setLayout(gl_panel_1);
		
		JLabel tzmcLabel = new JLabel("\u5E16\u5B50\u540D\u79F0\uFF1A");
		tzmcLabel.setIcon(new ImageIcon(TieZiFrm.class.getResource("/tupian/edit.png")));
		
		JButton ssButton = new JButton("\u641C\u7D22");
		/**
		 * 搜索帖子事件监听
		 */
		ssButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				souSuoTieZi(e);
			}
		});
		ssButton.setIcon(new ImageIcon(TieZiFrm.class.getResource("/tupian/search.png")));
		
		JButton sxButton = new JButton("\u5237\u65B0");
		//刷新按钮
		sxButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				tbzt = instance.getTbzt();
//				String text = tbzt;
				getTieZiData();
			
			}
		});
		sxButton.setIcon(new ImageIcon(TieZiFrm.class.getResource("/tupian/login.png")));
		
		JScrollPane scrollPane_4 = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addComponent(tzmcLabel)
					.addGap(10)
					.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ssButton)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(sxButton)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(sxButton, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(ssButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(tzmcLabel)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(12)
							.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		s_tieZi = new JTextArea();
		s_tieZi.setLineWrap(true);
		scrollPane_4.setViewportView(s_tieZi);
		panel.setLayout(gl_panel);
		
		table = new JTable();
		table.setRowHeight(25);//表格高度
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				bookTableMousePressed(me);
			}
			
			//鼠标单击事件
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowI = table.rowAtPoint(e.getPoint());//得到table的行数
				if (rowI > -1){
					str = (String) table.getModel().getValueAt(rowI, 0);
					logger.debug("单击鼠标的帖子是： "+str);
					MTz instance = MTz.getInstance();
					instance.setTzbt(str);//帖子标题
					String tbzt = instance.getTbzt();//贴吧主题
					String tzbt = instance.getTzbt();//帖子标题
					logger.debug("贴吧主题是："+tbzt);
					logger.debug("帖子标题是："+tzbt);
					
					MessageContexts.getInstance().offerMessage(new MessageObj() {
						
						@Override
						public void handlerMessage(MyHttp info) {
							//TODO 处理消息
							dataHF = (List<Map<String, Object>>) info.getData();//拿到服务器响应数据
//							new NeiRongFrm().setVisible(true);
						}
						
						@Override
						public MyHttp getMessage() {
							//TODO 发送消息
							MyHttp myHttp = new MyHttp();
							myHttp.setData(tbzt);//贴吧主题
							myHttp.setData2(tzbt);//帖子标题
							myHttp.setMyUrl("M_NRServiece2");//回复
							myHttp.setType(UrlType.NALMORE);
							return myHttp;
						}
					});
					
					
					
					
					
					
					
					
					
					MessageContexts.getInstance().offerMessage(new MessageObj() {
						
						@Override
						public void handlerMessage(MyHttp info) {
							//TODO 处理消息
//							JOptionPane.showMessageDialog(null, info);
							dataZW = (List<Map<String, Object>>) info.getData();//拿到服务器响应数据
							
							for (int i = 0; i < dataZW.size(); i++) {
								zhengWen = (String) dataZW.get(i).get("ZW");
							}
							logger.debug("正文是:"+zhengWen);
//							Object object = data.get(0).get("U_NAME");
//							table.setModel(new MyModel3(data));
							
//							new NeiRongFrm().setVisible(true);
//							infoLabel.setText(object.toString());
						}
						
						@Override
						public MyHttp getMessage() {
							//TODO 发送消息
							MyHttp myHttp = new MyHttp();
							myHttp.setData(tbzt);//贴吧主题
							myHttp.setData2(tzbt);//帖子标题
							myHttp.setMyUrl("M_NRServiece");//正文
							myHttp.setType(UrlType.NALMORE);
							return myHttp;
						}
					});
						
					new NeiRongFrm3().setVisible(true);
				}
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new MyModel3(new TieBaFrm().getDataTZ()));
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
	 * 搜索帖子事件处理
	 * @param e
	 */
	private void souSuoTieZi(ActionEvent e) {
		

		String s_tieZi = this.s_tieZi.getText();// 获取用户搜索帖子的字段

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
				myHttp.setData(s_tieZi);//用户搜索帖子的字段
				myHttp.setData2(tbzt);//贴吧主题
				myHttp.setMyUrl("S_TieZiServiece");
				myHttp.setType(UrlType.NALMORE);
				return myHttp;
			}
		});
		table.setModel(new MyModel3(data));
		logger.debug("搜索帖子结果："+data);
	
		
	}

	/**
	 * 发表帖子处理
	 * @param e
	 */
	private void faBiaoTieZi(ActionEvent e) {

//		Tiezi instance = Tiezi.getInstance();
		String tieZiBiaoTi = tzbtField.getText();//获取用户输入的帖子标题
		String tieZiZhengWen = tzzwArea.getText();//获取用户输入的帖子正文
		String u_name = MYh.getInstance().getU_name();//获取用户名
		Tiezi.getInstance().setTzneirong(tieZiZhengWen);//帖子内容
		
		try {
			StringUtil.JlabelSetText(tzzwArea, tieZiZhengWen);
		} catch (InterruptedException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
//		instance.setU_zt(text);
//		instance.setU_jianjie(text2);
//		instance.setU_name(yongHuNiCheng());
		
		MessageContexts.getInstance().offerMessage(new MessageObj() {
			
			@Override
			public void handlerMessage(MyHttp info) {
				//TODO 处理消息
				data  = (List<Map<String, Object>>) info.getData();//拿到服务器响应数据
//				new TieZiFrm().setVisible(true);
			}
			
			@Override
			public MyHttp getMessage() {
				//TODO 发送消息
				MyHttp myHttp = new MyHttp();
				myHttp.setData(tbzt);//1.贴吧主题
				myHttp.setData2(tieZiBiaoTi);//2.帖子标题
				myHttp.setData3(u_name);//3.发帖人名字
				myHttp.setData4(SqlDate.getDate());//4.发帖时间
				myHttp.setData5(tieZiZhengWen);//5.帖子正文
//				myHttp.setData6(" ");//6.回复内容
				myHttp.setMyUrl("C_TZServiece");
				myHttp.setType(UrlType.NALMORE);
				return myHttp;
			}
		});			
	
	}

	/**
	 * TODO 表格栏点击事件处理
	 * @param me
	 */
	private void bookTableMousePressed(MouseEvent me) {
		int selectedRow = this.table.getSelectedRow();
		
	}

	/**
	 * 获取帖子信息
	 */
	private void getTieZiData() {
		MessageContexts.getInstance().offerMessage(new MessageObj() {
			
			@Override
			public void handlerMessage(MyHttp info) {
				//TODO 处理消息
//						JOptionPane.showMessageDialog(null, info);
				data  = (List<Map<String, Object>>) info.getData();//拿到服务器响应数据
//						Object object = data.get(0).get("U_NAME");
				logger.debug(data);
				table.setModel(new MyModel3(data));
//						infoLabel.setText(object.toString());
			}
			
			@Override
			public MyHttp getMessage() {
				//TODO 发送消息
				MyHttp myHttp = new MyHttp();
				myHttp.setData(tbzt);//贴吧主题
				myHttp.setMyUrl("M_TZServiece");
				myHttp.setType(UrlType.NALMORE);
				return myHttp;
			}
		});
	}
}
