//레코드 결과를 배열로 받을 때 단점? 레코드의 총 갯수를 알수가 없다. 

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
	
	//Java Data Base Connectivity = 자바의 데이터베이스 연동 기술을 통칭
	/*jdbc의 3총사 : 접속시도 객체가 아니라, 접속한 이후 그 결과를 담는 객체이다. 
	 모두가 인터페이스이다. 
	 왜? 95년도에 개발된 java가 그 이후에 개발된 데이터베이스나 다른 프로그램을 어떻게 접근할 수 있을까?
	 	자바는 어떤 DB가 새로 생기지오는 관심이 없도 자신들의 드라이브를 이용하도록 강제함
		계획은 SUN이 세우고, 드라이버 구현은 밴더사함. 
		그래서 드라이버 자체는 다운로드는 밴더사로 가야함. 하지만 사용법은 계획을 세운 SUN의 API를 봐야함
		
		현존하는 어떤 DB와도 상관없는 중립적인 인터페이스가 드라이버다. 구현을 강제함
	 
	 */
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//레코드셋 객체를 이용하여 총 레코드수 알아맞춰보기
	public ResultSetTest() {
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);
			if(con!=null){
				String sql="select * from company";
				pstmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY); //접속된 이후에 쿼리문을 날리수 있기 때문에 con에서 얻어오자
				//rs의 커서를 전방향, 후방향 등으로 자유롭게 움직이거나 한꺼번에 건너뛰게 하려면 
				//scroll가능한 상수옵셥을 부여해야한다. 
				
				//select문의 결과 집합을 대상으로 단지 보기만 할꺼면 READ_ONLY,
				//수정을 가할꺼면 UPDATABEL
				//zino쌤 경험상 SELECT문에 의한 레코드는 읽기위함이다. 
				rs= pstmt.executeQuery();
				
				//제일 마지막으로 보내기
				rs.last(); 
				/* rs.last(); 
				 전방향 전용 결과 집합에 부적합한 작업이 수행되었습니다=
				 forword로만 한발자국씩 갈수있는 메서드인데 왜 더 큰 기능을 요구하냐?
				 */
				int num=rs.getRow(); //현재 커서가 가리키는 레코드 번호, 즉 레코드의 위치
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
