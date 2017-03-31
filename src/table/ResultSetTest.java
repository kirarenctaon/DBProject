//���ڵ� ����� �迭�� ���� �� ����? ���ڵ��� �� ������ �˼��� ����. 

package table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetTest {
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="batman";
	String password="1234";
	
	//Java Data Base Connectivity = �ڹ��� �����ͺ��̽� ���� ����� ��Ī
	/*jdbc�� 3�ѻ� : ���ӽõ� ��ü�� �ƴ϶�, ������ ���� �� ����� ��� ��ü�̴�. 
	 ��ΰ� �������̽��̴�. 
	 ��? 95�⵵�� ���ߵ� java�� �� ���Ŀ� ���ߵ� �����ͺ��̽��� �ٸ� ���α׷��� ��� ������ �� ������?
	 	�ڹٴ� � DB�� ���� ���������� ������ ���� �ڽŵ��� ����̺긦 �̿��ϵ��� ������
		��ȹ�� SUN�� �����, ����̹� ������ �������. 
		�׷��� ����̹� ��ü�� �ٿ�ε�� ������ ������. ������ ������ ��ȹ�� ���� SUN�� API�� ������
		
		�����ϴ� � DB�͵� ������� �߸����� �������̽��� ����̹���. ������ ������
	 
	 */
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//���ڵ�� ��ü�� �̿��Ͽ� �� ���ڵ�� �˾Ƹ��纸��
	public ResultSetTest() {
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);
			if(con!=null){
				String sql="select * from company";
				pstmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY); //���ӵ� ���Ŀ� �������� ������ �ֱ� ������ con���� ������
				//rs�� Ŀ���� ������, �Ĺ��� ������ �����Ӱ� �����̰ų� �Ѳ����� �ǳʶٰ� �Ϸ��� 
				//scroll������ ����ɼ��� �ο��ؾ��Ѵ�. 
				
				//select���� ��� ������ ������� ���� ���⸸ �Ҳ��� READ_ONLY,
				//������ ���Ҳ��� UPDATABEL
				//zino�� ����� SELECT���� ���� ���ڵ�� �б������̴�. 
				rs= pstmt.executeQuery();
				
				//���� ���������� ������
				rs.last(); 
				/* rs.last(); 
				 ������ ���� ��� ���տ� �������� �۾��� ����Ǿ����ϴ�=
				 forword�θ� �ѹ��ڱ��� �����ִ� �޼����ε� �� �� ū ����� �䱸�ϳ�?
				 */
				int num=rs.getRow(); //���� Ŀ���� ����Ű�� ���ڵ� ��ȣ, �� ���ڵ��� ��ġ
				System.out.println(num);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new ResultSetTest();
	}

}
