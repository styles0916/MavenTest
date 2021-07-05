<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
input[type="button"] {
	width: 100px;
}
</style>
</head>
<body>
	<c:choose>
		<c:when test="${empty memberInfo}">
			<input type="button" value="로그인" onclick="location.href='form.do';">
		</c:when>
		<c:when test="${!empty memberInfo}">
			<input type="button" value="로그아웃"
				onclick="location.href='logout.do';">
			<input type="button" value="마이페이지"
				onclick="location.href='mypage.do';">
		</c:when>
	</c:choose>
	
	<input type="button" value="등록" onclick="location.href='write.do';">
	
	<hr>

	<c:forEach var="member" items="${list }">
	${member.mno } ${member.mname } ${member.email }<br>
	</c:forEach>
	<hr>
	<%-- 	<%@ page import="chapter07.*"%> --%>
	<%-- 	<%@ page import="java.util.*"%> --%>
	<%
	// 	List<MemberVo> list = (List<MemberVo>) request.getAttribute("list");

	// 	for (MemberVo vo : list) {
	// 		out.print(vo.getMno() + " " + vo.getMname() + " " + vo.getEmail() + "<br>");
	// 	}
	%>
</body>
</html>