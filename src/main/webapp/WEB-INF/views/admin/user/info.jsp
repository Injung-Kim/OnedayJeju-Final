<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/views/layout/adminHeader.jsp" />

<link rel="stylesheet" type="text/css" href="/resources/css/adminUser.css" />

<script type="text/javascript">
function del(userNo) {
	var result = confirm("계정을 삭제하시겠습니까? 삭제된 계정은 복원할 수 없습니다.");
	if(result) {
		location.href='delete?userNo='+${info.userNo};
	}
}

/* $(document).ready(function() {
	
	//삭제 버튼 클릭
	$("#btnDelete").click(function() {
		var result = confirm('계정을 삭제하시겠습니까? 삭제된 계정은 복원할 수 없습니다.');
		if(result){
			location.href='/admin/user/delete?userNo='+${userNo}; //확인
		} else { } //취소
	});
}
 */
</script>

<div class="container">
	<div class="pageHeader">
		<p class="pull-left">회원관리 > <span id="page">회원정보</span></p>
	</div><!-- End pageHeader -->
	
	<div class="pageContent">
		<table id="tbInfo" class="table table-bordered table-hover">
			<tr>
				<th>아이디</th>
				<td>${info.userId}</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" id="userPw" name="userPw" placeholder="비밀번호는 변경만 가능" readonly><small>&ensp;8~15자리의 영문 소문자, 숫자 조합</small></td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${info.userName}</td>
			</tr>
			<tr>
				<th>닉네임</th>
				<td>${info.userNick}</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td><fmt:formatDate value="${info.userBirthDate}" pattern="yyyy-MM-dd"/></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${info.userEmail}</td>
			</tr>
			<tr>
				<th>휴대전화번호</th>
				<td>${info.userPhone}</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>${info.userAddress}${info.userAddressDetail}</td>
			</tr>
		</table>
	</div><!-- End pageContent -->
	
	<div id="btn" class="text-center">
		<button type="button" id="btnBack" onclick="location.href='/admin/user/list'">이전</button>
		<c:if test="${uno ne info.userNo}">
			<button type="button" id="btnModify" onclick="location.href='/admin/user/modify?userNo=${info.userNo}'">수정</button>
			<button type="button" id="btnDelete" onclick="del(${info.userNo})">삭제</button>
		</c:if>
	</div><!-- End btn -->

</div><!-- End container -->

<c:import url="/WEB-INF/views/layout/adminFooter.jsp" />