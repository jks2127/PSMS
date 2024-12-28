package com.academics;
import com.psms.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Academics extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String roll=req.getParameter("input_roll");
		PrintWriter out=res.getWriter();
		String q="select * from academics where roll=? order by test_name asc";
		
		Connection con=CP.createC();
		try {
			PreparedStatement pstmt=con.prepareStatement(q);
			pstmt.setString(1, roll);
			
			ResultSet rs=pstmt.executeQuery();
			out.println("<html lang=\"en\">\r\n"
					+ "<head>\r\n"
					+ "    <meta charset=\"UTF-8\">\r\n"
					+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "    <script type=\"text/javascript\" src=\"styles/js/bootstrap.min.js\"></script>\r\n"
					+ "    <link rel=\"stylesheet\" href=\"styles/css/bootstrap.min.css\">"
					+ "    <link rel=\"stylesheet\" href=\"styles/style.css\">\r\n"
					+ "    <title>Academics</title>\r\n"
					+ "</head>\r\n"
					+ "<body style=\"background-color: rgb(224, 224, 224);\">\r\n"
					+ "	<nav class=\"navbar navbar-expand-lg navbar-light bg-warning\">\r\n"
					+ "  		<div class=\"container-fluid\">\r\n"
					+ "			<ul class=\"navbar-nav me-auto mb-2 mb-lg-0\">\r\n"
					+ "				<li class=\"nav-item dropdown\">\r\n"
					+ "				   	<a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\">\r\n"
					+ "				     	      <img src=\"img/reorder_black_24dp.svg\">\r\n"
					+ "				    </a>\r\n"
					+ "				    <ul class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">\r\n"
					+ "					    <li><a class=\"dropdown-item\" href=\"add.html\">Add</a></li>\r\n"
					+ "				        <li><a class=\"dropdown-item\" href=\"disp1.html\">Display</a></li>\r\n"
					+ "				        <li><a class=\"dropdown-item\" href=\"del1.html\">Delete</a></li>\r\n"
					+ "				        <li><a class=\"dropdown-item\" href=\"modi1.html\">Modify</a></li>\r\n"
					+ "				    </ul>\r\n"
					+ "				</li>\r\n"
					+ "			</ul>\r\n"
					+ "			<h2 class=\"navbar-brand\" style=\"text-align: center; margin-right:46%\">PSMS</h2>\r\n"
					+ "			<a href=\"LogOut\"><img src=\"img/log-out-outline.svg\" width=\"30px\"></a>"
					+ "			\r\n"
					+ "		</div>\r\n"
					+ "	</nav>"
					+ "");
			out.println("<table class=\"table\">"
					+ "<thead>"
					+ "		<tr>"
					+ "			<th>Test Name</th>"
					+ "			<th>Total Marks</th>"
					+ "			<th>Mark Obtained</th>"
					+ "			<th>Percentage (%)</th>"
					+ "		</tr>"
					+ "	</thead><tbody>");
			int tfmark=0,tomark=0;
			while(rs.next()) {
				out.println("<tr><td>"+rs.getString(2)+"</td>"
						+ "<td>"+rs.getInt(3)+"</td>"
						+ "<td>"+rs.getInt(4)+"</td>"
						+ "<td>"+rs.getFloat(5)+"</td></tr>");
				
				tfmark=tfmark+rs.getInt(3);
				tomark=tomark+rs.getInt(4);
			}
			out.println("<tr><td><b>Average</b></td> "
					+ "<td>"+tfmark+"</td>"
					+ "<td>"+tomark+"</td>"
					+ "<td><strong>"+ String.format("%.2f", (float)(tomark*100)/tfmark)+"<strong></td>"
					+ "</tr>"
					+ "</tbody></table>");
			out.println("</body></html>");
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
