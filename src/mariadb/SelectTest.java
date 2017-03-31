package mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//마리아 디비를 연동하여 레코드를 콘솔에 찍기
//dbms제조사가 제공하는 드라이버 준비하기

public class SelectTest {
	String driver="org.mariadb.jdbc.Driver";
	String url="jdbc:mariadb://localhost:3306/db0331";
	String user="root";
	String password="";
	
	Connection con;//접속정보를 가진 인터페이스
	PreparedStatement pstmt;//쿼리 수행 객체
	ResultSet rs;//쿼리문이 select문일 경우 원격지의 데이터베이스의 테이블과 동일한 결과집합을 담아놓는 객체(=테이블과 같다)
	
	public SelectTest(){
		/*
		 DB연동의 4단계
		 	1. 드라이버를 로드하라
			2. 접속하라
			3.원하는 쿼리문 날리기
			4. DB 관련된자원 닫기
		*/
		try {
			Class.forName(driver);
			System.out.println("로드성공");//1단계 끝
			
			con=DriverManager.getConnection(url,user,password);//getConnection의 반환형이 Connection
			if(con!=null){
				System.out.println("접속 성공");
				
				//접속에 성공했으니 원하는 쿼리문을 날리자
				String sql="select * from member";//콘솔창이 아니니까 세미콜론 안찍어도됨
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();// 쿼리수행후 반환되는 결과 집합을 받자. 왜? 쿼리문이니까. 
				
				while(rs.next()){//포인터 한칸 전진
					String name=rs.getString("name");//컬럼에 해당하는 값 반환
					int age=rs.getInt("age"); //나이반환
					int member_id=rs.getInt("member_id"); 
					System.out.println(member_id+name+','+age);
				}//3단계끝
			}
			else{
				System.out.println("접속 실패");
			}//2단계끝
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