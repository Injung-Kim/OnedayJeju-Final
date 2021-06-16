<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/views/layout/adminHeader.jsp" />

<link rel="stylesheet" type="text/css" href="/resources/css/adminUser.css" />

<script type="text/javascript">
</script>


<div class="container">
	<div class="pageHeader">
		<span class="pull-left">회원관리 > <span id="page">관리자 계정 생성</span></span><br>
	</div><!-- End pageHeader -->
	
	<div class="pageContent">
		<%-- 정보 입력 폼 --%>
		<form id="createForm" role="form" action="/admin/user/create" method="post">
							
			<%-- 정보 입력 테이블 --%>
			<table id="tbCreate" class="table">
				<tr>
					<th>관리자 아이디</th>
					<td><input type="text" class="form-control" id="userId" name="userId" placeholder="관리자 아이디" required></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" class="form-control" id="userPw" name="userPw" placeholder="비밀번호" required></td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" class="form-control" id="checkPw" name="checkPw" placeholder="비밀번호 확인" required></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" class="form-control" id="userName" name="userName" placeholder="이름" required></td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td><input type="text" class="form-control" id="userNick" name="userNick" placeholder="닉네임" required></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="email" class="form-control" id="userEmail" name="userEmail" placeholder="이메일" required></td>
				</tr>
			</table>
		
			<div id="btnGroupCreate" align="center">
				<button type="submit" class="btn" id="btnInsert">등록</button>
				<button type="button" class="btn" id="btnCancleCreate" onclick="location.href='/'">취소</button>
			</div><!-- End btn -->
		</form><!-- End createForm -->
	</div><!-- End pageContent -->
	
</div><!-- End container -->

<c:import url="/WEB-INF/views/layout/adminFooter.jsp" />