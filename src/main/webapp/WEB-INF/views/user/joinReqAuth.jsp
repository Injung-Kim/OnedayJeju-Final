<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<link rel="stylesheet" type="text/css" href="/resources/css/join.css" />

<div class="container">
	<div class="pageHeader">
		<span></span>
	</div><!-- End pageHeader -->
	
	<div class="pageContent">
		<div class="msgBox">
			<div class="msg">
				<h1>Welcome !</h1>
				<br><br>
				<p>
				회원가입을 축하합니다.<br><br>
				입력하신 이메일(${JejuUser.userEmail })로 인증메일이 발송되었습니다.<br><br>
				이메일에 포함된 인증 링크를 통해 회원가입을 완료하실 수 있습니다.
				</p>
				<br><br><br><br>
				<button type="button" class="btn" id="btnMain" onclick="location.href='/'">메인으로</button>
			</div><!-- End msg -->
		</div><!-- End msgBox -->
	</div><!-- End pageContent -->
	
</div><!-- End container -->

<c:import url="/WEB-INF/views/layout/footer.jsp" />
