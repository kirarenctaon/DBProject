/*
�츮�� ������� �����ͺ��̽� ��ǰ�� ��� DBMS�̴�. 
DB(�����)MS(�����ý��� ) : ��Ʈ��ũ����̶� ���� ������ �����ϴ�. 

���� ������� ��Ʈ��ũ ���������� TCP/IP����̹Ƿ�, 
������ ȣ��Ʈ�� �����Ϸ��� �� ȣ��Ʈ�� �ּҸ� �˾ƾ� �ϴµ�, 
����� TCP/IP������ IP�ּҸ� �˾ƾ��Ѵ�.  + USER ���� �������� �˾ƾ� �Ѵ�. 
*/

package oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestMain {
	public static void main(String[] args) {
		/*1�ܰ� : ����Ŭ�� �ڹٰ� ������ �� �ִ� �ڵ尡 ����ִ� jar ������ �޸𸮿� �ε��ؾ��Ѵ�. 
					�̷� �����ͺ��̽� ���� jar������ �ڹٿ����� ����̹��� �Ѵ�.
					����̹��� DB�����翡�� �����Ѵ�. 
					�������� ��ǰ�� Oracle�̶��  Oracle��
									   mysql�̶�� mysqlȨ������
										mmsql�̶�� ms ��
			2�ܰ�  : ����Ŭ�� ��������.*/
	
		Connection con=null; //��������, try�� �ۿ��� �������� ��������
		PreparedStatement pstmt=null; //��������, try�� �ۿ��� �������� ��������
		
		try {
			//����̹� Ŭ�����ε� : �����Ѱ� ��Ʈ�������� ������ ��
			Class.forName("oracle.jdbc.driver.OracleDriver"); //����Ŭ���� Ŭ����. explorer���� �����ؿ���
			System.out.println("����̹� �ε� ����");
		
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "batman", "1234"); //localhost ���ڽ�����Ÿ��
			if(con!=null){
				System.out.println("���Ӽ���"); //�� ������ sqlplus�� ������ �Ͱ� ���� ȿ���� ��Ÿ��
				
				//���� ������ ������ ���̺� insert
				String sql="insert into company(company_id, brand) values(seq_company.nextval, '����Ű')";
				//������ ������ ���ؼ��� �������� �����ϴ� ��ü�� �̿��ؾ� �ϴµ�, �� ��ü�� �ٷ� PreparedStatement �������̽��̴�. 
				pstmt=con.prepareStatement(sql);
				//(�������̽��� �ڽ��� new�� ���� sun���� ����Ҽ� �ִ� ����� �����ϴ� ��찡 ���� ������ con.�� ������ ��ȯ���� ���� ������ �༮���� ��Ÿ��)
				int result=pstmt.executeUpdate();//���������� �޼���
				if(result==1){//�� ������ ���࿡ ���� �ݿ��� ���ڵ��� ���� ��ȯ�� �ش�. (�������� �����ϸ� ������ �Ѱ� �� ��ȯ���ִ� ���̿�) 
					System.out.println("�Է¼���");
				}else{
					System.out.println("�Է½���");
				}
				
			}else{
				System.out.println("���ӽ���");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("����̹��� ã�� �� �����ϴ�.");
			e.printStackTrace(); //���ñ����� ������ �����
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//��Ʈ���� DB�����۾� �Ŀ� �ݵ�� �ݴ� ó���� �ؾ��Ѵ�. 
			if(pstmt!=null){//�ö�������� ������� ���ǹ�!!
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null){//�ö�������� ������� ���ǹ�!!
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
		}//finally ��		
	}
}

