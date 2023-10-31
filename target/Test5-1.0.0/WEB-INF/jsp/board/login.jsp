<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- login form {s} -->
<form:form class="form-signin" name="form" id="form" role="form" modelAttribute="userVO" method="post" action="${pageContext.request.contextPath}/board/saveBoard">
	<div class="text-center mb-4">
		<h1 class="h3 mb-3 font-weight-normal">로그인 화면</h1>
	</div>
	
	<div class="form-label-group">
		<form:input path="uid" id="uid" class="form-control" placeholder="User ID" required="" autofocus="" />
		<label for="uid" class="sr-only">아이디</label>
	</div>
	
	<div class="form-label-group">
		<form:password path="pwd" id="pwd" class="form-control" placeholder="User Password" required="" />
		<label for="pwd" class="sr-only">비밀번호</label>
	</div>
	
	<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
	
	<span style="font-size:11pt;">Sign up</span>
	
</form:form>
<!-- login form {e} -->