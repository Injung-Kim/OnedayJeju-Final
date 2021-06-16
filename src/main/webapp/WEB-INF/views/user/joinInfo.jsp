<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<link rel="stylesheet" type="text/css" href="/resources/css/join.css" />

<!-- 다음 우편api 적용 -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	
	$("#userId").focus(); //아이디 최초 포커스

	$("#msgId").hide();
	$("#msgPw").hide();
	$("#checkPwMsg1").hide();
	$("#checkPwMsg2").hide();
	$("#msgName").hide();
	$("#msgNick").hide();
	$("#msgPhone").hide();
	
	
	//아이디 정규식 & 중복확인
	$("#userId").change(function() {
		var regId = /^[a-z0-9]{5,12}$/ //5~12자리의 영문 소문자, 숫자
		var id = $("#userId").val();
		
		if(id == "") { //아이디 입력값 없을 때
				$("#msgId").show();
				$("#msgId").html("아이디를 입력하세요.");
				$("#btnJoin").prop("disabled", true);
				return false;
			 }
		
		$.ajax({
			async : false
			, type: "get"
			, url: "/user/joinInfo/checkId"
			, data: { id }
			, dataType: "json"
			, success: function(data) {
//				console.log("성공")
//				console.log(data)
							
				if(data >= 1) { //아이디 중복값이 있을 때
					$("#msgId").show();
					$("#msgId").html("이미 사용중인 아이디입니다.");
					$("#btnJoin").prop("disabled", true);
				
				} else { //아이디 중복값이 없을 때
					if( regId.test(id) ) { //정규식에 맞을 때
						$("#msgId").show();
						$("#msgId").html("사용 가능한 아이디입니다.");
						$("#btnJoin").prop("disabled", false);
					
					} else { //정규식에 맞지 않을 때
						$("#msgId").show();
						$("#msgId").html("5~12자리의 영문 소문자, 숫자만 입력하세요.");
					}
				}
			} //End success
			, error: function() { //AJAX 실패 시 콜백함수
				console.log("실패")
			} //End error
		}) //End ajax
	}); //End change fn

	
	//닉네임 정규식 & 중복확인
	$("#userNick").change(function() {
		var regNick = /^[가-힣a-zA-Z0-9]{1,20}$/; //20자 이내의 한글, 영문, 숫자
		var nick = $("#userNick").val();
		
		if(nick == "") { //닉네임 입력값 없을 때
				$("#msgNick").show();
				$("#msgNick").html("닉네임을 입력하세요.");
				$("#btnJoin").prop("disabled", true);
				return false;
			 }
		
		$.ajax({
			async : false
			, type: "get"
			, url: "/user/joinInfo/checkNick"
			, data: { nick }
			, dataType: "json"
			, success: function(data) {
//				console.log("성공")
//				console.log(data)
							
				if(data >= 1) { //닉네임 중복값이 있을 때
					$("#msgNick").show();
					$("#msgNick").html("이미 사용중인 닉네임입니다.");
					$("#btnJoin").prop("disabled", true);
				
				} else { //닉네임 중복값이 없을 때
				
					if( regNick.test(nick) ) { //정규식에 맞을 때
						$("#msgNick").show();
						$("#msgNick").html("사용 가능한 닉네임입니다.");
						$("#btnJoin").prop("disabled", false);
					
					} else { //정규식에 맞지 않을 때
						$("#msgNick").show();
						$("#msgNick").html("20자 이내의 한글, 영문, 숫자만 입력하세요.");
					}
				}
			} //End success
			, error: function() { //AJAX 실패 시 콜백함수
				console.log("실패")
			} //End error
		}) //End ajax
	}); //End change fn

	
	//비밀번호 정규식
	$("#userPw").change(function() {
		var regPw = /^[a-z0-9]{8,15}$/; //8~15자리의 영문 소문자, 숫자
		var pw = $("#userPw").val();
		
		if(pw == "") { //입력값이 없을 때
			$("#msgPw").show();
			$("#msgPw").html("비밀번호를 입력하세요.");
		} else if( !regPw.test(pw) ) { //정규식에 맞지 않을 때
			$("#msgPw").show();
			$("#msgPw").html("8~15자리의 영문 소문자, 숫자만 입력하세요.");
		} else {
			$("#msgPw").hide();
		}
	});
	
	
	//비밀번호 일치여부 확인
	$("#checkPw").change(function() {
		var upw = $("#userPw").val();
		var cpw = $("#checkPw").val();
	
		if(upw != "" && cpw != "") { //둘 다 입력값이 있을 때
			if(upw == cpw) { //입력값이 일치할 때
				$("#msgChkPw2").show();
				$("#msgChkPw1").hide();
				$("#msgChkPw2").html("입력하신 비밀번호와 일치합니다.");
				$("#btnJoin").prop("disabled", false);
			} else {		//입력값이 일치하지 않을 때
				$("#msgChkPw1").show();
				$("#msgChkPw2").hide();
				$("#msgChkPw1").html("입력하신 비밀번호와 일치하지 않습니다.");
				$("#btnJoin").prop("disabled", true);
			}
		} else {
			$("#msgChkPw1").hide();
			$("#msgChkPw2").hide();
		}
	});

	
	//이름 정규식
	$("#userName").change(function() {
		var regName = /^[가-힣a-zA-Z]{1,20}$/; //20자 이내의 한글, 영문 대소문자
		var name = $("#userName").val();
		
		if(name == "") { //입력값이 없을 때
			$("#msgName").show();
			$("#msgName").html("이름을 실명으로 입력하세요.");
		} else if( !regName.test(name) ) { //정규식에 맞지 않을 때
			$("#msgName").show();
			$("#msgName").html("20자 이내의 한글, 영문 대소문자만 입력하세요.");
		} else {
			$("#msgName").hide();
		}
	});
	
	
	//휴대전화번호 정규식
	$("#userPhone").change(function() {
		var regPhone = /^[0-9]{1,11}$/; //11자리의 0~9까지의 숫자
		var phone = $("#userPhone").val();
		
		if(phone == "") { //입력값이 없을 때
			$("#msgPhone").show();
			$("#msgPhone").html("전화번호를 입력하세요.");
		} else if( !regPhone.test(phone) ) { //정규식에 맞지 않을 때
			$("#msgPhone").show();
			$("#msgPhone").html("11자리의 0~9까지의 숫자만 입력하세요.");
		} else {
			$("#msgPhone").hide();
		}
	});

}); //End ready fn


