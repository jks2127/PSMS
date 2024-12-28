package com.psms;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DispInd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String q;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession s=req.getSession();
		if(s.getAttribute("admin-name")==null) {
			res.sendRedirect("login.html");
		}else {
		PrintWriter out=res.getWriter();
		String course=req.getParameter("input_course");
		String roll=req.getParameter("input_roll");
		out.println("<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "	   <script type=\"text/javascript\" src=\"styles/js/bootstrap.min.js\"></script>\r\n"
				+ "    <link rel=\"stylesheet\" href=\"styles/css/bootstrap.min.css\">"
				+ "    <link rel=\"stylesheet\" href=\"styles/style.css\">\r\n"
				+ "    <title>Display Indivisual</title>\r\n"
				+ "</head>\r\n"
				+ "<body style=\"\">\r\n"
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
				+ "    <div class=\"input-div-disp\">\r\n"
				+ "        <table class=\"table\">\r\n"
				+ "            <tr>\r\n"
				+ "                <td>\r\n"
				+ "                    <div >");
		try {

			Connection con=CP.createC();
			
			switch(course) {
			case "mca":
				q="select * from mca where roll=?";
				break;
			case "math":
				q="select * from math where roll=?";
				break;
			default:
				break;
			}
			
			PreparedStatement pstmt=con.prepareStatement(q);			
			pstmt.setString(1, roll);			
			ResultSet rs=pstmt.executeQuery();		
			while(rs.next()) {
				byte[] barr=rs.getBytes("pic");
				String imgpath=req.getRealPath("/")+"img"+File.separator+"modify.jpeg";
				OutputStream os=new FileOutputStream(imgpath);
				os.write(barr);	
				out.println("<label class=\"form-label\">Name</label>");
				out.println("<input type=\"text\" class=\"form-control\" name=\"input_name\" placeholder=\""+ rs.getString(1)+ "\" disabled>");
				out.println("<label class=\"form-label\">Phone</label>");
				out.println("<input type=\"number\" class=\"form-control\" name=\"input_phone\" placeholder=\""+rs.getLong(2)+"\" disabled>");
				out.println("<label class=\"form-label\">Address</label>");
				out.println("<textarea class=\"form-control\" name=\"input_address\" placeholder=\""+rs.getString(3)+"\" rows=\"4\" disabled></textarea>");
				out.println("</div></td><td>");
				out.println("<img src=\"img"+File.separator+"modify.jpeg\" width=\"200px\"></td></tr>");
				out.println("<tr>");
				out.println("<td><label class=\"form-label\">Date of Birth</label></td>");
				out.println("<td>"+rs.getString("dob")+"</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td><label class=\"form-label\">Fees Paid</label> </td>");
				out.println("<td>Rs.");
				out.println(rs.getInt("paid")+ "\t/\tRs.");
				out.println(rs.getInt("fees"));
				//out.println("<input type=\"number\" name=\"input_paid\" placeholder=\""+rs.getInt("paid")+"\">/");
				//out.println("<input type=\"number\" name=\"full_fees\" placeholder=\""+rs.getInt("fees")+"\">");
				out.println("</td></tr>");
				out.println("<tr><td><label class=\"form-label\">Fees Due</label></td>\r\n");
				out.println("<td>Rs."+ rs.getInt("balance") +"</td>");
				out.println("</tr></table>");
				
				
			}
			out.println("<form action=\"Academics\" method=\"POST\">\r\n"
					+ " <input type=\"hidden\" class=\"form-control\" name=\"input_roll\" value=\""+roll+"\">"
					+ "  <button class=\"btn btn-success\"  type=\"submit\">View Academic Performance</button>\r\n"
					+ "	 </form>");
			
			out.println("</div></div>"
					+ "</body></html>");
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	

}
}