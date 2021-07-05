<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="send.do" method="post">
		<table>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" required value="${memberVo.name }"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td>취미</td>
				<td>
					<input type="checkbox" name="hobby" value="1">독서
					<input type="checkbox" name="hobby" value="2">게임
					<input type="checkbox" name="hobby" value="3">영화
					<input type="checkbox" name="hobby" value="4">등산
				</td>
			</tr>
		</table>
		<input type="hidden" name="no" value="10">
		<input type="submit" value="전송">
	</form>
</body>
</html>