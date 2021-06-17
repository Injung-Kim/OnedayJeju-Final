<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty param.search }">
	<c:set var="searchParam" value="&search=${param.search }" />
</c:if>
<c:if test="${not empty param.condition }">
	<c:set var="conditionParam" value="&condition=${param.condition }" />
</c:if>

<div class="ntpage">
	<ul>

		<%-- 처음 페이지 버튼 --%>
		<%-- 첫 페이지가 아닐 때 버튼 노출 --%>
		<c:if test="${paging.curPage ne 1 }">
			<li><a href="/notice/list?curPage=1${searchParam }${conditionParam }"><span>&larr;</span></a></li>
		</c:if>





		<%-- 이전 페이지 버튼 --%>
		<%-- 첫 페이지면 금지 표시 --%>
		<c:if test="${paging.curPage ne 1 }">
			<li><a href="/notice/list?curPage=${paging.curPage-1 }${searchParam }${conditionParam }"><span>&laquo;</span></a></li>
		</c:if>
		<c:if test="${paging.curPage eq 1 }">
			<li class="disabled"><span>&laquo;</span></li>
		</c:if>





		<%-- 페이징 번호 표시 --%>
		<%-- 현재 페이지 번호는 active 클래스 부여 -> 파랑 바탕 버튼 --%>
		<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="page">
			<c:if test="${paging.curPage eq page }">
				<li class="active"><a href="/notice/list?curPage=${page }${searchParam }${conditionParam }">${page }</a></li>
			</c:if>
			<c:if test="${paging.curPage ne page }">
				<li><a href="/notice/list?curPage=${page }${searchParam }${conditionParam }">${page }</a></li>
			</c:if>
		</c:forEach>





		<%-- 다음 페이지 버튼 --%>
		<%-- 마지막 페이지면 동작 안함 --%>
		<c:if test="${paging.curPage ne paging.totalPage }">
			<li><a href="/notice/list?curPage=${paging.curPage+1 }${searchParam }${conditionParam }"><span>&raquo;</span></a></li>
		</c:if>
		<c:if test="${paging.curPage eq paging.totalPage }">
			<li class="disabled"><span>&raquo;</span></li>
		</c:if>
		
		
		
		
		<%-- 마지막 페이지 버튼 --%>
		<%-- 마지막 페이지가 아닐 때 버튼 노출 --%>
		<c:if test="${paging.curPage ne paging.totalPage }">
			<li><a href="/notice/list?curPage=${paging.totalPage }${searchParam }${conditionParam }"><span>&rarr;</span></a></li>
		</c:if>
	</ul>
</div>
