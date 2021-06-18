<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style type="text/css">
	.mypageQst{
		text-align : center;
	}
	.tableList{
		width : 900px;
		margin-top : 20px;
	}
	.qstTitle{
		width : 400px;
		overflow: hidden;
		white-space: nowrap;
		text-overflow: ellipsis;
		text-align: left;
	}
	.qstContent{
		width : 400px;
		overflow: hidden;
		white-space: nowrap;
		text-overflow: ellipsis;
		text-align: left;
		line-height : 40px;
		color : #ccc;
	}
</style>
<div class="mypageQst">
	<h3>작성한 질문글 목록</h3>
	<div class="tableList">
	<table class="table table-condensed table-striped" style="table-layout: fixed;">
		<thead>
		<tr>
			<th style="width : 10%;">번호</th>
			<th style="width : 50%;">제목</th>
			<th style="width : 15%;">작성시간</th>
			<th style="width : 15%;">태그명</th>
			<th style="width : 10%;">조회수</th>
		</tr>
		</thead>
		<tbody>
		<c:if test="${fn:length(result) == 0}">
		<tr>
		<td colspan="5">
			아직 작성한 질문글이 없습니다.
		</td>
		</tr>
		</c:if>
		<c:forEach items="${result}" var="list">
		<tr>
			<td style="text-align: center;">${list.qstNo}</td>
			<td><div class="qstTitle"><a href="/qna/view?qstNo=${list.qstNo}">${list.title }</a></div><div class="qstContent">${list.qstContent}</div></td>
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
	</div>
</div>
