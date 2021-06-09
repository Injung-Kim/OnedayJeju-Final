<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<!-- css -->
<link rel="stylesheet" href="/resources/css/plist.css" type="text/css">

<script type="text/javascript">
$(document).ready(function() {
	
	//'일정 선택하기' 버튼 클릭 시
	$("#submitBtn").click(function() {
		console.log("#submitBtn clicked");
		
		$.ajax({
			type: "POST"
			, url: "/expenses/elist"
			, data: {selectPlan: $("selectPlan").val()}
			, dataType: ""
			, success: function(res) {
				$(loacation).attr("href", "/expenses/elist?pno=${pNo}")
			}
			, error: function() {}
		});
		
	})
	
	//'취소' 버튼 클릭 시
	$("#cancelBtn").click(function() {
		console.log("#cancelBtn clicked");
		history.go(-1);
	})
	
})

</script>



<div class="container" align="center">


<div class="sub_title">경비 작성할 일정 선택하기
	<p style="font-size:0.4em; color:#d6d6d6">(여행일 기준으로 정렬됩니다)</p>
</div>

<form action="/expenses/plist" method="post">

<div class="list-container">
	<table class="table">
		<tr>
			<th style="width: 15%;">선택</th>
			<th style="width: 35%">일정명</th>
			<th style="width: 50%;">여행일</th>
		</tr>
		
	<c:forEach items="${pList }" var="p">
		<c:if test="${uno eq p.userNo }">
		<tr>
			<td style="text-align: center;">
				<input type="radio" name="selectPlan" value="${p.pNo}" style="width:20px; height: 20px;" checked="checked" />
			</td>
			<td>${p.pTitle }</td>
			<td>
				<fmt:formatDate value="${p.pDepartureDate}" pattern="yyyy년 MM월 dd일" /> ~
				<c:set var="arrivalTime" value="${p.pDepartureDate.getTime() + (p.pNumDays * 24 * 60 * 60 * 1000) }" />
				<c:set var="arrivalDate" value='<%=new Date((long) pageContext.getAttribute("arrivalTime")) %>' />
				<fmt:formatDate value="${arrivalDate}" pattern="yyyy년 MM월 dd일" />
				<span>(${p.pNumDays-1}박 ${p.pNumDays}일)</span>
			</td>
		</tr>
		</c:if>
	</c:forEach>
</table>
</div>

<div class="button-container">
	<button id="submitBtn" ><span>일정 선택하기</span></button><br>
	<button type="button" id="cancelBtn" ><span>취소</span></button>
</div>
	


</form>

</div><!-- .container -->


<c:import url="/WEB-INF/views/layout/footer.jsp" />
