package bookshop.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.dao.AdminMemberDAO;

@WebServlet("/adminDeleteMember")
public class AdminDeleteMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AdminMemberDAO.getInstance().deleteMember(request.getParameter("memberId"));
		
		String jsScript = "";
		
		jsScript += "<script>";
		jsScript += "location.href='adminMemberList'";
		jsScript += "</script>";
		
		response.setContentType("text/html; charset=UTF-8"); 
		PrintWriter pw = response.getWriter();
		pw.print(jsScript);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
