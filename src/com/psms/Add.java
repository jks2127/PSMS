package com.psms;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/Add.java")
@MultipartConfig(fileSizeThreshold=1024*1024, maxFileSize=1024*1024*5,maxRequestSize=1024*1024*5*5)

public class Add extends HttpServlet{
	private static final long serialVersionUID = 1L;
	String q_get,q;
	ResultSet rs_get;
	PreparedStatement pstmt_get;
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		HttpSession s=req.getSession();
		if(s.getAttribute("admin-name")==null) {
			res.sendRedirect("login.html");
		}else {
		
		res.setContentType("text/html");
		try {
			PrintWriter out=res.getWriter();
			Connection con=CP.createC();
			
			String course=req.getParameter("input_course");
			System.out.println(course);
			switch(course) {
			case "mca":
				q_get="select si from mca order by si desc";
				q="insert into mca(name,phone,address,fees,paid,balance,pic,roll,si,dob) values(?,?,?,90000,?,90000-?,?,?,?,?)";
				break;
				
			case "math":
				q_get="select si from math order by si desc";
				q="insert into math(name,phone,address,fees,paid,balance,pic,roll,si,dob) values(?,?,?,90000,?,90000-?,?,?,?,?)";
				break;
			default:
			}
			
			

			PreparedStatement pstmt=con.prepareStatement(q);
			
			pstmt.setString(1, req.getParameter("input_name"));
			pstmt.setLong(2, Long.parseLong(req.getParameter("input_phone")));
			pstmt.setString(3, req.getParameter("input_address"));
			pstmt.setInt(4, Integer.parseInt(req.getParameter("input_paid")));
			pstmt.setInt(5, Integer.parseInt(req.getParameter("input_paid")));
			
			Part part=req.getPart("input_pic");
			InputStream inputStream=part.getInputStream();
			//String filename=part.getSubmittedFileName();
			//System.out.println(filename);
			
			pstmt.setBlob(6, inputStream );
			switch(course) {
			case "mca":
				pstmt_get=con.prepareStatement(q_get);
				rs_get=pstmt_get.executeQuery();
				int num;
				String r;
				if(rs_get.next()) {
					num=rs_get.getInt("si")+1;
					r="MC"+num;
					pstmt.setInt(8, num);
					pstmt.setString(7, r);
				}else {
					num=1;
					r="MC"+num;
					pstmt.setInt(8, num);
					pstmt.setString(7, r);
				}
				break;
			case "math":
				pstmt_get=con.prepareStatement(q_get);
				rs_get=pstmt_get.executeQuery();
				if(rs_get.next()) {
					num=rs_get.getInt("si")+1;
					r="MT"+num;
					pstmt.setInt(8, num);
					pstmt.setString(7, r);
				}else {
					num=1;
					r="MT"+num;
					pstmt.setInt(8, num);
					pstmt.setString(7, r);
				}
				break;
			default:
				break;
			}
			
			pstmt.setString(9, req.getParameter("input_dob"));
			pstmt.executeUpdate();
			
			res.sendRedirect("add.html");
			
		} catch (IOException | SQLException | ServletException e) {
			e.printStackTrace();
		}
	}
	
}
}