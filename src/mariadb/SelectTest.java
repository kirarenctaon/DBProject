package mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//������ ��� �����Ͽ� ���ڵ带 �ֿܼ� ���
//dbms�����簡 �����ϴ� ����̹� �غ��ϱ�

public class SelectTest {
	String driver="org.mariadb.jdbc.Driver";
	String url="jdbc:mariadb://localhost:3306/db0331";
	String user="root";
	String password="";
	
	Connection con;//���������� ���� �������̽�
	PreparedStatement pstmt;//���� ���� ��ü
	ResultSet rs;//�������� select���� ��� �������� �����ͺ��̽��� ���̺�� ������ ��������� ��Ƴ��� ��ü(=���̺�� ����)
	
	public SelectTest(){
		/*
		 DB������ 4�ܰ�
		 	1. ����̹��� �ε��϶�
			2. �����϶�
			3.���ϴ� ������ ������
			4. DB ���õ��ڿ� �ݱ�
		*/
		try {
			Class.forName(driver);
			System.out.println("�ε强��");//1�ܰ� ��
			
			con=DriverManager.getConnection(url,user,password);//getConnection�� ��ȯ���� Connection
			if(con!=null){
				System.out.println("���� ����");
				
				//���ӿ� ���������� ���ϴ� �������� ������
				String sql="select * from member";//�ܼ�â�� �ƴϴϱ� �����ݷ� ������
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();// ���������� ��ȯ�Ǵ� ��� ������ ����. ��? �������̴ϱ�. 
				
				while(rs.next()){//������ ��ĭ ����
					String name=rs.getString("name");//�÷��� �ش��ϴ� �� ��ȯ
					int age=rs.getInt("age"); //���̹�ȯ
					int member_id=rs.getInt("member_id"); 
					System.out.println(member_id+name+','+age);
				}//3�ܰ賡
			}
			else{
				System.out.println("���� ����");
			}//2�ܰ賡
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(con!=null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(pstmt!=null){
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		new SelectTest();
	}

}