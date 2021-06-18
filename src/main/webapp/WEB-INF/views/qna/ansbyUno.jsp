<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style type="text/css">
	.mypageAns{
		text-align: center;
	}
	.tableList{
		width : 900px;
		margin-top : 20px;
	}
	
	.ansContent{
		width : 500px;
		overflow: hidden;
		white-space: nowrap;
		text-overflow: ellipsis;
		text-align: left;
	}
	.ansTitle{
		width : 500px;
		overflow: hidden;
		white-space: nowrap;
		text-overflow: ellipsis;
		text-align: left;
		line-height : 40px;
		color : #ccc;
	}
</style>
<div class="mypageAns">
<h3> 나의 답변 목록</h3>
	<div class="tableList">
	<table class="table table-condensed table-striped" style="table-layout: fixed;">
		<thead>
		<tr>
			<th style="width : 10%;">번호</th>
			<th style="width : 65%;">내용</th>
			<th style="width : 15%;">작성시간</th>
			<th style="width : 10%;">좋아요수</th>
		</tr>
		</thead>
		<tbody>
		<c:if test="${fn:length(result) == 0}">
		<tr>
			<td colspan="4">
			아직 작성한 답변글이 없습니다
			</td>
		</tr>
		</c:if>
		<c:forEach items="${result}" var="list" >
		<tr>
			<td style="text-align: center;">${list.ansNo}</td>
			<td>
			<div class="ansContent">
			<a href="/qna/view?qstNo=${list.qstNo}">${list.ansContent }</a>
			</div>
			<div class="ansTitle">[본문]${list.qstTitle}</div>
			</td>
			<td><fmt:formatDate value="${list.ansTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
			<td>${list.ansLikeCnt}</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
</div>