//Daum 우편번호 api 팝업
function openDaumPostcode(){
	new daum.Postcode({
		oncomplete: function(data){
			
			var userAddress = ''; //주소 변수
			var extraAddress = ''; //상세주소 변수
			
			if(data.userSelectedType === 'R'){ //사용자가 도로명 주소를 선택한 경우 
				userAddress = data.roadAddress; //선택한 도로명 주소 추가
				
			} else if(data.userSelectedType === 'J'){ //사용자가 지번 주소를 선택한 경우
				userAddress = data.roadAddress; //도로명 주소 추가
				if(data.roadAddress === ''){ //도로명 주소 선택값이 없는 경우
					userAddress = data.autoRoadAddress; //오토매핑된 도로명 주소 추가
				}
			} 
			
			if(data.noSelected === 'Y'){ //사용자가 선택 안 함을 선택한 경우
				userAddress = data.roadAddress; //도로명 주소 추가
				if(data.roadAddress === ''){ //도로명 주소 선택값이 없는 경우
					userAddress = data.autoRoadAddress; //오토매핑된 도로명 주소 추가
				}	
			}
			
			if(userAddress !== ''){ //선택된 주소값이 있을 경우
				if(data.bname !== '' && data.buildingName !== ''){ //법정동/법정리 이름이 있고, 건물명이 있을 경우 
					extraAddress += '(' + data.bname + ', ' + data.buildingName + ')'; //상세주소에 추가
					
				} else if(data.bname !== '' && data.buildingName === ''){ //법정동/법정리 이름이 있고, 건물명이 없을 경우
					extraAddress += '(' + data.bname + ')'; //상세주소에 법정동/법정리 이름 추가
					
				} else if(data.bname === '' && data.buildingName !== ''){ //법정동/법정리 이름이 없고, 건물명이 있을 경우
					extraAddress += '(' + data.buildingName + ')'; //상세주소에 건물명 추가
				}
			}				

			//우편번호와 주소 정보를 해당 필드에 넣는다
			document.getElementById("userZipcode").value = data.zonecode;
			document.getElementById("userAddress").value = userAddress;
			document.getElementById("userAddressDetail").value = extraAddress;
		
		}
	}).open({
		popupName: 'zipcodePopup' //팝업이름 설정(여러개의 팝업창 뜨는 것 방지)
	});
}; //End 우편번호 팝업
</script>




