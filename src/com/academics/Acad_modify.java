package com.academics;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Acad_modify extends HttpServlet{
	private static final long serialVersionUID = 1L;
	String roll;
	public void doPost(HttpServletRequest req,HttpServletResponse res)  {
		roll=req.getParameter("input_roll");
		
		RequestDispatcher rd=req.getRequestDispatcher("Academics");
		try {
			rd.include(req,res);
			PrintWriter out=res.getWriter();
			out.println("<!DOCTYPE html>"
					+ "<html>\r\n"
					+ "<head>\r\n"
					+ "<meta charset=\"UTF-8\">\r\n"
					+ "<meta name=\"viewport\" content=\"width=device-width; initial-scale=1\">\r\n"
					+ "		<script type=\"text/javascript\" src=\"styles/js/bootstrap.min.js\"></script>\r\n"
					+ "    <link rel=\"stylesheet\" href=\"styles/css/bootstrap.min.css\">"
					+ "     <script type=\"text/javascript\" src=\"styles/js/bootstrap.min.js\"></script>\r\n"
					+ "    <link rel=\"stylesheet\" href=\"styles/css/bootstrap.min.css\">"
					+ "    <link rel=\"stylesheet\" href=\"styles/style.css\">\r\n"
					+ "<title>Modify Marks</title>\r\n"
					+ "</head>\r\n"
					+ "<body>");
			out.println("<hr><div class=\"input-div\">\r\n"
					+ "<form action=\"Acad_modify_confirm\" method=\"POST\">\r\n"
					+ "<input name=\"input_roll\" type=\"hidden\" value=\""+roll+"\" >\r\n"
					+ "<label class=\"form-label\">Test Name</label>\r\n"
					+ "<input class=\"form-control\" name=\"input_testname\" type=\"text\" placeholder=\"1st semester/2nd semester etc\">\r\n"
					+ "<label class=\"form-label\">Full mark</label>\r\n"
					+ "<input class=\"form-control\" name=\"input_fmark\" type=\"number\" >\r\n"
					+ "<label class=\"form-label\">Obtained mark</label>\r\n"
					+ "<input class=\"form-control\" name=\"input_omark\" type=\"number\" >\r\n"
					+ "<button class=\"btn btn-danger mt-2\" type=\"submit\" style=\"width:220px;\">Modify Confirm</button>\r\n"
					+ "</form>\r\n"
					+ "</div>");
			out.println("</body></html>");
			
			
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}

}
