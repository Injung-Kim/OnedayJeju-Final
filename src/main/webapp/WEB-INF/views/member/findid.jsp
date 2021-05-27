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
	
	
	//확인 버튼 클릭 시
	$("#searchIdBtn").click(function() {
		console.log("#searchIdBtn clicked")
		$(".modal-body").html("")
		
		$.ajax({
			type: "POST"
			,url: "/member/findid"
			,data: {inputName_1: $("#inputName_1").val(), inputPhone_1: $("#inputPhone_1").val()}
			,dataType: "html"
			,success: function(res){
				$(".modal-body").html(res)
			}
			,error: function(){
				
			}
		})
		$("#findIdModal").modal("show");
	});
	
	
	//모달 내 '비밀번호 찾기' 버튼 클릭 시
	$("#findPwPage").click(function() {

		$("#search_2").prop("checked", true);
		$("#search_2").click();
		$("#inputId").val( $("#resultid").text() ); //존재하는 회원일 경우 비밀번호 찾기로 텍스트 복사
		
		
// 		document.getElementById("searchId").style.display = "none";
// 		document.getElementById("searchPw").style.display = "";

		$("#findIdModal").modal("hide");
	});
	
});


//라디오 설정값
function search_check(num) {
	
	if (num == 1) {
		document.getElementById("searchPw").style.display = "none";
		document.getElementById("searchId").style.display = "";
		
	} else {
		document.getElementById("searchId").style.display = "none";
		document.getElementById("searchPw").style.display = "";
	}
	
}



</script>


<div class="container" align="center">



<div class="sub_title">아이디 / 비밀번호 찾기
	<p style="font-size: 0.5em; color: #616161;">인증된 이메일만 정보 찾기가 가능합니다</p>
</div>

<div class="searchRadio">
	<input type="radio" id="search_1" name="search_total" onclick="search_check(1)" checked="checked">
	<label for="serarch_1">아이디 찾기</label>
</div>
<div class="searchRadio">
	<input type="radio" id="search_2" name="search_total" onclick="search_check(2)">
	<label for="serarch_2">비밀번호 찾기</label>
</div>

<div id="searchId">
	<div class="form-group">
		<label for="inputName_1">이름</label>
		<input type="text" id="inputName_1" name="inputName_1" placeholder="ex) 홍길동">
	</div>
	<div class="form-group">
		<label for="inputPhone_1">휴대폰 번호</label>
		<input type="text" id="inputPhone_1" name="inputPhone_1" placeholder="(숫자만 입력)">
	</div>
	<div class="form-group">
		<button id="searchIdBtn" type="button" class="btn btn-info">확인</button>
		<button id="cancelBtn" type="button" class="btn btn-default">취소</button>
	</div>
</div>

<div id="searchPw" style="display: none;">
	<div class="form-group">
		<label for="inputId">아이디</label>
		<input type="text" id="inputId" name="inputId_2" placeholder="ex) user123">
	</div>
	<div class="form-group">
		<label for="inputEmail_2">이메일</label>
		<input type="email" id="inputEmail_2" name="inputEmail_2" placeholder="ex) email@gmail.com">
	</div>
	<div class="form-group">
		<button id="searchPwBtn" type="button" class="btn btn-info">확인</button>
		<button id="cancelBtn" type="button" class="btn btn-default">취소</button>
	</div>
	
</div>




<!-- findId Modal -->
<div id="findIdModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="idFindModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		
		<div class="modal-content">
		
			<div class="modal-header">
				<h4 class="modal-title" id="idFindModalLabel">아이디 찾기 결과</h4>
					 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
         				 <span aria-hidden="true">&times;</span>
        			</button>
		    </div>
		    
			<div class="modal-body">
		    </div>

			<div class="modal-footer">			
		        <button type="button" class="btn btn-info" id="findPwPage">비밀번호 찾기</button>
		        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
		    </div>
		    
		</div>
	</div>
	
</div>





<c:if test="${!empty findId }">
	<div id="resultId" style="display: none;">
		<label>회원님의 아이디는 <strong style="color: blue; font-size: 15px;">${findId }</strong> 입니다.</label>
	</div>
</c:if>
<c:if test="${empty findId }">
	<div id="resultId" style="display: none;">
		<label>일치하는 회원정보가 없습니다.</label>
	</div>
</c:if>

</div><!-- .container -->






<c:import url="/WEB-INF/views/layout/footer.jsp" />
