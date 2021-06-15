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
		location.href="/admin/user/list?condition="+$("#condition").val()+"&search="+$("#search").val();
	});
	
	//관리자계정생성 버튼 클릭
	$("#btnCreate").click(function() {
		location.href="/admin/user/create";
	});
})
</script>

<div class="container">
	<div class="pageHeader">
		<span class="pull-left"><span id="page">회원관리</span> > 전체회원</span><br>
		<hr>
	</div><!-- End pageHeader -->
	
	<div class="pageContent">
	
		<%-- 사용자 검색 --%>
		<form action="/admin/user/list" method="get">
			<div class="form-inline pull-right">
			<select class="form-control" id="condition" name="condition">
				<option value="null" <c:if test="${condition eq ''}">selected</c:if>>선택</option>
				<option value="userId" <c:if test="${condition eq 'userId'}">selected</c:if>>아이디</option>
				<option value="userName" <c:if test="${condition eq 'userName'}">selected</c:if>>이름</option>
				<option value="userNick" <c:if test="${condition eq 'userNick'}">selected</c:if>>닉네임</option>
				<option value="userEmail" <c:if test="${condition eq 'userEmail'}">selected</c:if>>이메일</option>
			</select>
			<input type="text" id="search" name="search" class="form-control" value="${param.search}">
			<button type="button" id="btnSearch" class="btn">검색</button>
			</div><!-- End search -->
		</form>	
		<br><br>
		
		<%-- 목록 테이블 --%>
		<table id="userList" class="table table-hover" >
			<thead>
				<tr><p class="pull left">( 총 사용자 수 : ${paging.totalCount}명 )</p></tr>
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
	<%-- 최고관리자(userGrade == 0)계정으로 로그인했을 때만 버튼 표시 --%>
	<c:if test="${grade eq '0'}">
		<div class="pull-right">
			<button type="button" class="btn" id="btnCreate">관리자계정 생성</button>
		</div>
	</c:if>
	
<%-- 페이징 JSP --%>
<jsp:include page="/WEB-INF/views/util/adminUserPaging.jsp" />

</div><!-- End container -->

<c:import url="/WEB-INF/views/layout/adminFooter.jsp" />