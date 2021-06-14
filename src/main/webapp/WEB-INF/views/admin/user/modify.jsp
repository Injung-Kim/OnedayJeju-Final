<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/views/layout/adminHeader.jsp" />

<link rel="stylesheet" type="text/css" href="/resources/css/adminUser.css" />

<script type="text/javascript">
$(document).ready(function() {
	//등록 버튼 클릭
	$("#btnUpdate").click(function() {
		$("form").submit();
	})
	
	//취소 버튼 클릭
	$("#btnCancle").click(function() {
		location.href='info?userNo='+${user.userNo};
	})
})
</script>


<div class="container">
	<div class="pageHeader">
		<span class="pull-left">회원관리 > 회원정보 > <span id="page">정보수정</span></span>
		<hr>
	</div><!-- End pageHeader -->
	
	<div class="pageContent">
		<form id="modifyInfo" role="form" action="/admin/user/modify" method="post">
		<input type="hidden" name="userNo" value="${user.userNo}">
			<table id="tbInfo" class="table table-bordered table-hover">
				<tr>
					<th>아이디</th>
					<td><input type="text" class="form-control" id="userId" name="userId" value="${user.userId}"> </td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" class="form-control" id="userPw" name="userPw" placeholder="비밀번호 수정만 가능"></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" class="form-control" id="userName" name="userName" value="${user.userName}"></td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td><input type="text" class="form-control" id="userNick" name="userNick" value="${user.userNick}"></td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td><input type="date" class="form-control" id="userBirhDate" name="userBirthDate" value="<fmt:formatDate value="${user.userBirthDate}" pattern="yyyy-MM-dd"/>"></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="email" class="form-control" id="userEmail" name="userEmail" value="${user.userEmail}"></td>
				</tr>
				<tr>
					<th>휴대전화번호</th>
					<td><input type="tel" class="form-control" id="userPhone" name="userPhone" value="${user.userPhone}"></td>
				</tr>
				<tr>
					<th>주소</th>
					<td>
						<input type="text" class="form-control" id="userAddress" name="userAddress" value="${user.userAddress}">
						<input type="text" class="form-control" id="userAddressDetail" name="userAddressDetail" value="${user.userAddressDetail}">
					</td>
				</tr>
			</table>
		
			<div align="center">
				<button type="submit" class="btn" id="btnUpdate">등록</button>
				<button type="button" class="btn" id="btnCancle">취소</button>	
			</div><!-- End btn -->
		</form><!-- End modifyInfo -->
	</div><!-- End pageContent -->
</div><!-- End container -->

<c:import url="/WEB-INF/views/layout/adminFooter.jsp" />