package priv.sen.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import priv.sen.client.Client;
import priv.sen.net.MessageContexts;
import priv.sen.net.MessageObj;
import priv.sen.net.MyHttp;
import priv.sen.net.UrlType;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * 程序的主窗体
 * @author sen
 *
 */
public class NMianFrame extends JFrame {

	private JPanel contentPane;
	private JTextField userNameField;
	private JTextField passwordField;
	private JTable table;
	List<Map<String,Object>> data;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NMianFrame frame = new NMianFrame();
					frame.setVisible(true);
					
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							new Client().connet("127.0.0.1",9999);
						}
					}).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NMianFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 716, 617);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel userNameLabel = new JLabel("\u7528\u6237\u540D\uFF1A");
		
		JLabel passwordLabel = new JLabel("\u5BC6  \u7801\uFF1A");
		
		userNameField = new JTextField();
		userNameField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setColumns(10);
		
		JLabel infoLabel = new JLabel("New label");
		
	
		
		
		
		
		JButton dengLuButton = new JButton("\u767B\u5F55");
		
		//按钮点击事件
		dengLuButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String text = userNameField.getText();
				MessageContexts.getInstance().offerMessage(new MessageObj() {
					
					@Override
					public void handlerMessage(MyHttp info) {
						//TODO 处理消息
//						JOptionPane.showMessageDialog(null, info);
						data  = (List<Map<String, Object>>) info.getData();//拿到服务器响应数据
						Object object = data.get(0).get("ENAME");
						table.setModel(new MyModel(data));
						infoLabel.setText(object.toString());
					}
					
					@Override
					public MyHttp getMessage() {
						//TODO 发送消息
						MyHttp myHttp = new MyHttp();
						myHttp.setData(text);
						myHttp.setMyUrl("MenpServiece");
						myHttp.setType(UrlType.NALMORE);
						return myHttp;
					}
				});
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(86)
							.addComponent(userNameLabel)
							.addGap(4)
							.addComponent(userNameField, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(43)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(43)
									.addComponent(passwordLabel))
								.addComponent(infoLabel, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(95)
									.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(86)
							.addComponent(dengLuButton)))
					.addContainerGap(395, Short.MAX_VALUE))
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(53)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(userNameLabel)
						.addComponent(userNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(2)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(13)
							.addComponent(passwordLabel))
						.addComponent(infoLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(41)
					.addComponent(dengLuButton)
					.addGap(131)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(41, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new MyModel(data));
		table.repaint();
		table.getColumnModel().getColumn(0).setPreferredWidth(187);
		table.getColumnModel().getColumn(1).setPreferredWidth(201);
		table.getColumnModel().getColumn(2).setPreferredWidth(247);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
	}
}