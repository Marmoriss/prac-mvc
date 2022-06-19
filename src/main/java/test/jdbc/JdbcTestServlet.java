package test.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JdbcTestServlet
 */
@WebServlet("/jdbc/test")
public class JdbcTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/plain; charset=utf-8");
		response.getWriter().append("Database 연결 테스트... - 서버 콘솔을 확인하세요.");
		
		try {
			testDatabaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void testDatabaseConnection() throws Exception{
		
		String driverClass = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "prac";
		String password = "prac";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from member";
		
		// DQL
		// 1. DriverClass 등록
		Class.forName(driverClass);
		System.out.println("> DriverClass 등록 성공!");
		
		// 2. Connection 객체 생성
		conn = DriverManager.getConnection(url, user, password);
		System.out.println("> Connection 객체 생성 성공!");
		
		// 3. PreparedStatement 객체 생성 & 물음표 채우기
		pstmt = conn.prepareStatement(sql);
		System.out.println("> PreparedStatement 객체 생성 성공!");
		
		// 4. 쿼리 실행 & ResultSet 반환
		rset = pstmt.executeQuery();
		System.out.println("> 쿼리 실행 & ResultSet 반환 성공!");
		
		//5. ResultSet 처리
		while(rset.next()) {
			String memberId = rset.getString("member_id");
			String memberName = rset.getString("member_name");
			Date birthday = rset.getDate("birthday");
			
			System.out.printf("%s\t %s\t %s\n", memberId, memberName, birthday);
		}
		
		// 6. 자원 반납
		rset.close();
		pstmt.close();
		conn.close();
		System.out.println("> 자원 반납 완료!");
	}

}
