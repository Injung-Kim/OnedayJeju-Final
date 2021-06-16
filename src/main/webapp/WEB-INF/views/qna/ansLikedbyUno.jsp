<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table class="table table-condensed " style="table-layout: fixed;">
	<thead>
	<tr>
		<th style="width : 8%;">번호</th>
		<th style="width : 74%;">내용</th>
		<th style="width : 10%;">작성시간</th>
		<th style="width : 8%;">좋아요수</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${result}" var="list">
	<tr>
		<td style="text-align: center;">${list.ansNo}</td>
		<td><a href="/qna/view?qstNo=${list.qstNo}">${list.ansContent }</a><div>${list.qstTitle}</div></td>
		<td><fmt:formatDate value="${list.ansTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
		<td>${list.ansLikeCnt}</td>
	</tr>
	</c:forEach>
	</tbody>
</table>