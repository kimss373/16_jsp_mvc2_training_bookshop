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
import bookshop.dto.MemberDTO;

@WebServlet("/adminModifyMember")
public class AdminModifyMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberDTO memberDTO = AdminMemberDAO.getInstance().getMemberDetail(request.getParameter("memberId"));
		request.setAttribute("memberDTO", memberDTO);
		
		RequestDispatcher dis = request.getRequestDispatcher("views/admin/adminModifyMember.jsp");
		dis.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMemberId(request.getParameter("memberId"));
		memberDTO.setMemberNm(request.getParameter("memberNm"));
		memberDTO.setPasswd(request.getParameter("passwd"));
		memberDTO.setSex(request.getParameter("sex"));
		memberDTO.setBirthDt(request.getParameter("birthDt"));
		memberDTO.setHp(request.getParameter("hp"));
		memberDTO.setSmsstsYn(request.getParameter("smsstsYn"));
		memberDTO.setEmail(request.getParameter("email"));
		memberDTO.setEmailstsYn(request.getParameter("emailstsYn"));
		memberDTO.setZipcode(request.getParameter("zipcode"));
		memberDTO.setRoadAddress(request.getParameter("roadAddress"));
		memberDTO.setJibunAddress(request.getParameter("jibunAddress"));
		memberDTO.setNamujiAddress(request.getParameter("namujiAddress"));
		memberDTO.setPoint(Integer.parseInt(request.getParameter("point")));
		
		AdminMemberDAO.getInstance().updateMember(memberDTO);
		
		response.setContentType("text/html; charset=UTF-8");
		
		String jsScript = "";
		jsScript += "<script>";
		jsScript += "location.href='adminMemberList'";
		jsScript += "</script>";
		
		PrintWriter pw = response.getWriter();
		
		pw.print(jsScript);
		
		
	}

}
