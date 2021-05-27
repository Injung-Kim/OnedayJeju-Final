<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript">

$(document).ready(function() {
	
	//취소 버튼 클릭 시 
	$("#cancelBtn").click(function() {
		history.go(-1);
	});
});

//라디오 설정값
function search_check(num) {
	
	if (num == 2) {
		document.getElementById("searchId").style.display = "none";
		document.getElementById("searchPw").style.display = "";
		
	} else {
		document.getElementById("searchPw").style.display = "none";
		document.getElementById("searchId").style.display = "";
	}

	
}



</script>



<div class="container" align="center">



<div class="sub_title">아이디 / 비밀번호 찾기
	<p style="font-size: 0.5em;">인증된 이메일만 정보 찾기가 가능합니다</p>
</div>

<div class="searchRadio">
	<input type="radio" id="search_1" name="search_total" onclick="search_check(1)">
	<label for="serarch_1">아이디 찾기</label>
</div>
<div class="searchRadio">
	<input type="radio" id="search_2" name="search_total" onclick="search_check(2)" checked="checked">
	<label for="serarch_2">비밀번호 찾기</label>
</div>

<div id="searchId" style="display: none;">
	<div class="form-group">
		<label for="inputName_1">이름</label>
		<input type="text" id="inputName_1" name="inputName_1" placeholder="ex) 홍길동">
	</div>
	<div class="form-group">
		<label for="inputPhone_1">휴대폰 번호</label>
		<input type="text" id="inputPhone_1" name="inputPhone_1" placeholder="(숫자만 입력)">
	</div>
	<div class="form-group">
		<button id="searchBtn" type="button" onclick="idSerch_click()" class="btn btn-info">확인</button>
		<button id="cancelBtn" type="button" class="btn btn-default">취소</button>
	</div>
</div>

<div id="searchPw">
	<div class="form-group">
		<label for="inputId">아이디</label>
		<input type="text" id="inputId" name="inputId_2" placeholder="ex) user123">
	</div>
	<div class="form-group">
		<label for="inputEmail_2">이메일</label>
		<input type="email" id="inputEmail_2" name="inputEmail_2" placeholder="ex) email@gmail.com">
	</div>
	<div class="form-group">
		<button id="searchBtn2" type="button" class="btn btn-info">확인</button>
		<button id="cancelBtn" type="button" class="btn btn-default">취소</button>
	</div>
</div>


</div><!-- .container -->






<c:import url="/WEB-INF/views/layout/footer.jsp" />
