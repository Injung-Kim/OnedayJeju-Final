<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script type="text/javascript">
//아이디 정규식
var idReg = /^[a-z0-9]{5,12}$/; //5~12자리의 영문 소문자, 숫자 조합
//비밀번호 정규식
var pwReg = /^[a-z0-9]{8,15}$/; //8~15자리의 영문 소문자, 숫자 조합
//이름 정규식
var nameReg = /^[가-힣a-zA-Z]{1,20}$/; //실명
//닉네임 정규식
var nickReg = /^[가-힣a-zA-Z0-9]{1,20}$/; //20자 이내의 한글, 영문, 숫자
//휴대전화번호 정규식
var phoneReg = /^[0-9]{1,11}$/; //11자리의 0~9까지의 숫자

//아이디 중복확인
$(document).ready(function() {
	$("#inputId").blur(function() { //input 태그들이 포커스를 잃었을 때
		if($('#inputId').val()==''){ //아이디 입력값이 없을 때
			$('#idCheckMsg').text('아이디를 입력하세요.');
		} else if(idReg.test($('#inputId').val()) != true){
			$('#idCheckMsg').text('5~12자리의 영문 소문자, 숫자 조합만 가능합니다.')
		} else if($('#inputId').val()!=''){
			var inputId = $('#inputId').val();
				$.ajax({
					async: true
					, type: "post" //요청 메소드
					, url: "/user/joinInfo" //요청 URL
					, data: userId //요청 파라미터
					, dataType: "json"//응답받은 데이터의 형식(html 또는 json)
					, contentType: "application/json; charset=UTF-8"
					, success: function(res) { //AJAX 성공 시 콜백함수
						console.log("성공")					
						console.log(res) //응답 데이터 확인
						console.log(res.result)
						
						if(res.result > 0){
							$('#idCheckMsg').text('이미 사용중인 아이디입니다.');
								$("#inputForm").attr("disabled", true);
							
						} else {
							if(idReg.test(userId)){
								$('#idCheckMsg').text('사용 가능한 아이디입니다.');
								$('')
							}
						}
						}
					, error: function() { //AJAX 실패 시 콜백함수
						console.log("실패")
					}
				}) //End ajax
		} //End else if
	}) //End blur fn
}) //End ready fn
		

//닉네임 중복확인
//중복확인 버튼 누르면 모달창 열기
//닉네임 중복값 있을 경우 메시지 출력
//닉네임 중복값 없을 경우 메시지 출력
//확인 버튼 누르면 모달창 닫기	
</script>



</body>
</html>