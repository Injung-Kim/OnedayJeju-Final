<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<style type="text/css">
.container { width: 900px; margin: 0 auto; padding: 20px; }
h3  { text-align: center; }
.point { font-weight: bold; }
.required { color: #E74C3C; font-weight: normal; }
#btnBack { border-radius:30px; text-align: center; color: #FFFFFF; font-size: 15px; background-color: #D8D5DB; cursor:auto; }
#btnNext { border-radius:30px; text-align: center; color: #FFFFFF; font-size: 15px; background-color: #8BD7D2; cursor:auto; }
</style>

<div class="container">

<div class="Agreement">
	<h3>회원가입</h3>
	<span class="pull-right"><span class="point">약관동의</span> ＞ 정보입력</span>
<br><hr>

<div class="inputAgree" class="form-horizontal">
	<label for="step1">이용약관
		<span class="required">(필수)</span>
	</label>
	
	<div class="Agreement1" style="border: 1px solid #ccc">
		<p>
		제1조 목적<br>
		본 이용약관은 “어느 날, 제주”(이하 "사이트")의 서비스의 이용조건과 운영에 관한 제반 사항 규정을 목적으로 합니다.<br>
		제2조 용어의 정의<br>
		본 약관에서 사용되는 주요한 용어의 정의는 다음과 같습니다.<br>
		</p>
	</div><!-- End Agreement1 -->
	<label class="checkbox-inline">
		<input type="checkbox" name="userAgree" value="true">사이트 이용약관에 동의합니다.
	</label>
	
	<br><br>
    
    <label for="step2">개인정보 수집 및 이용에 대한 동의
		<span class="required">(필수)</span>
	</label> 

	<div class="Agreement2" style="border: 1px solid #ccc">
		<p>
		1. 개인정보 수집목적 및 이용목적<br>
		(1) 홈페이지 회원 가입 및 관리<br>
		회원 가입 의사 확인, 회원제 서비스 제공에 따른 본인 식별․인증, 회원자격 유지․관리, 제한적 본인확인제 시행에 따른 본인확인, 서비스 부정 이용 방지, 만 14세 미만 아동의 개인정보 처리시 법정대리인의 동의 여부 확인, 각종 고지․통지, 고충 처리 등의 목적<br><br>
		(2) 재화 또는 서비스 제공<br>
		물품 배송, 서비스 제공, 계약서․청구서 발송, 콘텐츠 제공, 맞춤 서비스 제공, 본인인증, 연령인증, 요금 결제 및 정산, 채권추심 등의 목적<br>
		</p>
	</div><!-- End Agreement2 -->
	<label class="checkbox-inline">
		<input type="checkbox" name="userAgree" value="true">개인정보 수집 및 이용에 동의합니다.
	</label>

	<br><br>

	<div id="btn" class="text-center">
		<button type="reset" class="btn" id="btnBack" onclick="location.href='/'">이전</button>
		<button type="button" class="btn" id="btnNext" onclick="location.href='/user/joinInfo'">다음</button>
	</div><!-- End btn -->
</div><!-- End inputAgree -->

</div><!-- End Agreement -->

</div><!-- End container -->


<c:import url="/WEB-INF/views/layout/footer.jsp" />
