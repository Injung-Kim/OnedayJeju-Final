<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />
<style type="text/css">
.sub_title {
	padding: 10px;
}
.myplan_btn {
	float: right;
	margin: 20px;
}
button[type="button"] {
	margin: 10px;
}
label, input {
	margin: 10px;
}
#submitBtn {
	margin-top: 50px;
	padding: 15px 30px;
	font-size: 24px;
	background-color: #00BD9D;
	border-radius: 10px;
	border: 1px solid #ccc;
	color: #FFFBFA;
}
#submitBtn:hover, #myplanBtn:hover {
	font-weight: bold;
}
#myplanBtn {
	padding: 5px 10px;
	background-color: #00BD9D;
	border-radius: 10px;
	border: 1px solid #ccc;
	color: #FFFBFA;
}
label {
	font-size: 16px;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	//나의 일정보기 버튼
	$("#myplanBtn").click(function(){
		$(location).attr("href", "/member/mypage")
	})
	//생성 버튼
	$("#submitBtn").click(function(){
		if( chkform() ){
			$("form").submit();
		}
	})

})
function count(type, position)  {
	// 결과를 표시할 element
	const resultElement = $(position)
	
	// 현재 화면에 표시된 값
	let number = resultElement.html();
	
	// 더하기/빼기
	if(type === 'plus') {
	  	number = parseInt(number) + 1;
	}else if(type === 'minus')  {
		if( parseInt(number) > 1)
	  	number = parseInt(number) - 1;
	}
	// 결과 출력
	resultElement.html(number);
	resultElement.parent().children("input").val(number);
}
function chkform(){
	if( $("#pTitle").val().trim() == '' ) {
		alert("일정명을 입력하세요.")
		$("#pTitle").focus();
		return false;
	} else if( $("#pDepartureDate").val().trim() == '' ) {
		alert("출발일을 설정하세요.")
		$("#pDepartureDate").focus();
		return false;
	}
	return true;
}
</script>

<div class="container">
	<div class="sub_title">
		<span style="font-size:24px;">여행일정 | </span><span style="font-size:20px;">일정생성</span>
	</div>
	<div class="content">
		<div class="myplan_btn">
			<button id="myplanBtn">나의 일정 보기</button>
		</div>
		<div class="clearfix"></div>
		<div class="form-box">
			<form class="form-horizontal" action="/plan/create" method="post" >
				<div class="form-group">
					<label for="pTitle" class="col-md-3 control-label">여행 일정명</label>
					<div class="col-md-5 col-md-offset-1">
						<input type="text" class="form-control" id="pTitle" placeholder="여행 일정명" name="pTitle" maxlength="50" >
					</div>
				</div>
				<div class="form-group">
					<label for="pDepartureDate" class="col-md-3 control-label">출발일</label>
					<div class="col-md-5 col-md-offset-1">
						<input type="date" class="form-control" id="pDepartureDate" name="pDepartureDate" >
					</div>
				</div>
				<div class="form-group">
					<label for="pNumDays" class="col-md-3 control-label">여행 일수</label>
					<div class="col-md-5 col-md-offset-1">
						<div class="text-center">
							<button type="button" onclick='count("minus", ".p_num_days")' >-</button>
							<input type="hidden" class="form-control" id="pNumDays" name="pNumDays" value="1">
							<span class="p_num_days">1</span><span> 일</span>
							<button type="button" onclick='count("plus", ".p_num_days")' >+</button>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="pNumPeople" class="col-md-3 control-label">여행 인원</label>
					<div class="col-md-5 col-md-offset-1">	
						<div class="text-center">
							<button type="button" onclick='count("minus", ".p_num_people")' >-</button>
							<input type="hidden" class="form-control" id="pNumPeople" name="pNumPeople" value="1">
							<span class="p_num_people">1</span><span> 명</span>
							<button type="button" onclick='count("plus", ".p_num_people")' >+</button>
						</div>
					</div>
				</div>
			</form>
			<div class="submit_btn text-center">
				<button id="submitBtn">생 성</button>
			</div>
		</div>
	</div>


</div><!-- .container -->


<c:import url="/WEB-INF/views/layout/footer.jsp" />
