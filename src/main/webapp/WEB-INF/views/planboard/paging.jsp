<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty param.search }">
	<c:set var="searchParam" value="&search=${param.search }" />
</c:if>

<!-- 페이지네이션 -->
<div class="text-center">
	<ul class="pagination pagination-sm">
		<!-- 첫페이지로 이동 -->
		<c:if test="${paging.curPage != 1 and paging.curPage != 0}">
		<li><a href="/board/list">&larr;</a></li>
		</c:if>
		
		<!-- 이전 페이징 리스트로 가기 -->
		<c:choose>
		<c:when test="${paging.startPage > paging.pageCount }">
		<li><a href="/board/list?curPage=${paging.startPage - paging.pageCount}${searchParam }">&laquo;</a></li>
		</c:when>
		<c:otherwise>
		<li class="disabled"><a>&laquo;</a></li>
		</c:otherwise>
		</c:choose>
		
		<!-- 이전 페이지로 가기 -->
<%-- 		<c:if test="${paging.curPage != 1 }">
		<li><a href="/board/list?curPage=${paging.curPage - 1 }">&lt;</a></li>
		</c:if> --%>
		
		<c:forEach var="i" begin="${paging.startPage }" end="${paging.endPage }" step="1">
			<c:choose>
			<c:when test="${i == paging.curPage}">
		<li class="active"><a>${i }</a></li>
			</c:when>
			<c:otherwise>
		<li><a href="/board/list?curPage=${i }${searchParam }">${i }</a></li>
			</c:otherwise>
			</c:choose>
		</c:forEach>
		
		<!-- 다음페이지로 가기 -->
<%-- 		<c:if test="${paging.curPage != paging.totalPage }">
		<li><a href="/board/list?curPage=${paging.curPage + 1}">&gt;</a></li>
		</c:if> --%>
		
		<!-- 다음페이지 리스트로 가기 -->
		<c:choose>
		<c:when test="${(paging.startPage + paging.pageCount) < paging.totalPage }">
		<li><a href="/board/list?curPage=${paging.startPage + paging.pageCount}${searchParam }">&raquo;</a></li>
		</c:when>
		<c:otherwise>
		<li class="disabled"><a>&raquo;</a></li>
		</c:otherwise>
		</c:choose>
		
		<!-- 마지막페이지로 이동 -->
		<c:if test="${paging.curPage != paging.totalPage }">
		<li><a href="/board/list?curPage=${paging.totalPage}${searchParam }">&rarr;</a></li>
		</c:if>
	</ul>
</div>