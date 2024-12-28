package com.academics;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.psms.*;

public class Acad_add extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest req,HttpServletResponse res) {
		try {
			PrintWriter out=res.getWriter();
			String roll=req.getParameter("input_roll");
			
			String q="insert into academics values(?,?,?,?,?)";
			
			Connection con=CP.createC();
			PreparedStatement pstmt=con.prepareStatement(q);
			
			pstmt.setString(1, req.getParameter("input_roll"));
			pstmt.setString(2, req.getParameter("input_testname"));
			pstmt.setInt(3, Integer.parseInt(req.getParameter("input_fmark")));
			pstmt.setInt(4, Integer.parseInt(req.getParameter("input_omark")));
			
			double percent=(double)(Integer.parseInt(req.getParameter("input_omark")) *100)/Integer.parseInt(req.getParameter("input_fmark"));
			
			pstmt.setString(5, String.format("%.2f", percent));
			
			pstmt.executeUpdate();
			
		
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
}
