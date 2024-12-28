package com.psms;

import java.io.IOException;

import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig
public class ModifyConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String q;

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		HttpSession s=req.getSession();
		if(s.getAttribute("admin-name")==null) {
			res.sendRedirect("login.html");
		}else {
		try {
			PrintWriter out=res.getWriter();
			String course=req.getParameter("input_course");
			String roll=req.getParameter("input_roll");
			System.out.println(req.getParameter("input_checkbox"));
			switch(course) {
			case "mca":
				if(req.getParameter("input_checkbox")==null) {
				q="update mca set name=?,phone=?, address=?, fees=?, paid=?, balance=?, dob=? where roll=?";					
				}else {
				q="update mca set name=?,phone=?, address=?, fees=?, paid=?, balance=?, pic=?, dob=? where roll=?";
				}
				break;
			case "math":
				if(req.getParameter("input_checkbox")==null) {
				q="update mca set name=?,phone=?, address=?, fees=?, paid=?, balance=?, dob=? where roll=?";					
				}else {
				q="update math set name=?,phone=?, address=?, fees=?, paid=?, balance=?, pic=?, dob=? where roll=?";
				}
				break;
			default:
				break;
			}
			
			Connection con=CP.createC();
			PreparedStatement pstmt=con.prepareStatement(q);
			
			pstmt.setString(1, req.getParameter("input_name"));
			pstmt.setLong(2, Long.parseLong(req.getParameter("input_phone")));
			pstmt.setString(3, req.getParameter("input_address"));
			pstmt.setInt(4, Integer.parseInt(req.getParameter("full_fees")));
			pstmt.setInt(5, Integer.parseInt(req.getParameter("input_paid")));
			pstmt.setInt(6,  Integer.parseInt(req.getParameter("full_fees"))-Integer.parseInt(req.getParameter("input_paid")));
			if(req.getParameter("input_checkbox") == null) {
				pstmt.setString(7, req.getParameter("input_dob"));
				pstmt.setString(8, roll);
			}else {
				Part part=req.getPart("input_pic");
				InputStream is=part.getInputStream();
				pstmt.setBlob(7, is);
				pstmt.setString(8, req.getParameter("input_dob"));
				pstmt.setString(9, roll);
			}
		
			
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
					+ "<form action=\"Modify\" method=\"POST\">"
					+ "<div class=\"container\">"
					+ "<input type=\"hidden\" value=\""+course+"\" name=\"input_course\">"
					+ "<input type=\"hidden\" value=\""+roll+"\" name=\"input_roll\">"
					+ "<button class=\"btn btn-primary\" type=\"submit\" style=\"width:200px; margin-left:41%; margin-top:15%;\">Go Back</button>"
					+ "</div></form></body></html>");
			
		} catch (IOException | SQLException | ServletException e) {
			e.printStackTrace();
		}
	}

}}
/*
	public void doPost(HttpServletRequest req,HttpServletResponse res) {
		String course=req.getParameter("input_course");
		String roll=req.getParameter("input_roll");
		PrintWriter out;
		String q;

			out = res.getWriter();
		
		
		Connection con=CP.createC();
	//Display code goes here
		out.println("<h2>modify confirm</h2>");
		System.out.println(req.getParameter("input_name"));

		switch(course) {
		case "mca":
			System.out.println("Test");

			q="update mca set name=?,phone=?, address=?, fees=?, paid=?, balance=?, pic=? where roll=?";
			break;
		case "math":
			q="";
			break;
		}
		
		
			PreparedStatement pstmt=con.prepareStatement(q);
			pstmt.setString(8, roll);
			pstmt.setString(1, req.getParameter("input_name"));
			pstmt.setLong(2, Long.parseLong(req.getParameter("input_phone")));
			pstmt.setString(3, req.getParameter("input_address"));
			pstmt.setInt(4, Integer.parseInt(req.getParameter("full_fees")));
			pstmt.setInt(5, Integer.parseInt(req.getParameter("input_paid")));
			pstmt.setInt(6, Integer.parseInt(req.getParameter("input_fees"))-Integer.parseInt(req.getParameter("input_paid")));
			
			Part part=req.getPart("input_pic");
			InputStream is=part.getInputStream();
			
			pstmt.setBlob(7, is);
			
			pstmt.executeUpdate();
	*/