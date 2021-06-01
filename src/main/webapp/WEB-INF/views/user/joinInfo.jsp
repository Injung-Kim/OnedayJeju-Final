<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<style type="text/css">
.container { width: 900px; margin: 0 auto; padding: 20px; }
h3 { text-align: center; }
.point { font-weight: bold; }
.point_required { color: #E74C3C; font-weight: normal; }
label { font-weight: normal; }
placeholder { font-size: 5px; font-weight: lighter; }
#btnDupliCheck { border: 1px solid #00BD9D; color: #00BD9D; background-color: #FFFFFF; }
#btnZipcode { border: 1px solid #00BD9D; color: #00BD9D; background-color: #FFFFFF; }
#btnCancle { border-radius:30px; text-align: center; color: #FFFFFF; font-size: 15px; background-color: #D8D5DB; cursor:auto; }
#btnJoin { border-radius:30px; text-align: center; color: #FFFFFF; font-size: 15px; background-color: #8BD7D2; cursor:auto; }
</style>

<div class="container">

<div class="InfoForm">
	<h3>회원가입</h3>
	<span class="pull-right">약관동의 ＞ <span class="point">정보입력</span></span>
	<br><hr>
	<span class="pull-right"><span class="point_required">*</span>항목은 필수입력항목입니다.</span>
<br><br>

<div class="inputInfoForm">
<form id="inputForm" class="form-horizontal" role="form" action="/user/joinInfo" method="POST">

	<div class="form-group">
		<label for="inputId" class="col-sm-2 control-label">
			<span class="point_required">*</span>아이디
		</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="inputId" name="userId" placeholder="5~12자리의 영문, 소문자, 숫자 조합">
		</div>
		<button type="button" class="btn" id="btnDupliCheck">중복확인</button>
	</div><!-- End inputId -->
		
	<div class="form-group">
		<label for="inputPw" class="col-sm-2 control-label">
			<span class="point_required">*</span>비밀번호
		</label>
		<div class="col-sm-4">
			<input type="password" class="form-control" id="inputPw" name="userPw" placeholder="8~15자리의 영문 소문자, 숫자 조합">
		</div>
	</div><!-- End inputPw -->
	
	<div class="form-group">
		<label for="inputPwCheck" class="col-sm-2 control-label">
			<span class="point_required">*</span>비밀번호확인
		</label>
		<div class="col-sm-4">
			<input type="password" class="form-control" id="inputPwCheck" placeholder="입력하신 비밀번호를 한 번 더 입력해주세요.">
		</div>
	</div><!-- End inputPwCheck -->
	
	<div class="form-group">
		<label for="inputName" class="col-sm-2 control-label">
			<span class="point_required">*</span>이름
		</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="inputName" name="userName" placeholder="실명으로 입력해주세요.">
		</div>
	</div><!-- End inputName -->

	<div class="form-group">
		<label for="inputNick" class="col-sm-2 control-label">
			<span class="point_required">*</span>닉네임
		</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="inputNick" name="userNick" placeholder="20자 이내의 한글, 영문, 숫자만 입력해주세요.">
		</div>
		<button type="button" class="btn" id="btnDupliCheck">중복확인</button>
	</div><!-- End inputNick -->

	<div class="form-group">
		<label for="inputBirthDate" class="col-sm-2 control-label">
			<span class="point_required">*</span>생년월일
		</label>
		<div class="col-sm-2">
			<input type="datetime" class="form-control" id="inputBirthDate" name="userBirthDate">
		</div>
	</div><!-- End inputBirthDate -->

	<div class="form-group">
		<label for="inputEmail" class="col-sm-2 control-label">
			<span class="point_required">*</span>이메일
		</label>
		<div class="col-sm-4">
			<input type="email" class="form-control" id="inputEmail" name="userEmail">
		</div>
	</div><!-- End inputEmail -->

	<div class="form-group">
		<label for="inputPhone" class="col-sm-2 control-label">
			<span class="point_required">*</span>휴대전화번호
		</label>
		<div class="col-sm-4">
			<input type="tel" class="form-control" id="inputPhone" name="userPhone">
		</div>
	</div><!-- End inputPhone -->

	<div class="form-group">
		<label for="inputZipcode" class="col-sm-2 control-label">
			<span class="point_required">*</span>주소
		</label>
		<div class="col-sm-2">
			<input type="number" class="form-control" id="inputZipcode" name="userZipcode" readonly="readonly">
		</div>
		<button type="button" class="btn" id="btnZipcode">우편번호</button>
	</div><!-- End inputZipcode -->

	<div class="form-group">
		<label for="inputAddress" class="col-sm-2 control-label"></label>
		<div class="col-sm-6">
			<input type="text" class="form-control" id="inputAddress" name="userAddress" readonly="readonly">
		</div>
	</div><!-- End input Address -->
	
	<div class="form-group">
		<label for="inputAddressDetail" class="col-sm-2 control-label"></label>
		<div class="col-sm-6">
			<input type="text" class="form-control" id="inputAddressDetail" name="userAddressDetail" placeholder="(상세주소)">
		</div>
	</div><!-- End input AddressDetail -->
	<br><br>
	<div id="btn" class="text-center">
		<button type="reset" class="btn" id="btnCancle" onclick="location.href='/'">취소</button>
		<button type="submit" class="btn" id="btnJoin" onclick="location.href='/user/welcome'">가입하기</button>
	</div><!-- End btn -->

</form>
</div><!-- End inputInfoForm -->

</div><!-- End InfoForm -->

</div><!-- .container -->


<c:import url="/WEB-INF/views/layout/footer.jsp" />
