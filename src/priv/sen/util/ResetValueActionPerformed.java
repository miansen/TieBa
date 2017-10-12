package priv.sen.util;

import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ResetValueActionPerformed {

	/**
	 * 重置用户输入框
	 * @param e
	 * @param jTextArea
	 * @param jTextField
	 */
	public static void resetValueActionPerformed(ActionEvent e,JTextArea...jTextArea) {
		for(int i=0;i<jTextArea.length;i++){
			jTextArea[i].setText("");
		}
		
//		jTextField[0].setText("");
	}
}
