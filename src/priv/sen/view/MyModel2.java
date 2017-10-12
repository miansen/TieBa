package priv.sen.view;

import java.util.List;
import java.util.Map;

import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.log4j.Logger;



/**
 * 主题贴吧主页的表格
 * @author sen
 *
 */
public class MyModel2 implements TableModel {
	private static Logger logger = Logger.getLogger(MyModel2.class);
	
	private static List<Map<String,Object>> data = null;
	private String[] names = {"主题贴吧", "吧主","贴吧简介","创吧时间"};//列名
	private String[] fieldNames = {"U_ZT", "U_NAME","U_JIANJIE","U_DATE"};//列名
	private Class[] columnTypes = new Class[] {
			String.class,String.class,String.class,String.class
		};//列的类型
	/**
	 * new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u59D3\u540D", "\u5DE5\u4F5C", "\u5DE5\u8D44"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		}
	 */

	public MyModel2(List<Map<String, Object>> data2) {
		this.data = data2;
	}
	

	/**
	 * 有多少行
	 */
	@Override
	public int getRowCount() {
		
		return data==null?0:data.size();
	}

	/**
	 * 有多少列
	 */
	@Override
	public int getColumnCount() {
		return 4;
	}

	/**
	 * 列的名称
	 */
	@Override
	public String getColumnName(int columnIndex) {
		return names[columnIndex];
	}

	/**
	 * 列的类型
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnTypes[columnIndex];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 拿数据
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Map<String, Object> map = data.get(rowIndex);
		String str = fieldNames[columnIndex];
		return map.get(str);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}
}
