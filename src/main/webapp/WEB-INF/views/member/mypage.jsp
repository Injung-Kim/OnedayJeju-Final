<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="/WEB-INF/views/layout/header.jsp" />

<!-- findPage css -->
<link rel="stylesheet" href="/resources/css/mypage.css" type="text/css">


<script type="text/javascript">
$(document).ready(function(){

	//'닉네임 변경' 버튼 클릭 시 (input창 토글)
	$("#updateNick").click(function (){
	  	$(".divToggle").toggle();
	});
	
	
	//settings 모달의 '변경하기' 버튼 클릭 시
	$("#modalSubmit").click(function() {
		console.log("settings 모달 내 변경 버튼");
		
		//전화번호 정규식
		$r1 = /^[0-9]*$/;
		
		//이메일 정규식
		$r2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		
		//주소 정규식
		$r3 = /^[a-zA-Z0-9가-힣_ ]*$/;
		
		if ( $("#newPhone").val() == '' || $("#newPhone").val().length != 11 || !$r1.test($("#newPhone").val()) ) {
			alert("휴대폰번호를 숫자로만 11자리 입력하세요");
			$("#newPhone").focus();
			return false;
		}
		if ( $("#newEmail").val() == '' || $("#newPhone").val().length > 30 || !$r2.test($("#newEmail").val()) ) {
			alert("이메일 형식(@)으로 30자 이내로 입력하세요");
			$("#newEmail").focus();
			return false;
		}
		if ( $("#newAddress").val() == '' || !$r3.test($("#newAddress").val()) ) {
			alert("주소는 한글, 숫자, 영어만 입력하세요");
			$("#newAddress").focus();
			return false;
		}
		if ( $("#newAddressDetail").val() == '' || !$r3.test($("#newAddressDetail").val()) ) {
			alert("상세 주소는 한글, 숫자, 영어만 입력하세요");
			$("#newAddressDetail").focus();
			return false;
		}
		
		
		$.ajax ({
			url: "/member/mypage/settings"
			, type: "POST"
			, data: {"newPhone": $("#newPhone").val()
					, "newEmail": $("#newEmail").val()
					, "newAddress": $("#newAddress").val()
					, "newAddressDetail": $("#newAddressDetail").val()}
			, dataType: "json"
			, success: function(res) {
				alert("변경되었습니다.");
				location.href=res.redirectUrl;
			}
			, error: function(res) {
				alert("!! 변경 실패 !!");
			}
		})
// 		location.reload(true);
	});
	
	
	//pw변경 모달의 '변경하기' 버튼 클릭 시
	$("#pwmodalSubmit").click(function() {
		console.log("비밀번호 변경 모달 내 변경 버튼");
		
		//비밀번호 정규식
// 		$reg = /^[a-z0-9]*$/;
		

// 		if ($("#newPw").val() != $("#newPwRe").val()) {
// 			alert("새 비밀번호와 새 비밀번호 확인값이 일치하지 않습니다");
// 			console.log("새 비밀번호와 새 비밀번호 확인값이 일치하지 않습니다");
// 			return false;
// 		}

// 		if (!$reg.test($("#newPw").val() || $("#newPw").val().length < 8 || $("#newPw").val().length > 15)) {
// 			alert("비밀번호는 8 ~ 15자리 영어 소문자, 숫자 조합으로 설정해야합니다");
// 			console.log("비밀번호는 8 ~ 15자리 영어 소문자, 숫자 조합으로 설정해야합니다");
// 			return false;
// 		}

// 		if ($("#newPw").val() == $("#newPwRe").val()) {
// 			alert("비밀번호가 변경되었습니다");
// 			console.log("비밀번호가 변경되었습니다");
// 			$('#setPwModal').modal("hide"); //닫기
// 			return true;
// 		}
	
		
		$.ajax ({
			url: "/member/mypage/updatepw"
			, type: "POST"
			, data: {"oldPw": $("#oldPw").val()
					, "newPw": $("#newPw").val()
					, "newPwRe": $("#newPwRe").val()}
			, dataType: "json"
			, success: function(res) {

				alert(res.msg)
				if(res.redirect) {
					location.href=res.url
				}
			}
			, error: function(res) {
// 				alert("!! 변경 실패 !!");
			}
		})
// 		location.reload(true);
	});

});


//프로필 사진 변경/삭제
function mysubmit(sub) {
	if(sub == 1) {
		console.log("'변경'버튼 클릭")
		document.upProfileForm.action="/member/mypage/profile";
	}
	if(sub == 2) {
		console.log("'삭제'버튼 클릭")
		document.upProfileForm.action="/member/mypage/delprofile";
	}
	document.upProfileForm.submit();
}

</script>


<div class="container">

<div class="sub_title">${nick} 님 마이페이지</div>

