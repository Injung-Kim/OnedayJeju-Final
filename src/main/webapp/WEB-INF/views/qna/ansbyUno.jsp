<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style type="text/css">
	.title{
		width : 450px;
		text-overflow: ellipsis;
		overflow: hidden;
		white-space: nowrap;
		line-height : 33px;
		color : #ccc;
	}
	.ansContent{
		width : 553px;
		text-overflow: ellipsis;
		overflow: hidden;
		white-space: nowrap;
	}
</style>
<table class="table table-condensed " style="table-layout: fixed;">
	<thead>
	<tr>
		<th style="width : 8%;">번호</th>
		<th style="width : 69%;">내용</th>
		<th style="width : 15%;">작성시간</th>
		<th style="width : 8%;">좋아요수</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${result}" var="list">
	<tr>
		<td style="text-align: center;">${list.ansNo}</td>
		<td><div class="ansContent"><a href="/qna/view?qstNo=${list.qstNo}">${list.ansContent }</a></div><div class="title">${list.qstTitle}</div></td>
		<td><fmt:formatDate value="${list.ansTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
		<td>${list.ansLikeCnt}</td>
	</tr>
	</c:forEach>
	</tbody>
</table>