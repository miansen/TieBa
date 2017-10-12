package priv.sen.view;

import java.util.List;
import java.util.Map;

import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;

/**
 * 帖子界面的表格
 * @author sen
 *
 */
public class MyModel4 implements TableModel {
	private static Logger logger = Logger.getLogger(MyModel4.class);
	private List<Map<String,Object>> data = null;
	private String[] names = {"回帖内容","名字","回帖时间"};//列名
	private String[] fieldNames = {"HF", "U_NAME","HUI_FU_DATE"};//列名
	private Class[] columnTypes = new Class[] {
			String.class,String.class,String.class
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

	public MyModel4(List<Map<String, Object>> data2) {
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
		return 3;
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
		String str2 = "";
		if(map.get(str) != null){
			return map.get(str);
		}else{
			return str2;
		}
		
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
