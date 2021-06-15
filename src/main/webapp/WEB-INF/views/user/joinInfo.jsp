<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<link rel="stylesheet" type="text/css" href="/resources/css/join.css" />
<!-- 다음 우편api 적용 -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	//아이디 중복확인
	$("#btnIdCheck").click(function() {
	});
});
//비밀번호 확인
$(document).ready(function() {
	$("#checkPwMsg1").hide();
	$("#checkPwMsg2").hide();
	$("input").keyup(function() {
		var upw = $("#userPw").val();
		var cpw = $("#checkPw").val();
	
		if(upw != "" || cpw != "") {
			if(upw == cpw) {
				$("#checkPwMsg1").show();
				$("#checkPwMsg2").hide();
				$("#btnJoin").removeAttr("disabled");
			} else {
				$("#checkPwMsg1").hide();
				$("#checkPwMsg2").show();
				$("#btnJoin").attr("disabled", "disabled");
			}
		}
	})		
});

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
}
</script>

<div class="container">
	<div class="pageHeader">
		<h2>회원가입</h2>
	</div><!-- End pageHeader -->
	
	<div class="pageContent">
		<!-- 회원정보입력 폼 -->
		<form id="infoForm" class="form-horizontal" role="form" action="/user/joinInfo" method="post">
			<table id="infotb" class="table">
				<thead>
					<span class="pull-right"><span id="required">*</span>항목은 필수입력항목입니다.</span><br><br>
				</thead>
				
				<tbody>
					<tr>
						<th><label for="userId control-label"><span id="required">*</span>아이디</label></th>
						<td><input type="text" class="col-md-5 form-control" id="userId" name="userId" placeholder="5~12자리의 영문 소문자, 숫자 조합" required></td>
					<td><button type="button" class="btn" id="btnIdCheck">중복확인</button></td>	
					</tr>
					<tr>
						<th><label for="userPw" class="control-label"><span id="required">*</span>비밀번호</label></th>
						<td><input type="password" class="form-control" id="userPw" name="userPw" placeholder="8~15자리의 영문 소문자, 숫자 조합" required></td>
					</tr>	
					<tr>
						<th><label for="checkPw" class="control-label"><span id="required">*</span>비밀번호확인</label></th>
						<td>
							<input type="password" class="form-control" id="checkPw" placeholder="입력하신 비밀번호를 한 번 더 입력해주세요" required>
							<span id="checkPwMsg1" style="color: blue; display: none;"><br><small>입력하신 비밀번호와 일치합니다.</small></span>
							<span id="checkPwMsg2" style="color: red; display: none;"><br><small>입력하신 비밀번호와 일치하지 않습니다.</small></span>
						</td>
					</tr>	
					<tr>
						<th><label for="userName" class="control-label"><span id="required">*</span>이름</label></th>
						<td><input type="text" class="form-control" id="userName" name="userName" placeholder="실명으로 입력해주세요" required></td>
					</tr>
					<tr>
						<th><label for="userNick" class="control-label"><span id="required">*</span>닉네임</label></th>
						<td><input type="text" class="form-control" id="userNick" name="userNick" placeholder="20자 이내의 한글, 영문, 숫자만 입력해주세요" required></td>
						<td><button type="button" class="btn" id="btnNickCheck">중복확인</button></td>
					</tr>
					<tr>
						<th><label for="userBirthDate" class="control-label"><span id="required">*</span>생년월일</label></th>
						<td><input type="date" class="form-control" id="userBirthDate" name="userBirthDate" placeholder="ex)20001231" required></td>
					</tr>
					<tr>
						<th><label for="userEmail" class="control-label"><span id="required">*</span>이메일</label></th>
						<td><input type="email" class="form-control" id="userEmail" name="userEmail" required></td>
					</tr>
					<tr>
						<th><label for="userPhone" class="control-label"><span id="required">*</span>휴대전화번호</label></th>
						<td><input type="tel" class="form-control" id="userPhone" name="userPhone" placeholder="'-'없이 입력해주세요" required></td>
					</tr>
					<tr>
						<th><label for="userZipcode" class="control-label"><span id="required">*</span>주소</label></th>
						<td><input type="text" class="form-control" id="userZipcode" name="userZipcode" required readonly="readonly"></td>
						<td><button type="button" class="btn" id="btnZipcode" onclick="openDaumPostcode()">우편번호</button></td>
					</tr>
					<tr>
						<th><label for="userAddress" class="control-label"></label></th>
						<td><input type="text" class="form-control" id="userAddress" name="userAddress" required readonly="readonly"></td>
					</tr>
					<tr>
						<th><label for="userAddressDetail" class="control-label"></label></th>
						<td><input type="text" class="form-control" id="userAddressDetail" name="userAddressDetail" placeholder="상세주소"></td>
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
