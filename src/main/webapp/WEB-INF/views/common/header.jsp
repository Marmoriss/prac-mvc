<%@page import="com.kh.prac.member.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    // 저장해둔 msg꺼내오기 - 로그인 실패 시 사용
    String msg = (String) session.getAttribute("msg");
    //현재 메세지가 세션에 저장되어있고, 리다이렉트로 /prac/으로 돌아간 후 로그인에 성공해도 실패했다는 메세지가 출력되기 때문에
    //메세지를 일회용으로 만들어서 로그인 성공했을 때는 보이지 않도록처리
    
    if(msg != null) session.removeAttribute("msg");

    // 저장해둔 Member객체 꺼내오기 - 로그인 성공 시 사용
    Member loginMember = (Member) session.getAttribute("loginMember");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello MVC</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<script>
window.onload = () => {

    //로그인 실패 시 메세지 띄우기
<% if(msg != null) { %>
    alert("<%= msg %>");
<% } %>

    document.loginFrm.onsubmit = (e) => {
        
        const memberId = document.querySelector("#memberId");
        const password = document.querySelector("#password");
        
        if(!/^.{4,}$/.test(memberId.value)){
            alert("유효한 아이디를 입력해주세요.");
            memberId.select();
            return false;
        }
        
        if(!/^.{4,}$/.test(password.value)){
            alert("유효한 비밀번호를 입력해주세요.");
            password.select();
            return false;
        }
    }
}

</script>
</head>
<body>
    <div id="container">
        <header>
            <h1>Hello MVC</h1>
             <div class="login-container">
             <% if(loginMember == null){ %>
                <!-- 로그인폼 시작 -->
                <form id="loginFrm" name="loginFrm" action="<%= request.getContextPath() %>/member/login" method="POST">
                    <table>
                        <tr>
                            <td><input type="text" name="memberId" id="memberId" placeholder="아이디" tabindex="1"></td>
                            <td><input type="submit" value="로그인" tabindex="3"></td>
                        </tr>
                        <tr>
                            <td><input type="password" name="password" id="password" placeholder="비밀번호" tabindex="2"></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="checkbox" name="saveId" id="saveId" />
                                <label for="saveId">아이디저장</label>&nbsp;&nbsp;
                                <input type="button" value="회원가입" 
                                    onclick="location.href='<%= request.getContextPath() %>/member/memberEnroll';">
                            </td>
                        </tr>
                    </table>
                </form>
                <!-- 로그인폼 끝-->
                <% // 로그인 성공시 보일 화면
                } else { %>
                    <table id="login">
                        <tr>
                        	<td>[<%= loginMember.getMemberName() %>]님, 안녕하세요.</td>
                        </tr>
                        <tr>
                        	<td>
                                <input type="button" value="내정보보기" />
                                <input type="button" value="로그아웃" 
                                    onclick="location.href='<%= request.getContextPath() %>/member/logout';"/>
                            </td>
                        </tr>
                    </table>
                <% } %>
            </div>
            <!-- 메인메뉴 시작 -->
            <nav>
                <ul class="main-nav">
                    <li class="home"><a href="<%= request.getContextPath() %>">Home</a></li>
                    <li class="notice"><a href="#">공지사항</a></li>
                    <li class="board"><a href="#">게시판</a></li>
                </ul>
            </nav>
            <!-- 메인메뉴 끝-->
            </header>
        