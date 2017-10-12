package priv.sen.gui2;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import org.apache.log4j.Logger;
import priv.sen.entry.MTz;
import priv.sen.entry.MYh;
import priv.sen.net.MessageContexts;
import priv.sen.net.MessageObj;
import priv.sen.net.MyHttp;
import priv.sen.net.UrlType;
import priv.sen.util.ResetValueActionPerformed;
import priv.sen.util.SqlDate;
import priv.sen.util.ZiTi;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.util.List;
import java.util.Map;

/**
 * 帖子详细信息的窗口
 * @author sen
 *
 */
public class NeiRongFrm3 extends JFrame {

	private static Logger logger = Logger.getLogger(NeiRongFrm3.class);
	
	private JPanel contentPane;
	private JTextArea hfArea;//回复框
	private JTextArea zwLabel;//正文显示区
	private JTextArea hfLabel;//回复显示区
	private List<Map<String,Object>> data;
	private String tzbt;//贴子标题

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NeiRongFrm3 frame = new NeiRongFrm3();
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
	public NeiRongFrm3() {
		setResizable(false);
		
		ZiTi ziTi = new ZiTi();
		ziTi.ziTi();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(NeiRongFrm3.class.getResource("/tupian/\u56FE\u68073.jpg")));
		setTitle("\u5E16\u5B50\u5185\u5BB9");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 659, 712);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u56DE\u590D\u5E16\u5B50", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "\u56DE\u590D", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JLabel fbhfLabel = new JLabel("\u53D1\u8868\u56DE\u590D\uFF1A");
		fbhfLabel.setIcon(new ImageIcon(NeiRongFrm3.class.getResource("/tupian/edit.png")));
		
		/**
		 * 文本编辑区
		 */
		
		/**
		 * 回复监听事件
		 */
		
		JScrollPane scrollPane_2 = new JScrollPane();
		JButton fbButton = new JButton("\u53D1\u8868");
		fbButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(hfArea.getText().equals("")){
					JOptionPane.showMessageDialog(null,"请输入回复内容!");
					return;
				}
				huiFuTieZi(e);
				JOptionPane.showMessageDialog(null,"回复成功!");
				getHuiFu();
				ResetValueActionPerformed.resetValueActionPerformed(e,hfArea);
				
			}
		});
		fbButton.setIcon(new ImageIcon(NeiRongFrm3.class.getResource("/tupian/reset.png")));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(fbhfLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(fbButton)
							.addContainerGap())
						.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(fbhfLabel)
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(fbButton)
					.addGap(83))
		);
		hfArea = new JTextArea();
		scrollPane_2.setViewportView(hfArea);
		hfArea.setLineWrap(true);
		
		//设置文本区边框
		hfArea.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		panel_1.setLayout(gl_panel_1);
		
		//TODO 帖子标题
		MTz instance = MTz.getInstance();
//		String str = tieBaZhuYe.getStr();//TODO 拿到用户点击的贴吧名称
		tzbt = instance.getTzbt();
		logger.debug("拿到用户点击的帖子标题:"+tzbt);
		JLabel lzLabel = new JLabel(tzbt);
		lzLabel.setFont(new Font("宋体", Font.BOLD, 16));
		lzLabel.setIcon(new ImageIcon(NeiRongFrm3.class.getResource("/tupian/userName.png")));
		
		//TODO 刷新按钮
		JButton sxButton = new JButton("\u5237\u65B0");
		//TODO 刷新监听事件
		sxButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				getHuiFu();
			
			
			}
		});
		
		
		
		
		
		
		sxButton.setIcon(new ImageIcon(NeiRongFrm3.class.getResource("/tupian/reset.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addComponent(lzLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 467, Short.MAX_VALUE)
					.addComponent(sxButton, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lzLabel))
						.addComponent(sxButton))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
							.addComponent(panel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
							.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 204, Short.MAX_VALUE))
		);
		
		hfLabel = new JTextArea();
		hfLabel.setFont(new Font("Monospaced", Font.PLAIN, 18));
		hfLabel.setLineWrap(true);
		hfLabel.setEditable(false);
		scrollPane_1.setViewportView(hfLabel);
		//滚动条始终在最下面
		DefaultCaret caret = (DefaultCaret)hfLabel.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		getHuiFuByData(new TieZiFrm().getDataHF());
		hfLabel.paintImmediately(hfLabel.getBounds());
		
		zwLabel = new JTextArea();
		zwLabel.setFont(new Font("Monospaced", Font.PLAIN, 18));
		zwLabel.setLineWrap(true);
		zwLabel.setEditable(false);
		scrollPane.setViewportView(zwLabel);
		contentPane.setLayout(gl_contentPane);
	
		zwLabel.setText(new TieZiFrm().getZhengWen());
		//设置窗口居中显示
		this.setLocationRelativeTo(null);
		
		//设置只关闭当前窗口
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
	}

	/**
	 * 回复帖子事件处理
	 * @param e
	 */
	private void huiFuTieZi(ActionEvent e) {

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
				myHttp.setData(MTz.getInstance().getTbzt());//1.贴吧主题
				myHttp.setData2(tzbt);//2.帖子标题
//				myHttp.setData3(" ");//3.帖子正文
				myHttp.setData3(MYh.getInstance().getU_name());//回复人名字
				myHttp.setData4(SqlDate.getDate());//回复时间
				myHttp.setData5(hfArea.getText());//回复内容
				myHttp.setMyUrl("C_HFServiece");
				myHttp.setType(UrlType.NALMORE);
				return myHttp;
			}
		});			
	}

	/**
	 * 回复显示区追加
	 * @param data
	 */
	private void getHuiFuByData(List<Map<String,Object>> data) {
		String hfName = " ";//回复人名字
		String hfDate = " ";//回复事件
		String hf = " ";//回复内容
		hfLabel.setText("");
			for (int i = 0; i < data.size(); i++) {
					hfName = (String) data.get(i).get("U_NAME");
					hfDate = (String) data.get(i).get("HUI_FU_DATE");
					hf = (String) data.get(i).get("HF");
					hfLabel.append(hfName+"\t");
					hfLabel.append(hfDate+"\n");
					hfLabel.append(hf+"\n");
					hfLabel.append("--------------------------------------------------------------------"+"\n");
			}
	}

	/**
	 * 获取回复的信息
	 */
	private void getHuiFu() {
		MessageContexts.getInstance().offerMessage(new MessageObj() {
			
			@Override
			public void handlerMessage(MyHttp info) {
				//TODO 处理消息
				data  = (List<Map<String, Object>>) info.getData();//拿到服务器响应数据
				getHuiFuByData(data);	
			}
			
			@Override
			public MyHttp getMessage() {
				//TODO 发送消息
				MyHttp myHttp = new MyHttp();
				myHttp.setData(MTz.getInstance().getTbzt());//贴吧主题
				myHttp.setData2(tzbt);//帖子标题
				myHttp.setMyUrl("M_NRServiece2");
				myHttp.setType(UrlType.NALMORE);
				return myHttp;
			}
		});
	}
}
