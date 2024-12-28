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

public class Modify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String q;
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		HttpSession s=req.getSession();
		if(s.getAttribute("admin-name")==null) {
			res.sendRedirect("login.html");
		}else {
		String course=req.getParameter("input_course");
		String roll=req.getParameter("input_roll");
		PrintWriter out=res.getWriter();		
		
		try {
			Connection con=CP.createC();
			//Display code goes here
				
				switch(course) {
				case "mca":
					q="select * from mca where roll=?";
					break;
				case "math":
					q="select * from math where roll=?";
					break;
				}
				
				out.println("<!DOCTYPE html>\r\n"
						+ "<html lang=\"en\">\r\n"
						+ "<head>\r\n"
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
						+ "\r\n"
						+ "	<form action=\"ModifyConfirm\" method=\"POST\" enctype=\"multipart/form-data\">\r\n"
						+ "		<div class=\"container\">\r\n"
						+ "	    <div class=\"input-div-disp\">\r\n"
						+ "	        <table class=\"table\" style=\"width:700px; margin:auto;\">\r\n"
						+ "	            <tr>\r\n"
						+ "	                <td>\r\n"
						+ "	                    <div>\r\n");
				
			PreparedStatement pstmt=con.prepareStatement(q);
			pstmt.setString(1, roll);
			ResultSet rs=pstmt.executeQuery();
			
			if(rs.next()) {
				byte[] barr=rs.getBytes(8);
				String imgpath=req.getRealPath("/")+"img"+File.separator+"modify.jpeg";
				OutputStream os=new FileOutputStream(imgpath);
				os.write(barr);
				os.close();
				
				out.println("<label class=\"form-label\">Name</label>");
				out.println("<input type=\"text\" class=\"form-control\" name=\"input_name\" value=\""+ rs.getString(1)+ "\">");
				out.println("<label class=\"form-label\">Phone</label>");
				out.println("<input type=\"number\" class=\"form-control\" name=\"input_phone\" value=\""+rs.getLong(2)+"\">");
				out.println("<label class=\"form-label\">Address</label>");
				out.println("<textarea class=\"form-control\" name=\"input_address\" rows=\"4\" >"+rs.getString(3)+"</textarea>");
				//out.println("<label class=\"form-label\">Roll No. (Do not alter)</label>");
				out.println("<input type=\"hidden\" class=\"form-control\" name=\"input_roll\" value=\""+rs.getString(7)+"\">\r\n");
				//out.println("<label class=\"form-label\">Course (Do not alter)</label>");
				out.println("<input type=\"hidden\" class=\"form-control\" name=\"input_course\" value=\""+course+"\">\r\n");
				out.println("</div></td><td style=\"width:400px\">");
				out.println("<img src=\"img"+File.separator+"modify.jpeg\" width=\"160px\" style=\"border:4px solid while; border-radius:10px;\">\r\n");
				out.println("<input type=\"file\" class=\"form-control mt-2\" name=\"input_pic\" style=\"width:300px\">\r\n");
				out.println("<br>"
						  + "<input type=\"checkbox\" class=\"m-1\" id=\"checkid\" name=\"input_checkbox\" value=\"selected\">"						
						  + "<label class=\"form-label\" for=\"checkid\">Check this box if you are uploading a new Picture</label>");
				out.println("</td></tr><tr><td>");
				out.println("<label class=\"form-label\">Date of Birth</label>");
				out.println("<input class=\"form-control\" type=\"date\" name=\"input_dob\" value=\""+rs.getString("dob")+"\">");
				out.println("</td></tr>");
				out.println("<tr><td><label class=\"form-label\">Fees Paid</label> </td>\r\n<td>");
				out.println("<input type=\"number\" name=\"input_paid\" value=\""+rs.getInt(5)+"\" class=\"form-control\" style=\"width:250px\">");
				out.println("<input type=\"number\" name=\"full_fees\" value=\""+rs.getInt(4)+"\" class=\"form-control\" style=\"width:250px\"></td>\r\n");
				out.println("</tr><tr><td>");
				out.println("<label class=\"form-label\" >Fees Due</label> </td>\r\n"
						+ "	                <td>" +rs.getInt(6)+ "</td></tr></table>\r\n");  
			}
			
			out.println("<div style=\"width:200px; margin:auto\">");
			out.println("<button type=\"submit\" class=\"btn btn-warning\" style=\"width:200px; margin:auto\" >Modify Confirm</button>"
					+ "</div></div></div>"					
					+ "</form>"
					
					+ "<form action=\"Acad_modify\" method=\"POST\">\r\n"
					+ "<input type=\"hidden\" value=\""+roll+"\" name=\"input_roll\">\r\n"
					+ "<label><strong>* To modify test marks you can left the above spaces blank</strong></label>"

					+ "<div style=\"width:200px; margin:auto\">"
					+ "<button class=\"btn btn-danger mt-3\" style=\"width:200px; margin:auto\">Modify Test Marks</button>\r\n"
					+ "</div>"
					+ "</form>\r\n"
					
					+ "</body></html>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}}