/*
우리가 사용중인 데이터베이스 제품은 모두 DBMS이다. 
DB(저장소)MS(관리시스템 ) : 네트워크기반이라서 원격 접속이 가능하다. 

현재 사용중인 네트워크 프로토콜은 TCP/IP기반이므로, 
원격지 호스트를 접속하려면 그 호스트의 주소를 알아야 하는데, 
기반이 TCP/IP인지라 IP주소를 알아야한다.  + USER 계정 정보까지 알아야 한다. 
*/

package oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestMain {
	public static void main(String[] args) {
		/*1단계 : 오라클을 자바가 제어할 수 있는 코드가 들어있는 jar 파일을 메모리에 로드해야한다. 
					이런 데이터베이스 제어 jar파일을 자바에서는 드라이버라 한다.
					드라이버는 DB제조사에서 제공한다. 
					내가쓰는 제품이 Oracle이라면  Oracle사
									   mysql이라면 mysql홈페이지
										mmsql이라면 ms 사
			2단계  : 오라클에 접속하자.*/
	
		Connection con=null; //지역변수, try문 밖에서 받을려고 꺼낸거임
		PreparedStatement pstmt=null; //지역변수, try문 밖에서 받을려고 꺼낸거임
		
		try {
			//드라이버 클래스로드 : 희한한게 스트링형으로 넣으면 됨
			Class.forName("oracle.jdbc.driver.OracleDriver"); //오라클제어 클래스. explorer에서 복사해오자
			System.out.println("드라이버 로드 성공");
		
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "batman", "1234"); //localhost 나자신을나타냄
			if(con!=null){
				System.out.println("접속성공"); //이 시점에 sqlplus에 접속한 것과 같은 효과가 나타남
				
				//현재 유저가 보유한 테이블에 insert
				String sql="insert into company(company_id, brand) values(seq_company.nextval, '나이키')";
				//쿼리문 수행을 위해서는 쿼리문을 전담하는 객체를 이용해야 하는데, 이 객체가 바로 PreparedStatement 인터페이스이다. 
				pstmt=con.prepareStatement(sql);
				//(인터페이스는 자식을 new기 보다 sun에서 사용할수 있는 방법을 제공하는 경우가 많기 때문에 con.을 찍으면 반환형에 따라 쓸만한 녀석을이 나타함)
				int result=pstmt.executeUpdate();//쿼리문실행 메서드
				if(result==1){//이 쿼리문 수행에 의해 반영된 레코드의 수를 반환해 준다. (쿼리문이 성공하면 언제나 한건 만 반환해주는 걸이용) 
					System.out.println("입력성공");
				}else{
					System.out.println("입력실패");
				}
				
			}else{
				System.out.println("접속실패");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");
			e.printStackTrace(); //스택구조로 에러를 출력함
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//스트림과 DB연동작업 후엔 반드시 닫는 처리를 해야한다. 
			if(pstmt!=null){//올라왔을때만 닫으라는 조건문!!
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null){//올라왔을때만 닫으라는 조건문!!
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
		}//finally 끝		
	}
}

