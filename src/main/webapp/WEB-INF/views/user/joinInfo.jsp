<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<link rel="stylesheet" type="text/css" href="/resources/css/join.css" />

<!-- 다음 우편api 적용 -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script type="text/javascript">
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
			document.getElementById("inputZipcode").value = data.zonecode;
			document.getElementById("inputAddress").value = userAddress;
			document.getElementById("inputAddressDetail").value = extraAddress;
		
		}
	}).open({
		popupName: 'zipcodePopup' //팝업이름 설정(여러개의 팝업창 뜨는 것 방지)
	});
}

</script>


<div class="container">
	<div class="pageHeader">
		<h3>회원가입</h3>
		<span class="pull-right">약관동의 ＞ <span id="page">정보입력</span></span><br>
		<hr>
	</div><!-- End pageHeader -->
	
	<div class="pageContent">
		<span class="pull-right"><span id="required">*</span>항목은 필수입력항목입니다.</span>
		<br><br>
		<form id="inputForm" class="form-horizontal" role="form" action="/user/joinInfo">
			<div class="row">
				<div class="col-md-7 col-md-offset-3">
					<div class="form-group">
						<div class="col-md-3">
							<label for="inputId control-label"><span id="required">*</span>아이디</label>
						</div>
						<div class="col-md-6">
							<input type="text" class="col-md-5 form-control" id="inputId" name="userId" placeholder="5~12자리의 영문 소문자, 숫자 조합" required>
						</div>
						<div class="col-md-2">
							<button type="button" class="btn" id="btnIdCheck" onclick="openIdCheck()">중복확인</button>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-md-3">
							<label for="inputPw" class="control-label"><span id="required">*</span>비밀번호</label>
						</div>
						<div class="col-md-6">
							<input type="password" class="form-control" id="inputPw" name="userPw" placeholder="8~15자리의 영문 소문자, 숫자 조합" required>
						</div>			
					</div>	
			
					<div class="form-group">
						<div class="col-md-3">
							<label for="inputPwCheck" class="control-label"><span id="required">*</span>비밀번호확인</label>
						</div>
						<div class="col-md-6">
							<input type="password" class="form-control" id="inputPwCheck" placeholder="입력하신 비밀번호를 한 번 더 입력해주세요" required>
						</div>			
					</div>
					
					<div class="form-group">
						<div class="col-md-3">
							<label for="inputName" class="control-label"><span id="required">*</span>이름</label>
						</div>
						<div class="col-md-6">
							<input type="text" class="form-control" id="inputName" name="userName" placeholder="실명으로 입력해주세요" required>
						</div>			
					</div>
					
					<div class="form-group">
						<div class="col-md-3">
							<label for="inputNick" class="control-label"><span id="required">*</span>닉네임</label>
						</div>
						<div class="col-md-6">
							<input type="text" class="form-control" id="inputNick" name="userNick" placeholder="20자 이내의 한글, 영문, 숫자만 입력해주세요" required>
						</div>
						<div class="col-md-2">
							<button type="button" class="btn" id="btnNickCheck" onclick="openNickCheck()">중복확인</button>
						</div>			
					</div>
					
					<div class="form-group">
						<div class="col-md-3">
							<label for="inputBirthDate" class="control-label"><span id="required">*</span>생년월일</label>
						</div>
						<div class="col-md-4">
							<input type="date" class="form-control" id="inputBirthDate" name="userBirthDate" required>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-md-3">
							<label for="inputEmail" class="control-label"><span id="required">*</span>이메일</label>
						</div>
						<div class="col-md-6">
							<input type="email" class="form-control" id="inputEmail" name="userEmail" required>
						</div>
						<div class="btn-group">
							<div class="col-md-2">
								<button type="button" class="btn dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
								메일 선택&ensp;<span class="caret"></span>
								</button>
								<ul class="dropdown-menu" role="menu">
									<li value="gmail.com">gmail.com</li>
									<li value="naver.com">naver.com</li>
									<li value="kakao.com">kakao.com</li>
									<li value="daum.net">daum.net</li>
									<li value="nate.com">nate.com</li>
									<li value="insert">직접 입력</li>
								</ul>
							</div>		
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-md-3">
							<label for="inputPhone" class="control-label"><span id="required">*</span>휴대전화번호</label>
						</div>
						<div class="col-md-6">
							<input type="tel" class="form-control" id="inputPhone" name="userPhone" required>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-md-3">
							<label for="inputZipcode" class="control-label"><span id="required">*</span>주소</label>
						</div>
						<div class="col-md-6">
							<input type="text" class="form-control" id="inputZipcode" name="userZipcode" required readonly="readonly">
						</div>
						<div class="col-md-2">
							<button type="button" class="btn" id="btnZipcode" onclick="openDaumPostcode()">우편번호</button>
						</div>			
					</div>
					
					<div class="form-group">
						<div class="col-md-3">
							<label for="inputAddress" class="control-label"></label>
						</div>
						<div class="col-md-8">
							<input type="text" class="form-control" id="inputAddress" name="userAddress" required readonly="readonly">
						</div>			
					</div>					
					
					<div class="form-group">
						<div class="col-md-3">
							<label for="inputAddressDetail" class="control-label"></label>
						</div>
						<div class="col-md-8">
							<input type="text" class="form-control" id="inputAddressDetail" name="userAddressDetail" placeholder="상세주소">
						</div>			
					</div>
				</div><!-- End col -->
			</div><!-- End row -->	
			<br><br>		
			<div align="center">
				<button type="reset" class="btn" id="btnCancle" onclick="location.href='/'">취소</button>
				<button type="submit" class="btn" id="btnJoin">가입하기</button>
			</div><!-- End btn -->
		</form><!-- End inputForm -->
	</div><!-- End pageContent -->
	
</div><!-- End container -->

<c:import url="/WEB-INF/views/layout/footer.jsp" />
