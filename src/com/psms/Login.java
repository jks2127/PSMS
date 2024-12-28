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

public class Login extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		String username=req.getParameter("input_username");
		String password=req.getParameter("input_password");
		try {
			PrintWriter out=res.getWriter();
			String q="select * from login";
			Connection con=CP.createC();
			PreparedStatement pstmt=con.prepareStatement(q);
			
			ResultSet rs=pstmt.executeQuery();
			Boolean b=true;
			while(rs.next()){
				if(username.equalsIgnoreCase(rs.getString(1)) && password.equals(rs.getString(2))) {
					HttpSession s=req.getSession();
					s.setAttribute("admin-name", rs.getString(3));
					res.sendRedirect("options.html");
					b=false;
				}
			}	
			if(b) {
				out.println("<!DOCTYPE html>\r\n"
						+ "<html>\r\n"
						+ "<head>\r\n"
						+ "	<meta charset=\"utf-8\">\r\n"
						+ "	<script type=\"text/javascript\" src=\"styles/js/bootstrap.min.js\"></script>\r\n"
						+ "    <link rel=\"stylesheet\" href=\"styles/css/bootstrap.min.css\">\r\n"
						+ "  	<link rel=\"stylesheet\" href=\"styles/style.css\">\r\n"
						+ "	<title>Wrong Credential</title>\r\n"
						+ "</head>\r\n"
						+ "<body>"
						+ "		<div style=\"width:310px; margin:auto; margin-top:10%\">\r\n"
						+ "		<label class=\"form-label\" ><strong>Wrong Username and Password Entered</strong></label>\r\n"
						+ " 	</div>"
						+ "		<div style=\"width:200px; margin:auto;\">\r\n"
						+ "		<a class=\"btn btn-warning\" href=\"/psms\" style=\"width:200px; margin:auto;\">Try again</a>\r\n"
						+ "		</div>"	
						+ "</body>\r\n"
						+ "</html>");
			}
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		
	}
}
