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

table {
	text-align: center;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
// 	var result = true;

// 	function check() {
// 		$('input[name="school"]').each(
// 				function(i, v) {
// 					if ($('input[name="school"]').eq(i).val().replace(/\s/gi,
// 							'') == '') {
// 						alert((i + 1) + "번째 학교명을 입력해주세요.");
// 						$('input[name="school"]').eq(i).focus();
// 						result = false;
// 						return false; // callback 함수가 중지되는 것. check()가 중지되는 것이 아님.
// 					} else {
// 						result = true;
// 					}
// 				});
// 	}
// 	function check2() {
// 		alert(result);
// 		return result;
// 	}

	function check3() {
		var result = true;

		$('input[name="school"]').each(
				function(i, v) {
					if ($('input[name="school"]').eq(i).val().replace(/\s/gi,'') == '') {
						alert((i + 1) + "번째 학교명을 입력해주세요.");
						$('input[name="school"]').eq(i).focus();
						result = false;
						return false; // callback 함수가 중지되는 것. check()가 중지되는 것이 아님.
					} else {
						result = true;
					}
				});
		if (result) {
			$('form[action="insert.do"]').submit();
		}
	}
</script>
</head>
<body>
<!-- 	<form action="insert.do" method="POST" onsubmit="return check2();"> -->
	<form action="insert.do" method="POST">
		<table>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="mname"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pwd"></td>
			</tr>
			<tr>
				<td>학교명</td>
				<td>졸업년도</td>
			</tr>
			<tr>
				<td><input name="school" type="text" ></td>
				<td><input type="text" name="year"></td>
			</tr>
			<tr>
				<td><input name="school" type="text"></td>
				<td><input type="text" name="year"></td>
			</tr>
			<tr>
				<td><input name="school" type="text"></td>
				<td><input type="text" name="year"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
<!-- 					<input type="submit" value="등록" onclick="check();"> -->
					<input type="button" value="등록" onclick="check3();">
					<input type="button" value="목록" onclick="location.href='index.do';">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>