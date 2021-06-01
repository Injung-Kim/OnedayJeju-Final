<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<!-- findPage css -->
<link rel="stylesheet" href="/resources/css/find.css" type="text/css">

<script type="text/javascript">

$(document).ready(function() {
	
	//취소 버튼 hover 시
	$("#cancelIdBtn").hover(function() {
		$("#cancelIdBtn").text("로그인페이지로");
		$("#cancelIdBtn").css("color", "#8BD7D2");
	}, function() {
		$("#cancelIdBtn").text("취소");
		$("#cancelIdBtn").css("color", "#ddd");
	});
	
	//취소 버튼 클릭 시
	$("#cancelIdBtn").click(function() {
// 		$("#cancelIdBtn").css("color", "#fff");
// 		$(this).css("color", "#fff");
		$(location).attr("href", "/member/login");
	});
	
	//비밀번호 찾기 버튼 클릭 시
	$("#searchPwBtn").click(function() {
		$(location).attr("href", "/member/findpw");
	})
	
	
	//아이디 찾기 페이지 확인 버튼 클릭 시
	$("#searchIdBtn").click(function() {
		console.log("#searchIdBtn clicked")
		$(".modal-body").html("")
		
		$.ajax({
			type: "POST"
			,url: "/member/findid"
			,data: {inputName_1: $("#inputName_1").val(), inputPhone_1: $("#inputPhone_1").val()}
			,dataType: "html"
			,success: function(res){
				$(".id-modal-body").html(res)
			}
			,error: function(){
			}
		})
		$("#findIdModal").modal("show");
	});
	
	
	//모달 내 '비밀번호 찾기' 버튼 클릭 시
	$("#findPwPage").click(function() {

		$("#search_2").prop("checked", true); //checked 속성(property) 추가
		$("#search_2").click();
		$("#inputId").val( $("#resultid").text() ); //존재하는 회원일 경우 비밀번호 찾기로 텍스트 복사
		
		$("#findIdModal").modal("hide");
	});
	
	//모달 내 닫기 버튼 클릭 시 input 내용 지우기
	$("#closeModal").click(function() {
		
		console.log("#closeModal clicked")
		$("#findIdModal").modal("hide");
		
		$("#inputName_1").val('');
		$("#inputPhone_1").val('');
		
	})
	
});



</script>


<div class="container" align="center">



<div class="sub_title">아이디 찾기
	<p style="font-size:0.4em; color:#d6d6d6">이메일 인증이 완료된 회원만 정보 찾기가 가능합니다</p>
</div>



<!-- 아이디 찾기 -->
<div id="searchId">
	<div class="input-container">
		<input type="text" id="inputName_1" name="inputName_1" autocomplete="off">
		<label for="inputName_1">이름</label>
		<div class="bar"></div>
	</div>
	<div class="input-container">
		<input type="text" id="inputPhone_1" name="inputPhone_1" autocomplete="off">
		<label for="inputPhone_1">휴대폰 번호(숫자만 입력)</label>
		<div class="bar"></div>
	</div>
	<div class="button-container">
		<button type="button" id="searchIdBtn"><span>확인</span></button><br>
		<button type="button" id="cancelIdBtn" onclick="test()"><span>취소</span></button><br><br>
		<button type="button" id="searchPwBtn"><span>비밀번호 찾기</span></button>
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
		    
			<div class="id-modal-body">
		    </div>

			<div class="modal-footer">			
		        <button type="button" class="btn btn-info" id="findPwPage">비밀번호 찾기</button>
		        <button type="button" id="closeModal" class="btn btn-default">닫기</button>
		    </div>
		    
		</div>
	</div>
</div>

</div><!-- .container end -->



<c:import url="/WEB-INF/views/layout/footer.jsp" />
