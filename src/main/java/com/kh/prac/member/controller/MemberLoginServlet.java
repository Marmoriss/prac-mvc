package com.kh.prac.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.prac.member.model.dto.Member;
import com.kh.prac.member.model.service.MemberService;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		// 1. 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		// 2. 사용자 입력값 처리
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		
		System.out.println("memberId@MemberLoginServlet = " + memberId);
		System.out.println("password@MemberLoginServlet = " + password);
		
		
		// 3. 업무 로직 처리 : 로그인 여부 판단
		
		MemberService memberService = new MemberService();
		Member member = memberService.findById(memberId);
		System.out.println("member@MemberLoginServlet = " + member);
		
		HttpSession session = request.getSession();
		
		//로그인 성공(아이디가 존재하고 비밀번호가 일치하는 경우)
		if(member != null && password.equals(member.getPassword())) {
			session.setAttribute("loginMember", member);
		}
		// 로그인 실패 (아이디가 존재하지 않거나, 비밀번호가 틀린 경우)
		else {
			session.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		
		// 4. 응답 메세지 처리 : 로그인 후 url변경을 위해 리다이렉트 처리 -> 리다이렉트는 GET방식이다.
		response.sendRedirect(request.getContextPath() + "/");
	}

}





