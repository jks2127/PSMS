package com.psms;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import javax.servlet.http.*;
public class LogOut extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req,HttpServletResponse res) {
		HttpSession s=req.getSession();
		s.invalidate();
		try {
			res.sendRedirect("login.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
