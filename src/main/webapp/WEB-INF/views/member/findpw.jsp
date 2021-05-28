<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript">

$(document).ready(function() {
	
	//취소 버튼 클릭 시
	$("#cancelPwBtn").click(function() {
		$(location).attr("href", "/member/login");
	});
	
	//아이디 찾기 버튼 클릭 시
	$("#searchIdBtn").click(function() {
		$(location).attr("href", "/member/findid");
	})
	
	
	//비밀번호 찾기 페이지 확인 버튼 클릭 시
	$("#searchPwBtn").click(function() {
		
		console.log("#searchPwBtn clicked")
		$(".pw-modal-body").html("")
		
		$.ajax({
			type: "POST"
			,url: "/member/findpw"
			,data: {inputId_2: $("#inputId_2").val(), inputEmail_2: $("#inputEmail_2").val()}
			,dataType: "html"
			,success: function(res){
				$(".pw-modal-body").html(res)
			}
			,error: function(){
			}
		})
		$("#findPwModal").modal("show");
	});
	
	
	//모달 내 닫기 버튼 클릭 시 input 내용 지우기
	$("#closeModal").click(function() {
		
		console.log("#closeModal clicked")
		$("#findPwModal").modal("hide");
		
		$("#inputId_2").val('');
		$("#inputEmail_2").val('');
		
	})
	
	

	
});



</script>


<div class="container" align="center">



<div class="sub_title">비밀번호 찾기
	<p style="font-size: 0.5em; color: #616161;">이메일 인증이 완료된 회원만 정보 찾기가 가능합니다</p>
</div>


<!-- 비밀번호 찾기 -->
<div id="searchPw">
	<div class="form-group">
		<label for="inputId">아이디</label>
		<input type="text" id="inputId_2" name="inputId_2" placeholder="ex) user123">
	</div>
	<div class="form-group">
		<label for="inputEmail_2">이메일</label>
		<input type="email" id="inputEmail_2" name="inputEmail_2" placeholder="ex) email@gmail.com">
	</div>
	<div class="form-group">
		<button id="searchPwBtn" type="button" class="btn btn-info">확인</button>
		<button id="cancelPwBtn" type="button" class="btn btn-default">취소</button><br><br>
		<button id="searchIdBtn" type="button" class="btn btn-default">아이디 찾기</button>
	</div>
	
</div>





<!-- findPw Modal -->
<div id="findPwModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="pwFindModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		
		<div class="modal-content">
		
			<div class="modal-header">
				<h4 class="modal-title" id="pwFindModalLabel">비밀번호 찾기 결과</h4>
					 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
         				 <span aria-hidden="true">&times;</span>
        			</button>
		    </div>
		    
			<div class="pw-modal-body">
		    </div>

			<div class="modal-footer">			
		        <button type="button" id="closeModal" class="btn btn-default">닫기</button>
		    </div>
		    
		</div>
	</div>
</div>



<c:import url="/WEB-INF/views/layout/footer.jsp" />
