<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty param.search}">
	<c:set var="searchParam" value="&search=${param.search}" />
</c:if>

<div class="paging text-center">
	<ul class="pagination">

		<%-- 처음 페이지 버튼, 첫 페이지가 아닐 때 버튼 표시 --%>	
		<c:if test="${paging.curPage ne 1}">
			<li><a href="/admin/user/list?curPage=1${searchParam}"><span>&laquo;</span></a></li>
		</c:if>
		
		<%-- 이전 페이지 버튼, 첫 페이지이면 비활성화 표시 --%>
		<c:if test="${paging.curPage ne 1}">
			<li><a href="/admin/user/list?curPage=${paging.curPage-1}${searchParam}"><span>이전</span></a></li>
		</c:if>
		<c:if test="${paging.curPage eq 1}">
			<li class="disabled"><span>이전</span></li>
		</c:if>
		
		<%-- 페이징 번호 표시, 현재 페이지 번호는 활성화 표시 --%>
		<c:forEach begin="${paging.startPage}" end="${paiging.endPage}" var="page">
			<c:if test="${paging.curPage eq page }">
				<li class="active"><a href="/admin/user/list?curPage=${page}${searchParam}">${page}</a></li>
			</c:if>
			<c:if test="${paging.curPage ne page}">
				<li><a href="/admin/user/list?curPage=${page}${searchParam}">${page}</a></li>
			</c:if>
		</c:forEach>
		
		<%-- 다음 페이지 버튼, 마지막 페이지이면 동작 안 함 --%>
		<c:if test="${paging.curPage ne paging.totalPage}">
			<li><a href="/admin/user/list?curPage=${paging.curPage+1}${searchParam}"><span>다음</span></a></li>
		</c:if>
		<c:if test="${paging.curPage eq paging.totalPage}">
			<li class="disabled"><span>다음</span></li>
		</c:if>
		
		<%-- 마지막 페이지 버튼, 마지막 페이지가 아닐 때 버튼 표시 --%>
		<c:if test="${paging.curPage ne paging.totalPage}">
			<li><a href="/admin/user/list?curPage=${paging.totalPage}${searchParam}"><span>&raquo;</span></a></li>
		</c:if>
	</ul>
</div>