package bookshop.controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.dao.AdminMemberDAO;
import bookshop.dto.MemberDTO;

@WebServlet("/adminMemberList")
public class AdminMemberList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<MemberDTO> memberDTOList = new ArrayList<MemberDTO>();
		int viewCnt;
		if (request.getParameter("viewCnt") == null) {
			viewCnt = 10;
		} else {
			viewCnt = Integer.parseInt(request.getParameter("viewCnt"));
		}
		
		int page;
		if (request.getParameter("page") == null) {
			page = 1;
		} else {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		
		if (request.getParameter("sort") == null || request.getParameter("searchWord") == null) {
			
			memberDTOList = AdminMemberDAO.getInstance().getMemberList(viewCnt);
			
		} else {
			
			memberDTOList = AdminMemberDAO.getInstance().getMemberList(request.getParameter("sort"), request.getParameter("searchWord"), viewCnt, page);
			
		}
		
		request.setAttribute("memberDTOList", memberDTOList);
		
		RequestDispatcher dis = request.getRequestDispatcher("views/admin/adminMemberList.jsp");
		dis.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