<div class="user_info">

	<div class="user_profile" align="center">
		<div class="profile">
			<c:choose>
				<c:when test="${sessionScope.profile ne null}">
					<img class="img-circle" alt="user" src="../resources/upload/${sessionScope.profile}" style="width:120px; height:120px;">
				</c:when>
				<c:otherwise>
					<img class="img-circle" alt="user" src="../resources/upload/user.png" style="width:120px; height:120px;">
				</c:otherwise>
			</c:choose>
		</div>
		
		<button class="openFileBtn" onclick="onclick=document.all.file.click();">프로필 사진 선택</button>
		
		<form method="post" name="upProfileForm" enctype="multipart/form-data">
			<input type="file" id="file" name="file" style="display:none;"/>
			<button onclick="mysubmit(1)">변경</button>
			<button onclick="mysubmit(2)">삭제</button>
			<p style="font-size: 0.8em; color:#00BD9D; font-weight:500;">변경 버튼을 눌러야 적용됩니다</p>
		</form>
	</div>
	
	<div class="user_data">
		<button id="setBtn" type="button" data-toggle="modal" data-target="#settingsModal"><i class="fas fa-cog fa-2x"></i></button>
		<table class="data_table">
			<tr>
				<td>가입일: </td>
				<td><fmt:formatDate value="${userData.userJoinDate}" pattern="yyyy-MM-dd"/> 가입</p>
			</tr>
			<tr>
				<td>닉네임: </td>
				<td><button id="updateNick">닉네임 변경</button>
				<div class="divToggle" style="display:none; margin: 3px;">
				<form action="/member/mypage" method="post" name="updateForm">
					<input type="text" id="newNick" name="userNick" />
					<button id="okNickBtn">변경</button>
				</form>
				</div>
				</td>
			</tr>
			<tr>
				<td class="phone">전화번호: </td>
				<td>${userData.userPhone}</td>
			</tr>
			<tr>
				<td>이메일: </td>
				<td>${userData.userEmail}</td>
			</tr>
			<tr>
				<td>주소: </td>
				<td>${userData.userAddress} ${userData.userAddressDetail}</td>
			</tr>
		</table>
		<button class="createPlanBtn"><span>일정 만들기</span></button>
	</div>
</div>

<div class="user_board_btn">
	<button>나의 일정</button>
	<button>여행 후기</button>
	<button>Q & A</button>
</div>

<div class="user_board_content">

</div>





<!-- settings 모달 -->
<div class="modal fade" id="settingsModal" role="dialog">
	<div class="modal-dialog">
		
		<!-- Modal Content -->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h3 id="modal-title" class="modal-title">회원정보 변경하기</h3>
			</div>
			
			<div class="modal-body">
				<table class="table modal-table">
					<tr>
						<td><label>아이디</label></td>
						<td>${userData.userId}</td>
					</tr>
					<tr>
						<td><label>생년월일</label></td>
						<td><fmt:formatDate value="${userData.userBirthDate}" pattern="yyyy년 MM월 dd일" /> </td>
					</tr>
					<tr>
						<td><label>비밀번호</label></td>
						<td><button id="setPwBtn" type="button" data-toggle="modal" data-target="#setPwModal">비밀번호 변경</button></td>
					</tr>
					<tr>
						<td><label for="newPhone">휴대폰번호</label></td>
						<td><input type="text" id="newPhone" name="newPhone" value="${userData.userPhone}" /></td>
					</tr>
					<tr>
						<td><label for="newEmail">이메일</label></td>
						<td><input type="text" id="newEmail" name="newEmail" value="${userData.userEmail}" /></td>
					</tr>
					<tr>
						<td><label for="newAddress">주소</label></td>
						<td><input type="text" id="newAddress" name="newAddress" value="${userData.userAddress}" /></td>
						<td><label for="newAddress">상세주소</label></td>
						<td><input type="text" id="newAddressDetail" name="newAddressDetail" value="${userData.userAddressDetail}" /></td>
					</tr>
				</table>
			</div>
			
			<div class="modal-footer">
				<button id="modalSubmit" type="submit">변경하기</button>
				<button type="button" data-dismiss="modal" class="cancel">취소</button>
			</div>
		</div>
	</div>
</div>


<!-- 비밀번호 변경 모달 -->
<div class="modal fade" id="setPwModal" role="dialog">
	<div class="modal-dialog">
		
		<!-- Modal Content -->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h3 id="modal-title" class="modal-title">비밀번호 변경하기</h3>
			</div>
			
			<div class="modal-body">
				<form action="/member/mypage/updatepw" method="post" >
				<table class="table modal-table">
					<tr>
						<td><label for="oldPw">현재 비밀번호</label></td>
						<td><input type="password" id="oldPw" name="oldPw" autocomplete="off" /></td>
					</tr>
					<tr>
						<td><label for="newPw">새 비밀번호</label></td>
						<td><input type="password" id="newPw" name="newPw" autocomplete="off" /></td>
					</tr>
					<tr>
						<td><label for="newPwRe">새 비밀번호 확인</label></td>
						<td><input type="password" id="newPwRe" name="newPwRe" autocomplete="off" /></td>
					</tr>
				</table>
				</form>
			</div>
			
			<div class="modal-footer">
				<button id="pwmodalSubmit" type="submit">변경하기</button>
				<button type="button" data-dismiss="modal" class="cancel">취소</button>
			</div>
		</div>
	</div>
</div>



</div><!-- .container -->

<c:import url="/WEB-INF/views/layout/footer.jsp" />
