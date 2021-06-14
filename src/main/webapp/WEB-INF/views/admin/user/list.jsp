<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/views/layout/adminHeader.jsp" />

<link rel="stylesheet" type="text/css" href="/resources/css/adminUser.css" />

<script type="text/javascript">
$(document).ready(function() {
	//검색버튼 클릭
	$("#btnSearch").click(function() {
		location.href="/admin/user/list?search="+$("#search").val();
	});
	
	//관리자계정생성 버튼 클릭
	$("#btnCreate").click(function() {
		location.href="/admin/user/create";
	});
})
</script>

<div class="container">
	<div class="pageHeader">
		<span class="pull-left">회원관리 > <span id="page">전체회원</span></span>
	<hr>
	</div><!-- End pageHeader -->
	
	<div class="pageContent">
		<%-- 총 회원 수 --%>
		<span class="pull left">( 총 회원 수 : ${paging.totalCount }명 )</span>
		
		<%-- 회원 검색 --%>
		<div class="form-inline pull-right">
			<select class="form-control">
				<option value="#">선택</option>
				<option value="#">아이디</option>
				<option value="#">이름</option>
				<option value="#">닉네임</option>
				<option value="#">이메일</option>
			</select>
			<input type="text" id="search" class="form-control" name="search" value="${param.search}">
			<button type="button" id="btnSearch" class="btn">검색</button>
		</div><!-- End search -->
		<br><br>
				
		<table class="table table-hover">
			<thead>
				<tr>
					<th>번호</th>
					<th>아이디</th>
					<th>이름</th>
					<th>닉네임</th>
					<th>이메일</th>
					<th>가입일</th>
					<th>상태</th>
					<th>등급</th>
				</tr>
			</thead>
		
			<tbody>
			<c:forEach items="${list}" var="list">
				<tr>
					<td>${list.userNo}</td>
					<td><a href="/admin/user/info?userNo=${list.userNo}">${list.userId}</a></td>
					<td>${list.userName}</td>
					<td>${list.userNick}</td>
					<td>${list.userEmail}</td>
					<td><fmt:formatDate value="${list.userJoinDate}" pattern="yyyy-MM-dd"/></td>
					<td>${list.userMailAuth}</td>
					<td>${list.userGrade}</td>
				</tr>
			</c:forEach>	
			</tbody>
		</table>
			
	</div><!-- End pageContent -->
	
	<%-- 관리자계정 생성 버튼 --%>
	<c:if test="${grade eq '0'}">
		<div class="pull-right">
			<button type="button" class="btn" id="btnCreate">관리자계정 생성</button>
		</div>
	</c:if>
	
	<%-- 페이징 JSP --%>
<%-- 	<jsp:include page="/WEB-INF/views/util/adminUserPaging.jsp" /> --%>

</div><!-- End container -->

<c:import url="/WEB-INF/views/layout/adminFooter.jsp" />