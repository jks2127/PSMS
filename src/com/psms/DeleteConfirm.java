package com.psms;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteConfirm extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		String course=req.getParameter("input_course");
		String roll=req.getParameter("input_roll");
		
		try {
			PrintWriter out=res.getWriter();
			out.println("<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "<head>\r\n"
					+ "	<meta charset=\"utf-8\">\r\n"
					+ "	<script type=\"text/javascript\" src=\"styles/js/bootstrap.min.js\"></script>\r\n"
					+ "    <link rel=\"stylesheet\" href=\"styles/css/bootstrap.min.css\">\r\n"
					+ "  	<link rel=\"stylesheet\" href=\"styles/style.css\">\r\n"
					+ "	<title>Confirm Delete</title>\r\n"
					+ "</head>\r\n"
					+ "<body>\r\n"
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
					+ "			<a href=\"LogOut\"><img src=\"img/log-out-outline.svg\" width=\"30px\"></a>\r\n"
					+ "			\r\n"
					+ "		</div>\r\n"
					+ "	</nav>\r\n"
					+ "	<form action=\"Delete\" method=\"POST\">\r\n"
					+ "	<div class=\"container\">\r\n"
					+ "		<input name=\"input_course\" type=\"hidden\" value=\""+course+"\">\r\n"
					+ "		<input name=\"input_roll\" type=\"hidden\" value=\""+roll+"\" >\r\n"
					+ "		<div style=\"width:400px; margin:auto; margin-top:10%\">\r\n"
					+ "		<label class=\"form-label\" ><strong>The data will be deleted permanently. Are you sure?</strong></label>\r\n"
					+ "		</div>\r\n"
					+ "		\r\n"
					+ "		<br>\r\n"
					+ "		<div style=\"width:420px; margin:auto;\">\r\n"
					+ "		<button type=\"submit\"  class=\"btn btn-danger\" style=\"width:200px; \">YES</button>\r\n"
					+ "		<a class=\"btn btn-warning\" href=\"Del\" style=\"width:200px;\">NO</a>\r\n"
					+ "		</div>\r\n"
					+ "	</div>\r\n"
					+ "	</form>\r\n"
					+ "</body>\r\n"
					+ "</html>");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
