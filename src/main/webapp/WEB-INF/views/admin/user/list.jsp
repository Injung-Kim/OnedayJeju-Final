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
		<p class="pull-left">회원관리 > <span id="page">전체회원</span></p>
	</div><!-- End pageHeader -->
	
	<div class="pageContent">
		<%-- 관리자계정 생성 버튼 --%>
		<div class="form-inline pull-right">
			<select class="form-control">
				<option value="">선택</option>
			</select>
			<input type="text" id="search" class="form-control" name="search" value="${param.search}">
			<button id="btnSearch" class="btn">검색</button>
		</div>
		
		<%-- 총 회원 수 --%>
		<span class="pull left">( 총 회원 수 : ${paging.totalCount }명 )</span>
		<div class="clearfix"></div>
		<br>
		
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
			<c:forEach items="${userList}" var="jejuUser">
				<tr>
					<td>${jejuUser.userNo}</td>
					<td><a href="/admin/user/info?userNo=${jejuUser.userNo}">${jejuUser.userId}</a></td>
					<td>${jejuUser.userName}</td>
					<td>${jejuUser.userNick}</td>
					<td>${jejuUser.userEmail}</td>
					<td><fmt:formatDate value="${jejuUser.userJoinDate}" pattern="yyyy-MM-dd"/></td>
					<td>${jejuUser.userMailAuth}</td>
					<td>${jejuUser.userGrade}</td>
				</tr>
			</c:forEach>	
			</tbody>
		</table>
			
	</div><!-- End pageContent -->
	
	<%-- 관리자계정 생성 버튼 --%>
	<c:if test="${grade eq '0'}">
		<div class="pull-right">
			<button class="btn" id="btnCreate">관리자계정 생성</button>
		</div>
	</c:if>
	
	<%-- 페이징 JSP --%>
	<jsp:include page="/WEB-INF/views/util/adminUserPaging.jsp" />

</div><!-- End container -->

<c:import url="/WEB-INF/views/layout/adminFooter.jsp" />