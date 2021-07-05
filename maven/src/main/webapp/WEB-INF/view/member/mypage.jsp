<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
input[type="submit"], input[type="button"] {
	width: 100px;
}
</style>
</head>
<body>
	<form action="update.do" method="POST">
		<table>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" value="${member.email }"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="mname" value="${member.mname }"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pwd"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="수정">
					<input type="button" value="목록" onclick="location.href='index.do';"></td>
			</tr>
		</table>
		<input type="hidden" name="mno" value="${member.mno }">
	</form>
</body>
</html>