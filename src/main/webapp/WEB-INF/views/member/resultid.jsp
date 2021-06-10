<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty findId }">
	<h2 id="id_value">회원님의 아이디는 <br>
	<label id="resultid" style="color:#00BD9D;">${findId}</label> 입니다.</h2>
</c:if>

<c:if test="${empty findId }">
	<h2 id="id_value">존재하지 않는 회원 정보입니다.</h2>
</c:if>

