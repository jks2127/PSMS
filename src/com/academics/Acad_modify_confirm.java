package com.academics;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.psms.*;

public class Acad_modify_confirm extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest req,HttpServletResponse res) {
		try {
			String roll=req.getParameter("input_roll");

			PrintWriter out=res.getWriter();
			String q="update academics set total_mark=?, obt_mark=?, percent=? where roll=? and test_name=?";

			Connection con=CP.createC();
			PreparedStatement pstmt=con.prepareStatement(q);

			pstmt.setInt(1,Integer.parseInt(req.getParameter("input_fmark")));
			pstmt.setInt(2, Integer.parseInt(req.getParameter("input_omark")));

			double p=(double)(Integer.parseInt(req.getParameter("input_omark"))*100)/Integer.parseInt(req.getParameter("input_fmark"));

			pstmt.setDouble(3, p);

			pstmt.setString(4, roll);
			pstmt.setString(5, req.getParameter("input_testname"));
			
			pstmt.execute();
			
			out.println("<script type=\"text/javascript\">alert(\"Modified Successfully\");</script>");
			
			out.println("<html><head>"
					+ "<meta charset=\"UTF-8\">\r\n"
					+ "<meta name=\"viewport\" content=\"width=device-width; initial-scale=1\">\r\n"
					+ "		<script type=\"text/javascript\" src=\"styles/js/bootstrap.min.js\"></script>\r\n"
					+ "    <link rel=\"stylesheet\" href=\"styles/css/bootstrap.min.css\">"
					+ "<title>Modify</title>\r\n"
					+ "</head>\r\n"
					+ "<body style=\"background-color: rgb(255,255,255)\">\r\n"
					+ "	\r\n"
					+ "<nav class=\"navbar navbar-expand-lg navbar-light bg-warning\">\r\n"
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
					+ "</head><body>"
					+ "<form action=\"Acad_modify\" method=\"POST\">"
					+ "<div class=\"container\">"
					+ "<input type=\"hidden\" value=\""+roll+"\" name=\"input_roll\">"
					+ "<button class=\"btn btn-primary\" type=\"submit\" style=\"width:200px; margin-left:41%; margin-top:15%;\">Go Back</button>"
					+ "</div></form></body></html>");
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	
}
