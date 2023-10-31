<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<title>회원 가입</title>
</head>
<body>
	<div>
		<h1>회원 가입</h1>
	<form name="frm" method="post">
	<div class="container">
		<table border="0" width="400px">
			<tr>
				<td>회원 아이디</td>
				<td><input class="form-control form-control-sm" style="width:200px;" name="memId" id="memId"></td>
			</tr>
			<tr>
				<td>회원 이름</td>
				<td><input class="form-control form-control-sm" style="width:200px;" name="memNm" id="memNm"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input class="form-control form-control-sm" style="width:200px;" type="password" name="memPw" id="memPw"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button class="btn btn-primary me-1 mb-1" type="button" id="btnJoin">회원가입</button>
				</td>
			</tr>
		</table>
	</div>
	</form>

</body>
</html>

<script>






</script>