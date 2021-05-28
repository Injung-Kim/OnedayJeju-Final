<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${findPw eq 'Success'}">
	<h4 id="pw_value">회원가입 시 인증한 이메일로 임시 비밀번호가 전송되었습니다<br></h4>
	<h2>로그인 후 <label id="resultpw" style="color:red;">비밀번호 변경</label>을 꼭 해주세요!</h2>
</c:if>

<c:if test="${findPw eq 'Fail'}">
	<h2 id="pw_value">존재하지 않는 회원 정보입니다.</h2>
	
</c:if>

