/*
�� ��ü�� JTable�� �����ڿ��� �䱸�ϴ� ��Ʈ�ѷ� ��ü�̴�. 
�� ��ü�� ������ �����ΰ� ������ �и����Ѵ� �߰��� ���� 
  
  
 */

package model;

import javax.swing.table.AbstractTableModel;

public class MariaModel extends AbstractTableModel{
	
	
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="batman";
	String password="1234";
	
	String[][] data=new String[3][2];
 			
	public MariaModel() {
		data[0][0]="û����";
		data[0][1]="�����ٳ�";
		
		data[1][0]="�����";
		data[1][1]="���";
		
		data[2][0]="ġ��";
		data[2][1]="����Ű";
	}	
	
	@Override
	public int getColumnCount() { //�÷��� ������ ��ȯ�ϴ� �ż���

		return 2;
	}

	@Override
	public int getRowCount() { //���ڵ��� ������ ��ȯ�ϴ� �ż���

		return 3;
	}

	@Override
	public Object getValueAt(int row, int col) { //Ư�� ��ġ�� ���� ��ȯ�ϴ� �޼���
		//�� ���� ����ϴ� ��ü�� ����ڰ� �ƴ� JTable, ���� �� �μ��� JTable�� �ѱ�
		
		System.out.println("row="+row+", col="+col+"�� �� �־���ؿ�?");
		return data[row][col];
		//return null;
	}
}
