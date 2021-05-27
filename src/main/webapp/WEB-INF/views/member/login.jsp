<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	
	$("#loginBtn").click(function() {
		var userId = $("#userId").val()
		var userPw = $("#userPw").val()
		
		if(userId == "") {
			alert("아이디를 입력하세요")
			$("#userId").focus();
			return;
		}
		if(userPw == "") {
			alert("비밀번호를 입력하세요")
			$("#userPw").focus();
			return;
		}
		
	})

});

</script>





<div class="container" align="center">



<div class="sub_title">로그인</div>

<form action="/member/login" method="post">
	
	<div>
		<label>아이디 </label>
		<input type="text" id="userId" name="userId" placeholder="id" />
	</div>
	
	<div>
		<label>비밀번호 </label>
		<input type="password" id="userPw" name="userPw" placeholder="password" />
	</div>
	
	<c:if test="${msg eq 'failure'}">
		<div style="color:red; margin: 20px;">
			아이디 또는 비밀번호를 확인해주세요.
		</div>
	</c:if>
	
	
	<div>
		<label>아이디를 잊으셨나요? <a href="/member/findid">아이디 찾기</a></label><br>
		<label>비밀번호를 잊으셨나요? <a href="/member/findpw">비밀번호 찾기</a></label>
	</div>
	
	<div>
		<button type="button" id="joinBtn">회원가입 하기</button><br>
		<button id="loginBtn">로그인</button>
	</div>
	
</form>


</div><!-- .container -->






<c:import url="/WEB-INF/views/layout/footer.jsp" />
