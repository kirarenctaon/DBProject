/*
 � ���ø����̼� �����ϴ� ������������ ���ƾ� ����� �����ǰ�, ���ø����̼��� ǰ���� �ö󰣴�. 
 Ư�� �����ΰ� ������ �����ִ� GUI�� �ִ� ���ø����̼��� ���, 
 �������� ���� ������ �ʴ� ���������� ������Ʈ�� ���� �����η����� ���� �������� ���, ���� ���������� ����Ǿ��� �� �����ϱⰡ ���������. 
 ��, ������������ ��������. 

�̷��� ������ ������ ������ ��������, ������ ������� �̹� �����ߴ� �������̴�.
������ ������ "������ ������(��)�� �и����� �����ߴ��� ������������ ���� �ö󰬴�"�� ������ ������ MVC�����̶�� �Ѵ�. 

 JTable�� swing������Ʈ �� MVC������ ����ִ� ������Ʈ�̸�, �����ο� �ش��ϴ� JTable�� ������ �ش��ϴ�
 DB�����͸� �и���Ű�� ���� TableModel �̶�� �߰� ��Ʈ�ѷ��� �������ش�.  
 */
package model;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ModelTest extends JFrame{
	 //�����⿡ �Ұ�, ������ ¥���� model�� �����϶�
	JTable table;
	JScrollPane scroll;
	
	public ModelTest() {
		//table=new JTable(new MariaModel());
		table=new JTable(new OracleModel());
		scroll=new JScrollPane(table);
		add(scroll);
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) {
		new ModelTest();
	}
}
