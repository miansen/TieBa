package priv.sen.util;

import java.awt.Font;
import javax.swing.UIManager;

/**
 * 
 * @author sen 改变系统默认字体的工具类
 */
public class ZiTi {

	public void ziTi() {
		// 改变系统默认字体
		Font font = new Font("Dialog", Font.PLAIN, 12);
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, font);
			}
		}
	}
}