<div class="container" id="joinInfocontainer">
	<div class="pageHeader">
		<h2>회원가입</h2>
	</div><!-- End pageHeader -->
	
	<div class="pageContent">
		<!-- 회원정보입력 폼 -->
		<form id="infoForm" class="form-horizontal" role="form" action="/user/joinInfo" method="post">
			
			<!-- 정보 입력 테이블 -->
			<table id="tbInfo" class="table">
				<thead>
					<span class="pull-right"><span id="required">*</span>항목은 필수입력항목입니다.</span><br><br>
				</thead>
				
				<tbody>
					<tr>
						<th><span id="required">*</span>아이디</th>
						<td>
							<input type="text" class="col-md-5 form-control" id="userId" name="userId" maxlength="12" placeholder="5~12자리의 영문 소문자, 숫자 조합" required>
							<span id="msgId" class="msg"></span>
						</td>
					</tr>
					<tr>
						<th><span id="required">*</span>비밀번호</th>
						<td>
							<input type="password" class="form-control" id="userPw" name="userPw" maxlength="15" placeholder="8~15자리의 영문 소문자, 숫자 조합" required>
							<span id="msgPw" class="msg"></span>
						</td>
					</tr>	
					<tr>
						<th><span id="required">*</span>비밀번호확인</th>
						<td>
							<input type="password" class="form-control" id="checkPw" maxlength="15" placeholder="입력하신 비밀번호를 한 번 더 입력해주세요" required>
							<span id="msgChkPw1" class="msg"></span>
							<span id="msgChkPw2"></span>
						</td>
					</tr>	
					<tr>
						<th><span id="required">*</span>이름</th>
						<td>
							<input type="text" class="form-control" id="userName" name="userName" maxlength="20" placeholder="실명으로 입력해주세요" required>
							<span id="msgName" class="msg"></span>	
						</td>
					</tr>
					<tr>
						<th><span id="required">*</span>닉네임</th>
						<td>
							<input type="text" class="form-control" id="userNick" name="userNick" maxlength="20" placeholder="20자 이내의 한글, 영문, 숫자만 입력해주세요" required>
							<span id="msgNick" class="msg"></span>
						</td>
					</tr>
					<tr>
						<th><span id="required">*</span>생년월일</th>
						<td><input type="date" class="form-control" id="userBirthDate" name="userBirthDate" placeholder="ex)20001231" required></td>
					</tr>
					<tr>
						<th><span id="required">*</span>이메일</th>
						<td><input type="email" class="form-control" id="userEmail" maxlength="30" name="userEmail" required></td>
					</tr>
					<tr>
						<th><span id="required">*</span>휴대전화번호</th>
						<td>
							<input type="tel" class="form-control" id="userPhone" name="userPhone" maxlength="11" placeholder="'-'없이 입력해주세요" required>
							<span id="msgPhone" class="msg"></span>
						</td>
					</tr>
					<tr>
						<th rowspan="3"><span id="required">*</span>주소</th>
						<td>
							<input type="text" class="form-control" id="userZipcode" name="userZipcode" required readonly="readonly">
							<button type="button" class="btn" id="btnZipcode" onclick="openDaumPostcode()">우편번호</button>
						</td>
					<tr>	
						<td><input type="text" class="form-control" id="userAddress" name="userAddress" maxlength="50" required readonly="readonly"></td>
					</tr>
					<tr>
						<td><input type="text" class="form-control" id="userAddressDetail" name="userAddressDetail" maxlength="50" placeholder="상세주소"></td>
					</tr>				
				</tbody>
			</table>
			
			<div id="btnGroupInfo" align="center">
				<button type="submit" class="btn" id="btnJoin">가입하기</button>
			</div><!-- End btn -->
		</form><!-- End inputForm -->
	</div><!-- End pageContent -->
</div><!-- End container -->

<c:import url="/WEB-INF/views/layout/footer.jsp" />
