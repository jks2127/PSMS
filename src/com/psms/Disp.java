package com.psms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Disp extends HttpServlet{
	private static final long serialVersionUID = 1L;
	String q_mca;
	String q_math;
	PreparedStatement pstmt;
	ResultSet rs;
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession s=req.getSession();
		if(s.getAttribute("admin-name")==null) {
			res.sendRedirect("login.html");
		}else {
		try {
			PrintWriter out=res.getWriter();
			Connection con=CP.createC();
			String course=req.getParameter("input_course");
			
			switch(course) {
			case "mca":
				q_mca="select name,roll from mca";
				pstmt=con.prepareStatement(q_mca);
				rs=pstmt.executeQuery();
				break;
			case "math":
				q_math="select name,roll from math";
				pstmt=con.prepareStatement(q_math);
				rs=pstmt.executeQuery();
				break;
			default:
				break;
			}
			
			
			out.println("<html lang=\"en\">\r\n"
					+ "<head>\r\n"
					+ "    <meta charset=\"UTF-8\">\r\n"
					+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "    <script type=\"text/javascript\" src=\"styles/js/bootstrap.min.js\"></script>\r\n"
					+ "    <link rel=\"stylesheet\" href=\"styles/css/bootstrap.min.css\">"
					+ "    <link rel=\"stylesheet\" href=\"style.css\">\r\n"
					+ "    <title>Dsiplay</title>\r\n"
					+ "</head>\r\n"
					+ "<body style=\"background-color: rgb(224, 224, 224);\">\r\n"
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
					+ "    <div class=\"container mt-3\">\r\n"
					+ "    <div class=\"input-div\">\r\n"
					+ "        <form action=\"DispInd\" method=\"POST\">\r\n"
					+ "				<label class=form-label>Department Choosen : "+course+"</label>\r\n"
					+ "				<input type=\"hidden\" class=\"form-control\" value=\""+course+"\" name=\"input_course\">"
					+ " <br>"
					+ "            <label class=\"form-label\">Enter Roll No.</label>\r\n"
					+ "            <input type=\"text\" class=\"form-control\" name=\"input_roll\">\r\n"
					+ "            <button type=\"submit\" class=\"btn btn-warning mt-3\">Select</button>\r\n"
					+ "        </form>\r\n"
					+ "<br><br>\r\n"
					+ "        <table class=\"table mt-4\">\r\n"
					+ "            <thead>\r\n"
					+ "                <tr>\r\n"
					+ "                    <td>Name</td>\r\n"
					+ "                    <td>Roll</td>\r\n"
					+ "                    <td>Course</td>\r\n"
					+ "                </tr>\r\n"
					+ "            </thead>\r\n"
					+ "            <tbody>\r\n"
					+ "            ");
			
			while(rs.next()) {
				out.println("<tr>");

				out.println("<td>" + rs.getString("name") + "</td>");
				out.println("<td>" + rs.getString("roll") + "</td>");
				
				switch(course) {
				case "mca":
					out.println("<td> MCA </td>");
					break;
				case "math":
					out.println("<td> MATH </td>");
					break;
				default:
					break;
				}

				out.println("</tr>");

			}
			out.println("</tbody></table>"
					+ "</div></div>"
					+ "</body>"
					+ "</html>");
			
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}}

}
