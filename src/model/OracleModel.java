/*
�� ��ü�� JTable�� �����ڿ��� �䱸�ϴ� ��Ʈ�ѷ� ��ü�̴�. 
�� ��ü�� ������ �����ΰ� ������ �и����Ѵ� �߰��� ���� 
  
  
 */

package model;

import javax.swing.table.AbstractTableModel;

public class OracleModel extends AbstractTableModel{
	
	
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="batman";
	String password="1234";
	
	String[][] data=new String[3][3];
 			
	public OracleModel() {
		data[0][0]="¥���";
		data[0][1]="�ϰ����";
		data[0][2]="5000";
		
		data[1][0]="������ġ";
		data[1][1]="�������";
		data[1][2]="1000";
		
		data[2][0]="��ũ������ ġŲ";
		data[2][1]="KFC";
		data[2][2]="100";
	}	
	
	@Override
	public int getColumnCount() { //�÷��� ������ ��ȯ�ϴ� �ż���

		return data[0].length;
	}

	@Override
	public int getRowCount() { //���ڵ��� ������ ��ȯ�ϴ� �ż���

		return data.length;
	}

	@Override
	public Object getValueAt(int row, int col) { //Ư�� ��ġ�� ���� ��ȯ�ϴ� �޼���
		//�� ���� ����ϴ� ��ü�� ����ڰ� �ƴ� JTable, ���� �� �μ��� JTable�� �ѱ�
		
		System.out.println("row="+row+", col="+col+"�� �� �־���ؿ�?");
		return data[row][col];
		//return null;
	}
}
