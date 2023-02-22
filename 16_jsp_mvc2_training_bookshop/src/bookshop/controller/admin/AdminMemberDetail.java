package bookshop.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.dao.AdminMemberDAO;
import bookshop.dto.MemberDTO;

@WebServlet("/adminMemberDetail")
public class AdminMemberDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberDTO memberDTO = AdminMemberDAO.getInstance().getMemberDetail(request.getParameter("memberId"));
		request.setAttribute("memberDTO", memberDTO);
		
		RequestDispatcher dis = request.getRequestDispatcher("views/admin/adminMemberDetail.jsp");
		dis.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}
