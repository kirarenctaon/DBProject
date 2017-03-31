/*
이 객체는 JTable의 생성자에서 요구하는 컨트롤러 객체이다. 
이 객체의 역할은 디자인과 로직을 분리시켜는 중간자 역할 
  
  
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
		data[0][0]="청바지";
		data[0][1]="지오다노";
		
		data[1][0]="가디건";
		data[1][1]="뱅뱅";
		
		data[2][0]="치마";
		data[2][1]="나이키";
	}	
	
	@Override
	public int getColumnCount() { //컬럼의 갯수를 반환하는 매서드

		return 2;
	}

	@Override
	public int getRowCount() { //레코드의 갯수를 반환하는 매서드

		return 3;
	}

	@Override
	public Object getValueAt(int row, int col) { //특정 위치의 값을 반환하는 메서드
		//이 모델을 사용하는 주체는 사용자가 아닌 JTable, 따라서 이 인수도 JTable이 넘김
		
		System.out.println("row="+row+", col="+col+"에 뭘 넣어야해요?");
		return data[row][col];
		//return null;
	}
}
