<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table class="table table-condensed " style="table-layout: fixed;">
	<thead>
	<tr>
		<th style="width : 8%;">번호</th>
		<th style="width : 40%;">제목</th>
		<th style="width : 13%;">작성시간</th>
		<th style="width : 20%;">태그명</th>
		<th style="width : 13%;">조회수</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${result}" var="list">
	<tr>
		<td style="text-align: center;">${list.qstNo}</td>
		<td><a href="/qna/view?qstNo=${list.qstNo}">${list.title }</a><div>${list.qstContent}</div></td>
		<td><fmt:formatDate value="${list.qstTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
		<td>
		<c:forEach items="${list.qtags}" var="tagName">
		#${tagName.TAG_NAME}
		</c:forEach>
		</td>
		<td>${list.qstCnt}</td>
	</tr>
	</c:forEach>
	</tbody>
</table>
