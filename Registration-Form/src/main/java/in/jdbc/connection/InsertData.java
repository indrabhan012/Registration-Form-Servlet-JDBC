package in.jdbc.connection;

//this is servlet code.
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Insert")
public class InsertData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertData() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String pnumber = request.getParameter("pnumber");
		String age = request.getParameter("age");
		String address = request.getParameter("address");
		String course = request.getParameter("course");

		// JDBC Connection
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ragistration_form", "root","Indar@28929");
			Statement st = con.createStatement();
			// write query
			String query = "INSERT INTO registration (first_name, last_name, email, phone_number, age, address, course) values (?, ?, ?, ?, ?, ?, ?)";
			// ResultSet rs = st.executeQuery(query);
			//String query =	"DELETE FROM registration WHERE id = 2,3,4,5,6";

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, fname);
			pstmt.setString(2, lname);
			pstmt.setString(3, email);
			pstmt.setString(4, pnumber);
			pstmt.setString(5, age);
			pstmt.setString(6, address);
			pstmt.setString(7, course);

			int rowsAffected = pstmt.executeUpdate();

			// PreparedStatement std = con.prepareStatement(query);
			if (rowsAffected > 0) {
				out.print("<html><body gbcolor:'skyblue'><h2 bgcolor:'red'>Data Has Been Submitted...</h2></body></html>");

			} else {
				out.print("<h2>Data Not Submitted!...please try again</h2>");

			}

			pstmt.close();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			out.print("<h2>Data Not Submitted!...please try again</h2>");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.print("<h2>Data Not Submitted!...please try again</h2>");
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
