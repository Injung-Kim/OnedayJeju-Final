<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<link rel="stylesheet" href="/resources/css/review.css" type="text/css">

<script type="text/javascript">
$(document).ready(function() {
	$("#btnWrite").click(function() {
		$(location).attr("href", "/review/write");
	})
	
	//검색 버튼 클릭
	$("#btnSearch").click(function() {
		
		location.href="/review/list?search="+$("#search").val();
		
	});
})
</script>

<style type="text/css">
.textBox{text-align:center; padding:50px 0;}

table {
	table-layout: fixed;
}

table, th, td {
	text-align: center;
}

</style>

<div class="container">
<div class="textBox">
	<h1>여행후기</h1>
	<p>당신의 여행 후기를 들려주세요</p>
</div>
<hr>

<table class="table table-striped table-hover table-condensed">
<thead>
	<tr>
		<th style="width: 10%">글번호</th>
		<th style="width: 45%">제목</th>
		<th style="width: 20%">작성자</th>
		<th style="width: 10%">조회수</th>
		<th style="width: 15%">작성일</th>
	</tr>
</thead>
<tbody>
<c:forEach items="${review }" var="review">
	<tr>
		<td>${review.rvNo }</td>
		<td><a href="/review/view?rvNo=${review.rvNo}">${review.rvTitle }</a></td>
		<td>${review.userId }</td>
		<td>${review.rvHit }</td>
		<td><fmt:formatDate value="${review.rvCreateDate }" pattern="yy-MM-dd" /></td>
	</tr>
</c:forEach>
</tbody>
</table>

<span class="pull-left">total : ${paging.totalCount }</span>

<button id="btnWrite" class="btn btn-primary pull-right">글쓰기</button>

<div class="clearfix"></div>

<!-- <div class="form-inline text-center"> -->
<%-- 	<input class="form-control" type="text" id="search" value="${param.search }" /> --%>
<!-- 	<button id="btnSearch" class="btn">검색</button> -->
<!-- </div> -->

<%-- 페이징 JSP --%>
<jsp:include page="/WEB-INF/views/util/reviewPaging.jsp" />

</div><!-- .container -->

<c:import url="/WEB-INF/views/layout/footer.jsp" />
