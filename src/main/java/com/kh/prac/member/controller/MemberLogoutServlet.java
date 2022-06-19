package com.kh.prac.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberLogoutServlet
 */
@WebServlet("/member/logout")
public class MemberLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 업무 로직 처리 : 세션 무효화
		// request.getSession(false) -> 세션이 있으면 주고, 없으면 null을 주렴
		HttpSession session = request.getSession(false);
		
		if(session != null) 
			session.invalidate(); // 무효화
		
		// 2. 리다이렉트
		response.sendRedirect(request.getContextPath() + "/");
	}

}
