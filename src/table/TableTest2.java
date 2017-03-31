/*
  스윙의 컴포넌트 중 데이터베이스의 결과집합을 시각화 하기에 최적화된 컴포넌트가 있는데 JTable이다!!
  레코드의 갯수에 따라 배열의 크기를 지정해서 개발해보자!
 */
package table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.mariadb.jdbc.internal.packet.send.InterfaceAuthSwitchSendResponsePacket;

public class TableTest2 extends JFrame{
	JTable table;
	JScrollPane scroll;
	
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="batman";
	String password="1234";
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;//select문일 경우만 필요한 왜?? 결과를 담아야 하므로....
	
	String[][] data;
	String[] column={
			"empno","ename","mgr","job","hiredate","sal","comm","deptno"
	};
	
	public TableTest2() {
		//setLayout(new FlowLayout());
		loadData();
		table=new JTable(data,column);
		scroll=new JScrollPane(table);
		
		add(scroll);
		
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	/*레코드 채워넣기!!
		테이블을 생성하기 전에, mariaDB 연동하여 member 테이블의 레코드를 이차원 배열에 담아놓자!
		왜?? JTable의 생성자의 인수로 이차원 배열이 사용되니깐!!	*/
	public void loadData(){
		/*
		 * 1단계-드라이버 로드
		 * 2단계-접속
		 * 3단계-원하는 쿼리문 수행
		 * 4단계-데이터베이스 닫기
		 */
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,user,password);
			if(con!=null){
				String sql="select *from emp";
				
				pstmt=con.prepareStatement(sql
						, ResultSet.TYPE_SCROLL_INSENSITIVE
						, ResultSet.CONCUR_READ_ONLY);
				rs=pstmt.executeQuery();
				
				//rs를 last()로 보내고, 위치를 묻자. pstmt에 대입할때 값을 더 넣자!! 
				rs.last();
				int total=rs.getRow();
				
				//while문을 돌리기 위해 rs 원상복구
				rs.beforeFirst();  //아무것도 가리키지 않는 상태
				
				//first()를 쓰고 rs.next()를 쓰면 두번째 값을 가리키게 됨
				
				//이차원 배열 생성
				data= new String[total][column.length];
				int index=0;
				
				while(rs.next()){
					int empno=rs.getInt("empno");
					String ename=rs.getString("ename");
					String job=rs.getString("job");
					int mgr=rs.getInt("mgr");
					String hiredate=rs.getString("hiredate");
					int sal=rs.getInt("sal");
					String comm=rs.getString("comm"); 
					//int comm=rs.getInt("comm"); //이렇게 하면 null값이 다 0으로 표기되니까 그냥 String형으로 받자
					int deptno=rs.getInt("deptno");
					
					data[index][0]=Integer.toString(empno);
					data[index][1]=ename;
					data[index][2]=job;
					data[index][3]=Integer.toString(mgr);
					data[index][4]=hiredate;
					data[index][5]=Integer.toString(sal);
					data[index][6]=comm;
					data[index][7]=Integer.toString(deptno);
				
					index++;
				}
			}else{
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
	
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}//finally끝
	}
	public static void  main(String[] args){
		new TableTest2();
	}
}
