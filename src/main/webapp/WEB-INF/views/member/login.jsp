<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />







<div class="container" align="center">



<div style="font-size: 30px">로그인</div>

<form action="/member/login" method="post">
	
	<div>
		<label>아이디 </label>
		<input type="text" id="userId" name="userId" placeholder="id" />
	</div>
	
	<div>
		<label>비밀번호 </label>
		<input type="password" id="userPw" name="userPw" placeholder="password" />
	</div>
	
	<div>
		<label>아이디를 잊으셨나요? <a href="#">아이디 찾기</a></label><br>
		<label>비밀번호를 잊으셨나요? <a href="#">비밀번호 찾기</a></label>
	</div>
	
	<div>
		<button type="button" id="joinBtn">회원가입 하기</button><br>
		<button>로그인</button>
	</div>


</form>


</div><!-- .container -->






<c:import url="/WEB-INF/views/layout/footer.jsp" />
